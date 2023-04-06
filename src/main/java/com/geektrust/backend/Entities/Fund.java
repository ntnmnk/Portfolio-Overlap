package com.geektrust.backend.Entities;

import java.util.Set;


public class Fund implements IFund {
    private final String name;
    private final Set<String> stocks;


    public Fund(String name, Set<String> stocks) {
        this.name = name;
        this.stocks = stocks;
    }

    

  
    public String getName() {
        return name;
    }
   
   
    public Set<String> getStocks() {
        return stocks;
    }
    
    
}
