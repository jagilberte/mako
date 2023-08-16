package com.makobrothers.shared.infrastructure.bus.event

import com.makobrothers.shared.domain.Service
import com.makobrothers.shared.domain.Utils
import com.makobrothers.shared.domain.bus.event.DomainEvent
import java.io.Serializable
import java.lang.reflect.InvocationTargetException

@Service
class DomainEventJsonDeserializer(information: DomainEventsInformation) {
    private val information: DomainEventsInformation

    init {
        this.information = information
    }

    @Throws(
        InvocationTargetException::class,
        IllegalAccessException::class,
        NoSuchMethodException::class,
        InstantiationException::class
    )
    fun deserialize(body: String?): DomainEvent {
        val eventData: HashMap<String, Serializable> = Utils.jsonDecode(body)
        val data = eventData["data"] as HashMap<String, Serializable>?
        val attributes = data!!["attributes"] as HashMap<String, Serializable>?
        val domainEventClass: Class<out DomainEvent?> = information.forName(data["type"] as String?)
        val nullInstance: DomainEvent? = domainEventClass.getConstructor().newInstance()
        val fromPrimitivesMethod = domainEventClass.getMethod(
            "fromPrimitives",
            String::class.java,
            HashMap::class.java,
            String::class.java,
            String::class.java
        )
        val domainEvent = fromPrimitivesMethod.invoke(
            nullInstance,
            attributes!!["id"] as String?,
            attributes,
            data["id"] as String?,
            data["occurred_on"] as String?
        )
        return domainEvent as DomainEvent
    }
}
