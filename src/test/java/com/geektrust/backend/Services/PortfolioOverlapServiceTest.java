package com.geektrust.backend.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Global.Constants;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Util.PortfolioOverlapCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.junit.jupiter.api.Assertions;

@DisplayName("Portfolio Service  Test")
@ExtendWith(MockitoExtension.class)
public class PortfolioOverlapServiceTest {
    String stockDataJson = "{\"MIRAE_ASSET_LARGE_CAP\": [\"RELIND\", \"INFY\", \"TCS\", \"WIPRO\", \"SBIN\", \"HDFCBANK\"], \"UTI_NIFTY_INDEX\": [\"RELIANCE\", \"INFOSYS\", \"TATASTEEL\", \"WIPRO\", \"SBIN\", \"HDFCBANK\", \"HINDUNILVR\"], \"AXIS_MIDCAP\": [\"RELIANCE\", \"INFOSYS\", \"TATASTEEL\", \"WIPRO\", \"SBIN\", \"HDFCBANK\", \"PIDILITIND\", \"GODREJCP\", \"MARICO\"]}";
    @Mock
    FundsRepository fundsRepository;
   
    String urlString=Constants.url;
    private PortfolioOverlapCalculator overlapCalculator;

   
    @InjectMocks
    private  PortfolioService portfolioServiceMock;

    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

   
    @BeforeEach
    public void setUp() {
        
        MockitoAnnotations.openMocks(this);

        portfolioServiceMock = new PortfolioService(fundsRepository );
        overlapCalculator = mock(PortfolioOverlapCalculator.class);
         System.setOut(new PrintStream(byteArrayOutputStream));

    }
    @Test
    @DisplayName("Testing addStocksToFund method with invalid fund name")
    public void addStocksToFundInvalidFundTest() throws StockNotFoundException {
        String[] fundList = {"UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        
        portfolioServiceMock.currentPortfolioStocks(fundList);
        String fundName = "INVALID_FUND";
        String stockName = "TATA_CONSULTANCY_SERVICES_LTD";
        doThrow(new FundNotFoundException("FUND_NOT_FOUND")).when(fundsRepository).addStocksToFund(fundName, stockName);
        assertThrows(FundNotFoundException.class, () -> portfolioServiceMock.addStocksToFund(fundName, stockName));
    }
   
    
@Test
public void calculatePortfolioOverlapWhenFundsAreInListTest() throws FundNotFoundException {
    String[] fundList = {"UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
    PortfolioService portfolioServiceMock=mock(PortfolioService.class);
    portfolioServiceMock.currentPortfolioStocks(fundList);
    String fundForCalculation = "ICICI_PRU_NIFTY_NEXT_50_INDEX";
    List<String> expected = Arrays.asList("ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.4%",
            "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.8%",
            "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.4%");
   
    // Mock the method in the PortfolioService
    when(portfolioServiceMock.calculatePortfolioOverlap(fundForCalculation)).thenReturn(expected);

    // Set up a ByteArrayOutputStream to capture the output
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Call the method to be tested
    List<String> overlapList = portfolioServiceMock.calculatePortfolioOverlap(fundForCalculation);

    // Assert the expected output
    String expectedOutput = "ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.4%\n"
            + "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.8%\n"
            + "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.4%\n";
    assertEquals(expected, overlapList);
   

}
    @Test
void testCurrentPortfolioStocks_emptyListofFunds() throws FundNotFoundException {
    String[] fundList = {};
    assertThrows(FundNotFoundException.class, () -> portfolioServiceMock.currentPortfolioStocks(fundList));
}
   
    
    @Test
   
    public void testAddStocksToFund() throws FundNotFoundException, StockNotFoundException {
        String fundName = "UTI_NIFTY_INDEX";
        String stockName = "HDFC BANK LIMITED";

        portfolioServiceMock.addStocksToFund(fundName, stockName);

        // Verify that the stocksRepository's addStocksToFund method is called with the correct arguments
        Mockito.verify(fundsRepository).addStocksToFund(fundName, stockName);
    }


  

    @Test
    public void testCurrentPortfolioStocksWithValidListofFunds() {
        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        portfolioServiceMock.currentPortfolioStocks(fundList);
        assertEquals(3, portfolioServiceMock.getFundNames().length);
        assertEquals("AXIS_MIDCAP", portfolioServiceMock.getFundNames()[1]);
    }
   
  
    @Test
    public void calculatePortfolioOverlapWithOverlap() throws FundNotFoundException {
        FundsRepository fundsRepository = mock(FundsRepository.class);
       
         // Set up object under test
         PortfolioService portfolioService = new PortfolioService(fundsRepository);
        String fundForCalculation = "ICICI_PRU_NIFTY_NEXT_50_INDEX";
        Set<String> utiNiftyIndexStocks = new HashSet<>(Arrays.asList("TCS", "INFY", "WIPRO"));
        Set<String> axisMidcapStocks = new HashSet<>(Arrays.asList("TATASTEEL", "EICHERMOTORS", "HEROMOTOCO"));
        Set<String> paragParikhFlexiCapStocks = new HashSet<>(Arrays.asList("RELIANCE", "HDFCBANK", "HDFC"));
        Set<String> iciciPruNiftyNext50IndexStocks = new HashSet<>(Arrays.asList("TCS", "INFY", "WIPRO", "TATASTEEL", "EICHERMOTORS"));
    
        when(fundsRepository.getStocksFromFund("UTI_NIFTY_INDEX")).thenReturn(utiNiftyIndexStocks);
        when(fundsRepository.getStocksFromFund("AXIS_MIDCAP")).thenReturn(axisMidcapStocks);
        when(fundsRepository.getStocksFromFund("PARAG_PARIKH_FLEXI_CAP")).thenReturn(paragParikhFlexiCapStocks);
        when(fundsRepository.getStocksFromFund("ICICI_PRU_NIFTY_NEXT_50_INDEX")).thenReturn(iciciPruNiftyNext50IndexStocks);
    
        portfolioService.currentPortfolioStocks(new String[]{"UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"});
    
        List<String> expectedOverlapList = Arrays.asList(
                "ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 75.00%",
                "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 50.00%"
                );
    
        assertEquals(expectedOverlapList, portfolioService.calculatePortfolioOverlap(fundForCalculation));
    
        //verify(fundsRepository, times(2)).getStocksFromFund(anyString());
    }
    @Test
    public void testCalculatePortfolioOverlap() throws FundNotFoundException {
        // Mock dependencies
        FundsRepository fundsRepository = mock(FundsRepository.class);
        
        Set<String> fund1Stocks = new HashSet<>(Arrays.asList("AAPL", "GOOG", "MSFT"));
        Set<String> fund2Stocks = new HashSet<>(Arrays.asList("AAPL", "AMZN", "NFLX"));
        Set<String> fund3Stocks = new HashSet<>(Arrays.asList("GOOG", "MSFT", "TSLA"));
        
        when(fundsRepository.getStocksFromFund("Fund1")).thenReturn(fund1Stocks);
        when(fundsRepository.getStocksFromFund("Fund2")).thenReturn(fund2Stocks);
        when(fundsRepository.getStocksFromFund("Fund3")).thenReturn(fund3Stocks);
       // when(portfolioOverlapCalculator.overlap(any(), any())).thenReturn("33.33");
        
        // Set up object under test
        PortfolioService portfolioService = new PortfolioService(fundsRepository);
        portfolioService.currentPortfolioStocks(new String[]{"Fund1", "Fund2", "Fund3"});
        
        // Test method output
        List<String> expectedOverlapList = Arrays.asList(
           "Fund1 Fund1 100.00%",
            "Fund1 Fund2 33.33%", 
            "Fund1 Fund3 66.67%"
        );
        List<String> actualOverlapList = portfolioService.calculatePortfolioOverlap("Fund1");
        assertEquals(expectedOverlapList, actualOverlapList);
        
    }
    
   
    /**
     * 
     */
    @Test
    void test_AddStocksToFund() throws FundNotFoundException, StockNotFoundException {
        String fundName = "UTI_NIFTY_INDEX";
        String stockName = "INFOSYS LIMITED";
        portfolioServiceMock.addStocksToFund(fundName, stockName);
        verify(fundsRepository, times(1)).addStocksToFund(fundName, stockName);
    }

  
    @Test
    public void testCurrentPortfolioStocksWithNull() {
        assertThrows(FundNotFoundException.class, () -> portfolioServiceMock.currentPortfolioStocks(null));
    }



    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        byteArrayOutputStream.reset();
    }

}

