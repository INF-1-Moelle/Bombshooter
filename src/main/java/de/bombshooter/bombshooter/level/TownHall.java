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
        tileablePImage.image(gfx, -(tileablePImage.getWidth()/2f), -(tileablePImage.getHeight()/2f), getSize().x, getSize().y);
        ballista.onDraw(gfx);
        gfx.popMatrix();
    }

    /**
     * Called upon death of the object
     */
    @Override
    protected void onDeath() {

    }

    class Ballista extends LevelObject{

        ArrayList<Arrow> arrows = new ArrayList<>();
        boolean shoot = false;

        public Ballista(PVector pos, PVector size) {
            super(pos, size);
        }

        public void shootArrow(PVector direction) {
            arrows.add(new BallistaArrow(getPosition(), direction));
            shoot = true;
        }

        @Override
        public void onDraw(PGraphics gfx) {
            TileablePImage ballistaImage = GameWindow.getInstance().getMediaManager().loadImageById("level.ballista");
            PVector mouse = new PVector(GameWindow.getInstance().mouseX, GameWindow.getInstance().mouseY);
            float angleBallista = mouse.copy().sub(getPosition()).heading();

            gfx.pushMatrix();
            gfx.rotate(angleBallista + gfx.HALF_PI);
            if (!shoot) {
                ballistaImage.image(gfx,
                        -(ballistaImage.getTileHeight()/2f), -(ballistaImage.getTileWidth()/2f),
                        ballistaImage.getTileHeight(), ballistaImage.getTileWidth(),
                        0, 0, 50, 50);
            } else {
                int frame = GameWindow.getInstance().frameCount / 10 % 7;
                ballistaImage.image(gfx,
                        -(ballistaImage.getTileHeight()/2f), -(ballistaImage.getTileWidth()/2f),
                        ballistaImage.getTileHeight(), ballistaImage.getTileWidth(),
                        0, 50 * (frame), 50, 50 * (frame));
                if (frame == 6) {
                    shoot = false;
                }
            }

            //TODO change to global mouse Handling in MouseHandler
            if(GameWindow.getInstance().mousePressed){
                shootArrow(mouse.copy().sub(getPosition()).normalize().mult(10));
            }
            gfx.popMatrix();
            arrows.forEach(a -> a.draw(gfx));
        }
    }
}
