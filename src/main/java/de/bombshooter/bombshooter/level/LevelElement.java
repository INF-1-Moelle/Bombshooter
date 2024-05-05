package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.generics.DrawableElement;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class LevelElement extends DrawableElement {

    private PVector position;
    private PVector size;

    public LevelElement() {
        super();
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
