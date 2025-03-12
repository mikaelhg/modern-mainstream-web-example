import com.github.gradle.node.npm.task.NpxTask

plugins {
    java
    id("com.github.node-gradle.node") version "7.1.0"
}

node {
    version = "22.14.0"
    download = true
}

val buildTask = tasks.register<NpxTask>("buildWebapp") {
    val buildDir = project.layout.buildDirectory.get().asFile.absolutePath
    command = "vite"
    args.set(listOf("build", "--outDir", "${buildDir}/webapp/static"))
    dependsOn(tasks.npmInstall)
    outputs.dir("${buildDir}/webapp")
}

sourceSets {
    java {
        main {
            resources {
                // This makes the processResources task automatically depend on the buildWebapp one
                srcDir(buildTask)
            }
        }
    }
}
