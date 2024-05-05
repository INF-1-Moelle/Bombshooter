package de.bombshooter.bombshooter.generics;

import processing.core.PImage;

public class TileablePImage {
    PImage image;
    String fileName;
    int width;
    int height;
    boolean tiled;
    int tileWidth;
    int tileHeight;

    public TileablePImage(PImage image, String fileName, int width, int height, boolean tiled) {
        this.image = image;
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        this.tiled = tiled;
    }

    public void setTileSize(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public PImage getImage() {
        return image;
    }

    public String getFileName() {
        return fileName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isTiled() {
        return tiled;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
