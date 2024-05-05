package de.bombshooter.bombshooter;

import de.bombshooter.bombshooter.ui.UIHandler;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.opengl.PGraphicsOpenGL;

public class GameWindowDaniel extends PApplet {

    private static GameWindowDaniel INSTANCE = null;
    private final UIHandler uiHandler;
    PImage townhall;
    PImage ballista;
    PImage arrow;
    PVector posBallista = new PVector();
    PVector posArrow = new PVector();
    PVector target = new PVector();
    PVector direction = new PVector();
    boolean shoot = false;
    int frameOld, frameNew;


    public static GameWindowDaniel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameWindowDaniel();
        }

        return INSTANCE;
    }

    private GameWindowDaniel() {
        INSTANCE = this;
        this.uiHandler = new UIHandler();
    }

    @Override
    public void settings() {
        size(displayWidth, displayHeight, P2D);
    }

    @Override
    public void setup() {
        surface.setResizable(true);
        noStroke();
        background(0);
        frameRate(60);
        ((PGraphicsOpenGL) g).textureSampling(2);
        imageMode(CENTER);
        posBallista.x = width / 2f;
        posBallista.y = height / 2f;
        townhall = loadImage("./bombshooter-media/townhall_V1.png");
        ballista = loadImage("./bombshooter-media/ballista_V1.png");
        arrow = loadImage("./bombshooter-media/ballista_arrow_V1.png");
    }

    @Override
    public void draw() {
        background(0);
        uiHandler.draw(getGraphics());
        target = new PVector(mouseX, mouseY);
        if (shoot) {
            frameNew = frameCount;
            if((frameNew - frameOld) >= 120){
                posArrow = new PVector(width / 2f, height / 2f);
                direction = (target.copy().sub(posArrow));
                direction = direction.normalize().mult(10);
                frameOld = frameNew;
            }
        }
        float angleArrow = direction.heading();
        float angleBallista = target.copy().sub(posBallista).heading();
        image(townhall, width / 2f, height / 2f, 120, 120);
        pushMatrix();
        translate(posBallista.x, posBallista.y);
        rotate(angleBallista + HALF_PI);
        if (!shoot) {
            image(ballista, 0, 0, 100, 100, 0, 0, 50, 50);
        } else {
            int frame = frameCount / 15 % 7;
            image(ballista, 0, 0, 100, 100, 0, 50 * (frame +1), 50, 50 * (frame + 2));
            if (frame == 6) {
                shoot = false;
            }
        }
        popMatrix();
        posArrow = posArrow.add(direction);
        pushMatrix();
        translate(posArrow.x, posArrow.y);
        rotate(angleArrow + HALF_PI);
        image(arrow, 0, 0, 50, 50, 0, 0, 25, 25);
        popMatrix();
    }

    @Override
    public void windowResize(int newWidth, int newHeight) {
        uiHandler.onResize(newWidth, newHeight);
    }

    @Override
    public void mouseClicked() {
        shoot = true;
    }

    public UIHandler getUiHandler() {
        return uiHandler;
    }
}
