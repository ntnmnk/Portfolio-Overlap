package com.geektrust.backend.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Services.PortfolioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculateOverlapCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    IFundsRepository fundsRepository;

    @InjectMocks
    PortfolioService portOverlapServiceMock;

    CalculateOverlapCommand calculateOverlapCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
      
        portOverlapServiceMock = mock(PortfolioService.class);
        calculateOverlapCommand = new CalculateOverlapCommand(portOverlapServiceMock);
        System.setOut(new PrintStream(outputStreamCaptor));

        
    }   
    

    @Test

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
//         doNothing().when(portOverlapServiceMock).currentPortfolioStocks(fundList);
//         String fundForCalculation = "NAVI_FUND";
//         when(portOverlapServiceMock.calculatePortfolioOverlap(fundForCalculation)).thenThrow(new FundNotFoundException());
//         Throwable exception = assertThrows(FundNotFoundException.class, () -> {
//          calculateOverlapCommand.execute(Arrays.asList("CALCULATE_OVERLAP", fundForCalculation));
//         });
//         assertEquals("FUND_NOT_FOUND", outputStreamCaptor.toString().trim());
//         System.out.println(exception.getMessage());
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
    public void test_shouldCatchNullPointerExceptionAndPrintCommandNotFoundMessage() throws CommandNotFoundException {
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
