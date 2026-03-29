package org.misterstorm.eventplatform.ingestionservice.domain.model

import com.fasterxml.jackson.databind.JsonNode
import java.time.Instant

data class EventDTO(
    val eventId: String,
    val eventType: String,
    val timestamp: Instant,
    val source: String,
    val data: JsonNode
)

