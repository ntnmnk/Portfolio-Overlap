package com.geektrust.backend.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    
    private IPortfolioService portfolioService;
    private CurrentPortfolioCommand currentPortfolioCommand;

    @BeforeEach
    void setUp() {
        portfolioService = mock(IPortfolioService.class);
        currentPortfolioCommand = new CurrentPortfolioCommand(portfolioService);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_shouldCallCurrentPortfolioStocksMethodOfPortfolioOverlapService() throws Exception {
        List<String> tokens = new ArrayList<>(Arrays.asList("CURRENT_PORTFOLIO", "stock1", "stock2"));
        currentPortfolioCommand.execute(tokens);
        verify(portfolioService).currentPortfolioStocks(new String[] { "stock1", "stock2" });
    }
    // @Test
    // void execute_shouldThrowCommandNotFoundException_whenTokensListIsEmpty() {
    //     List<String> tokens = new ArrayList<>();
    //     assertThrows(CommandNotFoundException.class, () -> currentPortfolioCommand.execute(tokens));
    // }


   
    // @Test
    // @DisplayName("current command execute methodshould return (Catching Null Pointer Exception) There is no Command if there is no command found")

    // public void executeThrowsCommandNotFoundExceptionWhenFundNotFound(){
        
    //     {
    //         // Arrange
    //         List<String> tokens = Arrays.asList("CURRENT_PORTFOLIO", "UNKNOWN_FUND");
    //         doThrow(new FundNotFoundException("FUND_NOT_FOUND")).when(portfolioService).currentPortfolioStocks(new String[]{"UNKNOWN_FUND"});
    
    //         // Act and Assert
    //         assertThrows(FundNotFoundException.class, () -> {
    //             currentPortfolioCommand.execute(tokens);
    //         });
    
    //         String expectedOutput = "FUND_NOT_FOUND";
    //        String actualOutput = outputStreamCaptor.toString();
    //         assertEquals(expectedOutput, actualOutput);
    //     }
    // }

    // @AfterEach
    // public void tearDown() {
    //     System.setOut(standardOut);
    // }

}
