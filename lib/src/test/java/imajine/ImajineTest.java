package imajine;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ImajineTest {
    final String ROOT_DIR = System.getProperty("user.dir");
    final String LENNA_PATH = ROOT_DIR + "/src/test/resources/lenna.png";
    final String LENNA_OUTPUT_PATH = ROOT_DIR + "/src/test/resources/lenna.png";
    final String EVENTS_HORIZON_PATH = ROOT_DIR + "/src/test/resources/events_horizon.png";

    @Test
    public void toStringShouldBeValidJSON() throws IOException {
        final String EXPECTED_STRING = "{ \"path\": \""
                + ROOT_DIR
                + "/src/test/resources/lenna.png\", \"format\": \"png\", \"width\": 512, \"height\": 512 }";

        Imajine lenna = new Imajine(LENNA_PATH);
        Assert.assertEquals(lenna.toString(), EXPECTED_STRING);
    }

    @Test
    public void pixelCanBeSet() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Pixel pixel = new Pixel(42, 84, 123, 72, 9);

        lenna.setPixel(pixel);

        Pixel p = lenna.getPixel(42, 84);

        assertEquals(p.toString(), pixel.toString(), "Pixel was not properly set");
    }

    @Test
    public void lennaFilter() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);

        for (int i = 0; i < lenna.getWidth(); i++) {
            for (int j = 0; j < lenna.getWidth(); j++) {
                Pixel originalPixel = lenna.getPixel(i, j);
                Pixel negativePixel = new Pixel(
                        originalPixel.getX(),
                        originalPixel.getY(),
                        originalPixel.getBlue(),
                        originalPixel.getBlue(),
                        originalPixel.getBlue());

                lenna.setPixel(negativePixel);
            }
        }

        lenna.save(LENNA_OUTPUT_PATH);
    }

    @Test
    public void eventsHorizon() throws IOException {
        final int IMAGE_SIZE = 2000;
        Imajine im = new Imajine(IMAGE_SIZE, IMAGE_SIZE);

        for (int col = 0; col < IMAGE_SIZE; col++) {
            for (int row = 0; row < IMAGE_SIZE; row++) {
                float x = col, y = row;
                float w = IMAGE_SIZE, h = IMAGE_SIZE;
                float cx = (2 * x - w) / h;
                float cy = (2 * y - w) / h;
                float d = (float) Math.sqrt(cx * cx + cy * cy);

                d -= 0.5;
                d += 0.01 * h / (2 * (y - x) + h - w);
                d = Math.abs(d);

                if (d < 1e-6f) {
                    d = 1e-6f;
                }

                d = 0.1f / d;

                int color = (int) (255 * d / (1 + d));
                Pixel p = new Pixel(row, col, color, color, color);

                im.setPixel(p);
            }
        }
        im.save(EVENTS_HORIZON_PATH);
    }
}
