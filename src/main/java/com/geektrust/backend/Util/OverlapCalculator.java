package com.geektrust.backend.Util;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OverlapCalculator {


    public String overlap(Set<String> stockList1, Set<String> stockList2) {

        if (stockList1 == null || stockList2 == null) {
            return "One of list is empty, please check the input";
        }
        int totalstocks = stockList1.size() + stockList2.size();

        List<String> list = new ArrayList<String>();

        // Set<String> commonElements = new HashSet<>();
        // commonElements.addAll(stockList1);
        // commonElements.addAll(stockList2);


       
        Set<String> intersection =
                stockList1.stream().filter(i -> stockList2.contains(i)).collect(Collectors.toSet());

        int commonCount = intersection.size();
        DecimalFormat df = new DecimalFormat("#.##");

        df.setMinimumFractionDigits(2);


        Double overlap = (200 * (double) commonCount / totalstocks);

        return df.format(overlap);



    }

   
}
