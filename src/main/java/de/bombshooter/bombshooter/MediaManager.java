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
import java.util.HashMap;
import java.util.Map;
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

        JsonObject mediaJson = JsonParser.parseReader(new InputStreamReader(getClass().getResourceAsStream("/media.json"))).getAsJsonObject();
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

            //Load the image when not cached
            if (v == null) {

                TileablePImage tileablePImage = GSON.fromJson(textures.get(id), TileablePImage.class);

                PImage loadedImg = GameWindow.getInstance().requestImage(mediaLocation + tileablePImage.getFileName());
                if (loadedImg == null) {
                    try {
                        loadedImg = new PImageAWT(ImageIO.read(getClass().getResourceAsStream("/missingtexture.png")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                tileablePImage.setImage(loadedImg);

                return new MediaObject(30L, tileablePImage);
            }

            //return the cached image and renew its cache lifespan
            v.setLifespan(30L);
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
        private long lifespan;
        private final TileablePImage tileablePImage;

        public MediaObject(long lifespan, TileablePImage image) {
            this.lifespan = lifespan;
            this.tileablePImage = image;
        }

        public long getLifespan() {
            return lifespan;
        }

        public void setLifespan(long lifespan) {
            this.lifespan = lifespan;
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
                    if (v.getLifespan() <= 0) {
                        imageCache.remove(k);
                    } else {
                        v.setLifespan(v.getLifespan() - 1);
                    }
                });
            }
        }

        public void interrupt() {
            interrupted = true;
        }
    }

}
