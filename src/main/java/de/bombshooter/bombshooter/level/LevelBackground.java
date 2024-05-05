package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import processing.core.PGraphics;
import processing.core.PVector;

public class LevelBackground extends LevelObject {

    public LevelBackground(PVector size) {
        super(new PVector(0, 0), size);
    }


    /**
     * Draw the element
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.level.Level}
     *
     * @param gfx The graphics object to draw on
     */
    @Override
    protected void onDraw(PGraphics gfx) {

        TileablePImage bg = GameWindow.getInstance().getMediaManager().loadImageById("level.background");
        gfx.image(bg.getImage(), getPosition().x, getPosition().y);

    }
}