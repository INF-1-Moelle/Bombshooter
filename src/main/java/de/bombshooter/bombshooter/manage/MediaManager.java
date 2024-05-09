package de.bombshooter.bombshooter.manage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.awt.PImageAWT;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MediaManager {

    private String mediaLocation;
    private final Map<String, TileablePImage> textures;
    private final ConcurrentHashMap<String, MediaObject> imageCache = new ConcurrentHashMap<>();
    private GarbageCollectionThread gcThread;
    private Future<?> gcTask;
    private final Logger logger;


    public MediaManager() {
        textures = new HashMap<>();
        logger = LoggerFactory.getLogger(getClass());
    }

    public void init(String mediaDir, String version) throws IOException {

        try (InputStream mediaJsonStream = Objects.requireNonNull(getClass().getResourceAsStream("/media.json"))) {
            JsonObject mediaJson = JsonParser.parseReader(new InputStreamReader(mediaJsonStream)).getAsJsonObject();

            mediaLocation = mediaJson.get("game.mediadirformat").getAsString()
                    .replaceAll("\\$mediadir", mediaDir)
                    .replaceAll("\\$version", version);

            mediaJson.get("textures").getAsJsonObject().asMap()
                    .forEach((key, value) -> textures.put(key, TileablePImage.fromJson(value.getAsJsonObject())));
        }

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
                TileablePImage tileablePImage = textures.get(id);

                PImage loadedImg = GameWindow.getInstance().requestImage(mediaLocation + tileablePImage.getFileName());

                if (!new File(mediaLocation + tileablePImage.getFileName()).exists() || loadedImg == null) {
                    try {
                        loadedImg = new PImageAWT(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/missingtexture.png"))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                tileablePImage.setImage(loadedImg);

                return new MediaObject(System.currentTimeMillis() + 30 * 1000, tileablePImage);
            }

            //return the cached image and renew its cache lifespan
            v.setDeathTime(System.currentTimeMillis() + 30 * 1000);
            return v;
        }).tileablePImage;
    }

    static class MediaObject {
        private long deathTime;
        private final TileablePImage tileablePImage;

        public MediaObject(long deathTime, TileablePImage image) {
            this.deathTime = deathTime;
            this.tileablePImage = image;
        }

        public void setDeathTime(long deathTime) {
            this.deathTime = deathTime;
        }

        public long getDeathTime() {
            return deathTime;
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

                    if (time >= v.getDeathTime()) {
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
