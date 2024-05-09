package de.bombshooter.bombshooter.level.entity;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.graphics.BGraphics;
import processing.core.PConstants;
import processing.core.PVector;

public class BallistaArrow extends Arrow {
    private boolean normalArrow;
    private int shootFrame;

    public BallistaArrow(PVector position, PVector velocity, boolean normalArrow, int shootFrame) {
        super(position, velocity);
        this.normalArrow = normalArrow;
        this.shootFrame = shootFrame;
    }

    @Override
    protected void onDraw(BGraphics gfx) {
        getPosition().add(velocity);

        TileablePImage texture = GameWindow.getInstance().getMediaManager().loadImageById("level.ballista.arrow");
        TileablePImage texture2 = GameWindow.getInstance().getMediaManager().loadImageById("level.ballista.arrowFX");
        gfx.pushMatrix();
        if (normalArrow) {
            texture
                    .rotateWithOffset(gfx, velocity.heading() + PConstants.HALF_PI, getPosition())
                    .image(gfx, new PVector(0, 0), texture.getSize());
        } else {
            int frame = (GameWindow.getInstance().frameCount - shootFrame) / 4;
            frame %= 10;

            texture2
                    .rotateWithOffset(gfx, velocity.heading() + PConstants.HALF_PI, getPosition())
                    .image(gfx, new PVector(0, 0), texture2.getTileSize(), frame);
        }

        gfx.popMatrix();
    }

    @Override
    public String toString() {
        return "BallistaArrow{" +
                "normalArrow=" + normalArrow +
                ", shootFrame=" + shootFrame +
                "} " + super.toString();
    }
}
