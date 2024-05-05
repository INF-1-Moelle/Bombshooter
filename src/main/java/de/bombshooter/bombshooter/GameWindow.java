package de.bombshooter.bombshooter;

import de.bombshooter.bombshooter.level.Level;
import de.bombshooter.bombshooter.ui.UIHandler;
import processing.core.PApplet;

import java.io.IOException;
import java.util.logging.Logger;

public class GameWindow extends PApplet {

    private static GameWindow INSTANCE = null;
    private final MediaManager mediaMgr;
    private final UIHandler uiHandler;
    private final Level level;

    private final Logger logger = Logger.getLogger(GameWindow.class.getName());

    public static GameWindow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameWindow();
        }

        return INSTANCE;
    }

    private GameWindow() {
        INSTANCE = this;
        this.mediaMgr = new MediaManager();
        this.uiHandler = new UIHandler();
        this.level = new Level();
    }

    @Override
    public void settings() {

        try {
            mediaMgr.init("bombshooter-media", "v1");
        } catch (IOException e) {
            logger.log(java.util.logging.Level.SEVERE, "Failed initialize MediaManager", e);
            System.exit(-1);
        }

        size(displayWidth, displayHeight, P2D);
    }

    @Override
    public void setup() {

        level.initDefaultObjects(getGraphics());

        surface.setResizable(true);
        noStroke();
        background(0);
        frameRate(60);
    }

    @Override
    public void draw() {
        background(255);
        fill(0);
        ellipse(mouseX, mouseY, 20, 20);
        uiHandler.draw(getGraphics());
        level.draw(getGraphics());
    }

    @Override
    public void windowResize(int newWidth, int newHeight) {
        uiHandler.onResize(newWidth, newHeight);
    }

    public MediaManager getMediaManager() {
        return mediaMgr;
    }

    public UIHandler getUIHandler() {
        return uiHandler;
    }

    public Level getLevel() {
        return level;
    }
}
