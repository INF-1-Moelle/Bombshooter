package de.bombshooter.bombshooter.ui;

import de.bombshooter.bombshooter.graphics.BGraphics;
import de.bombshooter.bombshooter.ui.elements.Crosshair;
import processing.core.PVector;

import java.util.HashMap;

public class UIHandler {

    private Crosshair crosshair;
    private final HashMap<String, UIElement> elements;
    private float scale;

    public UIHandler() {
        elements = new HashMap<>();
        scale = 5;
    }

    public void initDefaultElements(BGraphics gfx) {
        this.crosshair = new Crosshair(new PVector(0, 0), new PVector(50, 50), "crosshair");
        crosshair.setVisible(true);
        addElement(crosshair);
    }

    /**
     * Add an element to the UI
     *
     * @param element the element to add
     */
    public void addElement(UIElement element) {
        elements.put(element.getId(), element);
    }

    /**
     * Remove an element from the UI
     *
     * @param element the element to remove
     */
    public void removeElement(UIElement element) {
        removeElement(element.getId());
    }

    /**
     * Remove an element from the UI
     *
     * @param Id the id of the element to remove
     */
    public void removeElement(String Id) {
        elements.remove(Id);
    }

    /**
     * Retrieve an element from the UI
     *
     * @param Id the id of the element to retrieve
     * @return the element
     */
    public UIElement getElement(String Id) {
        return elements.get(Id);
    }

    /**
     * Draw all elements of the UI
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.GameWindow}
     *
     * @param gfx the graphics object to draw the elements on
     */
    public void draw(BGraphics gfx) {
        for (UIElement element : elements.values()) {
            if (element.isVisible()) {
                element.draw(gfx);
            }
        }
    }

    /**
     * Resize all elements of the UI
     *
     * @param newWidth  the new width of the window
     * @param newHeight the new height of the window
     */
    public void onResize(int newWidth, int newHeight) {
        //TODO change scale
    }

    /**
     * Retrieve the scale of the UI
     *
     * @return the scale factor
     */
    public float getScale() {
        return scale;
    }
}
