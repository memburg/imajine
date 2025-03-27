package imajine;

public class Adjustments {
    /**
     * Adjusts the brightness of the given image by applying a brightness factor to
     * each pixel.
     * The brightness factor should be in the range [-1.0, 1.0], where:
     * <ul>
     * <li>-1.0 represents the darkest adjustment (completely black).</li>
     * <li>0.0 represents no adjustment (original brightness).</li>
     * <li>1.0 represents the brightest adjustment (maximum brightness).</li>
     * </ul>
     *
     * @param image            The image to adjust. This must be an instance of
     *                         {@code Imajine}.
     * @param brightnessFactor The factor by which to adjust the brightness. Values
     *                         outside the range [-1.0, 1.0] will be clamped to this
     *                         range.
     * @throws IllegalArgumentException if the image is null.
     */
    public static void adjustBrightness(Imajine image, double brightnessFactor) {
        // Ensure brightnessFactor is within the valid range
        brightnessFactor = Math.max(-1.0, Math.min(1.0, brightnessFactor));

        // Iterate over each pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Get the current pixel
                Pixel pixel = image.getPixel(x, y);

                // Adjust the RGB values based on the brightness factor
                int r = adjustColorComponent(pixel.getRed(), brightnessFactor);
                int g = adjustColorComponent(pixel.getGreen(), brightnessFactor);
                int b = adjustColorComponent(pixel.getBlue(), brightnessFactor);

                // Create a new pixel with the adjusted RGB values
                Pixel adjustedPixel = new Pixel(x, y, r, g, b);

                // Set the adjusted pixel back to the image
                image.setPixel(adjustedPixel);
            }
        }
    }

    /**
     * Adjusts the contrast of the given image by applying a contrast factor.
     * The contrast factor determines the intensity of the contrast adjustment,
     * where values range from -1.0 (minimum contrast) to 1.0 (maximum contrast).
     *
     * <p>
     * The method iterates over each pixel in the image, adjusts its RGB
     * components based on the contrast factor, and updates the image with the
     * modified pixel values.
     * </p>
     *
     * @param image          The image to adjust. Must be an instance of
     *                       {@code Imajine}.
     * @param contrastFactor The contrast adjustment factor. Values should be
     *                       between -1.0 and 1.0. Values outside this range
     *                       will be clamped to the nearest valid value.
     * @throws IllegalArgumentException if the image is null.
     */
    public static void adjustContrast(Imajine image, double contrastFactor) {
        // Ensure contrastFactor is within a reasonable range (e.g., -1.0 to 1.0)
        contrastFactor = Math.max(-1.0, Math.min(1.0, contrastFactor));

        // Precompute the scaling factor for contrast adjustment
        double scalingFactor = (1.0 + contrastFactor) / (1.0 - contrastFactor);

        // Iterate over each pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Get the current pixel
                Pixel pixel = image.getPixel(x, y);

                // Adjust the RGB values for contrast
                int r = adjustContrastComponent(pixel.getRed(), scalingFactor);
                int g = adjustContrastComponent(pixel.getGreen(), scalingFactor);
                int b = adjustContrastComponent(pixel.getBlue(), scalingFactor);

                // Create a new pixel with the adjusted RGB values
                Pixel adjustedPixel = new Pixel(x, y, r, g, b);

                // Set the adjusted pixel back to the image
                image.setPixel(adjustedPixel);
            }
        }
    }

    /**
     * Applies a threshold filter to the given image. Each pixel in the image is
     * converted to grayscale and compared against the specified threshold value. If
     * the grayscale value of a pixel is less than the threshold, the pixel is set
     * to black (0, 0, 0). Otherwise, the pixel is set to white (255, 255, 255).
     *
     * @param image     The image to which the threshold filter will be applied. The
     *                  image should support methods for retrieving and setting
     *                  pixel values.
     * @param threshold The threshold value used to determine whether a pixel is set
     *                  to black or white. This value should be in the range [0,
     *                  255].
     */
    public static void threshold(Imajine image, int threshold) {
        // Iterate over each pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Get the current pixel
                Pixel pixel = image.getPixel(x, y);

                // Calculate the grayscale value of the pixel
                int gray = (int) (0.299 * pixel.getRed() + 0.587 * pixel.getGreen() + 0.114 * pixel.getBlue());

                // Set the pixel to black or white based on the threshold
                int value = gray < threshold ? 0 : 255;

                // Create a new pixel with the thresholded value
                Pixel thresholdedPixel = new Pixel(x, y, value, value, value);

                // Set the thresholded pixel back to the image
                image.setPixel(thresholdedPixel);
            }
        }
    }

    /**
     * Inverts the colors of the given image. Each pixel's RGB values are inverted
     * by subtracting them from 255.
     *
     * @param image The image to be inverted. This must be an instance of
     *              {@code Imajine}.
     */
    public static void invert(Imajine image) {
        // Iterate over each pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Get the current pixel
                Pixel pixel = image.getPixel(x, y);

                // Invert the RGB values
                int r = 255 - pixel.getRed();
                int g = 255 - pixel.getGreen();
                int b = 255 - pixel.getBlue();

                // Create a new pixel with the inverted RGB values
                Pixel invertedPixel = new Pixel(x, y, r, g, b);

                // Set the inverted pixel back to the image
                image.setPixel(invertedPixel);
            }
        }
    }

    /**
     * Desaturates the colors of the given image by converting each pixel to its
     * grayscale equivalent. The grayscale value is calculated using the luminosity
     * method.
     *
     * @param image The image to be desaturated. This must be an instance of
     *              {@code Imajine}.
     */
    public static void desaturate(Imajine image) {
        // Iterate over each pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Get the current pixel
                Pixel pixel = image.getPixel(x, y);

                // Calculate the grayscale value of the pixel
                int gray = (int) (0.299 * pixel.getRed() + 0.587 * pixel.getGreen() + 0.114 * pixel.getBlue());

                // Create a new pixel with the grayscale value
                Pixel desaturatedPixel = new Pixel(x, y, gray, gray, gray);

                // Set the desaturated pixel back to the image
                image.setPixel(desaturatedPixel);
            }
        }
    }

    /**
     * Posterizes the colors of the given image by reducing the number of color
     * levels in each RGB channel. The number of levels is specified by the
     * {@code levels} parameter.
     *
     * @param image  The image to be posterized. This must be an instance of
     *               {@code Imajine}.
     * @param levels The number of color levels to use for posterization. This
     *               should be a positive integer greater than 1.
     */
    public static void posterize(Imajine image, int levels) {
        // Iterate over each pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Get the current pixel
                Pixel pixel = image.getPixel(x, y);

                // Posterize the RGB values
                int r = posterizeComponent(pixel.getRed(), levels);
                int g = posterizeComponent(pixel.getGreen(), levels);
                int b = posterizeComponent(pixel.getBlue(), levels);

                // Create a new pixel with the posterized RGB values
                Pixel posterizedPixel = new Pixel(x, y, r, g, b);

                // Set the posterized pixel back to the image
                image.setPixel(posterizedPixel);
            }
        }
    }

    /**
     * Posterizes a single color component by reducing its number of levels.
     *
     * @param colorComponent The color component to posterize (0-255).
     * @param levels         The number of levels to use for posterization.
     * @return The posterized color component.
     */
    private static int posterizeComponent(int colorComponent, int levels) {
        // Divide the color component into 4 equal intervals
        int interval = 256 / levels;

        // Determine the posterized value based on the interval
        int value = (colorComponent / interval) * interval;

        // Ensure the value is within the valid range (0-255)
        return Math.max(0, Math.min(255, value));
    }

    /**
     * Adjusts a single color component based on the brightness factor.
     *
     * @param colorComponent   The color component to adjust (0-255).
     * @param brightnessFactor The brightness adjustment factor (-1.0 to 1.0).
     * @return The adjusted color component.
     */
    private static int adjustColorComponent(int colorComponent, double brightnessFactor) {
        // Adjust the color component based on the brightness factor
        int adjustedComponent = (int) (colorComponent * (1 + brightnessFactor));

        // Ensure the adjusted component is within the valid range (0-255)
        return Math.max(0, Math.min(255, adjustedComponent));
    }

    /**
     * Adjusts a single color component based on the contrast scaling factor.
     *
     * @param colorComponent The color component to adjust (0-255).
     * @param scalingFactor  The contrast scaling factor.
     * @return The adjusted color component.
     */
    private static int adjustContrastComponent(int colorComponent, double scalingFactor) {
        // Normalize the color component to the range [-128, 128]
        double normalizedComponent = colorComponent - 128;

        // Apply the contrast scaling factor
        double adjustedComponent = normalizedComponent * scalingFactor;

        // Denormalize the color component back to the range [0, 255]
        int result = (int) (adjustedComponent + 128);

        // Clamp the result to ensure it stays within the valid range
        return Math.max(0, Math.min(255, result));
    }
}
