package hs.kr.equus.feed.domain.question.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object FeedWriterMismatchException : EquusException(
    ErrorCode.FEED_WRITER_MISMATCH
)
