package de.bombshooter.bombshooter.level.entity;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.level.DamageableLevelObject;
import processing.core.PVector;

abstract class Enemy extends DamageableLevelObject {
    float speed;
    PVector direction;

    public Enemy(PVector position, PVector size, float maxHealth, float speed) {
        super(position, size, maxHealth);
        this.speed = speed;
        this.direction = new PVector(0,0);
    }

    /**
     * Called upon death of the object
     */
    @Override
    protected abstract void onDeath();

    /**
     * Calculate the direction to the town hall
     * @return direction to the town hall as a {@link processing.core.PVector}
     */
    public PVector calcDirection() {
        PVector target = GameWindow.getInstance().getLevel().getTownHall().getPosition();
        PVector direction = target.copy().sub(getPosition());
        return direction.normalize();
    }

    public void walk() {
        this.calcDirection();   //später nur dann, wenn ein neues Tile betreten wird (calcDirection sollte dann nen ganzes PathFinding machen)
        getPosition().add(direction.copy().mult(speed));
    }
}
