package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.graphics.BGraphics;
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

    protected void onDraw(BGraphics gfx) {
        TileablePImage bg = GameWindow.getInstance().getMediaManager().loadImageById("level.background");

        for (int x = 0; x < getSize().x + bg.getTileWidth(); x += bg.getTileWidth()) {
            for (int y = 0; y < getSize().y + bg.getTileHeight(); y += bg.getTileHeight()) {
                boolean t = GameWindow.getInstance().noise((float) (x + GameWindow.getInstance().frameCount) / bg.getTileWidth() / 10, (float) y / bg.getTileHeight() / 10,
                GameWindow.getInstance().frameCount * 0.01f) > 0.5f;
                int o = 2 + (t ? 1 : 0);
                bg.image(gfx, new PVector(x, y), bg.getTileSize(), bg.getTileWidth() * o, 0, bg.getTileWidth() * (o + 1), bg.getTileHeight());
            }
        }

    }

    @Override
    public String toString() {
        return "LevelBackground{} " + super.toString();
    }
}