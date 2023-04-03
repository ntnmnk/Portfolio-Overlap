package com.geektrust.backend.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Services.PortfolioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CalculateOverlapCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    PortfolioService portOverlapServiceMock;

    @InjectMocks
    CalculateOverlapCommand calculateOverlapCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
       // calculateOverlapCommand = new CalculateOverlapCommand(portOverlapServiceMock);


        System.setOut(new PrintStream(outputStreamCaptor));
    }   
    @Test
    @DisplayName("addStockCommand execute methodshould return (Catching Null Pointer Exception) There is no Command if there is no command found")
    public void execute_shouldPrintCommandNotFoundMessage_whenCommandNotFoundExceptionIsThrown() throws CommandNotFoundException {
        // Arrange
        String fundName = "MIRAE_ASSET_EMERGING_BLUECHIP";
        List<String> tokens = new ArrayList<>();
        tokens.add("calculate_overlap");
        tokens.add(fundName);
        doThrow(new CommandNotFoundException("COMMAND_NOT_FOUND")).when(portOverlapServiceMock).calculatePortfolioOverlap(fundName);
    
        // Act
        calculateOverlapCommand.execute(tokens);
    
        // Assert
        assertEquals("COMMAND_NOT_FOUND", outputStreamCaptor.toString().trim());
    
    }
//     @Test
// @DisplayName("Testing calculatePortfolioOverlap percent when funds are not in list")
// public void calculatePortfolioOverlapWhenFundsAreNotInListTest() {
       
//     String[] fundList = {"UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
//     portOverlapServiceMock.currentPortfolioStocks(fundList);
//     String fundForCalculation = "NAVI_FUND";
//     assertThrows(FundNotFoundException.class, () -> {
//      calculateOverlapCommand.execute(Arrays.asList("CALCULATE_OVERLAP", fundForCalculation));
//     });
// }
// @Test
// @DisplayName("Testing calculatePortfolioOverlap percent when funds are not in list")
// public void calculatePortfolioOverlapWhenFundsAreNotInListTest4() throws FundNotFoundException {
//         // Arrange
//         String[] fundList = {"UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
//         Mockito.doNothing().when(portOverlapServiceMock).currentPortfolioStocks(fundList);
//         String fundForCalculation = "NAVI_FUND";
//         List<String> tokens = new ArrayList<>();
//         tokens.add("CALCULATE_OVERLAP");
//         tokens.add(fundForCalculation);
    
//         // Act & Assert
//         assertThrows(FundNotFoundException.class, () -> {
//             calculateOverlapCommand.execute(tokens);
//         });
// }

   
   @Test
    public void calculatePortfolioOverlapWhenFundsAreInListTest2() throws FundNotFoundException {
        String[] fundList = {"UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        portOverlapServiceMock.currentPortfolioStocks(fundList);
        String fundForCalculation = "ICICI_PRU_NIFTY_NEXT_50_INDEX";
        List<String> tokens = new ArrayList<>();
        tokens.add("CALCULATE_OVERLAP");
        tokens.add(fundForCalculation);
        List<String> expected = Arrays.asList("ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.4%\n"
                + "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.8%\n"
                + "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.4%\n");
           
        // Mock the method in the PortfolioService
        when(portOverlapServiceMock.calculatePortfolioOverlap(fundForCalculation)).thenReturn(expected);

        // Act
        calculateOverlapCommand.execute(tokens);

        String expected2 ="ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.4%\n"+ "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.8%\n" +"ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.4%\n";
        // Assert
        verify(portOverlapServiceMock).calculatePortfolioOverlap(fundForCalculation);
        assertEquals(expected2.toString().trim(), outputStreamCaptor.toString().trim());

    }
    @Test
    @DisplayName("execute method should catch NullPointerException and print \"COMMAND_NOT_FOUND\" to console")
    public void execute_shouldCatchNullPointerExceptionAndPrintCommandNotFoundMessage() throws CommandNotFoundException {
        // Arrange
        List<String> tokens = null;

        // Act
        calculateOverlapCommand.execute(tokens);

        // Assert
        assertEquals("COMMAND_NOT_FOUND", outputStreamCaptor.toString().trim());
    }



    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        outputStreamCaptor.reset();
    }


}
