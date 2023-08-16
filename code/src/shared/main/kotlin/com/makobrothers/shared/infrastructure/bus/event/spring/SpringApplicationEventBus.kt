package com.makobrothers.shared.infrastructure.bus.event.spring

import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Primary
import com.makobrothers.shared.domain.Service
import com.makobrothers.shared.domain.bus.event.DomainEvent
import com.makobrothers.shared.domain.bus.event.EventBus
import java.util.function.Consumer

@Primary
@Service
class SpringApplicationEventBus(private val publisher: ApplicationEventPublisher) : EventBus {
    fun publish(events: List<DomainEvent?>) {
        events.forEach(Consumer<DomainEvent> { event: DomainEvent? -> this.publish(event) })
    }

    private fun publish(event: DomainEvent) {
        publisher.publishEvent(event)
    }
}
