package de.bombshooter.bombshooter;

import processing.awt.PImageAWT;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Properties;

public class MediaManager {

    private String mediaLocation;
    private Properties prop;

    public MediaManager() {
        prop = new Properties();
    }

    protected void init(String mediaDir, String version) throws IOException {
        this.prop.load(getClass().getResourceAsStream("/media.properties"));
        mediaLocation = prop.getProperty("game.mediadirformat").replaceAll("\\$mediadir", mediaDir).replaceAll("\\$version", version);
    }

    public PImage loadImageById(String id) {
        PImage loadedImg = GameWindow.getInstance().loadImage(mediaLocation + prop.getProperty(id));

        if (loadedImg == null) {
            try {
                loadedImg = new PImageAWT(ImageIO.read(getClass().getResourceAsStream("/missingtexture.png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return loadedImg;
    }

}
