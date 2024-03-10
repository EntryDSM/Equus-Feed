package hs.kr.equus.feed.infrastructure.s3.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object ImageNotFoundException : EquusException(
    ErrorCode.IMAGE_NOT_FOUND
)
