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

![](/resources/lenna_examples.png)