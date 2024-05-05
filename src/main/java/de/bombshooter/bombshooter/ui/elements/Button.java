package de.bombshooter.bombshooter.ui.elements;

import de.bombshooter.bombshooter.ui.UIElement;
import processing.core.PGraphics;
import processing.core.PVector;

public class Button extends UIElement {

    private String text;

    public Button(PVector position, PVector size, String id) {
        super(position, size, id);
    }

    /**
     * Draw the element
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.ui.UIHandler}
     * @param gfx The graphics object to draw on
     */
    @Override
    protected void onDraw(PGraphics gfx) {
        gfx.fill(0);
        gfx.rect(this.getPosition().x, getPosition().y, getSize().x, getSize().y);
    }

    /**
     * Retrieve the text of the button
     *
     * @return the text of the button
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text of the button
     *
     * @param text the text of the button
     * @return the {@link Button} for chaining
     */
    public Button setText(String text) {
        this.text = text;
        return this;
    }
}
