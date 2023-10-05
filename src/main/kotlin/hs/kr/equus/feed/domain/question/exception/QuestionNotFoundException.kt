package hs.kr.equus.feed.domain.question.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object QuestionNotFoundException : EquusException(
    ErrorCode.QUESTION_NOT_FOUND
)
