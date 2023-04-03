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
    IFundsRepository fundsRepository;
    @Mock
    PortfolioService portOverlapServiceMock;
   
    @InjectMocks
    AddStockCommand addStockCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
    }   
    
    @Test
    public void testExecute_withNullParameter() throws CommandNotFoundException {
        List<String> tokens = null;
        addStockCommand.execute(tokens);
        verify(portOverlapServiceMock, never()).addStocksToFund(anyString(), anyString());
    }

    @Test
    public void testExecute_withCommandNotFoundException() throws CommandNotFoundException {
        String fundName = "Fund1";
        List<String> tokens = Arrays.asList("ADD_STOCK", fundName);
        doThrow(new CommandNotFoundException()).when(portOverlapServiceMock).addStocksToFund(fundName, "");
        addStockCommand.execute(tokens);
        verify(portOverlapServiceMock, times(1)).addStocksToFund(fundName, "");
    }

}


