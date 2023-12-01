package hs.kr.equus.feed.infrastructure.kafka.consumer

import hs.kr.equus.feed.domain.reply.domain.repository.ReplyRepository
import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteReplyTableConsumer(
    private val replyRepository: ReplyRepository
) {

    @KafkaListener(topics = [KafkaTopics.DELETE_ALL_TABLE], groupId = "delete-all-table-reply")
    @Transactional
    fun execute() = replyRepository.deleteAll()
}
