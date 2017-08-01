package io.mikael.poc

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(Application::class.java, *args)
    }
}

@SpringBootApplication
class Application

@Configuration
class ApplicationRoutes {

    @Value("classpath:/static/index.html")
    private lateinit var indexHtml: Resource

    @Bean
    fun mainRouter(): RouterFunction<ServerResponse> = router {
        GET("/") {
            // Workaround for https://github.com/spring-projects/spring-boot/issues/9785
            ok().contentType(TEXT_HTML).syncBody(indexHtml)
        }
        GET("/hello") {
            ok().contentType(TEXT_PLAIN).syncBody("Hello, World!")
        }
    }.filter { req, next ->
        // log begin with jaeger
        next.handle(req)
        // log end with jaeger
    }

}
