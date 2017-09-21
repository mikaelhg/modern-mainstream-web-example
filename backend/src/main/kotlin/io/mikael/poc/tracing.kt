package io.mikael.poc

import com.uber.jaeger.Configuration as JaegerConfiguration
import com.uber.jaeger.samplers.ProbabilisticSampler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TracingConfiguration {

    @Bean
    fun jaegerTracer()
            = JaegerConfiguration("spring-boot",
                JaegerConfiguration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
                JaegerConfiguration.ReporterConfiguration())
                .tracer!!

}
