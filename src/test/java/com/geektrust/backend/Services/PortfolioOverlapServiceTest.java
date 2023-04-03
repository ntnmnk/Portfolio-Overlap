package com.geektrust.backend.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.AbstractMap;
import java.util.ArrayList;
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
import com.geektrust.backend.Util.OverlapCalculator;
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


@ExtendWith(MockitoExtension.class)
public class PortfolioOverlapServiceTest {

    String urlString=Constants.url;
    private OverlapCalculator overlapCalculator;

    @Mock
    FundsRepository fundsRepository=new FundsRepository(urlString);
    
    private PortfolioService portfolioServiceMock;

   
    //PortfolioService mockPortfolioOverlapService = new PortfolioService(mockStockRepository);
  //  PortfolioService mockPortfolioOverlapService;
    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    // @BeforeEach
    // public void setUp() {
    //     System.setOut(new PrintStream(byteArrayOutputStream));
    // }
    @BeforeEach
    public void setUp() {
        
        MockitoAnnotations.openMocks(this);

        portfolioServiceMock = new PortfolioService(fundsRepository);
        overlapCalculator = mock(OverlapCalculator.class);
         System.setOut(new PrintStream(byteArrayOutputStream));

    }
   
    @Test
void testCurrentPortfolioStocks_emptyArray() throws FundNotFoundException {
    String[] fundList = {};
    assertThrows(FundNotFoundException.class, () -> portfolioServiceMock.currentPortfolioStocks(fundList));
}
   
    
    @Test
    @DisplayName("Test adding stocks to a fund")
    public void testAddStocksToFund() throws FundNotFoundException, StockNotFoundException {
        String fundName = "UTI_NIFTY_INDEX";
        String stockName = "HDFC BANK LIMITED";

        portfolioServiceMock.addStocksToFund(fundName, stockName);

        // Verify that the stocksRepository's addStocksToFund method is called with the correct arguments
        Mockito.verify(fundsRepository).addStocksToFund(fundName, stockName);
    }


  

    @Test
    public void testCurrentPortfolioStocksWithValidInput() {
        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        portfolioServiceMock.currentPortfolioStocks(fundList);
        assertEquals(3, portfolioServiceMock.getFundNames().length);
        assertEquals("AXIS_MIDCAP", portfolioServiceMock.getFundNames()[1]);
    }
    @Test
    public void testCalculatePortfolioOverlapWithInvalidFund() {
        String fundsToCompare = "UTI";
       // when(fundsRepository.getStocksFromFund(fundsToCompare)).thenReturn(null);
        assertThrows(FundNotFoundException.class, () -> portfolioServiceMock.calculatePortfolioOverlap(fundsToCompare));
    }
    // @Test
    // public void testCalculatePortfolioOverlap() throws FundNotFoundException {
    //     String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
    //     portfolioServiceMock.currentPortfolioStocks(fundList);
    //     List<String> overlaps = portfolioServiceMock.calculatePortfolioOverlap("ICICI_PRU_NIFTY_NEXT_50_INDEX");
    //     assertEquals(Arrays.asList("ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 25.0%", "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 50.0%"), overlaps);
    // }
   
    // @Test
    // @DisplayName("Testing addition of  Stocks to funds of User when stock name have space in between eg HDFC BANK LIMITED")

    // public void addStocksToFundWhenStockHaveSpacesInBetweenTest(){

    //     String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
    //     portfolioServiceMock.currentPortfolioStocks(fundList);

    //     String fundName = "UTI_NIFTY_INDEX";
    //     String stockName = "HDFC BANK LIMITED";

    //     portfolioServiceMock.addStocksToFund(fundName, stockName);

    //     String fundForCalculation = "SBI_LARGE_&_MIDCAP";
    //     List<String> result=portfolioServiceMock.calculatePortfolioOverlap(fundForCalculation);

    //     assertEquals("SBI_LARGE_&_MIDCAP UTI_NIFTY_INDEX 25.00%\n"+"SBI_LARGE_&_MIDCAP AXIS_MIDCAP 33.33%\n", byteArrayOutputStream.toString().trim());

    // }
    /**
     * 
     */
    @Test
    void testAddStocksToFund4() throws FundNotFoundException, StockNotFoundException {
        String fundName = "UTI_NIFTY_INDEX";
        String stockName = "INFOSYS";
        portfolioServiceMock.addStocksToFund(fundName, stockName);
        verify(fundsRepository, times(1)).addStocksToFund(fundName, stockName);
    }
    // @Test
    // void calculatePortfolioOverlapWhenFundsAreInListTest2() throws FundNotFoundException {
    //     String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
    //     portfolioServiceMock.currentPortfolioStocks(fundList);
    //     String fundsToCompare = "ICICI_PRU_NIFTY_NEXT_50_INDEX";
    //     List<String> expected = new ArrayList<>();
    //     expected.add("ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.37%");
    //     expected.add("ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.81%");
    //     expected.add("ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.41%");
    //     assertEquals(expected, portfolioServiceMock.calculatePortfolioOverlap(fundsToCompare));
    // }
    @Test
    public void testCurrentPortfolioStocksWithNull() {
        assertThrows(FundNotFoundException.class, () -> portfolioServiceMock.currentPortfolioStocks(null));
    }

    // @Test
    // void calculatePortfolioOverlapWhenFundsAreNotInListTest2() throws FundNotFoundException {
    //     String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
    //     portfolioServiceMock.currentPortfolioStocks(fundList);
    //     String fundsToCompare = "AXIS_MIDCAP_FUND";
    //     List<String> expected = new ArrayList<String>();
    //     assertEquals(expected, portfolioServiceMock.calculatePortfolioOverlap(fundsToCompare));
    // }

    // @Test
    // @DisplayName("Testing calculatePortfolioOverlap percent when funds are not in list")
    
    // public void calculatePortfolioOverlapWhenFundsAreNotInListTest(){

    //     String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
    //     portfolioServiceMock.currentPortfolioStocks(fundList);
    //     String fundForCalculation = "NAVI_FUND";
    //     portfolioServiceMock.calculatePortfolioOverlap(fundForCalculation);
    //     assertEquals("FUND_NOT_FOUND", byteArrayOutputStream.toString().trim());
    //     //assertThrows(FundNotFoundException.class, ()->{portfolioServiceMock.calculatePortfolioOverlap(fundForCalculation);});
    // }

    // class MockStockRepositories extends FundsRepository{

    //     public MockStockRepositories(String url)  {
    //         super(url);
    //         //TODO Auto-generated constructor stub
    //     }

    //     Map<String,Set<String>> fundsAndStockMap = Map.ofEntries(
    //         Map.entry("UTI_NIFTY_INDEX", Arrays.asList("INFOSYS LIMITED","EPL LIMITED", "ICICI BANK LIMITED", "ICICI LOMBARD GENERAL INSURANCE COMPANY LIMITED",
    //         "BHARTI AIRTEL LIMITED").stream().collect(Collectors.toSet())),
    //         Map.entry("AXIS_MIDCAP", Arrays.asList("JK CEMENT LIMITED",
    //         "INFOSYS LIMITED", "SANOFI INDIA LIMITED", "BHARTI AIRTEL LIMITED").stream().collect(Collectors.toSet())),
    //         Map.entry("PARAG_PARIKH_FLEXI_CAP", Arrays.asList("FACEBOOK INC",
    //         "MICROSOFT CORPORATION").stream().collect(Collectors.toSet())),
    //         Map.entry("MIRAE_ASSET_LARGE_CAP", Arrays.asList("EPL LIMITED",
    //                 "INFOSYS LIMITED","OIL & NATURAL GAS CORPORATION LIMITED").stream().collect(Collectors.toSet())),
    //                 Map.entry("SBI_LARGE_&_MIDCAP", Arrays.asList("INFOSYS LIMITED",
    //         "TTK PRESTIGE LIMITED").stream().collect(Collectors.toSet()))
    // );
    

    
    //     @Override
    //     public Set<String> getStocksFromFund(String fundName) throws FundNotFoundException {
    //         Set<String> stockListOfFund = this.fundsAndStockMap.get(fundName);
    //         if(stockListOfFund == null){
    //             throw new FundNotFoundException("STOCKS_NOT_FOUND");
    //         }
    //         return stockListOfFund;
    //     }
    
    //     @Override
    //     public Set<String> addStocksToFund(String fundName, String stockName)
    //             throws FundNotFoundException, StockNotFoundException {
    //         Set<String> updatedStockList = getStocksFromFund(fundName);
    //         if(updatedStockList == null){
    //             throw new StockNotFoundException("STOCKS_NOT_FOUND");
    //         }
    //         updatedStockList.add(stockName);
    //         return updatedStockList;
    //     }
    // }    
  

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        byteArrayOutputStream.reset();
    }

}

