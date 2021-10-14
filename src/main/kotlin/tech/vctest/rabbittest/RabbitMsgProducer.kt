package tech.vctest.rabbittest

import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate

class RabbitMsgProducer(
    private val rabbitTemplate: RabbitTemplate,
    private val exchange: Exchange,
    private val queue: Queue
    ) {

    private var counter = 0

    fun send() {
        rabbitTemplate.convertAndSend(exchange.name, queue.name, "Msg: ${counter++}")
    }
}