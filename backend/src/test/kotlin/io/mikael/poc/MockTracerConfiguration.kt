package io.mikael.poc

import io.opentracing.noop.NoopTracerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * The OpenTracing Spring autoconfiguration is kind of half-ass, and throws an exception
 * if you disable it as per documentation, and don't set a specific environmental variable.
 */
@Configuration
class MockTracerConfiguration {
    @Bean
    fun tracer() = NoopTracerFactory.create()!!
}