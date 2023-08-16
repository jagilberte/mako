package com.makobrothers.shared.infrastructure.bus.event

import com.makobrothers.shared.domain.Utils
import com.makobrothers.shared.domain.bus.event.DomainEvent

class DomainEventSubscriberInformation(
    private val subscriberClass: Class<*>,
    subscribedEvents: List<Class<out DomainEvent?>>
) {
    private val subscribedEvents: List<Class<out DomainEvent?>>

    init {
        this.subscribedEvents = subscribedEvents
    }

    fun subscriberClass(): Class<*> {
        return subscriberClass
    }

    fun contextName(): String {
        val nameParts = subscriberClass.getName().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        return nameParts[2]
    }

    fun moduleName(): String {
        val nameParts = subscriberClass.getName().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        return nameParts[3]
    }

    fun className(): String {
        val nameParts = subscriberClass.getName().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        return nameParts[nameParts.size - 1]
    }

    fun subscribedEvents(): List<Class<out DomainEvent?>> {
        return subscribedEvents
    }

    fun formatRabbitMqQueueName(): String {
        return java.lang.String.format("codelytv.%s.%s.%s", contextName(), moduleName(), Utils.toSnake(className()))
    }
}
