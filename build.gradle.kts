/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.6.1/userguide/tutorial_java_projects.html
 */

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")
    implementation("org.eclipse.jetty:jetty-util-ajax:9.4.32.v20200930")
    implementation("org.eclipse.jetty:jetty-server:9.4.32.v20200930")
    implementation("org.json:json:20200518")
    implementation("org.postgresql:postgresql:42.2.18")

    // Use JUnit test framework
    testImplementation("junit:junit:4.13")
}

application {
    // Define the main class for the application.
    mainClassName = "rps.game.backend.App"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "rps.game.backend.Main"
    }
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter {
            it.name.endsWith("jar") }.map { zipTree(it) }
    })
}