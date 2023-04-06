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
    @DisplayName("Checking if the amount of funds in api are equal to te fund method is accessing")
    public void checkingFundSize(){

        assertEquals(10, fundsRepository.getFundAndStockMap().keySet().size());
    }
    @Test
    @DisplayName("Checking if the given fund contains given stock")
    public void checkingAvailabilityOfStockInGivenFund(){

        String fundName = "SBI_LARGE_&_MIDCAP";
        String stockName = "KIRLOSKAR OIL ENGINES LIMITED";
        assertTrue(fundsRepository.getStocksFromFund(fundName).contains(stockName));
    }
    @Test
    @DisplayName("Testing size of new stocklist of a fund after addition of stock in a given fund")
    public void addStocksToFundTest(){
        String fundName = "SBI_LARGE_&_MIDCAP";
        String stockName = "NEW BANK LIMITED";
        fundsRepository.addStocksToFund(fundName, stockName);
        assertEquals(54, fundsRepository.getStocksFromFund(fundName).size());
    }
    
    @Test
    @DisplayName("Testing exception handling when passing an Unknown fund and trying to get list")
    public void unknownFundHandlingOnGetStockToFundTest(){

        assertThrows(FundNotFoundException.class, ()->{fundsRepository.getStocksFromFund("UNKNOWN_FUND");});
    }
    @Test
    @DisplayName("Testign exception handling when passing an Unknown stock and trying to get list")
    public void unknownFundHandlingOnAddStockToFundTest(){

        assertThrows(FundNotFoundException.class, ()->{fundsRepository.addStocksToFund("UNKNOWN_FUND", "NOICL");});
    }

   

   

@Test
@DisplayName("Testing size of new stocklist of a fund after addition of stock already exist in a given fund")
public void addStocksToDublicateFundTest(){
    String fundName = "SBI_LARGE_&_MIDCAP";
    String stockName = "PVR LIMITED";
    fundsRepository.addStocksToFund(fundName, stockName);
    assertEquals(53, fundsRepository.getStocksFromFund(fundName).size());
}


}
