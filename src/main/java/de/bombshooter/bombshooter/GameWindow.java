package de.bombshooter.bombshooter;

import de.bombshooter.bombshooter.level.Level;
import de.bombshooter.bombshooter.ui.UIHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.core.PApplet;
import processing.opengl.PGraphicsOpenGL;

import java.io.IOException;

public class GameWindow extends PApplet {

    private static GameWindow INSTANCE = null;
    private final MediaManager mediaMgr;
    private final UIHandler uiHandler;
    private final Level level;

    private final Logger logger = LoggerFactory.getLogger(GameWindow.class);

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

    /**
     * The Processing settings method
     *
     * @see <a href="https://processing.org/reference">Processing Documentation</a>
     */
    @Override
    public void settings() {

        try {
            mediaMgr.init("bombshooter-media", "v1");
        } catch (IOException e) {
            logger.info("Failed initialize MediaManager", e);
            System.exit(-1);
        }

        size(displayWidth, displayHeight, P2D);
    }

    /**
     * The Processing setup method
     *
     * @see <a href="https://processing.org/reference">Processing Documentation</a>
     */
    @Override
    public void setup() {
        ((PGraphicsOpenGL) g).textureSampling(2);    //https://github.com/benfry/processing4/blob/main/core/src/processing/opengl/Texture.java#L54-L68C44
        frameRate(60);
        level.initDefaultObjects(getGraphics());
        surface.setResizable(true);
        noStroke();
        background(0);
    }

    /**
     * The Processing draw method
     *
     * @see <a href="https://processing.org/reference">Processing Documentation</a>
     */
    @Override
    public void draw() {
        background(255);
        fill(0);
        ellipse(mouseX, mouseY, 20, 20);

        uiHandler.draw(getGraphics());
        level.draw(getGraphics());
    }

    /**
     * Called when the window is resized
     *
     * @param newWidth the new width of the window
     * @param newHeight the new height of the window
     */
    @Override
    public void windowResize(int newWidth, int newHeight) {
        uiHandler.onResize(newWidth, newHeight);
    }

    /**
     * Retrieve the MediaManager instance
     *
     * @return the MediaManager instance
     */
    public MediaManager getMediaManager() {
        return mediaMgr;
    }

    /**
     * Retrieve the UIHandler instance
     *
     * @return the UIHandler instance
     */
    public UIHandler getUIHandler() {
        return uiHandler;
    }

    /**
     * Retrieve the Level instance
     *
     * @return the Level instance
     */
    public Level getLevel() {
        return level;
    }
}
