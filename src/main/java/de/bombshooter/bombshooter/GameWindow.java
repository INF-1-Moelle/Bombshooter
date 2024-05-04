package de.bombshooter.bombshooter;

import processing.core.PApplet;

public class GameWindow extends PApplet {

    private static GameWindow INSTANCE = null;

    public static GameWindow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameWindow();
        }

        return INSTANCE;
    }

    public GameWindow() {
        INSTANCE = this;
    }

    @Override
    public void settings() {
        size(1000, 1000);
    }

    @Override
    public void setup() {
        surface.setResizable(true);
        noStroke();
        background(0);
        frameRate(30);
    }

    @Override
    public void draw() {
    }

}
