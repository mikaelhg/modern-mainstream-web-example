package io.mikael.poc

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestContainerConfiguration::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ApiTests(private val mockMvc: MockMvc) {

    @Test
    fun readCounter() {
        mockMvc.get("/api/counter") {
            accept = MediaType.APPLICATION_JSON
            with(oidcLogin())
        }.andExpect {
            status { isOk() }
            content { jsonPath("$.counter") { value(1) } }
        }
    }
}
