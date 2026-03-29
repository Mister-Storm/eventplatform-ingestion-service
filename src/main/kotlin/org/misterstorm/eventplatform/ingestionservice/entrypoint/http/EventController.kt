package org.misterstorm.eventplatform.ingestionservice.entrypoint.http

import org.misterstorm.eventplatform.ingestionservice.application.usecase.IngestEventUseCase
import org.misterstorm.eventplatform.ingestionservice.domain.model.EventDTO
import org.misterstorm.eventplatform.ingestionservice.domain.validator.EventValidator
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/events")
class EventController(private val eventUseCase: IngestEventUseCase) {

    private val logger = LoggerFactory.getLogger(EventController::class.java)

    @PostMapping
    fun receiveEvent(@RequestBody event: EventDTO): ResponseEntity<Void> {
        MDC.put("eventId", event.eventId)
        MDC.put("eventType", event.eventType)

        try {
            logger.info("event received")
            eventUseCase.execute(event)
            logger.info("event validated successfully")
            return ResponseEntity.accepted().build()
        } finally {
            MDC.clear()
        }
    }
}