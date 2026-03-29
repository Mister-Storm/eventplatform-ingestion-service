package org.misterstorm.eventplatform.ingestionservice.domain.validator

import org.misterstorm.eventplatform.ingestionservice.domain.exception.ValidationException
import org.misterstorm.eventplatform.ingestionservice.domain.exception.error.ErrorCodeEnum
import org.misterstorm.eventplatform.ingestionservice.domain.model.EventDTO
import org.springframework.stereotype.Component

@Component
class EventValidator {

    fun validate(event: EventDTO) {
        if (event.eventId.isBlank()) {
            throw ValidationException(
                errorCode = ErrorCodeEnum.INVALID_EVENT_ID,
                message = "eventId must not be blank"
            )
        }

        if (event.eventType.isBlank()) {
            throw ValidationException(
                errorCode = ErrorCodeEnum.INVALID_EVENT_TYPE,
                message = "eventType must not be blank"
            )
        }

        if (event.data.isEmpty) {
            throw ValidationException(
                errorCode = ErrorCodeEnum.INVALID_EVENT_DATA,
                message = "data must not be empty"
            )
        }
    }
}
