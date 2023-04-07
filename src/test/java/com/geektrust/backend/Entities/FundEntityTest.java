package com.geektrust.backend.Entities;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Funds Test")
public class FundEntityTest {
    @Test
    @DisplayName("Check whether stock exist return true if stock exist in fund")
    public void chekcStockExist() {
        String name = "ICICI_PRU_NIFTY_NEXT_50_INDEX";
        Set<String> stocks = new HashSet<String>();
        stocks.add("MARICO LIMITED");
        stocks.add("ACC LIMITED");
        stocks.add("GAIL (INDIA) LIMITED");
        stocks.add("INFO EDGE (INDIA) LIMITED");

        Fund fund =  new Fund.Builder()
        .withName(name)
        .withStocks(stocks)
        .build();
;
        String testStock = "ACC LIMITED";
        //System.out.println(fund.getStocks());
        assertTrue(fund.getStocks().contains(testStock));

                    
    }

}
