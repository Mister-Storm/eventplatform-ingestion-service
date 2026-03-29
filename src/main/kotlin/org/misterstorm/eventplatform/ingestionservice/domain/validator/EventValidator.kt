package org.misterstorm.eventplatform.ingestionservice.domain.validator

import org.misterstorm.eventplatform.ingestionservice.domain.model.EventDTO
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class EventValidator {

    fun validate(event: EventDTO) {
        require(event.eventId.isNotBlank()) { "eventId is required" }
        require(event.eventType.isNotBlank()) { "eventType is required" }
        require(event.timestamp.isBefore(Instant.now().plusSeconds(300))) {
            "timestamp is too far in the future"
        }
        require(!event.data.isEmpty) { "data must not be empty" }
    }

}