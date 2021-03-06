plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
    application
}

application {
    mainClass.set("ru.sal4i.sal4ibot.Sal4iBot")
}

group = "ru.sal4i"
version = "1.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-alpha.9")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.slf4j:slf4j-log4j12:1.7.36")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("commons-io:commons-io:2.11.0")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}