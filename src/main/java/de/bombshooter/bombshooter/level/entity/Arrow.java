package de.bombshooter.bombshooter.level.entity;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.level.Level;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class Arrow extends Projectile{
    PVector direction;

    public Arrow(PVector pos, PVector direction){
        super(pos, new PVector (3, 44));
        this.direction = direction;
    }
}
