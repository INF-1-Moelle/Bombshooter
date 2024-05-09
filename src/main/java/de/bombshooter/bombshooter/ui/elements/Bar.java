package de.bombshooter.bombshooter.ui.elements;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
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
     *
     * @param gfx The graphics object to draw on
     */
    @Override
    protected void onDraw(PGraphics gfx) {
        TileablePImage tileablePImage = GameWindow.getInstance().getMediaManager().loadImageById("ui.healthbar");
        gfx.pushMatrix();
        gfx.translate(getPosition().x, getPosition().y);
        int progress = (int) PApplet.lerp(0, (float) tileablePImage.getHeight() /tileablePImage.getTileHeight() - 1, value / maxValue);
        //
        //background
        int offset = progress * tileablePImage.getTileHeight();
        tileablePImage.image(gfx, 0, 0, getSize().x * getScale(), getSize().y * getScale(),
                0, offset, tileablePImage.getTileWidth(), tileablePImage.getTileHeight() + offset);
        gfx.popMatrix();

        if (GameWindow.getInstance().frameCount % 10 == 0) {

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
     *
     * @param maxValue the maximum value
     */
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

}
