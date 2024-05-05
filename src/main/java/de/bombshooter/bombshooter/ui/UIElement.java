package de.bombshooter.bombshooter.ui;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.DrawableElement;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public abstract class UIElement extends DrawableElement {

    private final PVector position;
    private final PVector size;
    private List<UIElement> childs;
    private final String id;

    public UIElement(PVector position, PVector size, String id) {
        super();
        this.childs = new ArrayList<>();
        this.position = position;
        this.size = size;
        this.id = id;
    }

    /**
     * Add a child to the element
     *
     * @param child the child to add
     */
    public void addChild(UIElement child) {
        childs.add(child);
    }

    /**
     * Remove a child from the element
     *
     * @param child the child to remove
     */
    public void removeChild(UIElement child) {
        childs.remove(child);
    }


    /**
     * Draw the element
     * Should be called in the draw method of {@link UIHandler}
     * @param gfx the graphics object to draw on
     */
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

    /**
     * Retrieve the position of the element
     *
     * @return the position as a {@link PVector}
     */
    public PVector getPosition() {
        return position;
    }

    /**
     * Retrieve the size of the element
     *
     * @return the size as a {@link PVector}
     */
    public PVector getSize() {
        return size.copy().mult(getScale());
    }

    public float getScale() {
        return GameWindow.getInstance().getUIHandler().getScale();
    }

    /**
     * Retrieve the childs of the element
     *
     * @return the childs as a list
     */
    public List<UIElement> getChilds() {
        return childs;
    }

    /**
     * Retrieve the id of the element
     *
     * @return the id
     */
    protected String getId() {
        return id;
    }
}
