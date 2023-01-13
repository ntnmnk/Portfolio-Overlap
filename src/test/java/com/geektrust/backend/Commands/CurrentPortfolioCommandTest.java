package com.geektrust.backend.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.PortfolioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Testing Command for adding Stock into user fund")
@ExtendWith(MockitoExtension.class)

public class CurrentPortfolioCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    PortfolioService portOverlapServiceMock=mock(PortfolioService.class);

    @InjectMocks
    CurrentPortfolioCommand currentPortfolioCommand;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
    }   

    @Test
    @DisplayName("addStockCommand execute methodshould return (Catching Null Pointer Exception) There is no Command if there is no command found")

    public void addStockToFundCommandTestReturnMessage(){
        
        String[] fundList = {"AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX"};
        portOverlapServiceMock.currentPortfolioStocks(fundList);
        List<String> tokens = new ArrayList<>(Arrays.asList("CURRENT"));
        tokens=null;
        currentPortfolioCommand.execute(tokens);
        doThrow(new CommandNotFoundException("COMMAND_NOT_FOUND")).when(portOverlapServiceMock).currentPortfolioStocks(fundList);
        assertEquals("COMMAND_NOT_FOUND", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
