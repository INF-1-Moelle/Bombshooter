package de.bombshooter.bombshooter.ui.elements;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.ui.UIElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class Bar extends UIElement {

    private float value;
    private float maxValue;
    Logger logger = LoggerFactory.getLogger("BarDebug");

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
        int progress = (int) PApplet.lerp(0, 42, value / maxValue);
        //
        //background
        int offset = progress*(int)getSize().y;
        gfx.image(image, 0, 0, getSize().x*getScale(), getSize().y*getScale(),
                0, offset, 46, 10 + offset);
        gfx.popMatrix();

        if (GameWindow.getInstance().frameCount % 10 == 0){
            //logger.info("Progress: {}, value: {}, maxValue: {}", progress, value, maxValue);
            logger.info("params: c: {}, d: {}, u1: {}, v1: {}, u2: {}, v2: {}", getSize().x*getScale(), getSize().y*getScale(),
                    0, offset, 46, 10 + offset);
            if (value == maxValue) {
                setValueTo(0);
            }
            setValueTo(value + 1);
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
