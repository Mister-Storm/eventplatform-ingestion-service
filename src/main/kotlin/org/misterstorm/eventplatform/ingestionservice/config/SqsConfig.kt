package org.misterstorm.eventplatform.ingestionservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

@Configuration
class SqsConfig {

    @Bean
    fun sqsClient(
        @Value("\${sqs.endpoint.url}") endpointUrl: String,
        @Value("\${sqs.use.secret}") useSecret: String,
        @Value("\${aws.sqs.key}") key: String,
    ): SqsClient {
        return SqsClient.builder()
            .endpointOverride(URI.create(endpointUrl))
            .region(Region.US_EAST_1)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(key, useSecret)
                )
            )
            .build()
    }
}