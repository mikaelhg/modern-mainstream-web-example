package io.mikael.poc

import io.opentracing.Tracer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

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
    fun mainRouter(tracer: Tracer): RouterFunction<ServerResponse> = router {
        GET("/", applicationHandler::frontPage)
        GET("/hello", applicationHandler::helloWorld)
    }.filter { req, next ->
        val sb = tracer.buildSpan("asdf")
        sb.startActive(true).use {
            next.handle(req)
        }
    }

}

@Component
class ApplicationHandler {

    @Value("classpath:/static/index.html")
    private lateinit var indexHtml: Resource

    fun frontPage(req: ServerRequest) : Mono<ServerResponse>
            = ok().contentType(TEXT_HTML).syncBody(indexHtml)

    fun helloWorld(req: ServerRequest) : Mono<ServerResponse>
            = ok().contentType(TEXT_PLAIN).syncBody("Hello, World!")

}
