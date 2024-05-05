package de.bombshooter.bombshooter.generics;

import processing.core.PGraphics;

public abstract class DrawableElement {

    private boolean visible;

    public DrawableElement() {
        this.visible = true;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    protected abstract void onDraw(PGraphics gfx);
}
