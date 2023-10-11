package hs.kr.equus.feed.infrastructure.kafka.configuration

import hs.kr.equus.user.infrastructure.kafka.dto.CreateReplyEventRequest
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
    fun replyConsumer(): ConsumerFactory<String, CreateReplyEventRequest> {
        return DefaultKafkaConsumerFactory(
            consumerConfigFactory(),
            StringDeserializer(),
            JsonDeserializer(CreateReplyEventRequest::class.java)
        )
    }

    @Bean
    fun replyDtoChangeListener(): ConcurrentKafkaListenerContainerFactory<String, CreateReplyEventRequest> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, CreateReplyEventRequest>()
        factory.consumerFactory = replyConsumer()
        return factory
    }

    private fun producerConfig(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperty.serverAddress
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }
    private fun consumerConfigFactory(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperty.serverAddress
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }
}
