package io.mikael.poc

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest(properties = ["""
    spring.autoconfigure.exclude=\
    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
    org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
"""])
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApiTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun readCounter() {
        mockMvc.get("/api/counter") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { json("""{"counter":1}""") }
        }
    }

}
