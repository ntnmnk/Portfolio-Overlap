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
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;
import com.geektrust.backend.Services.PortfolioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class CurrentPortfolioCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    private IPortfolioService portfolioServiceMock;

    @InjectMocks
    private CurrentPortfolioCommand currentPortfolioCommand;

    @BeforeEach
    void setUp() {
        portfolioServiceMock = mock(PortfolioService.class);
        currentPortfolioCommand = new CurrentPortfolioCommand(portfolioServiceMock);
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testExecuteWithEmptyTokens() {
        assertThrows(IllegalArgumentException.class, () -> currentPortfolioCommand.execute(new ArrayList<>()));
//assertEquals("FUND_NOT_FOUND\n", outputStreamCaptor.toString());
    }


   
    @Test
    public void testExecuteWithValidFundList() {
        List<String> tokens = Arrays.asList("CURRENT_PORTFOLIO", "SBI_LARGE_&_MIDCAP", "HDFC_SMALL_CAP");

        currentPortfolioCommand.execute(tokens);

        verify(portfolioServiceMock).currentPortfolioStocks(new String[] { "SBI_LARGE_&_MIDCAP", "HDFC_SMALL_CAP" });
    }

    @Test
    public void testExecuteWithFundNotFound() {
        List<String> tokens = Arrays.asList("CURRENT_PORTFOLIO", "SBI_LARGE_&_MIDCAP", "INVALID_FUND");

        doThrow(new FundNotFoundException("FUND_NOT_FOUND")).when(portfolioServiceMock)
                .currentPortfolioStocks(any(String[].class));

        currentPortfolioCommand.execute(tokens);

        assertEquals("FUND_NOT_FOUND\n", outputStreamCaptor.toString());
    }



   
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
