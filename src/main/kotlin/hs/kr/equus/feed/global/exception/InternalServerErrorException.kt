package hs.kr.equus.feed.global.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object InternalServerErrorException : EquusException(
    ErrorCode.INTERNAL_SERVER_ERROR
)
