package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.generics.DrawableElement;
import de.bombshooter.bombshooter.graphics.BGraphics;
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
    public void draw(BGraphics gfx) {
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

    /**
     * Retrieve the maximum radius (half of diagonal) of the object
     *
     * @return max radius
     */
    public float getMaxRadius() {
        return 0.5f * (float) Math.sqrt(size.x * size.x + size.y * size.y);
    }

    @Override
    public String toString() {
        return "LevelObject{" +
                "position=" + position +
                ", size=" + size +
                "} " + super.toString();
    }
}
