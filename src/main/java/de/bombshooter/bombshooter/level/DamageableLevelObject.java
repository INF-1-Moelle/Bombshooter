package de.bombshooter.bombshooter.level;

import processing.core.PVector;

public abstract class DamageableLevelObject extends LevelObject {

    private float maxHealth;
    private float health;

    public DamageableLevelObject(PVector pos, PVector size, float maxHealth) {
        super(pos, size);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    /**
     * @param damage the damage to apply
     * @return the new health
     */
    public float applyDamage(float damage) {
        float health = getHealth();
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
    public float applyHealing(float healing) {
        return applyDamage(-healing);
    }

    /**
     * @return the max health
     */
    public float getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return the current health
     */
    public float getHealth() {
        return health;
    }

    /**
     * @param maxHealth the new health
     */
    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Called upon death of the object
     */
    protected abstract void onDeath();

    @Override
    public String toString() {
        return "DamageableLevelObject{" +
                "maxHealth=" + maxHealth +
                ", health=" + health +
                "} " + super.toString();
    }
}
