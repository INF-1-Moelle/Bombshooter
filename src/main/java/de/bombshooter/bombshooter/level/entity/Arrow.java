package de.bombshooter.bombshooter.level.entity;

import processing.core.PVector;

public abstract class Arrow extends Projectile{
    protected PVector velocity;

    public Arrow(PVector pos, PVector velocity){
        super(pos, new PVector (3, 44));
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "Arrow{" +
                "velocity=" + velocity +
                "} " + super.toString();
    }
}
