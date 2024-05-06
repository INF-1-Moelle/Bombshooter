package de.bombshooter.bombshooter;

import com.google.gson.*;
import de.bombshooter.bombshooter.generics.TileablePImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.awt.PImageAWT;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class MediaManager {

    private String mediaLocation;
    private final Map<String, JsonObject> textures;
    private final ConcurrentHashMap<String, MediaObject> imageCache = new ConcurrentHashMap<>();
    private GarbageCollectionThread gcThread;
    private Future<?> gcTask;
    private final Logger logger;
    private final Gson GSON;


    public MediaManager() {
        textures = new HashMap<>();
        logger = LoggerFactory.getLogger(getClass());
        GSON = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(TileablePImage.class, new TileablePImageDeserializer())
                .create();
    }

    protected void init(String mediaDir, String version) throws IOException {

        JsonObject mediaJson = JsonParser.parseReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/media.json")))).getAsJsonObject();
        mediaLocation = mediaJson.get("game.mediadirformat").getAsString().replaceAll("\\$mediadir", mediaDir).replaceAll("\\$version", version);

        mediaJson.get("textures").getAsJsonObject().asMap().forEach((key, value) -> textures.put(key, value.getAsJsonObject()));

        startGarbageCollection();
    }

    protected void startGarbageCollection() {
        this.gcThread = new GarbageCollectionThread();
        this.gcTask = Executors.newVirtualThreadPerTaskExecutor().submit(this.gcThread);
    }

    protected void stopGarbageCollection() {
        try {
            gcThread.interrupt();
            gcTask.get();
        } catch (ExecutionException | InterruptedException e) {
            logger.warn("Failed to stop garbage collection thread", e);
        }
    }

    public TileablePImage loadImageById(String id) {
        return imageCache.compute(id, (k, v) -> {

            if (v == null) {
                //Load the image when not cached
                TileablePImage tileablePImage = GSON.fromJson(textures.get(id), TileablePImage.class);

                PImage loadedImg = GameWindow.getInstance().requestImage(mediaLocation + tileablePImage.getFileName());
                if (loadedImg == null) {
                    try {
                        loadedImg = new PImageAWT(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/missingtexture.png"))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                tileablePImage.setImage(loadedImg);

                return new MediaObject(System.currentTimeMillis() + 30*1000, tileablePImage);
            }

            //return the cached image and renew its cache lifespan
            v.setDeathtime(System.currentTimeMillis() + 30*1000);
            return v;
        }).tileablePImage;
    }

    static class TileablePImageDeserializer implements JsonDeserializer<TileablePImage> {
        public TileablePImage deserialize(JsonElement e, Type t, JsonDeserializationContext context) {
            JsonObject o = e.getAsJsonObject();

            var raw = new TileablePImage(null, o.get("file").getAsString(), o.get("width").getAsInt(), o.get("height").getAsInt(), o.get("tiled").getAsBoolean());
            if (raw.isTiled()) {
                raw.setTileSize(o.get("tilewidth").getAsInt(), o.get("tileheight").getAsInt());
            }
            return raw;
        }
    }

    static class MediaObject {
        private long deathtime;
        private final TileablePImage tileablePImage;

        public MediaObject(long deathtime, TileablePImage image) {
            this.deathtime = deathtime;
            this.tileablePImage = image;
        }

        public void setDeathtime(long deathtime){
            this.deathtime = deathtime;
        }

        public long getDeathtime() {
            return deathtime;
        }
    }

    class GarbageCollectionThread implements Runnable {

        private boolean interrupted = false;

        @Override
        public void run() {
            while (!interrupted) {
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) {
                    LoggerFactory.getLogger(getClass()).warn("Garbage collection thread interrupted", e);
                }

                imageCache.forEach((k, v) -> {
                    long time = new Date().getTime();

                    if (time >= v.getDeathtime()){
                        imageCache.remove(k);
                    }
                });
            }
        }

        public void interrupt() {
            interrupted = true;
        }
    }

}
