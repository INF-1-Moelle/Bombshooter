package de.bombshooter.bombshooter.ui.elements;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.graphics.BGraphics;
import de.bombshooter.bombshooter.ui.UIElement;
import processing.core.PVector;

public class Crosshair extends UIElement {

        public Crosshair(PVector position, PVector size, String id) {
            super(position, size, id);
        }

        @Override
        protected void onDraw(BGraphics gfx) {
            TileablePImage crosshair = GameWindow.getInstance().getMediaManager().loadImageById("ui.crosshair");
            crosshair.image(gfx, new PVector(GameWindow.getInstance().mouseX, GameWindow.getInstance().mouseY), new PVector(40, 40));
        }
}
