package de.bombshooter.bombshooter.ui;

import processing.core.PGraphics;
import processing.core.PVector;

import java.util.List;

public abstract class UIElement {

    private boolean visible;
    private PVector position;
    private PVector size;
    private List<UIElement> childs;

    public UIElement(PVector position, PVector size) {
        this.visible = true;
        this.position = position;
        this.size = size;
    }

    public void addChild(UIElement child) {
        childs.add(child);
    }

    public void removeChild(UIElement child) {
        childs.remove(child);
    }


    public void draw(PGraphics gfx) {

        // background
        onDraw(gfx);

        // childs
        gfx.pushMatrix();
        gfx.translate(position.x, position.y);

        for (UIElement element : childs) {
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

    public List<UIElement> getChilds() {
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
