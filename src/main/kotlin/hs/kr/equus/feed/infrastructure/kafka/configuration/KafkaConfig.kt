package hs.kr.equus.feed.infrastructure.kafka.configuration

import hs.kr.equus.feed.infrastructure.kafka.dto.CreateReplyEventRequest
import hs.kr.equus.feed.infrastructure.kafka.dto.DeleteReplyEventRequest
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfig(
    private val kafkaProperty: KafkaProperty
) {
    @Bean
    fun createReplyProducerFactory(): DefaultKafkaProducerFactory<String, CreateReplyEventRequest> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    @Bean
    fun createReplyKafkaTemplate(): KafkaTemplate<String, CreateReplyEventRequest> {
        return KafkaTemplate(createReplyProducerFactory())
    }

    @Bean
    fun createReplyConsumer(): ConsumerFactory<String, CreateReplyEventRequest> {
        return DefaultKafkaConsumerFactory(
            consumerConfigFactory(),
            StringDeserializer(),
            JsonDeserializer(CreateReplyEventRequest::class.java)
        )
    }

    @Bean
    fun createReplyDtoChangeListener(): ConcurrentKafkaListenerContainerFactory<String, CreateReplyEventRequest> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, CreateReplyEventRequest>()
        factory.consumerFactory = createReplyConsumer()
        return factory
    }

    @Bean
    fun deleteReplyProducerFactory(): DefaultKafkaProducerFactory<String, DeleteReplyEventRequest> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    @Bean
    fun deleteReplyKafkaTemplate(): KafkaTemplate<String, DeleteReplyEventRequest> {
        return KafkaTemplate(deleteReplyProducerFactory())
    }

    @Bean
    fun deleteReplyConsumer(): ConsumerFactory<String, DeleteReplyEventRequest> {
        return DefaultKafkaConsumerFactory(
            consumerConfigFactory(),
            StringDeserializer(),
            JsonDeserializer(DeleteReplyEventRequest::class.java)
        )
    }

    @Bean
    fun deleteReplyDtoChangeListener(): ConcurrentKafkaListenerContainerFactory<String, DeleteReplyEventRequest> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, DeleteReplyEventRequest>()
        factory.consumerFactory = deleteReplyConsumer()
        return factory
    }

    private fun producerConfig(): Map<String, Any> {
        return mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperty.serverAddress,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
        )
    }

    private fun consumerConfigFactory(): Map<String, Any> {
        return mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperty.serverAddress,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
        )
    }
}
