package io.mikael.poc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(Application::class.java, *args)
    }
}

@SpringBootApplication
class Application

@RestController
@RequestMapping("api")
class ApiController {

    var i = 1

    @GetMapping(path = ["counter"], produces = [APPLICATION_JSON_VALUE])
    fun counter() = mapOf("counter" to i++)

}
