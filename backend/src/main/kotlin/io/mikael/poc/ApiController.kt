package io.mikael.poc

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class ApiController {

    var i = 1

    @GetMapping(path = ["counter"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun counter() = mapOf("counter" to i++)

}