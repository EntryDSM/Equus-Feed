package hs.kr.equus.feed.infrastructure.kafka.consumer

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteUserQuestionConsumer(
    private val questionRepository: QuestionRepository
) {
    @KafkaListener(
        topics = [KafkaTopics.DELETE_USER],
        groupId = "delete-user-question",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    fun execute(userId: UUID) = questionRepository.deleteAllByUserId(userId)
}
