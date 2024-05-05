package de.bombshooter.bombshooter.ui.elements;

import de.bombshooter.bombshooter.ui.UIElement;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Bar extends UIElement {

    private float value;
    private float maxValue;

    public Bar(float x, float y, float width, float height) {
        super(new PVector(x, y), new PVector(width, height));
        this.value = 0;
        this.maxValue = 100;
    }

    @Override
    protected void onDraw(PGraphics gfx) {
        //TODO
    }

    @Override
    protected String getId() {
        return "healthbar";
    }

    public void setValueTo(float value) {
        this.value = value;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

}
