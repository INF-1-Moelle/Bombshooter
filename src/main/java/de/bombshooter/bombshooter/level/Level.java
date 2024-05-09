package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.graphics.BGraphics;
import de.bombshooter.bombshooter.ui.elements.Bar;
import processing.core.PVector;

import java.util.ArrayList;

public class Level {

    private TownHall townHall;
    private LevelBackground background;
    private final ArrayList<LevelObject> elements;

    public Level() {
        elements = new ArrayList<>();
    }

    /**
     * Initialize the default objects of the level
     * like the town hall and the background
     *
     * @param gfx the graphics object to calculate the positions from
     */
    public void initDefaultObjects(BGraphics gfx) {

        this.townHall = new TownHall(new PVector(gfx.width / 2f, gfx.height / 2f), new PVector(60, 60));

        this.background = new LevelBackground(new PVector(gfx.width, gfx.height));

        addElement(background);
        addElement(townHall);

        GameWindow.getInstance().getUIHandler().addElement(new Bar(50, 50, 46, 10, "testbutton"));
    }

    /**
     * Add an element to the level
     *
     * @param element the element to add
     */
    public void addElement(LevelObject element) {
        elements.add(element);
    }

    /**
     * Remove an element from the level
     *
     * @param element the element to remove
     */
    public void removeElement(LevelObject element) {
        elements.remove(element);
    }

    /**
     * Draw all elements of the level
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.GameWindow}
     *
     * @param gfx the graphics object to draw the elements on
     */
    public void draw(BGraphics gfx) {
        for (LevelObject element : elements) {
            if (element.isVisible()) {
                element.draw(gfx);
            }
        }
    }

    /**
     * Get the town hall of the level
     *
     * @return the town hall
     */
    public TownHall getTownHall() {
        return townHall;
    }

    /**
     * Get the background of the level
     *
     * @return the background
     */
    public LevelBackground getBackground() {
        return background;
    }
}
