package de.bombshooter.bombshooter.ui.elements;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.graphics.BGraphics;
import de.bombshooter.bombshooter.ui.UIElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.core.PApplet;
import processing.core.PVector;

public class Bar extends UIElement {

    private float value;
    Logger logger = LoggerFactory.getLogger("BarDebug");

    public Bar(float x, float y, float width, float height, String id) {
        super(new PVector(x, y), new PVector(width, height), id);
        this.value = 0;
    }

    /**
     * Draw the element
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.ui.UIHandler}
     *
     * @param gfx The graphics object to draw on
     */
    @Override
    protected void onDraw(BGraphics gfx) {
        TileablePImage tileablePImage = GameWindow.getInstance().getMediaManager().loadImageById("ui.healthbar");
        gfx.pushMatrix();
        gfx.translate(getPosition().x, getPosition().y);

        int progress = PApplet.floor(value * tileablePImage.getVerticalTileAmount());
        gfx.image(tileablePImage, new PVector(0, 0), getSize().mult(getScale()), progress);

        gfx.popMatrix();

        /*if (GameWindow.getInstance().frameCount % 10 == 0) {

           if (value == maxValue) {
                setValueTo(0);
            }
            setValueTo(value + 1);
        }*/
    }

    /**
     * Set the value of the bar
     */
    public void setValueTo(float value) {
        this.value = value;
    }

}
