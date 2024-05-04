package de.bombshooter.bombshooter.ui;

import processing.core.PGraphics;

import java.util.ArrayList;

public class UIHandler {

    private ArrayList<UIElement> elements = new ArrayList<>();


    public void addElement(UIElement element) {
        elements.add(element);
    }

    public void removeElement(UIElement element) {
        elements.remove(element);
    }

    public void  draw(PGraphics gfx) {
        for (UIElement element : elements) {
            if (element.isVisible()) {
                element.draw(gfx);
            }
        }
    }

    public void onResize(int newWidth, int newHeight) {

    }

//    public float th (float h) {
//        return height/720.0 * h;
//    }
//    public float tw (float w) {
//        return width/1280.0 * w;
//    }
//    public float ty (float y) {
//        return height/720.0 * y;
//    }
//    public float tx (float x) {
//        return width/1280.0 * x;
//    }
//    public float tt (float t) {
//        return min(height/720.0, width/1280.0) * t;
//    }

}
