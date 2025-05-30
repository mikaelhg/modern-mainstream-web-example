package io.mikael.poc

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class ApiController(val counterService: CounterService) {

    @GetMapping(path = ["counter"], produces = [APPLICATION_JSON_VALUE])
    fun counter() = mapOf("counter" to counterService.nextValue())

}
