package org.misterstorm.eventplatform.ingestionservice.application.usecase.error

import org.misterstorm.eventplatform.ingestionservice.domain.exception.error.ErrorCodeEnum

fun ErrorCodeEnum.toResponseEnum(): ErrorCodeResponseEnum {
    return when (this) {
        ErrorCodeEnum.INVALID_EVENT_ID,
        ErrorCodeEnum.INVALID_EVENT_TYPE,
        ErrorCodeEnum.INVALID_EVENT_DATA -> ErrorCodeResponseEnum.MALFORMED_REQUEST

    }
}