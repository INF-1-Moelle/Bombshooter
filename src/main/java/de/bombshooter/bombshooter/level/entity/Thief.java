package de.bombshooter.bombshooter.level.entity;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.graphics.BGraphics;
import processing.core.PVector;

public class Thief extends Enemy {
    public Thief(int lvl) {
        super(new PVector((float) Math.random(), (float) Math.random()), new PVector(20, 20), 100 * (float) Math.pow(1.2, lvl), 0.5f);
    }

    @Override
    protected void onDeath() {
        // ig maybe particle spawnen und geld und exp droppen
    }

    @Override
    protected void onDraw(BGraphics gfx) {
        gfx.pushMatrix();
        gfx.translate(getPosition().x, getPosition().y);
        gfx.rotate(direction.heading());

        TileablePImage tileablePImage = GameWindow.getInstance().getMediaManager().loadImageById("level.thief");
        int animation = 1;

        tileablePImage.image(gfx, new PVector(0, 0), tileablePImage.getSize().mult(2), 0, tileablePImage.getTileHeight() * animation, tileablePImage.getTileWidth(), tileablePImage.getTileHeight() * (animation + 1));
        gfx.popMatrix();
    }

    @Override
    public String toString() {
        return "Thief{" +
                "speed=" + speed +
                ", direction=" + direction +
                "} " + super.toString();
    }
}
