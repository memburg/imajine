package imajine

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

/**
 * The Imajine class represents an image with its associated metadata and operations.
 */
class Imajine {

    val width: Int
    val height: Int
    private val bufferedImage: BufferedImage
    private val source: String?
    private val format: String?

    /**
     * Constructs an Imajine object from the specified image file.
     *
     * @param source the path to the image file
     * @throws IOException if an error occurs during reading the file
     */
    constructor(source: String) {
        require(source.isNotEmpty()) { "Source cannot be null or empty" }

        this.source = source
        val file = File(source)

        if (!file.exists() || !file.isFile) {
            throw IOException("File does not exist or is not a file: $source")
        }

        bufferedImage = ImageIO.read(file) ?: throw IOException("Failed to read image from file: $source")

        width = bufferedImage.width
        height = bufferedImage.height
        format = source.substringAfterLast('.', "")
    }

    /**
     * Constructs an Imajine object with the specified width and height.
     *
     * @param width the width of the image
     * @param height the height of the image
     * @throws IllegalArgumentException if the width or height is less than or equal to zero
     */
    constructor(width: Int, height: Int) {
        require(width > 0 && height > 0) { "Width and height must be greater than zero" }

        this.width = width
        this.height = height
        this.source = null
        this.format = null

        bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    }

    /**
     * Saves the image to the specified output file.
     *
     * @param output the path to the output file
     * @throws IOException if an error occurs during writing the file
     */
    fun save(output: String) {
        require(output.isNotEmpty()) { "Output cannot be null or empty" }

        val file = File(output)
        val format = output.substringAfterLast('.', "").uppercase()

        if (!ImageIO.write(bufferedImage, format, file)) {
            throw IOException("Failed to write image to file: $output")
        }
    }

    /**
     * Sets the pixel at the specified location to the specified color.
     *
     * @param pixel the pixel to set
     */
    fun setPixel(pixel: Pixel) {
        val color = Color(pixel.red, pixel.green, pixel.blue)
        bufferedImage.setRGB(pixel.x, pixel.y, color.rgb)
    }

    /**
     * Gets the pixel at the specified location.
     *
     * @param x the x-coordinate of the pixel
     * @param y the y-coordinate of the pixel
     * @return the pixel at the specified location
     */
    fun getPixel(x: Int, y: Int): Pixel {
        val rgb = bufferedImage.getRGB(x, y)
        val r = rgb shr 16 and 0xff
        val g = rgb shr 8 and 0xff
        val b = rgb and 0xff
        return Pixel(x, y, r, g, b)
    }

    /**
     * Returns a string representation of the Imajine object.
     *
     * @return a string representation of the Imajine object
     */
    override fun toString(): String {
        return "{ \"path\": \"$source\", \"format\": \"$format\", \"width\": $width, \"height\": $height }"
    }
}
