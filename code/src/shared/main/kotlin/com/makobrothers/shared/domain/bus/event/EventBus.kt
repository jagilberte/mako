package com.makobrothers.shared.domain.bus.event

interface EventBus {
    fun publish(events: List<DomainEvent?>?)
}
