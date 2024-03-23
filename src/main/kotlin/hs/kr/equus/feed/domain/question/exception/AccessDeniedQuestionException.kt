package hs.kr.equus.feed.domain.question.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object AccessDeniedQuestionException : EquusException(
    ErrorCode.ACCESS_DENIED_QUESTION
)
