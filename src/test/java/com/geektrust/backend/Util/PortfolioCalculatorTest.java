package com.geektrust.backend.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class PortfolioCalculatorTest {

    OverlapCalculator overlapCalculator=new OverlapCalculator();
    
    @Test
    public void TestCalculate(){

          Set<String> fundList1 = Arrays.asList("NAZARA", "BALAJIAMINES", "ALKYLAMINES").stream().collect(Collectors.toSet());
          Set<String> fundList2 = Arrays.asList("MAXHEALTH", "BALAJIAMINES", "NAZARA", "TATACOFEE").stream().collect(Collectors.toSet());
          String overlap = overlapCalculator.overlap(fundList1, fundList2);
          assertEquals("57.14", overlap);
    }
}
