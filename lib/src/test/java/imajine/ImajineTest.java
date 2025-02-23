package imajine;

import java.io.IOException;

import javax.script.ScriptEngine;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ImajineTest {
    private ScriptEngine engine;

    @Test
    public void someLibraryMethodReturnsTrue() throws IOException {
        String rootDir = System.getProperty("user.dir");
        Imajine lenna = new Imajine(rootDir + "/src/test/resources/lenna.png");

        String lennaStr = lenna.toString();
        assertTrue(lenna.someLibraryMethod(), "someLibraryMethod should return 'true'");
    }
}
