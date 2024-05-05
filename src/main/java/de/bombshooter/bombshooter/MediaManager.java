package de.bombshooter.bombshooter;

import processing.awt.PImageAWT;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class MediaManager {

    private String mediaLocation;
    private Properties prop;

    private ConcurrentHashMap<String, MediaObject> images = new ConcurrentHashMap<>();

    public MediaManager() {
        prop = new Properties();
    }

    protected void init(String mediaDir, String version) throws IOException {
        this.prop.load(getClass().getResourceAsStream("/media.properties"));
        mediaLocation = prop.getProperty("game.mediadirformat").replaceAll("\\$mediadir", mediaDir).replaceAll("\\$version", version);
        startGarbageCollection();
    }

    protected void startGarbageCollection() {
        Executors.newVirtualThreadPerTaskExecutor().execute(new GarbageCollectionThread());
    }

    public PImage loadImageById(String id) {
        return images.compute(id, (k, v) -> {

            //Load the image when not cached
            if (v == null) {
                PImage loadedImg = GameWindow.getInstance().requestImage(mediaLocation + prop.getProperty(id));
                if (loadedImg == null) {
                    try {
                        loadedImg = new PImageAWT(ImageIO.read(getClass().getResourceAsStream("/missingtexture.png")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                return new MediaObject(30L, loadedImg);
            }

            //return the cached image and renew its cache lifespan
            v.setLifespan(30L);
            return v;

        }).object;
    }

    class MediaObject {
        long lifespan;
        PImage object;

        public MediaObject(long lifespan, PImage object) {
            this.lifespan = lifespan;
            this.object = object;
        }

        public long getLifespan() {
            return lifespan;
        }

        public void setLifespan(long lifespan) {
            this.lifespan = lifespan;
        }

        public PImage getObject() {
            return object;
        }

        public void setObject(PImage object) {
            this.object = object;
        }
    }

    class GarbageCollectionThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                images.forEach((k, v) -> {
                    if (v.getLifespan() <= 0) {
                        images.remove(k);
                    } else {
                        v.setLifespan(v.getLifespan() - 1);
                    }
                });
            }
        }
    }

}
