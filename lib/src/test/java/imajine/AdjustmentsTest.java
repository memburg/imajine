package imajine;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

public class AdjustmentsTest {
    final String ROOT_DIR = System.getProperty("user.dir");
    final String LENNA_PATH = ROOT_DIR + "/src/test/resources/lenna.png";
    final String LENNA_POSBR_PATH = ROOT_DIR + "/src/test/output/lenna_posbr.png";
    final String LENNA_NEGBR_PATH = ROOT_DIR + "/src/test/output/lenna_negbr.png";
    final String LENNA_POSCO_PATH = ROOT_DIR + "/src/test/output/lenna_posco.png";
    final String LENNA_NEGCO_PATH = ROOT_DIR + "/src/test/output/lenna_negco.png";
    final String LENNA_THRES_PATH = ROOT_DIR + "/src/test/output/lenna_thres.png";
    final String LENNA_INVER_PATH = ROOT_DIR + "/src/test/output/lenna_inver.png";
    final String LENNA_DESAT_PATH = ROOT_DIR + "/src/test/output/lenna_desat.png";
    final String LENNA_POSTE_PATH = ROOT_DIR + "/src/test/output/lenna_poste.png";
    final String LENNA_PHOTO_FILTER_PATH = ROOT_DIR + "/src/test/output/lenna_photo_filter.png";

    @Test
    public void shouldIncreaseBrightness() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.adjustBrightness(lenna, 0.34);
        lenna.save(LENNA_POSBR_PATH);

        Assert.assertTrue(new File(LENNA_POSBR_PATH).isFile());
    }

    @Test
    public void shouldDecreaseBrightness() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.adjustBrightness(lenna, -0.34);
        lenna.save(LENNA_NEGBR_PATH);

        Assert.assertTrue(new File(LENNA_NEGBR_PATH).isFile());
    }

    @Test
    public void shouldIncreaseContrast() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.adjustContrast(lenna, 0.34);
        lenna.save(LENNA_POSCO_PATH);

        Assert.assertTrue(new File(LENNA_POSCO_PATH).isFile());
    }

    @Test
    public void shouldDecreaseContrast() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.adjustContrast(lenna, -0.34);
        lenna.save(LENNA_NEGCO_PATH);

        Assert.assertTrue(new File(LENNA_NEGCO_PATH).isFile());
    }

    @Test
    public void shouldReturnThresholdImage() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.threshold(lenna, 100);
        lenna.save(LENNA_THRES_PATH);

        Assert.assertTrue(new File(LENNA_THRES_PATH).isFile());
    }

    @Test
    public void shouldReturnInvertImageColor() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.invert(lenna);
        lenna.save(LENNA_INVER_PATH);

        Assert.assertTrue(new File(LENNA_INVER_PATH).isFile());
    }

    @Test
    public void shouldReturnDesaturatedImage() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.desaturate(lenna);
        lenna.save(LENNA_DESAT_PATH);

        Assert.assertTrue(new File(LENNA_DESAT_PATH).isFile());
    }

    @Test
    public void shouldReturnPosterizedImage() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.posterize(lenna, 4);
        lenna.save(LENNA_POSTE_PATH);

        Assert.assertTrue(new File(LENNA_POSTE_PATH).isFile());
    }

    @Test
    public void shouldReturnColoredImage() throws IOException {
        Imajine lenna = new Imajine(LENNA_PATH);
        Adjustments.applyPhotoFilter(lenna, 0, 50, 9, 0.5f);
        lenna.save(LENNA_PHOTO_FILTER_PATH);

        Assert.assertTrue(new File(LENNA_PHOTO_FILTER_PATH).isFile());
    }
}
