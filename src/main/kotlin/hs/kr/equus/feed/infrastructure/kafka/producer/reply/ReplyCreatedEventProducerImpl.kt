package hs.kr.equus.feed.infrastructure.kafka.producer.reply

import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.feed.infrastructure.kafka.dto.CreateReplyEventRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReplyCreatedEventProducerImpl(
    private val kafkaTemplate: KafkaTemplate<String, CreateReplyEventRequest>
) : ReplyCreatedEventProducer {
    override fun send(createEventRequest: CreateReplyEventRequest) {
        kafkaTemplate.send(KafkaTopics.CREATE_REPLY, createEventRequest)
    }
}
