package imajine;

import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;

public class Imajine {
    private int width, height;

    @Nonnull
    String source;

    @Nonnull
    BufferedImage bufferedImage;

    public Imajine(String source) {
        this.source = source;
        width = 1920;
        height = 1080;
    }

    public boolean someLibraryMethod() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%dx%d", width, height);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
