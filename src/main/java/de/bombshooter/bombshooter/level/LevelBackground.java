package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import processing.core.PGraphics;
import processing.core.PVector;

public class LevelBackground extends LevelObject {

    public LevelBackground(PVector size) {
        super(new PVector(0, 0), size);
    }


    /**
     * Draw the element
     * Should be called in the draw method of {@link de.bombshooter.bombshooter.level.Level}
     *
     * @param gfx The graphics object to draw on
     */
    @Override

    protected void onDraw(PGraphics gfx) {
        TileablePImage bg = GameWindow.getInstance().getMediaManager().loadImageById("level.background");

        for (int x = 0; x < getSize().x; x += bg.getTileWidth()) {
            for (int y = 0; y < getSize().y; y += bg.getTileHeight()) {
                gfx.image(bg.getImage(), x, y, bg.getTileWidth(), bg.getTileHeight(), bg.getTileWidth()*2,0,bg.getTileWidth()*3, bg.getTileHeight());
            }
        }

    }
}