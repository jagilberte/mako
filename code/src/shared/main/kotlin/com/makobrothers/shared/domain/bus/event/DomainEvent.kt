package com.makobrothers.shared.domain.bus.event

import com.makobrothers.shared.domain.Utils
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

abstract class DomainEvent {
    private var aggregateId: String? = null
    private var eventId: String? = null
    private var occurredOn: String? = null

    constructor(aggregateId: String?) {
        this.aggregateId = aggregateId
        eventId = UUID.randomUUID().toString()
        occurredOn = Utils.dateToString(LocalDateTime.now())
    }

    constructor(aggregateId: String?, eventId: String?, occurredOn: String?) {
        this.aggregateId = aggregateId
        this.eventId = eventId
        this.occurredOn = occurredOn
    }

    protected constructor()

    abstract fun eventName(): String?
    abstract fun toPrimitives(): HashMap<String?, Serializable?>?
    abstract fun fromPrimitives(
        aggregateId: String?,
        body: HashMap<String?, Serializable?>?,
        eventId: String?,
        occurredOn: String?
    ): DomainEvent?

    fun aggregateId(): String? {
        return aggregateId
    }

    fun eventId(): String? {
        return eventId
    }

    fun occurredOn(): String? {
        return occurredOn
    }
}