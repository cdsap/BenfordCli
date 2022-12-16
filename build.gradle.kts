import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.1.1")
    implementation( "org.jetbrains.lets-plot:lets-plot-image-export:2.5.1")
    implementation("org.slf4j:slf4j-simple:1.7.9")
    implementation("com.github.ajalt.clikt:clikt:3.5.0")
}


tasks.register<Jar>("fatJar") {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    group = "Build"

    description = "Produces a fatJar for the binary"

    dependsOn(project.tasks.named("jar"))

    manifest {
        attributes("Main-Class" to "org.gradle.eoy.hackathon.BenfordCliKt")
    }

    inputs.files(project.configurations.getByName("runtimeClasspath"))
    from(project.configurations.getByName("runtimeClasspath").map {
        if (it.isDirectory) it else project.zipTree(it)
    })
    with(project.tasks["jar"] as CopySpec)
}

 open class FatBinaryTask : DefaultTask() {

    @InputFile
    val fatJar: RegularFileProperty = project.objects.fileProperty()

    @OutputFile
    val outputFile: RegularFileProperty = project.objects.fileProperty()

    @TaskAction
    fun buildBinary() {
        val fileJar = fatJar.get()
        outputFile.get().asFile.apply {
            parentFile.mkdirs()
            delete()
            writeText("#!/bin/sh\n\nexec java \$JAVA_OPTS -jar \$0 \"\$@\"\n\n")
            appendBytes(fileJar.asFile.readBytes())
            setExecutable(true)
        }
    }
 }

tasks.register<FatBinaryTask>("fatBinary") {
    group = "Build"
    description = "Produces a executable binary for the Reporter"

    dependsOn(tasks.named("fatJar"))

    fatJar.set((tasks.named("fatJar") as TaskProvider<Jar>).get().archiveFile)
    outputFile.set(File("${project.buildDir}/libs/benford"))
}
