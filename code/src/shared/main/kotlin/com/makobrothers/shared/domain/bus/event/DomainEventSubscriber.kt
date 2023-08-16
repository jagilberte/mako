package com.makobrothers.shared.domain.bus.event

import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Inherited
annotation class DomainEventSubscriber(vararg val value: KClass<out DomainEvent?>)
