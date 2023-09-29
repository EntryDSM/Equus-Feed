package hs.kr.equus.feed.infrastructure.feign.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object FeignUnAuthorizedException : EquusException(
    ErrorCode.FEIGN_UNAUTHORIZED
)
