package io.mikael.poc

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest(classes = [
    Application::class,
    TestContainerConfiguration::class,
    TestSecurityConfiguration::class
])
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
