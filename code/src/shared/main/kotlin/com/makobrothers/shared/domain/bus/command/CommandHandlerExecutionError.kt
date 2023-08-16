package com.makobrothers.shared.domain.bus.command

class CommandHandlerExecutionError(cause: Throwable?) : RuntimeException(cause)
