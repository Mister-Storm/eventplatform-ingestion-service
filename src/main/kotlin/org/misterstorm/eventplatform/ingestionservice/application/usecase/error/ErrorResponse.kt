package org.misterstorm.eventplatform.ingestionservice.application.usecase.error

import java.time.Instant

data class ErrorResponse(
    val errorCode: ErrorCodeResponseEnum,
    val description: String,
    val timestamp: Instant
)

enum class ErrorCodeResponseEnum {
    MALFORMED_REQUEST,
    INTERNAL_ERROR,
}