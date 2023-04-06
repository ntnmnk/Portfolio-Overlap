package com.geektrust.backend.Util;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PortfolioOverlapCalculator implements IPortfolioOverlapCalculator  {

    @Override
    public String overlap(Set<String> currentFundStocks, Set<String> fundForCalculationStocks) {

        if (currentFundStocks == null || fundForCalculationStocks == null) {
            return "Please check the input";
        }
        int totalstocks = currentFundStocks.size() + fundForCalculationStocks.size();


        // Set<String> commonElements = new HashSet<>();
        // commonElements.addAll(stockList1);
        // commonElements.addAll(stockList2);


       
        Set<String> intersection =
        currentFundStocks.stream().filter(i -> fundForCalculationStocks.contains(i)).collect(Collectors.toSet());

        int commonCount = intersection.size();
        DecimalFormat df = new DecimalFormat("#.##");

        df.setMinimumFractionDigits(2);


        Double overlap = (200 * (double) commonCount / totalstocks);

        return df.format(overlap);



    }

   
}
