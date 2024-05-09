package de.bombshooter.bombshooter.graphics;

import de.bombshooter.bombshooter.generics.TileablePImage;
import de.bombshooter.bombshooter.level.LevelObject;
import processing.core.PImage;
import processing.core.PVector;
import processing.opengl.PGraphics2D;

public class BGraphics extends PGraphics2D {
    public BGraphics() {
        super();
    }

    public void translate(PVector t) {
        translate(t.x, t.y, t.z);
    }

    public void scale(PVector s) {
        scale(s.x, s.y, s.z);
    }

    public void image(PImage img, PVector pos, PVector size) {
        image(img, pos.x, pos.y, size.x, size.y);
    }

    public void image(TileablePImage img, PVector pos, PVector size) {
        image(img.getImage(), pos, size);
    }

    public void image(PImage img, PVector pos, PVector size, int u1, int v1, int u2, int v2) {
        image(img, pos.x, pos.y, size.x, size.y, u1, v1, u2, v2);
    }

    public void image(TileablePImage img, PVector pos, PVector size, int u1, int v1, int u2, int v2) {
        image(img.getImage(), pos, size, u1, v1, u2, v2);
    }

    public void image(TileablePImage img, PVector pos, PVector size, int tile) {
        if (img.getHorizontalTileAmount() == 1) image(img, pos, size, 0, tile);
        else if (img.getVerticalTileAmount() == 1) image(img, pos, size, tile, 0);
        else throw new IllegalArgumentException("image should have only one dimension with multiple tiles");
    }

    public void image(TileablePImage img, PVector pos, PVector size, int tileX, int tileY) {
        if (tileX < 0 || tileX >= img.getHorizontalTileAmount() || tileY < 0 || tileY >= img.getVerticalTileAmount())
            throw new IllegalArgumentException("tile out of bounds");

        image(img.getImage(), pos, size,
                tileX * img.getTileWidth(), tileY * img.getTileHeight(),
                (tileX + 1) * img.getTileWidth(), (tileY + 1) * img.getTileHeight());
    }

    public boolean isOnScreen(LevelObject object) {
        return isOnScreen(object.getPosition(), object.getMaxRadius());
    }

    public boolean isOnScreen(PVector pos, float margin) {
        PVector transformed = new PVector();
        getMatrix().mult(pos, transformed);

        return isInRect(transformed, new PVector(-margin, -margin), new PVector(width + margin, height + margin));
    }

    public static boolean isInRect(PVector pos, PVector rectP1, PVector rectP2){
        return pos.x >= rectP1.x && pos.x <= rectP2.x && pos.y >= rectP1.y && pos.y <= rectP2.y;
    }
}
