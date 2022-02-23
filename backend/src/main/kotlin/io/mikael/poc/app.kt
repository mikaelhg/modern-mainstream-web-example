package io.mikael.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        runApplication<Application>(*args)
    }
}

@SpringBootApplication(scanBasePackageClasses = [Application::class])
class Application
