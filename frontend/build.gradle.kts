import com.github.gradle.node.pnpm.task.PnpmTask

plugins {
    java
    id("com.github.node-gradle.node") version "7.1.0"
}

node {
    version = "24.16.0"
    pnpmVersion = "11.5.1"
    npmVersion = "11.16.0"
    download = true
}

val buildTask = tasks.register<PnpmTask>("buildWebapp") {
    description = "build the web app with pnpm"
    val webappDir = project.layout.buildDirectory.dir("webapp")
    val staticDir = webappDir.map { it.dir("static") }
    pnpmCommand.set(listOf("vite"))
    args.set(staticDir.map { dir -> listOf("build", "--outDir", dir.asFile.absolutePath) })
    outputs.dir(webappDir)
    dependsOn(tasks.named("pnpmInstall"))
}

sourceSets {
    main {
        resources {
            // This makes the processResources task automatically depend on the buildWebapp one
            srcDir(buildTask)
        }
    }
}
