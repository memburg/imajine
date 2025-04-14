package imajine

/**
 * The Pixel class represents a pixel with its coordinates and color values.
 */
data class Pixel(
    val x: Int,
    val y: Int,
    val red: Int,
    val green: Int,
    val blue: Int,
) {
    init {
        validateColorValue(red)
        validateColorValue(green)
        validateColorValue(blue)
    }

    /**
     * Validates that the color value is within the range 0-255.
     *
     * @param value the color value to validate
     * @throws IllegalArgumentException if the color value is out of range (0-255)
     */
    private fun validateColorValue(value: Int) {
        require(value in 0..255) { "Color value must be between 0 and 255" }
    }

    /**
     * Returns a string representation of the pixel.
     *
     * @return a string representation of the pixel
     */
    override fun toString(): String = "{ \"x\": $x, \"y\": $y, \"red\": $red, \"green\": $green, \"blue\": $blue }"
}
