import com.github.gradle.node.pnpm.task.PnpmTask

plugins {
    java
    id("com.github.node-gradle.node") version "7.1.0"
}

node {
    version = "24.8.0"
    pnpmVersion = "10.17.1"
    download = true
}

val buildTask = tasks.register<PnpmTask>("buildWebapp") {
    pnpmCommand.set(listOf("vite"))
    args.set(listOf("build", "--outDir", project.layout.buildDirectory.get().asFile.resolve("webapp/static").absolutePath))
    dependsOn(tasks.named("pnpmInstall"))
    outputs.dir(project.layout.buildDirectory.dir("webapp"))
}

sourceSets {
    main {
        resources {
            // This makes the processResources task automatically depend on the buildWebapp one
            srcDir(buildTask)
        }
    }
}