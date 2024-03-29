import com.github.gradle.node.npm.task.NpxTask

plugins {
    java
    id("com.github.node-gradle.node") version "3.5.1"
}

node {
    version.set("18.14.2")
    download.set(true)
}

val buildTask = tasks.register<NpxTask>("buildWebapp") {
    command.set("vite")
    args.set(listOf("build", "--outDir", "${project.buildDir}/webapp/static"))
    dependsOn(tasks.npmInstall)
    outputs.dir("${project.buildDir}/webapp")
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
