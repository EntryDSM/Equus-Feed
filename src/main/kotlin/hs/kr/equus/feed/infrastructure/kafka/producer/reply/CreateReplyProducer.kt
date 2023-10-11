package hs.kr.equus.feed.infrastructure.kafka.producer.reply

import java.util.UUID

interface CreateReplyProducer {
    fun send(questionId: UUID)
}
