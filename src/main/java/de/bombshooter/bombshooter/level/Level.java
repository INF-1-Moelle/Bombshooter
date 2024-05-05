package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.ui.elements.Bar;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;

public class Level {

    private TownHall townHall;
    private LevelBackground background;
    private final ArrayList<LevelObject> elements;

    public Level() {
        elements = new ArrayList<>();
    }

    public void initDefaultObjects(PGraphics gfx) {

        PVector townHallSize = new PVector(100, 100);

        this.townHall = new TownHall(new PVector((gfx.width / 2) - (townHallSize.x / 2), (gfx.height / 2) - (townHallSize.x / 2)), townHallSize);
        this.background = new LevelBackground(new PVector(gfx.width, gfx.height));

        addElement(background);
        addElement(townHall);
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
    }

    public TownHall getTownHall() {
        return townHall;
    }
}
