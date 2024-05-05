package de.bombshooter.bombshooter.level;

import processing.core.PVector;

public abstract class DamageableLevelObject extends LevelObject {

    private int maxHealth;
    private int health;

    public DamageableLevelObject(PVector pos, PVector size, int maxHealth) {
        super(pos, size);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    /**
     * @param damage the damage to apply
     * @return the new health
     */
    public int applyDamage(int damage) {
        int health = getHealth();
        if (health - damage > 0) {
            health -= damage;
        } else {
            health = 0;
            onDeath();
        }
        return health;
    }

    /**
     * @param healing the healing to apply
     * @return the new health
     */
    public int applyHealing(int healing) {
        return applyDamage(-healing);
    }

    /**
     * @return the max health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return the current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param maxHealth the new health
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Called upon death of the object
     */
    protected abstract void onDeath();

}
