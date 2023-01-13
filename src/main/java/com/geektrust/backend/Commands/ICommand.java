package com.geektrust.backend.Commands;

import java.util.List;

@FunctionalInterface
public interface ICommand {
    void execute(List<String> tokens);
}
