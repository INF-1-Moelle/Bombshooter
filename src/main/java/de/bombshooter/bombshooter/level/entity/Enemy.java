package de.bombshooter.bombshooter.level.entity;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.level.DamageableLevelObject;
import processing.core.PGraphics;
import processing.core.PVector;

public class Enemy extends DamageableLevelObject {
    public Enemy(PVector position, PVector size, int maxHealth) {
        super(position, size, maxHealth);
    }

    /**
     * Called upon death of the object
     */
    @Override
    protected void onDeath() {

    }

    /**
     * Calculate the direction to the town hall
     * @return direction to the town hall as a {@link processing.core.PVector}
     */
    public PVector calcDirection() {
        PVector target = GameWindow.getInstance().getLevel().getTownHall().getPosition();
        PVector direction = target.copy().sub(getPosition());
        return direction.normalize();
    }


    /**
     * Draw the element
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.level.Level}
     * @param gfx The graphics object to draw on
     */
    @Override
    protected void onDraw(PGraphics gfx) {

    }
}
