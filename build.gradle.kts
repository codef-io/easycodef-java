plugins {
    java
}

group = "io.codef.api"
version = "1.0.7"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    implementation("commons-codec:commons-codec:1.14")
    implementation("commons-io:commons-io:2.7")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:all")
    options.isDeprecation = true
}