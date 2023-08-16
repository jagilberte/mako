package com.makobrothers.shared.infrastructure.bus.command

import org.reflections.Reflections
import com.makobrothers.shared.domain.Service
import com.makobrothers.shared.domain.bus.command.Command
import com.makobrothers.shared.domain.bus.command.CommandHandler
import com.makobrothers.shared.domain.bus.command.CommandNotRegisteredError
import java.lang.reflect.ParameterizedType


@Service
class CommandHandlersInformation {
    var indexedCommandHandlers: HashMap<Class<out Command?>, Class<out CommandHandler?>>

    init {
        val reflections = Reflections("com.makobrothers")
        val classes: Set<Class<out CommandHandler?>> = reflections.getSubTypesOf(
            CommandHandler::class.java
        )
        indexedCommandHandlers = formatHandlers(classes)
    }

    @Throws(CommandNotRegisteredError::class)
    fun search(commandClass: Class<out Command?>): Class<out CommandHandler?> {
        return indexedCommandHandlers[commandClass]
            ?: throw CommandNotRegisteredError(commandClass)
    }

    private fun formatHandlers(
        commandHandlers: Set<Class<out CommandHandler?>>
    ): HashMap<Class<out Command?>, Class<out CommandHandler?>> {
        val handlers: HashMap<Class<out Command?>, Class<out CommandHandler?>> =
            HashMap<Class<out Command?>, Class<out CommandHandler?>>()
        for (handler in commandHandlers) {
            val paramType = handler.getGenericInterfaces()[0] as ParameterizedType
            val commandClass: Class<out Command?> = paramType.actualTypeArguments[0] as Class<out Command?>
            handlers[commandClass] = handler
        }
        return handlers
    }
}