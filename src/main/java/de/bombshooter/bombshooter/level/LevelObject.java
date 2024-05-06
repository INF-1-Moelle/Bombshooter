package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.DrawableElement;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class LevelObject extends DrawableElement {

    private PVector position;
    private PVector size;

    public LevelObject(PVector position, PVector size) {
        super();
        this.position = position;
        this.size = size;
    }

    /**
     * Draw the element
     * Should be called in the draw method of {@link Level}
     * @param gfx the graphics object to draw on
     */
    public void draw(PGraphics gfx) {
        onDraw(gfx);
    }

    /**
     * Retrieve the position of the object
     *
     * @return the position as a {@link PVector}
     */
    public PVector getPosition() {
        return position;
    }

    /**
     * Retrieve the size of the object
     *
     * @return the size as a {@link PVector}
     */
    public PVector getSize() {
        return size;
    }
}
