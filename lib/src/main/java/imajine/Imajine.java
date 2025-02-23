package imajine;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.annotation.Nonnull;

public class Imajine {
    @Nonnull
    private int width, height;

    @Nonnull
    private String source;

    @Nonnull
    private BufferedImage bufferedImage;

    public Imajine(String source) throws IOException {
        this.source = source;

        File f = new File(source);
        bufferedImage = ImageIO.read(f);

        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
    }

    public boolean someLibraryMethod() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("{ \"path\": \"%s\", \"width\": %d, \"height\": %d, \"format\": \"png\" }",
                source,
                width,
                height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
