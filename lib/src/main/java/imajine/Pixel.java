package imajine;

/**
 * The Pixel class represents a pixel with its coordinates and color values.
 */
public class Pixel {
    final int x;
    final int y;
    final int red;
    final int green;
    final int blue;
    final int alpha;

    /**
     * Constructs a Pixel object with the specified coordinates and color values.
     *
     * @param x     the x-coordinate of the pixel
     * @param y     the y-coordinate of the pixel
     * @param red   the red component of the pixel color (0-255)
     * @param green the green component of the pixel color (0-255)
     * @param blue  the blue component of the pixel color (0-255)
     * @param alpha the alpha component of the pixel color (0-255)
     * @throws IllegalArgumentException if any color value is out of range (0-255)
     */
    public Pixel(int x, int y, int red, int green, int blue, int alpha) {
        validateColorValue(red);
        validateColorValue(green);
        validateColorValue(blue);
        validateColorValue(alpha);

        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    /**
     * Constructs a Pixel object with the specified coordinates and color values.
     * The alpha component is set to 255 (opaque) by default.
     *
     * @param x     the x-coordinate of the pixel
     * @param y     the y-coordinate of the pixel
     * @param red   the red component of the pixel color (0-255)
     * @param green the green component of the pixel color (0-255)
     * @param blue  the blue component of the pixel color (0-255)
     * @throws IllegalArgumentException if any color value is out of range (0-255)
     */
    public Pixel(int x, int y, int red, int green, int blue) {
        this(x, y, red, green, blue, 255); // Default alpha to 255 (opaque)
    }

    /**
     * Validates that the color value is within the range 0-255.
     *
     * @param value the color value to validate
     * @throws IllegalArgumentException if the color value is out of range (0-255)
     */
    private void validateColorValue(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Color value must be between 0 and 255");
        }
    }

    /**
     * Gets the x-coordinate of the pixel.
     *
     * @return the x-coordinate of the pixel
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the pixel.
     *
     * @return the y-coordinate of the pixel
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the red component of the pixel color.
     *
     * @return the red component of the pixel color
     */
    public int getRed() {
        return red;
    }

    /**
     * Gets the green component of the pixel color.
     *
     * @return the green component of the pixel color
     */
    public int getGreen() {
        return green;
    }

    /**
     * Gets the blue component of the pixel color.
     *
     * @return the blue component of the pixel color
     */
    public int getBlue() {
        return blue;
    }

    /**
     * Gets the alpha component of the pixel color.
     *
     * @return the alpha component of the pixel color
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * Returns a string representation of the pixel.
     *
     * @return a string representation of the pixel
     */
    @Override
    public String toString() {
        return String.format("{ \"x\": %d, \"y\": %d, \"red\": %d, \"green\": %d, \"blue\": %d, \"alpha\": %d }",
                x,
                y,
                red,
                green,
                blue,
                alpha);
    }
}
