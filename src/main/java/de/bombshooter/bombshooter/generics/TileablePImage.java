package de.bombshooter.bombshooter.generics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.bombshooter.bombshooter.graphics.BGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class TileablePImage {
    PImage image;
    String fileName;
    int width;
    int height;
    boolean tiled;
    int tileWidth;
    int tileHeight;

    public TileablePImage(PImage image, String fileName, int width, int height, int tileWidth, int tileHeight, boolean tiled) {
        this.image = image;
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tiled = tiled;
    }

    /**
     * Should not be used inside {@link TileablePImage#fromJson(JsonElement)}
     *
     * @param tileWidth  The width of a tile
     * @param tileHeight The height of a tile
     */
    public void setTileSize(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    /**
     * Applies a centering to this texture (like this: {@code BGraphics.imageMode(CENTER)})
     *
     * @param gfx BGraphics instance
     * @return this
     */
    public TileablePImage center(BGraphics gfx) {
        gfx.translate(-getWidth() / 2f, -getHeight() / 2f);
        return this;
    }

    /**
     * Rotates the texture around its center
     *
     * @param gfx   BGraphics instance
     * @param angle angle to rotate
     * @return this
     */
    public TileablePImage rotate(BGraphics gfx, float angle) {
        gfx.rotate(angle);
        return this;
    }

    /**
     * Rotates the texture around its center and translating it to its position (offset)
     *
     * @param gfx    BGraphics instance
     * @param angle  angle to rotate
     * @param offset offset to apply
     * @return this
     */
    public TileablePImage rotateWithOffset(BGraphics gfx, float angle, PVector offset) {
        gfx.translate(offset.x, offset.y);
        return rotate(gfx, angle);
    }

    /**
     * Draw the image at 0,0 with default size
     *
     * @param gfx BGraphics instance
     */
    public void image(BGraphics gfx) {
        this.image(gfx, this.getSize());
    }

    /**
     * Draw the image at (0,0) with default size
     *
     * @param gfx  BGraphics instance
     * @param size Size of the drawn Image
     */
    public void image(BGraphics gfx, PVector size) {
        this.image(gfx, new PVector(), size);
    }

    /**
     * Draw the image
     *
     * @param gfx  BGraphics instance
     * @param pos  X-Position of the Image
     * @param size X-Size of the drawn Image
     */
    public void image(BGraphics gfx, PVector pos, PVector size) {
        this.image(gfx, pos, size, 0, 0, getWidth(), getHeight());
    }

    /**
     * Draw a cutout or tile of the image
     *
     * @param gfx           BGraphics instance
     * @param pos           Position of the Image
     * @param size          Size of the drawn Image
     * @param textureStartX x-position in the texture file from where to start the cutout
     * @param textureStartY y-position in the texture file from where to start the cutout
     * @param textureEndX   x-position in the texture file from where to end the cutout
     * @param textureEndY   y-position in the texture file from where to end the cutout
     */
    public void image(BGraphics gfx, PVector pos, PVector size, int textureStartX, int textureStartY, int textureEndX, int textureEndY) {
        gfx.image(getImage(), pos, size, textureStartX, textureStartY, textureEndX, textureEndY);
    }

    /**
     * Draw a cutout or tile of the image
     *
     * @param gfx  BGraphics instance
     * @param pos  Position of the Image
     * @param size Size of the drawn Image
     * @param tile n-th tile of the animation
     */
    public void image(BGraphics gfx, PVector pos, PVector size, int tile) {
        gfx.image(this, pos, size, tile);
    }

    /**
     * Draw a cutout or tile of the image
     *
     * @param gfx   BGraphics instance
     * @param pos   Position of the Image
     * @param size  Size of the drawn Image
     * @param tileX n-th tile in x-Position of the animation
     * @param tileY m-th tile in y-Position of the animation
     */
    public void image(BGraphics gfx, PVector pos, PVector size, int tileX, int tileY) {
        gfx.image(this, pos, size, tileX, tileY);
    }

    public static TileablePImage fromJson(JsonElement e) {
        JsonObject o = e.getAsJsonObject();

        int width = o.get("width").getAsInt();
        int height = o.get("height").getAsInt();
        int tileWidth = width;
        int tileHeight = height;
        boolean tiled = o.get("tiled").getAsBoolean();

        if (tiled) {
            tileWidth = o.get("tilewidth").getAsInt();
            tileHeight = o.get("tileheight").getAsInt();
        }

        return new TileablePImage(null, o.get("file").getAsString(),
                width, height,
                tileWidth, tileHeight, tiled);
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

    public PVector getSize() {
        return new PVector(width, height);
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

    public PVector getTileSize() {
        return new PVector(tileWidth, tileHeight);
    }

    public int getVerticalTileAmount() {
        return getHeight() / getTileHeight();
    }

    public int getHorizontalTileAmount() {
        return getWidth() / getTileWidth();
    }
}
