package io.mikael.poc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(Application::class.java, *args)
    }
}

@SpringBootApplication(scanBasePackageClasses = [Application::class])
class Application
