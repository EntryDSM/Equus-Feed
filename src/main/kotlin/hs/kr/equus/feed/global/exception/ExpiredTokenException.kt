package hs.kr.equus.feed.global.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object ExpiredTokenException : EquusException(
    ErrorCode.EXPIRED_TOKEN
)
