package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteQuestionTableConsumerService(
    private val questionRepository: QuestionRepository
) {
    @KafkaListener(topics = [KafkaTopics.DELETE_ALL_TABLE], groupId = "\${kafka.consumer.groupId2}")
    @Transactional
    fun execute() {
        questionRepository.deleteAll()
    }
}
