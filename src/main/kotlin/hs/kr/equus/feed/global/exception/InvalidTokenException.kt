package hs.kr.equus.feed.global.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object InvalidTokenException : EquusException(
    ErrorCode.INVALID_TOKEN
)
