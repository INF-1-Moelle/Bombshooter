package de.bombshooter.bombshooter.ui;

import de.bombshooter.bombshooter.generics.DrawableElement;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.List;

public abstract class UIElement extends DrawableElement {

    private final PVector position;
    private final PVector size;
    private List<UIElement> childs;
    private final String id;

    public UIElement(PVector position, PVector size, String id) {
        super();
        this.position = position;
        this.size = size;
        this.id = id;
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

    protected String getId(){
        return id;
    }
}
