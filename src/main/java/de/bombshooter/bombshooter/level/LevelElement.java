package de.bombshooter.bombshooter.level;

import processing.core.PGraphics;
import processing.core.PVector;

public abstract class LevelElement {

    private boolean visible;
    private PVector position;
    private PVector size;

    public LevelElement() {
        visible = true;
    }

    public void draw(PGraphics gfx) {
        onDraw(gfx);
    }

    public PVector getPosition() {
        return position;
    }

    public PVector getSize() {
        return size;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    protected abstract void onDraw(PGraphics gfx);
}
