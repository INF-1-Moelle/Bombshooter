package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.ui.elements.Bar;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;

public class Level {

    private final TownHall townHall;
    private final ArrayList<LevelObject> elements = new ArrayList<>();

    public Level(){

        PGraphics gfx = GameWindow.getInstance().getGraphics();
        PVector townHallSize = new PVector(100,100);
        this.townHall = new TownHall(new PVector((gfx.width/2)-(townHallSize.x/2), (gfx.height/2)-(townHallSize.x/2)), townHallSize);

    }

    public void addElement(LevelObject element) {
        elements.add(element);
    }

    public void removeElement(LevelObject element) {
        elements.remove(element);
    }

    public void draw(PGraphics gfx) {
        for (LevelObject element : elements) {
            if (element.isVisible()) {
                element.draw(gfx);
            }
        }

        ((Bar) GameWindow.getInstance().getUIHandler().getElement("healthbar")).setValueTo(300 /*health*/);
    }

    public TownHall getTownHall() {
        return townHall;
    }
}
