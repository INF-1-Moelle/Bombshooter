package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.ui.elements.Bar;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Level {

    private ArrayList<LevelObject> elements = new ArrayList<>();

    public void addElement(LevelObject element) {
        elements.add(element);
    }

    public void removeElement(LevelObject element) {
        elements.remove(element);
    }

    public void  draw(PGraphics gfx) {
        for (LevelObject element : elements) {
            if (element.isVisible()) {
                element.draw(gfx);
            }
        }

        ((Bar) GameWindow.getInstance().getUIHandler().getElement("healthbar")).setValueTo(300 /*health*/);
    }

}
