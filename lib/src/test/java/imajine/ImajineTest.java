package imajine;

import java.io.IOException;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ImajineTest {
    String rootDir = System.getProperty("user.dir");
    String lennaPath = rootDir + "/src/test/resources/lenna.png";

    @Test
    public void toStringShouldBeValidJSON() throws IOException {
        Imajine lenna = new Imajine(lennaPath);
        String lennaStr = lenna.toString();

        assertTrue(JsonParser.isValidJson(lennaStr), "lenna.toString() is not a valid JSON string");
    }

    @Test
    public void pixelCanBeSet() throws IOException {
        Imajine lenna = new Imajine(lennaPath);
        Pixel pixel = new Pixel(42, 84, 123, 72, 9);

        lenna.setPixel(pixel);

        Pixel p = lenna.getPixel(42, 84);

        assertEquals(p.toString(), pixel.toString(), "Pixel was not properly set");
    }
}
