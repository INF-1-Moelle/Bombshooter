package de.bombshooter.bombshooter.generics;

import processing.core.PGraphics;
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

    /**
     * Should not be used inside {@link de.bombshooter.bombshooter.MediaManager.TileablePImageDeserializer}
     *
     * @param tileWidth The width of a tile
     * @param tileHeight The height of a tile
     */
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

    /**
     * Draw the image at 0,0 with default size
     *
     * @param gfx PGraphics instance
     */
    public void image(PGraphics gfx){
        this.image(gfx, this.getWidth(), this.getHeight());
    }

    /**
     * Draw the image at (0,0) with default size
     *
     * @param gfx PGraphics instance
     * @param sizeX X-Size of the drawn Image
     * @param sizeY Y-Size of the drawn Image
     */
    public void image(PGraphics gfx, float sizeX, float sizeY){
        this.image(gfx, 0, 0, sizeX, sizeY);
    }

    /**
     * Draw the image
     *
     * @param gfx PGraphics instance
     * @param posX X-Position of the Image
     * @param posY Y-Position of the Image
     * @param sizeX X-Size of the drawn Image
     * @param sizeY Y-Size of the drawn Image
     */
    public void image(PGraphics gfx, float posX, float posY, float sizeX, float sizeY){
        this.image(gfx, posX, posY, sizeX, sizeY, 0, 0, this.getWidth(), this.getHeight());
    }

    /**
     * Draw a cutout or tile of the image
     *
     * @param gfx PGraphics instance
     * @param posX x-position of the Image
     * @param posY y-position of the Image
     * @param sizeX X-size of the drawn Image
     * @param sizeY Y-size of the drawn Image
     * @param textureStartX x-position in the texture file from where to start the cutout
     * @param textureStartY y-position in the texture file from where to start the cutout
     * @param textureEndX x-position in the texture file from where to end the cutout
     * @param textureEndY y-position in the texture file from where to end the cutout
     */
    public void image(PGraphics gfx, float posX, float posY, float sizeX, float sizeY, int textureStartX, int textureStartY, int textureEndX, int textureEndY){
        gfx.image(getImage(), posX, posY, sizeX, sizeY, textureStartX, textureStartY, textureEndX, textureEndY);
    }
}
