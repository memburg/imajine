package imajine;

import java.io.File;
import java.io.IOException;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.annotation.Nonnull;

public class Imajine {
    @Nonnull
    private int width, height;

    @Nonnull
    private String source, format;

    @Nonnull
    private BufferedImage bufferedImage;

    public Imajine(String source) throws IOException {
        this.source = source;

        File f = new File(source);
        bufferedImage = ImageIO.read(f);

        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        format = source.substring(source.lastIndexOf('.') + 1);
    }

    public void setPixel(Pixel pixel) {
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        bufferedImage.setRGB(pixel.getX(), pixel.getY(), color.getRGB());
    }

    public Pixel getPixel(int x, int y) {
        int rgb = bufferedImage.getRGB(x, y);

        int r = rgb >> 16 & 0xff;
        int g = rgb >> 8 & 0xff;
        int b = rgb & 0xff;

        return new Pixel(x, y, r, g, b);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("{ \"path\": \"%s\", \"width\": %d, \"height\": %d, \"format\": \"%s\" }",
                source,
                width,
                height,
                format);
    }
}
