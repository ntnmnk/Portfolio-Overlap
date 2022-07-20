package com.geektrust.backend.Commands;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@NoArgsConstructor
public class CommandInvoker {
    private static final Map<String, ICommand> commandMap = new HashMap<>();

    // Register the command into the HashMap
    public void register(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    // Get the registered Command
    private ICommand get(String commandName) {
        return commandMap.get(commandName);
    }

    // Execute the registered Command
    public void executeCommand(String commandName, List<String> tokens)
            throws CommandNotFoundException {
        ICommand command = get(commandName);
        if (command == null) {
            // Handle Exception
            throw new CommandNotFoundException("COMMAND_NOT_FOUND");
        }
        command.execute(tokens);

    }
}
