package org.misterstorm.eventplatform.ingestionservice.infrastructure.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import org.misterstorm.eventplatform.ingestionservice.domain.model.EventDTO
import org.misterstorm.eventplatform.ingestionservice.domain.port.EventPublisher
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

@Component
class SqsEventPublisher(
    private val sqsClient: SqsClient,
    private val objectMapper: ObjectMapper,
    @Value("\${aws.sqs.queue-url}") private val queueUrl: String
) : EventPublisher {

    override fun publish(event: EventDTO) {
        val message = objectMapper.writeValueAsString(event)

        val request = SendMessageRequest.builder()
            .queueUrl(queueUrl)
            .messageBody(message)
            .build()

        sqsClient.sendMessage(request)
    }
}