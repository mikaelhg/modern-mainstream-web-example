package io.mikael.poc

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok
import org.springframework.web.servlet.function.router

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(Application::class.java, *args)
    }
}

@SpringBootApplication
class Application

@Configuration
class ApplicationRoutes(val applicationHandler: ApplicationHandler) {

    @Bean
    fun mainRouter() = router {
        GET("/", applicationHandler::frontPage)
        GET("/hello", applicationHandler::helloWorld)
    }

}

@Component
class ApplicationHandler {

    @Value("classpath:/static/index.html")
    private lateinit var indexHtml: Resource

    fun frontPage(req: ServerRequest): ServerResponse
            = ok().contentType(TEXT_HTML).body(indexHtml)

    fun helloWorld(req: ServerRequest): ServerResponse
            = ok().contentType(TEXT_PLAIN).body("Hello, World!")

}
