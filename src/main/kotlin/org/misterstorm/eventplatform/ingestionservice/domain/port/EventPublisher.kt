package org.misterstorm.eventplatform.ingestionservice.domain.port

import org.misterstorm.eventplatform.ingestionservice.domain.model.EventDTO

fun interface EventPublisher {
    fun publish(event: EventDTO)
}