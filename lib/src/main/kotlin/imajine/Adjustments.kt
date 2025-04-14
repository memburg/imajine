package imajine

/**
 * The Adjustments class provides various image adjustment operations such as brightness, contrast, thresholding, inversion, desaturation, posterization, and photo filters.
 */
object Adjustments {

    /**
     * Adjusts the brightness of the given image by applying a brightness factor to each pixel.
     *
     * @param image The image to adjust.
     * @param brightnessFactor The factor by which to adjust the brightness. Clamped to [-1.0, 1.0].
     */
    fun adjustBrightness(image: Imajine, brightnessFactor: Double) {
        val clampedFactor = brightnessFactor.coerceIn(-1.0, 1.0)
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = image.getPixel(x, y)
                val r = adjustColorComponent(pixel.red, clampedFactor)
                val g = adjustColorComponent(pixel.green, clampedFactor)
                val b = adjustColorComponent(pixel.blue, clampedFactor)
                image.setPixel(Pixel(x, y, r, g, b))
            }
        }
    }

    /**
     * Adjusts the contrast of the given image by applying a contrast factor.
     *
     * @param image The image to adjust.
     * @param contrastFactor The contrast adjustment factor. Clamped to [-1.0, 1.0].
     */
    fun adjustContrast(image: Imajine, contrastFactor: Double) {
        val clampedFactor = contrastFactor.coerceIn(-1.0, 1.0)
        val scalingFactor = (1.0 + clampedFactor) / (1.0 - clampedFactor)
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = image.getPixel(x, y)
                val r = adjustContrastComponent(pixel.red, scalingFactor)
                val g = adjustContrastComponent(pixel.green, scalingFactor)
                val b = adjustContrastComponent(pixel.blue, scalingFactor)
                image.setPixel(Pixel(x, y, r, g, b))
            }
        }
    }

    /**
     * Applies a threshold filter to the given image.
     *
     * @param image The image to adjust.
     * @param threshold The threshold value (0-255).
     */
    fun threshold(image: Imajine, threshold: Int) {
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = image.getPixel(x, y)
                val gray = (0.299 * pixel.red + 0.587 * pixel.green + 0.114 * pixel.blue).toInt()
                val value = if (gray < threshold) 0 else 255
                image.setPixel(Pixel(x, y, value, value, value))
            }
        }
    }

    /**
     * Inverts the colors of the given image.
     *
     * @param image The image to adjust.
     */
    fun invert(image: Imajine) {
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = image.getPixel(x, y)
                val r = 255 - pixel.red
                val g = 255 - pixel.green
                val b = 255 - pixel.blue
                image.setPixel(Pixel(x, y, r, g, b))
            }
        }
    }

    /**
     * Desaturates the colors of the given image by converting each pixel to grayscale.
     *
     * @param image The image to adjust.
     */
    fun desaturate(image: Imajine) {
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = image.getPixel(x, y)
                val gray = (0.299 * pixel.red + 0.587 * pixel.green + 0.114 * pixel.blue).toInt()
                image.setPixel(Pixel(x, y, gray, gray, gray))
            }
        }
    }

    /**
     * Posterizes the colors of the given image by reducing the number of color levels.
     *
     * @param image The image to adjust.
     * @param levels The number of color levels (greater than 1).
     */
    fun posterize(image: Imajine, levels: Int) {
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = image.getPixel(x, y)
                val r = posterizeComponent(pixel.red, levels)
                val g = posterizeComponent(pixel.green, levels)
                val b = posterizeComponent(pixel.blue, levels)
                image.setPixel(Pixel(x, y, r, g, b))
            }
        }
    }

    /**
     * Applies a photo filter to the given image by blending each pixel with a specified color.
     *
     * @param image The image to adjust.
     * @param r The red component of the filter color (0-255).
     * @param g The green component of the filter color (0-255).
     * @param b The blue component of the filter color (0-255).
     * @param density The blending density (0.0 to 1.0).
     */
    fun applyPhotoFilter(image: Imajine, r: Int, g: Int, b: Int, density: Float) {
        val clampedDensity = density.coerceIn(0.0f, 1.0f)
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = image.getPixel(x, y)
                val alteredR = blendColor(pixel.red, r, clampedDensity)
                val alteredG = blendColor(pixel.green, g, clampedDensity)
                val alteredB = blendColor(pixel.blue, b, clampedDensity)
                image.setPixel(Pixel(x, y, alteredR, alteredG, alteredB))
            }
        }
    }

    private fun adjustColorComponent(colorComponent: Int, brightnessFactor: Double): Int {
        val adjusted = (colorComponent * (1 + brightnessFactor)).toInt()
        return adjusted.coerceIn(0, 255)
    }

    private fun adjustContrastComponent(colorComponent: Int, scalingFactor: Double): Int {
        val normalized = colorComponent - 128
        val adjusted = (normalized * scalingFactor).toInt() + 128
        return adjusted.coerceIn(0, 255)
    }

    private fun posterizeComponent(colorComponent: Int, levels: Int): Int {
        val interval = 256 / levels
        return (colorComponent / interval) * interval
    }

    private fun blendColor(original: Int, filter: Int, density: Float): Int {
        return (original * (1 - density) + filter * density).toInt()
    }
}
