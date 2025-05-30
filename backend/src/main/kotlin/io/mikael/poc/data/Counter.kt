package io.mikael.poc.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("counter")
data class Counter(
    @Id val id: Long,
    val name: String,
    val value: Long
)
