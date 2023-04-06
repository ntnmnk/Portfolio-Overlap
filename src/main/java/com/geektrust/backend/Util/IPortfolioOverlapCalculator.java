package com.geektrust.backend.Util;

import java.util.Set;

public interface IPortfolioOverlapCalculator {
    String overlap(Set<String> currentFundStocks, Set<String> fundForCalculationStocks);
}
