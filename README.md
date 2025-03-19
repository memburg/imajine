![alt text](/resources/header.png)

<p align="center">
  <br/>
  Imàjine is an easy-to-use image processing library written in Java.
  <br/>
  <br/>
</p>

<div align="center">

![java](https://img.shields.io/badge/java-21.0.5-b07219.svg)
![gradle](https://img.shields.io/badge/gradle-8.11-02303a.svg)
[![Release](https://jitpack.io/v/memburg/imajine.svg)](https://jitpack.io/#memburg/imajine)
![Weekly download statistics](https://jitpack.io/v/memburg/imajine/week.svg)
![Monthly download statistics](https://jitpack.io/v/memburg/imajine/month.svg)
</div>

## Why Imàjine?

Imàjine simplifies image processing in Java by providing an intuitive API for pixel-based operations. Traditional image processing in Java often involves complex and verbose code, making it difficult to perform even simple tasks. Imàjine abstracts away the boilerplate code, allowing developers to focus on the core logic of their applications. With Imàjine, it is easy to load, manipulate, and save images with just a few lines of code, making pixel-based operations more accessible and easy.

## Install

### Maven

Step 1. Add to pom.xml

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Step 2. Add the dependency

```xml
<dependency>
  <groupId>com.github.memburg</groupId>
  <artifactId>imajine</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle

Step 1. Add it in the root settings.gradle at the end of repositories:

```gradle
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}
```

Step 2. Add the dependency

```gradle
dependencies {
  implementation 'com.github.memburg:imajine:1.0.0'
}
```

## Usage

### Loading an Image

```java
import imajine.Imajine;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Imajine image = new Imajine("path/to/image.png");
        System.out.println(image);
    }
}
```

### Creating a New Image

```java
import imajine.Imajine;
import imajine.Pixel;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Imajine image = new Imajine(512, 512);
        Pixel pixel = new Pixel(0, 0, 255, 0, 0); // Red pixel
        image.setPixel(pixel);
        image.save("path/to/output.png");
    }
}
```

### Events Horizon Code

```java
final int IMAGE_SIZE = 512;
Imajine im = new Imajine(IMAGE_SIZE, IMAGE_SIZE);

for (int col = 0; col < IMAGE_SIZE; col++) {
  for (int row = 0; row < IMAGE_SIZE; row++) {
    float x = col, y = row;
    float w = IMAGE_SIZE, h = IMAGE_SIZE;
    float cx = (2 * x - w) / h;
    float cy = (2 * y - w) / h;
    float d = (float) Math.sqrt(cx * cx + cy * cy);

    d -= 0.5;
    d += 0.01 * h / (2 * (y - x) + h - w);
    d = Math.abs(d);

    if (d < 1e-6f) {
        d = 1e-6f;
    }

    d = 0.1f / d;

    int color = (int) (255 * d / (1 + d));
    Pixel p = new Pixel(row, col, color, color, color);

    im.setPixel(p);
  }
}

im.save(EVENTS_HORIZON_PATH);
```

## Examples Gallery

![]( /resources/lenna_examples.png )

> [!Note]
> The image above showcases the classic Lenna image, widely used as a standard test image in the field of image processing. The first panel displays the original Lenna image. Each subsequent panel demonstrates various pixel-based operations applied to the image, illustrating the versatility and power of pixel-level manipulations. These include transformations such as grayscale conversion, color adjustments, and noise addition, among others. This example highlights how fundamental pixel-based techniques can significantly alter and analyze image data, serving as a foundation for more advanced processing tasks.

![]( /resources/generative_art_examples.png )

> [!Note]
> This example demonstrates the creative and artistic potential of the library beyond traditional image processing tasks. From left to right: Grid Distotion, Event Horizon Simulation, Multicolor Gradient, Radial Symmetry Pattern.

## Running Tests

To run the tests, use the following command:

```sh
gradle test
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
