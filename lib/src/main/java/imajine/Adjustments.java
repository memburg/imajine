package imajine;

public class Adjustments {
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

    private static int adjustColorComponent(int colorComponent, double brightnessFactor) {
        // Adjust the color component based on the brightness factor
        int adjustedComponent = (int) (colorComponent * (1 + brightnessFactor));

        // Ensure the adjusted component is within the valid range (0-255)
        return Math.max(0, Math.min(255, adjustedComponent));
    }

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
