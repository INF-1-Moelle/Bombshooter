package de.bombshooter.bombshooter.level.entity;

import de.bombshooter.bombshooter.level.LevelObject;
import processing.core.PGraphics;
import processing.core.PVector;

public class Arrow extends LevelObject {

    public Arrow(PVector pos, PVector size){
        super(pos, size);
    }

    /**
     * Draw the element
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.level.Level}
     * @param gfx The graphics object to draw on
     */
    @Override
    protected void onDraw(PGraphics gfx) {

    }
}
