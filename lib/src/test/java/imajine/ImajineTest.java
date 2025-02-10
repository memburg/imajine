package imajine;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ImajineTest {
    @Test
    public void someLibraryMethodReturnsTrue() {
        Imajine lenna = new Imajine("lenna.png");
        System.out.println(lenna);
        assertTrue(lenna.someLibraryMethod(), "someLibraryMethod should return 'true'");
    }
}
