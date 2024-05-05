package de.bombshooter.bombshooter.level;

import processing.core.PVector;

public abstract class DamageableLevelObject extends LevelObject{

    private int maxHealth;
    private int health;

    public DamageableLevelObject(PVector pos, PVector size, int maxHealth){
        super(pos, size);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public int applyDamage(int damage) {
        int health = getHealth();
        if (health - damage > 0) {
            health -= damage;
        }else{
            health = 0;
            onDeath();
        }
        return health;
    }

    protected abstract void onDeath();

    public int applyHealing(int healing){
        return applyDamage(-healing);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
