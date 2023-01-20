package com.geektrust.backend.Services;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Global.Constants;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Repository.IFundsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PortfolioOverlapServiceTest {

    String urlString=Constants.url;
    
    FundsRepository mockStockRepository = new MockStockRepositories(urlString);
    PortfolioService mockPortfolioOverlapService = new PortfolioService(mockStockRepository);

    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    public void testSetPortfolio() {
        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);
        assertEquals(3, mockPortfolioOverlapService.getFundNames().length);
        assertEquals("AXIS_MIDCAP", mockPortfolioOverlapService.getFundNames()[1]);
    }
    
   
    @Test
    @DisplayName("Testing addition of  Stocks to funds of User when stock name have space in between eg HDFC BANK LIMITED")

    public void addStocksToFundWhenStockHaveSpacesInBetweenTest(){

        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);

        String fundName = "UTI_NIFTY_INDEX";
        String stockName = "HDFC BANK LIMITED";

        mockPortfolioOverlapService.addStocksToFund(fundName, stockName);

        String fundForCalculation = "SBI_LARGE_&_MIDCAP";
        mockPortfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);

        assertEquals("SBI_LARGE_&_MIDCAP UTI_NIFTY_INDEX 25.00%\n"+"SBI_LARGE_&_MIDCAP AXIS_MIDCAP 33.33%\n", byteArrayOutputStream.toString());

    }
    /**
     * 
     */
    @Test
    @DisplayName("Testing calculatePortfolioOverlap percent when funds are in list")
    
    public void calculatePortfolioOverlapWhenFundsAreInListTest(){

        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);
        String fundForCalculation = "MIRAE_ASSET_LARGE_CAP";
        mockPortfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);
        assertEquals("MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 50.00%\n"+"MIRAE_ASSET_LARGE_CAP AXIS_MIDCAP 28.57%\n", byteArrayOutputStream.toString());
    }

    @Test
    @DisplayName("Testing calculatePortfolioOverlap percent when funds are not in list")
    
    public void calculatePortfolioOverlapWhenFundsAreNotInListTest(){

        String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
        mockPortfolioOverlapService.currentPortfolioStocks(fundList);
        String fundForCalculation = "ROCKSTAR_GAMING";
        mockPortfolioOverlapService.calculatePortfolioOverlap(fundForCalculation);
        assertEquals("FUND_NOT_FOUND", byteArrayOutputStream.toString().trim());
    }

    class MockStockRepositories extends FundsRepository{

        public MockStockRepositories(String url) {
            super(url);
            //TODO Auto-generated constructor stub
        }

        Map<String , Set<String>> fundsAndStockMap = Map.ofEntries(
            new AbstractMap.SimpleEntry("UTI_NIFTY_INDEX", Arrays.asList("INFOSYS LIMITED","EPL LIMITED", "ICICI BANK LIMITED", "ICICI LOMBARD GENERAL INSURANCE COMPANY LIMITED",
            "BHARTI AIRTEL LIMITED").stream().collect(Collectors.toSet())),
            new AbstractMap.SimpleEntry("AXIS_MIDCAP", Arrays.asList("JK CEMENT LIMITED",
            "INFOSYS LIMITED", "SANOFI INDIA LIMITED", "BHARTI AIRTEL LIMITED").stream().collect(Collectors.toSet())),
            new AbstractMap.SimpleEntry("PARAG_PARIKH_FLEXI_CAP", Arrays.asList("FACEBOOK INC",
            "MICROSOFT CORPORATION").stream().collect(Collectors.toSet())),
            new AbstractMap.SimpleEntry("MIRAE_ASSET_LARGE_CAP", Arrays.asList("EPL LIMITED",
                    "INFOSYS LIMITED","OIL & NATURAL GAS CORPORATION LIMITED").stream().collect(Collectors.toSet())),
            new AbstractMap.SimpleEntry("SBI_LARGE_&_MIDCAP", Arrays.asList("INFOSYS LIMITED",
            "TTK PRESTIGE LIMITED").stream().collect(Collectors.toSet()))
    );
    

    
        @Override
        public Set<String> getStocksFromFund(String fundName) throws FundNotFoundException {
            Set<String> stockListOfFund = this.fundsAndStockMap.get(fundName);
            if(stockListOfFund == null){
                throw new FundNotFoundException("STOCKS_NOT_FOUND");
            }
            return stockListOfFund;
        }
    
        @Override
        public Set<String> addStocksToFund(String fundName, String stockName)
                throws FundNotFoundException, StockNotFoundException {
            Set<String> updatedStockList = getStocksFromFund(fundName);
            if(updatedStockList == null){
                throw new StockNotFoundException("STOCKS_NOT_FOUND");
            }
            updatedStockList.add(stockName);
            return updatedStockList;
        }
    }    

    @AfterEach
    public void flushOutput(){
        // Put things back
        System.out.flush();
        System.setOut(standardOut);        
    }
}

