package com.geektrust.backend.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class PortfolioCalculatorTest {

    PortfolioOverlapCalculator overlapCalculator=new PortfolioOverlapCalculator();
    
    @Test
    public void TestCalculate(){

          Set<String> fundList1 = Arrays.asList("NAZARA", "BALAJIAMINES", "ALKYLAMINES").stream().collect(Collectors.toSet());
          Set<String> fundList2 = Arrays.asList("MAXHEALTH", "BALAJIAMINES", "NAZARA", "TATACOFEE").stream().collect(Collectors.toSet());
          String overlap = overlapCalculator.overlap(fundList1, fundList2);
          assertEquals("57.14", overlap);
    }
    @Test
    public void testGetPortfolioOverlap_withEmptyList() {
        
       // Set<String> actualOverlap = overlapCalculator.overlap(Arrays.);
        
       // assertEquals(new HashSet<>(), actualOverlap);
    }

}
