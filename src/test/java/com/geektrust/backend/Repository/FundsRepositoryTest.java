package com.geektrust.backend.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;
import com.geektrust.backend.Entities.Fund;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Global.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Funds Repository Test")
@ExtendWith(MockitoExtension.class)

public class FundsRepositoryTest {
    String urlString=Constants.url;
    private FundsRepository fundsRepository;

    @BeforeEach
    void setUp() throws Exception {
       
        this.fundsRepository = new FundsRepository(urlString);
    }


    @Test
    @DisplayName("Checking that api returns equal number of funds")
    public void checkingFundSize(){

        assertEquals(10, fundsRepository.getFundAndStockMap().keySet().size());
    }
    @Test
    public void checkingOfAvailabilityOfStockInGivenFund(){

        String fundName = "SBI_LARGE_&_MIDCAP";
        String stockName = "KIRLOSKAR OIL ENGINES LIMITED";
        assertTrue(fundsRepository.getStocksFromFund(fundName).contains(stockName));
    }
    @Test
    public void addStocksToFundTest(){
        String fundName = "MIRAE_ASSET_LARGE_CAP";
        String stockName = "INFOSYS LIMITED";
        fundsRepository.addStocksToFund(fundName, stockName);
        assertEquals(63, fundsRepository.getStocksFromFund(fundName).size());
    }
    
    @Test
    public void unknownFundHandlingOnGetStockToFundTest(){

        assertThrows(FundNotFoundException.class, ()->{fundsRepository.getStocksFromFund("UNKNOWN_FUND");});
    }
    @Test
    public void unknownFundHandlingOnAddStockToFundTest(){

        assertThrows(FundNotFoundException.class, ()->{fundsRepository.addStocksToFund("UNKNOWN_FUND", "NOICL");});
    }

@Test
public void addStocksToDublicateFundTest(){
    String fundName = "AXIS_BLUECHIP";
    String stockName = "RELIANCE INDUSTRIES LIMITED";
    fundsRepository.addStocksToFund(fundName, stockName);
    assertEquals(33, fundsRepository.getStocksFromFund(fundName).size());
}


}
