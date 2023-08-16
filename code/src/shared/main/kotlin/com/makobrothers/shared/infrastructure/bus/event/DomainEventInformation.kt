package com.makobrothers.shared.infrastructure.bus.event

import org.reflections.Reflections
import com.makobrothers.shared.domain.Service
import com.makobrothers.shared.domain.bus.event.DomainEvent
import java.lang.reflect.InvocationTargetException
import java.util.function.Function

@Service
class DomainEventsInformation {
    var indexedDomainEvents: java.util.HashMap<String, Class<out DomainEvent?>>? = null

    init {
        val reflections = Reflections("tv.codely")
        val classes: Set<Class<out DomainEvent?>> = reflections.getSubTypesOf(DomainEvent::class.java)
        try {
            indexedDomainEvents = formatEvents(classes)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

    fun forName(name: String): Class<out DomainEvent?> {
        return indexedDomainEvents!![name]!!
    }

    fun forClass(domainEventClass: Class<out DomainEvent?>?): String {
        return indexedDomainEvents!!.entries
            .stream()
            .filter { (_, value): Map.Entry<String, Class<out DomainEvent?>> ->
                value == domainEventClass
            }
            .map<String>(Function<Map.Entry<String, Class<out DomainEvent?>>, String> { (key, value) -> java.util.Map.Entry.key })
            .findFirst().orElse("")
    }

    @Throws(
        NoSuchMethodException::class,
        IllegalAccessException::class,
        InvocationTargetException::class,
        InstantiationException::class
    )
    private fun formatEvents(
        domainEvents: Set<Class<out DomainEvent?>>
    ): HashMap<String, Class<out DomainEvent?>> {
        val events: HashMap<String, Class<out DomainEvent?>> = HashMap<String, Class<out DomainEvent?>>()
        for (domainEvent in domainEvents) {
            val nullInstance: DomainEvent? = domainEvent.getConstructor().newInstance()
            events[domainEvent.getMethod("eventName").invoke(nullInstance) as String] = domainEvent
        }
        return events
    }
}
