package hs.kr.equus.feed.infrastructure.kafka.producer.reply

import java.util.UUID

interface ReplyCreatedEventProducer {
    fun send(questionId: UUID)
}
