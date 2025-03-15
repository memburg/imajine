package imajine;

public class Pixel {
    final int x, y, red, green, blue, alpha;

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

    public Pixel(int x, int y, int red, int green, int blue) {
        this(x, y, red, green, blue, 255); // Default alpha to 255 (opaque)
    }

    private void validateColorValue(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Color value must be between 0 and 255");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

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
