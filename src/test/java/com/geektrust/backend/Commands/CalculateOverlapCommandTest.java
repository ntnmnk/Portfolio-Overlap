package com.geektrust.backend.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.geektrust.backend.Services.PortfolioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CalculateOverlapCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    PortfolioService portOverlapService;

    @InjectMocks
    CalculateOverlapCommand calculateOverlapCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }   

    @Test
    @DisplayName("addStockCommand execute methodshould return (Catching Null Pointer Exception) There is no Command if there is no command found")

    public void addStockToFundCommandTestReturnMessage(){
        
        String[] fundList = {"AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX"};
        portOverlapService.currentPortfolioStocks(fundList);
        List<String> tokens = null;
        calculateOverlapCommand.execute(tokens);
        
        assertEquals("There is no Command\n", outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
