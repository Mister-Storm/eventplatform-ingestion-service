package org.misterstorm.eventplatform.ingestionservice

import org.testcontainers.utility.DockerImageName
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest

@Testcontainers
class SqsIntegrationTest {

    companion object {

        @Container
        val localstack = LocalStackContainer(
            DockerImageName.parse("localstack/localstack:3.0")
                .asCompatibleSubstituteFor("localstack/localstack")
        )
            .withServices(LocalStackContainer.Service.SQS)
    }

    @Test
    fun `should send message to SQS`() {

        val sqsClient = SqsClient.builder()
            .endpointOverride(
                localstack.getEndpointOverride(LocalStackContainer.Service.SQS)
            )
            .region(Region.of(localstack.region))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(
                        localstack.accessKey,
                        localstack.secretKey
                    )
                )
            )
            .build()

        val queueUrl = sqsClient.createQueue(
            CreateQueueRequest.builder()
                .queueName("test-queue")
                .build()
        ).queueUrl()
        sqsClient.sendMessage {
            it.queueUrl(queueUrl)
                .messageBody("test-message")
        }

        val messages = sqsClient.receiveMessage(
            ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(1)
                .build()
        )

        assert(messages.messages().isNotEmpty())
    }
}