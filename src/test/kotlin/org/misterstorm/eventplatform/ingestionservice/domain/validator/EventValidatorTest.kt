package org.misterstorm.eventplatform.ingestionservice.domain.validator

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.misterstorm.eventplatform.ingestionservice.domain.exception.ValidationException
import org.misterstorm.eventplatform.ingestionservice.domain.model.EventDTO
import java.time.Instant

class EventValidatorTest {

    private val validator = EventValidator()

    @Test
    fun `should throw exception when eventId is blank`() {
        val event = EventDTO(
            eventId = "",
            eventType = "TEST",
            timestamp = Instant.now(),
            source = "test",
            data = ObjectMapper().createObjectNode()
        )

        val ex = assertThrows<ValidationException> {
            validator.validate(event)
        }

        assertEquals("INVALID_EVENT_ID", ex.errorCode.name)
    }
}