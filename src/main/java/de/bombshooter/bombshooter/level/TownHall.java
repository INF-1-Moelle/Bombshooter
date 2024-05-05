package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import processing.core.PGraphics;
import processing.core.PVector;

public class TownHall extends DamageableLevelObject {

    public TownHall(PVector pos, PVector size) {
        super(pos, size, -1);
    }

    /**
     * Draw the element
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.level.Level}
     *
     * @param gfx The graphics object to draw on
     */
    @Override
    protected void onDraw(PGraphics gfx) {
        TileablePImage tileablePImage = GameWindow.getInstance().getMediaManager().loadImageById("level.townhall");
        gfx.pushMatrix();
        //gfx.translate(getPosition().x, getPosition().y);
        gfx.image(tileablePImage.getImage(), getPosition().y, getPosition().y, getSize().x, getSize().y);
        gfx.popMatrix();
    }

    /**
     * Called upon death of the object
     */
    @Override
    protected void onDeath() {

    }
}
