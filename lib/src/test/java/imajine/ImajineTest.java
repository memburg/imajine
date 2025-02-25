package imajine;

import java.io.IOException;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ImajineTest {
    String rootDir = System.getProperty("user.dir");
    String lennaPath = rootDir + "/src/test/resources/lenna.png";

    @Test
    public void someLibraryMethodReturnsTrue() throws IOException {
        Imajine lenna = new Imajine(lennaPath);
        String lennaStr = lenna.toString();

        assertTrue(JsonParser.isValidJson(lennaStr), "lenna.toString() is not a valid JSON string");
    }
}
