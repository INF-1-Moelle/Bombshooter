package de.bombshooter.bombshooter.generics;

import de.bombshooter.bombshooter.graphics.BGraphics;

public abstract class DrawableElement {

    private boolean visible;

    public DrawableElement() {
        this(true);
    }

    public DrawableElement(boolean visible) {
        this.visible = visible;
    }

    /**
     * Set the visibility of the element
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return if the element should be drawn
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Draw the element
     *
     * @param gfx
     */
    protected abstract void onDraw(BGraphics gfx);

    @Override
    public String toString() {
        return "DrawableElement{" +
                "visible=" + visible +
                '}';
    }
}
