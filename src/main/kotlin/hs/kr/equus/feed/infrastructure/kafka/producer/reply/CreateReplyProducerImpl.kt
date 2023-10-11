package hs.kr.equus.feed.infrastructure.kafka.producer.reply

import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.user.infrastructure.kafka.dto.CreateReplyEventRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateReplyProducerImpl(
    private val kafkaTemplate: KafkaTemplate<String, CreateReplyEventRequest>
) : CreateReplyProducer {
    override fun send(questionId: UUID) {
        kafkaTemplate.send(KafkaTopics.CREATE_REPLY, CreateReplyEventRequest(questionId))
    }
}
