package com.geektrust.backend.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Services.IPortfolioService;
import com.geektrust.backend.Services.PortfolioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Testing Command for adding Stock into user fund")
@ExtendWith(MockitoExtension.class)

public class AddStockToFundCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    
    @Mock
    PortfolioService portfolioServiceMock;
    @InjectMocks
    AddStockCommand addStockCommand ;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        addStockCommand = new AddStockCommand(portfolioServiceMock);

        System.setOut(new PrintStream(outputStreamCaptor));
    }   
   

   
@Test
@DisplayName("execute should call addStocksToFund method of portfolioService with valid input")
void testExecuteWithValidInput() throws Exception {
    List<String> tokens = new ArrayList<>(Arrays.asList("ADD_STOCK", "fund", "stock"));
    addStockCommand.execute(tokens);
    verify(portfolioServiceMock).addStocksToFund("fund", "stock");
}

@Test
void execute_validInput_callsAddStocksToFund() throws Exception {
    List<String> tokens = Arrays.asList("ADD_STOCK", "FUND_NAME", "STOCK_NAME");
    doNothing().when(portfolioServiceMock).addStocksToFund(anyString(), anyString());

    addStockCommand.execute(tokens);

    // Verify that addStocksToFund was called with the correct arguments
    List<String> expected = Arrays.asList("FUND_NAME", "STOCK_NAME");
    String fundName = expected.get(0);
    String stockName = expected.get(1);
    assertEquals(fundName, "FUND_NAME");
    assertEquals(stockName, "STOCK_NAME");
}



    @Test
public void execute_addStockToFund() throws CommandNotFoundException {
    List<String> tokens = new ArrayList<>(Arrays.asList("ADD_STOCK", "AXIS_BLUECHIP", "HDFC BANK LIMITED"));

    addStockCommand.execute(tokens);

    verify(portfolioServiceMock, times(1)).addStocksToFund("AXIS_BLUECHIP", "HDFC BANK LIMITED");

}


@AfterEach
    public void restoreStreams() {
        System.setOut(standardOut);
    }


}


