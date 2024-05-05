package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class LevelBackground extends LevelObject {

    public LevelBackground(PVector size) {
        super(new PVector(0, 0), size);
    }

    /**
     * @param gfx
     */
    @Override
    protected void onDraw(PGraphics gfx) {

        PImage bg = GameWindow.getInstance().getMediaManager().loadImageById("level.background");
        gfx.image(bg, getPosition().x, getPosition().y);

    }
}