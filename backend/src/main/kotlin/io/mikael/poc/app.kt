package io.mikael.poc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router

@SpringBootApplication
class Application

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(Application::class.java, *args)
    }
}

@Configuration
class ApplicationRoutes {

    @Bean
    fun mainRouter() = router {
        GET("/hello") {
            ok().contentType(TEXT_PLAIN).syncBody("Hello, World!")
        }
    }

}
