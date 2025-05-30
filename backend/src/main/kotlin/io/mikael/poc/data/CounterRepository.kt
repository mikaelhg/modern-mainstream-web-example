package io.mikael.poc.data

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CounterRepository : CrudRepository<Counter, Long> {

    @Query("""
        UPDATE counters
        SET counter_value = counter_value + 1
        WHERE name = :name 
        RETURNING counter_value
    """)
    fun nextValue(name: String): Long

}
