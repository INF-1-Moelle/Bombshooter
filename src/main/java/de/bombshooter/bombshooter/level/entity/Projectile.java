package de.bombshooter.bombshooter.level.entity;

import de.bombshooter.bombshooter.level.LevelObject;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class Projectile extends LevelObject {

    public Projectile(PVector pos, PVector size){
        super(pos, size);
    }
}
