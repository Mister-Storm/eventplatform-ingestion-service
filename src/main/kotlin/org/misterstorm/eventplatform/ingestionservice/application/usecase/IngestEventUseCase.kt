package org.misterstorm.eventplatform.ingestionservice.application.usecase

import org.misterstorm.eventplatform.ingestionservice.domain.model.EventDTO
import org.misterstorm.eventplatform.ingestionservice.domain.port.EventPublisher
import org.misterstorm.eventplatform.ingestionservice.domain.validator.EventValidator
import org.springframework.stereotype.Component

@Component
class IngestEventUseCase(
    private val validator: EventValidator,
    private val eventPublisher: EventPublisher,
) {
    fun execute(event: EventDTO) {
        validator.validate(event)
        eventPublisher.publish(event)
    }
}