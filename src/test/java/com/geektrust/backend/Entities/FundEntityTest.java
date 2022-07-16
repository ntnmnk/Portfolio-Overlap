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
        String name = "AXIS_MIDCAP";
        Set<String> stocks = new HashSet<String>();
        stocks.add("KOTAK MAHINDRA BANK LIMITED");
        stocks.add("BATA INDIA LIMITED");
        stocks.add("MINDTREE LIMITED");
        stocks.add("L&T TECHNOLOGY SERVICES LIMITED");

        FundEntity fund = new FundEntity(name, stocks);
        String test = "KOTAK MAHINDRA BANK LIMITED";
        assertTrue(fund.getStocks().contains(test));



    }

}
