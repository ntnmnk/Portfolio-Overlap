package com.geektrust.backend.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Repository Test")
public class FundsRepositoryTest {
    FundsRepository fundsRepository=new FundsRepository();

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

}
