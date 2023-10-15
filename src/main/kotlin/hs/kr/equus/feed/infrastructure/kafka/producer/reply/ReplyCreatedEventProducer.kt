package hs.kr.equus.feed.infrastructure.kafka.producer.reply

import hs.kr.equus.feed.infrastructure.kafka.dto.CreateReplyEventRequest

interface ReplyCreatedEventProducer {
    fun send(createEventRequest: CreateReplyEventRequest)
}
