package tech.vctest.rabbittest

import org.junit.jupiter.api.Test
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@Profile("test")
@SpringBootTest
internal class RabbitSendMessageTest {

    @Autowired
    private lateinit var msgProducer: RabbitMsgProducer

    @Test
    fun sendMessages() {
        (1..100).forEach { msgProducer.send() }
    }

    @TestConfiguration
    class RabbitSendConfiguration {
        val queueName = "test.batch"

        @Bean
        fun queue() = Queue(queueName, true)

        @Bean
        fun exchange() = ExchangeBuilder.directExchange("amq.direct").durable(true).build<DirectExchange>()

        @Bean
        fun binding(queue: Queue, exchange: DirectExchange) = BindingBuilder.bind(queue).to(exchange).withQueueName()

        @Bean
        fun msgProducer(rabbitTemplate: RabbitTemplate, exchange: Exchange, queue: Queue) = RabbitMsgProducer(rabbitTemplate, exchange, queue)

    }

}