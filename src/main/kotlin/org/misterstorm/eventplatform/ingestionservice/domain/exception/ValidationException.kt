package org.misterstorm.eventplatform.ingestionservice.domain.exception

import org.misterstorm.eventplatform.ingestionservice.domain.exception.error.ErrorCodeEnum

class ValidationException(
    val errorCode: ErrorCodeEnum,
    override val message: String
) : RuntimeException(message)