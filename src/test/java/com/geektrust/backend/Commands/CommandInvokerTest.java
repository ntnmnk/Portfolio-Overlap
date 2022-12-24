package com.geektrust.backend.Commands;

import static org.mockito.ArgumentMatchers.anyList;

import java.util.ArrayList;

import com.geektrust.backend.Exceptions.CommandNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Command Invoker Test")
@ExtendWith(MockitoExtension.class)

public class CommandInvokerTest {
    private CommandInvoker commandInvoker;

    @Mock
    AddStockCommand addStockToFundCommand;

    @Mock
    CalculateOverlapCommand calculateOverlapCommand;

    @Mock
    CurrentPortfolioCommand currentPortfolioCommand;

    @BeforeEach
    void setup(){
        commandInvoker = new CommandInvoker();
        commandInvoker.register("CURRENT_PORTFOLIO", currentPortfolioCommand);
        commandInvoker.register("CALCULATE_OVERLAP", calculateOverlapCommand);
        commandInvoker.register("ADD_STOCK", addStockToFundCommand);
    }

    @Test
    @DisplayName("executeCommand method Should Execute Command Given CommandName and List of tokens")
    public void executeCommand_GivenNameAndTokens_ShouldExecuteCommand() {
        
        commandInvoker.executeCommand("CURRENT_PORTFOLIO",anyList());
        commandInvoker.executeCommand("CALCULATE_OVERLAP",anyList());
        commandInvoker.executeCommand("ADD_STOCK",anyList());       
    }

    @Test
    @DisplayName("executeCommand method Should Throw Exception Given Incorrect CommandName and List of tokens")
    public void executeCommand_GivenIncorrectNameAndTokens_ShouldThrowException() {
        //Act and Assert
        Assertions.assertThrows(CommandNotFoundException.class,() -> commandInvoker.executeCommand("RANDOM-COMMAND",new ArrayList<String>()));

    }

}
