package com.makobrothers.shared.domain.bus.command

interface CommandBus {
    @Throws(CommandHandlerExecutionError::class)
    fun dispatch(command: Command?)
}
