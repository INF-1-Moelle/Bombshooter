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
     * @param gfx
     */
    @Override
    protected void onDraw(PGraphics gfx) {

    }

    public PVector calcDirection() {
        PVector target = GameWindow.getInstance().getLevel().getTownHall().getPosition();
        PVector direction = target.copy().sub(getPosition());
        return direction.normalize();
    }


}
