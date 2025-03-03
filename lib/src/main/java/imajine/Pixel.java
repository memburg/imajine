package imajine;

public class Pixel {
    int x, y, red, green, blue, alpha;

    public Pixel(int x, int y, int red, int green, int blue, int alpha) {
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Pixel(int x, int y, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
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
        return String.format("{ \"x\": %d, \"y\": %d, \"red\": %d, \"green\": %d, \"blue\": %d }",
                x,
                y,
                red,
                green,
                blue);
    }
}
