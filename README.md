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

## Install

### Maven

Add to pom.xml

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

Add it in your root settings.gradle at the end of repositories:

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

### Examples gallery

![]( /resources/lenna_examples.png )
![]( /resources/generative_art_examples.png )

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