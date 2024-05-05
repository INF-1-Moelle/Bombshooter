package de.bombshooter.bombshooter;

import processing.core.PGraphics;
import processing.core.PImage;

import java.io.IOException;
import java.util.Properties;

public class MediaManager {

    private String mediaLocation;
    private Properties prop;

    public MediaManager(){
        prop = new Properties();

    }

    protected void init(String mediaDir, String version) throws IOException {
        this.prop.load(getClass().getResourceAsStream("/media.properties"));

        String basepath = prop.getProperty("game.mediadirformat").replaceAll("$mediadir",mediaDir).replaceAll("$version",version);
    }

    public PImage loadImageById(){
        GameWindow.getInstance().loadImage("");
    }

}
