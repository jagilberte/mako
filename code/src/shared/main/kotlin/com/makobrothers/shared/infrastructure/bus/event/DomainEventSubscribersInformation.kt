package com.makobrothers.shared.infrastructure.bus.event

import org.reflections.Reflections
import com.makobrothers.shared.domain.Service
import com.makobrothers.shared.domain.bus.event.DomainEventSubscriber
import java.util.*

@Service
class DomainEventSubscribersInformation @JvmOverloads constructor(var information: HashMap<Class<*>?, DomainEventSubscriberInformation?> = scanDomainEventSubscribers()) {
    fun all(): Collection<DomainEventSubscriberInformation?> {
        return information.values
    }

    fun rabbitMqFormattedNames(): Array<String> {
        return information.values
            .stream()
            .map<Any>(DomainEventSubscriberInformation::formatRabbitMqQueueName)
            .distinct()
            .toArray<String> { _Dummy_.__Array__() }
    }

    companion object {
        private fun scanDomainEventSubscribers(): HashMap<Class<*>?, DomainEventSubscriberInformation?> {
            val reflections = Reflections("tv.codely")
            val subscribers: Set<Class<*>> = reflections.getTypesAnnotatedWith(
                DomainEventSubscriber::class.java
            )
            val subscribersInformation = HashMap<Class<*>?, DomainEventSubscriberInformation?>()
            for (subscriberClass in subscribers) {
                val annotation: DomainEventSubscriber = subscriberClass.getAnnotation(DomainEventSubscriber::class.java)
                subscribersInformation[subscriberClass] =
                    DomainEventSubscriberInformation(subscriberClass, Arrays.asList(annotation.value()))
            }
            return subscribersInformation
        }
    }
}
