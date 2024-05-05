package de.bombshooter.bombshooter.level;

import processing.core.PGraphics;

import java.util.ArrayList;

public class Level {

    private ArrayList<LevelElement> elements = new ArrayList<>();

    public void addElement(LevelElement element) {
        elements.add(element);
    }

    public void removeElement(LevelElement element) {
        elements.remove(element);
    }

    public void  draw(PGraphics gfx) {
        for (LevelElement element : elements) {
            if (element.isVisible()) {
                element.draw(gfx);
            }
        }
    }

}
