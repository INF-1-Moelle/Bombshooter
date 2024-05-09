package de.bombshooter.bombshooter.level;

import de.bombshooter.bombshooter.GameWindow;
import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.graphics.BGraphics;
import de.bombshooter.bombshooter.level.entity.Arrow;
import de.bombshooter.bombshooter.level.entity.BallistaArrow;
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
    protected void onDraw(BGraphics gfx) {
        TileablePImage tileablePImage = GameWindow.getInstance().getMediaManager().loadImageById("level.townhall");

        gfx.pushMatrix();
        gfx.translate(getPosition().x, getPosition().y);
        gfx.image(tileablePImage, new PVector(0, 0), getSize());
        ballista.onDraw(gfx);
        gfx.popMatrix();
    }

    /**
     * Called upon death of the object
     */
    @Override
    protected void onDeath() {

    }

    static class Ballista extends LevelObject {

        private final ArrayList<Arrow> arrows;
        private int shootFrame;
        private boolean normalArrow = true;

        public Ballista(PVector pos, PVector size) {
            super(pos, size);
            arrows = new ArrayList<>();
            shootFrame = 0;
        }

        public void shootArrow(PVector velocity) {
            shootFrame = GameWindow.getInstance().frameCount;
            arrows.add(new BallistaArrow(new PVector(0, 0), velocity, normalArrow, shootFrame));
        }

        @Override
        public void onDraw(BGraphics gfx) {
            TileablePImage ballistaImage = GameWindow.getInstance().getMediaManager().loadImageById("level.ballista");
            PVector mouse = new PVector(GameWindow.getInstance().mouseX, GameWindow.getInstance().mouseY);
            float angleBallista = mouse.copy().sub(getPosition()).heading();

            gfx.pushMatrix();
            gfx.rotate(angleBallista + gfx.HALF_PI);

            int frame = (GameWindow.getInstance().frameCount - shootFrame) / 10;
            frame = (Math.min(frame, 6) + 1) % 7;

            ballistaImage.image(gfx, new PVector(),
                    ballistaImage.getTileSize(),
                    0, frame);

            gfx.popMatrix();

            //TODO change to global mouse Handling in MouseHandler
            if (GameWindow.getInstance().mousePressed && frame == 0) {
                shootArrow(mouse.copy().sub(getPosition()).normalize().mult(7));
            }

            if (GameWindow.getInstance().keyPressed && GameWindow.getInstance().key == '1') {
                normalArrow = true;
            } else if (GameWindow.getInstance().keyPressed && GameWindow.getInstance().key == '2') {
                normalArrow = false;
            }

            arrows.forEach(a -> a.draw(gfx));

            for (int i = arrows.size() - 1; i >= 0; i--) {
                Arrow arrow = arrows.get(i);

                if (!gfx.isOnScreen(arrow))
                    arrows.remove(arrow);
            }

        }
    }
}
