package com.makobrothers.shared.domain.bus.command

class CommandNotRegisteredError(command: Class<out Command?>) :
    Exception(String.format("The command <%s> hasn't a command handler associated", command.toString()))
