package org.misterstorm.eventplatform.ingestionservice.application.usecase.error

import org.misterstorm.eventplatform.ingestionservice.domain.exception.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(ex: ValidationException): ResponseEntity<ErrorResponse> {

        val response = ErrorResponse(
            errorCode = ex.errorCode.toResponseEnum(),
            description = ex.message,
            timestamp = Instant.now()
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {

        val response = ErrorResponse(
            errorCode = ErrorCodeResponseEnum.INTERNAL_ERROR,
            description = "Unexpected error",
            timestamp = Instant.now()
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}