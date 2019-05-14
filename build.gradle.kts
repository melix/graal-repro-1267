plugins {
    `java-library`
    id("com.palantir.graal") version "0.3.0-27-gf54868f"
}

repositories {
    jcenter()
}

graal {
   mainClass("graal.network.repro.Library")
   outputName("repro")
   graalVersion("1.0.0-rc16")
}

tasks.withType<JavaCompile>().configureEach {
    targetCompatibility = "1.8"
    sourceCompatibility = "1.8"
}