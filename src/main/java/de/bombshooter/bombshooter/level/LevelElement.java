package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.ui.UIElement;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public abstract class LevelElement {

    private boolean visible;
    private PVector position;
    private PVector size;
    private List<LevelElement> childs;

    public LevelElement() {
        childs = new ArrayList<>();
        visible = true;
    }

    public void addChild(LevelElement child) {
        childs.add(child);
    }

    public void removeChild(LevelElement child) {
        childs.remove(child);
    }

    public void draw(PGraphics gfx) {

        // background
        onDraw(gfx);

        // childs
        gfx.pushMatrix();
        gfx.translate(position.x, position.y);

        for (LevelElement element : childs) {
            if (element.isVisible())
                element.draw(gfx);
        }

        gfx.popMatrix();
    }

    public PVector getPosition() {
        return position;
    }

    public PVector getSize() {
        return size;
    }

    public List<LevelElement> getChilds() {
        return childs;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    protected abstract void onDraw(PGraphics gfx);
}
