package de.bombshooter.bombshooter.ui.elements;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.ui.UIElement;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Bar extends UIElement {

    private float value;
    private float maxValue;

    public Bar(float x, float y, float width, float height, String id) {
        super(new PVector(x, y), new PVector(width, height), id);
        this.value = 0;
        this.maxValue = 100;
    }

    /**
     * Draw the element
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.ui.UIHandler}
     * @param gfx The graphics object to draw on
     */
    @Override
    protected void onDraw(PGraphics gfx) {
        var image = GameWindow.getInstance().getMediaManager().loadImageById("ui.healthbar");
        gfx.pushMatrix();
        gfx.translate(getPosition().x, getPosition().y);
        //background
        gfx.image(image, 0, 0, getSize().x, getSize().y, 3, 20, 3 +46, 20 +10);
        //left vertical stripe
        gfx.image(image, 1*getScale(), 3*getScale(), 1*getScale(), 4*getScale(), 6, 10, 6+1, 10+5);
        //body fill stripes
        int progress = (int) PApplet.lerp(2, 42, value / maxValue);
        gfx.image(image, 2*getScale(), 4*getScale(), (progress-2)*getScale(), 2*getScale(), 8, 9, 8+progress-2, 9+2);
        gfx.image(image, 2*getScale(), (4+2)*getScale(), (progress-1)*getScale(), 2*getScale(), 8, 11, 8+progress-1, 11+2);
        gfx.image(image, 2*getScale(), (4+4)*getScale(), (progress)*getScale(), 2*getScale(), 8, 13, 8+progress, 13+2);
        gfx.popMatrix();

        if (GameWindow.getInstance().frameCount % 10 == 0){
            setValueTo(value + 1);
            if (value == maxValue){
                setValueTo(0);
            }
        }
    }

    /**
     * Set the value of the bar
     */
    public void setValueTo(float value) {
        this.value = value;
    }

    /**
     * Set the maximum value of the bar
     * @param maxValue the maximum value
     */
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

}
