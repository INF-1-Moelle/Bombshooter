package de.bombshooter.bombshooter.ui;

import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.HashMap;

public class UIHandler {

    private HashMap<String, UIElement> elements = new HashMap<>();


    public void addElement(UIElement element) {
        elements.put(element.getId(), element);
    }

    public void removeElement(UIElement element) {
        removeElement(element.getId());
    }

    public void removeElement(String Id) {
        elements.remove(Id);
    }

    public UIElement getElement(String Id) {
        return elements.get(Id);
    }

    public void draw(PGraphics gfx) {
        for (UIElement element : elements.values()) {
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
