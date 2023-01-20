package com.geektrust.backend.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.geektrust.backend.DTOs.FundsResponse;
import com.geektrust.backend.Entities.Fund;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Services.PortfolioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Testing Command for adding Stock into user fund")
@ExtendWith(MockitoExtension.class)
public class AddStockToFundCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    IFundsRepository fundsRepository;
    PortfolioService portOverlapServiceMock=mock(PortfolioService.class);

    @InjectMocks
    AddStockCommand addStockCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        System.setOut(new PrintStream(outputStreamCaptor));
    }   

    @Test
    @DisplayName("execute method Should Print Error Message to Console")

    public void execute_ShouldPrintErrorMessage()throws FundNotFoundException,NullPointerException{
        String stockName="LG";
        String fundName="AXIS_BLUECHIP";
      String[] fundList = {"AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX"};
    doThrow(new CommandNotFoundException("COMMAND_NOT_FOUND")).when(portOverlapServiceMock).addStocksToFund(fundName,stockName);
      List<String> tokens = null;
      addStockCommand.execute(tokens);
      
      assertEquals("COMMAND_NOT_FOUND", outputStreamCaptor.toString().trim());

    }

    // @Test
    // public void addStockToFundCommandTestReturnMessage(){
    //     String name = "UTI_NIFTY_INDEX";
    //     Set<String> stocks = new HashSet<String>();
    //     stocks.add("MARICO LIMITED");
    //     stocks.add("ACC LIMITED");
    //     stocks.add("GAIL (INDIA) LIMITED");
    //     stocks.add("INFO EDGE (INDIA) LIMITED");

    //     Funds fund = new Funds(name , stocks);
        
    //     String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
    //     portOverlapServiceMock.currentPortfolioStocks(fundList);
       

    //     String fundName = "UTI_NIFTY_INDEX";
    //     String stockName = ("HDFC BANK LIMITED");
    //     portOverlapServiceMock.addStocksToFund(fundName, stockName);
       
    //     List<String> tokens = new ArrayList<>(Arrays.asList("ADD_STOCK fundname stockname"));
    //     addStockCommand.execute(tokens);
       
    //     // String fundForCalculation = "SBI_LARGE_&_MIDCAP";
    //     // portOverlapServiceMock.calculatePortfolioOverlap(fundForCalculation);

        
    //     Assertions.assertEquals(fund.getStocks().contains(stockName), outputStreamCaptor.toString());
    //     verify(portOverlapServiceMock).addStocksToFund(fundName, stockName);
    // }


    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
