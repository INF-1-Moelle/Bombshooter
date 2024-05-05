package de.bombshooter.bombshooter;

import de.bombshooter.bombshooter.level.Level;
import de.bombshooter.bombshooter.ui.UIHandler;
import processing.core.PApplet;

public class GameWindow extends PApplet {

    private static GameWindow INSTANCE = null;
    private final MediaManager mediaMgr;
    private final UIHandler uiHandler;
    private final Level level;

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
        size(displayWidth, displayHeight, P2D);
    }

    @Override
    public void setup() {
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
    }

    @Override
    public void windowResize(int newWidth, int newHeight) {
        uiHandler.onResize(newWidth, newHeight);
    }

    public MediaManager getMediaManager(){return mediaMgr;}

    public UIHandler getUIHandler() {
        return uiHandler;
    }

    public Level getLevel() {
        return level;
    }
}
