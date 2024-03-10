package hs.kr.equus.feed.infrastructure.s3.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object EmptyFileException : EquusException(
    ErrorCode.FILE_IS_EMPTY
)
