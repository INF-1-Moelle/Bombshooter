package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.generics.DrawableElement;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class LevelObject extends DrawableElement {

    private final PVector position;
    private final PVector size;

    public LevelObject(PVector position, PVector size) {
        super();
        this.position = position;
        this.size = size;
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
}
