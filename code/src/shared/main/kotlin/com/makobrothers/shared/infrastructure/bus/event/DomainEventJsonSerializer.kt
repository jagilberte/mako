package com.makobrothers.shared.infrastructure.bus.event

import com.makobrothers.shared.domain.Utils
import com.makobrothers.shared.domain.bus.event.DomainEvent
import java.io.Serializable

object DomainEventJsonSerializer {
    fun serialize(domainEvent: DomainEvent): String {
        val attributes: HashMap<String, Serializable> = domainEvent.toPrimitives()
        attributes["id"] = domainEvent.aggregateId()
        return Utils.jsonEncode(object : java.util.HashMap<String?, Serializable?>() {
            init {
                put("data", object : java.util.HashMap<String?, Serializable?>() {
                    init {
                        put("id", domainEvent.eventId())
                        put("type", domainEvent.eventName())
                        put("occurred_on", domainEvent.occurredOn())
                        put("attributes", attributes)
                    }
                })
                put("meta", java.util.HashMap<String, Serializable>())
            }
        })
    }
}
