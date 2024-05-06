package de.bombshooter.bombshooter.level.entity;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class BallistaArrow extends Arrow{

    public BallistaArrow(PVector pos, PVector direction){
        super(pos, direction);
    }

    @Override
    protected void onDraw(PGraphics gfx) {

        TileablePImage texture = GameWindow.getInstance().getMediaManager().loadImageById("level.ballista.arrow");

        gfx.pushMatrix();
        gfx.translate(getPosition().x, getPosition().y);
        gfx.rotate(direction.heading()- PConstants.HALF_PI);
        gfx.image(texture.getImage(), 0, 0, texture.getWidth(), texture.getHeight());
        gfx.popMatrix();
    }
}
