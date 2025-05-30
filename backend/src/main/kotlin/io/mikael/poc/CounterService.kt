package io.mikael.poc

import io.mikael.poc.data.CounterRepository
import org.springframework.stereotype.Service

@Service
class CounterService(val counterRepository: CounterRepository) {

    fun nextValue(): Long {
        return counterRepository.nextValue("default")
    }
}