package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.level.entity.Arrow;
import de.bombshooter.bombshooter.level.entity.BallistaArrow;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;

public class TownHall extends DamageableLevelObject {
    Ballista ballista;


    public TownHall(PVector pos, PVector size) {
        super(pos, size, -1);
        ballista = new Ballista(pos, new PVector(27 * 2, 26 * 2));
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
        gfx.translate(getPosition().x, getPosition().y);
        gfx.image(tileablePImage.getImage(), -(tileablePImage.getWidth() / 2f), -(tileablePImage.getHeight() / 2f), getSize().x, getSize().y);
        ballista.onDraw(gfx);
        gfx.popMatrix();
    }

    /**
     * Called upon death of the object
     */
    @Override
    protected void onDeath() {

    }

    class Ballista extends LevelObject {

        private ArrayList<Arrow> arrows = new ArrayList<>();
        private int shootFrame = 0;

        public Ballista(PVector pos, PVector size) {
            super(pos, size);
        }

        public void shootArrow(PVector direction) {
            arrows.add(new BallistaArrow(getPosition(), direction));
            shootFrame = GameWindow.getInstance().frameCount;
        }

        @Override
        public void onDraw(PGraphics gfx) {
            TileablePImage ballistaImage = GameWindow.getInstance().getMediaManager().loadImageById("level.ballista");
            PVector mouse = new PVector(GameWindow.getInstance().mouseX, GameWindow.getInstance().mouseY);
            float angleBallista = mouse.copy().sub(getPosition()).heading();

            gfx.pushMatrix();
            gfx.rotate(angleBallista + gfx.HALF_PI);

            int frame = (GameWindow.getInstance().frameCount - shootFrame) / 10;
            frame = (Math.min(frame, 6) + 1) % 7;

            gfx.image(ballistaImage.getImage(), -(ballistaImage.getTileWidth() / 2f), -(ballistaImage.getTileHeight() / 2f),
                    ballistaImage.getTileWidth(), ballistaImage.getTileHeight(),
                    0, ballistaImage.getTileHeight() * frame, ballistaImage.getWidth(), ballistaImage.getTileHeight() * (frame + 1));


            //TODO change to global mouse Handling in MouseHandler
            if (GameWindow.getInstance().mousePressed && frame == 0) {
                shootArrow(mouse.copy().sub(getPosition()).normalize().mult(10));
            }
            gfx.popMatrix();
            arrows.forEach(a -> a.draw(gfx));
        }
    }
}
