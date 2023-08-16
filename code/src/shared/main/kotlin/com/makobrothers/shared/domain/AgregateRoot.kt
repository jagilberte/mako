package com.makobrothers.shared.domain

import com.makobrothers.shared.domain.bus.event.DomainEvent

abstract class AggregateRoot {
    private var domainEvents: List<DomainEvent> = ArrayList<DomainEvent>()

    fun pullDomainEvents(): List<DomainEvent> {
        val events: List<DomainEvent> = domainEvents
        domainEvents = emptyList<DomainEvent>()
        return events
    }

    protected fun record(event: DomainEvent) {
        domainEvents += event
    }
}
