package imajine;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;

public class Imajine {
    @Nonnull
    private int width, height;

    @Nonnull
    private BufferedImage bufferedImage;

    @Nullable
    private String source, format;

    /**
     * Constructs an Imajine object from the specified image file.
     *
     * @param source the path to the image file
     * @throws IOException if an error occurs during reading the file
     */
    public Imajine(String source) throws IOException {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Source cannot be null or empty");
        }

        this.source = source;

        File f = new File(source);

        if (!f.exists() || !f.isFile()) {
            throw new IOException("File does not exist or is not a file: " + source);
        }

        bufferedImage = ImageIO.read(f);

        if (bufferedImage == null) {
            throw new IOException("Failed to read image from file: " + source);
        }

        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        format = source.substring(source.lastIndexOf('.') + 1);
    }

    /**
     * Constructs an Imajine object with the specified width and height.
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @throws IllegalArgumentException if the width or height is less than or equal
     *                                  to zero
     */
    public Imajine(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than zero");
        }

        this.width = width;
        this.height = height;

        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Saves the image to the specified output file.
     *
     * @param output the path to the output file
     * @throws IOException if an error occurs during writing the file
     */
    public void save(String output) throws IOException {
        if (output == null || output.isEmpty()) {
            throw new IllegalArgumentException("Output cannot be null or empty");
        }

        File f = new File(output);
        String format = output.substring(output.lastIndexOf('.') + 1).toUpperCase();

        if (!ImageIO.write(bufferedImage, format, f)) {
            throw new IOException("Failed to write image to file: " + output);
        }
    }

    /**
     * Sets the pixel at the specified location to the specified color.
     *
     * @param pixel the pixel to set
     */
    public void setPixel(Pixel pixel) {
        if (pixel == null) {
            throw new IllegalArgumentException("Pixel cannot be null");
        }

        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        bufferedImage.setRGB(pixel.getX(), pixel.getY(), color.getRGB());
    }

    /**
     * Gets the pixel at the specified location.
     *
     * @param x the x-coordinate of the pixel
     * @param y the y-coordinate of the pixel
     * @return the pixel at the specified location
     */
    public Pixel getPixel(int x, int y) {
        int rgb = bufferedImage.getRGB(x, y);

        int r = rgb >> 16 & 0xff;
        int g = rgb >> 8 & 0xff;
        int b = rgb & 0xff;

        return new Pixel(x, y, r, g, b);
    }

    /**
     * Gets the width of the image.
     *
     * @return the width of the image
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the image.
     *
     * @return the height of the image
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns a string representation of the Imàjine object.
     *
     * @return a string representation of the Imàjine object
     */
    @Override
    public String toString() {
        return String.format("{ \"path\": \"%s\", \"format\": \"%s\", \"width\": %d, \"height\": %d }",
                source,
                format,
                width,
                height);
    }
}
