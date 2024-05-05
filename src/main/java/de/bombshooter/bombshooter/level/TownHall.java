package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import processing.core.PGraphics;
import processing.core.PVector;

public class TownHall extends DamageableLevelObject {

    public TownHall(PVector pos, PVector size){
       super(pos, size, -1);
    }

    /**
     * @param gfx
     */
    @Override
    protected void onDraw(PGraphics gfx) {
        GameWindow.getInstance().getLevel();
    }
}
