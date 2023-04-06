package com.geektrust.backend.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;
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
    
    @Mock
    private IPortfolioService portfolioService;

    @InjectMocks
    private CurrentPortfolioCommand currentPortfolioCommand;

    @BeforeEach
    void setUp() {
        portfolioService = mock(PortfolioService.class);
        currentPortfolioCommand = new CurrentPortfolioCommand(portfolioService);
        System.setOut(new PrintStream(outputStreamCaptor));
    }
   
   

   
    

  

    @Test
    public void testExecuteWithInvalidCommandName() {
        List<String> tokens = Arrays.asList("INVALID_COMMAND", "SBI_LARGE_&_MIDCAP", "HDFC_SMALL_CAP");

        currentPortfolioCommand.execute(tokens);

        assertEquals("COMMAND_NOT_FOUND\n", outputStreamCaptor.toString());
    }
   
  

    


    


   
   
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
