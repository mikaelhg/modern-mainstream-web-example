package io.mikael.poc

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.postgresql.PostgreSQLContainer

@TestConfiguration(proxyBeanMethods = false)
class TestContainerConfiguration {
    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer {
        return PostgreSQLContainer("postgres:18-alpine")
    }
}