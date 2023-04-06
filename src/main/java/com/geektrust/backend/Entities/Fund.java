package com.geektrust.backend.Entities;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



public class Fund {
    private final   String name;
    private final  Set<String> stocks;


    @JsonCreator
    public Fund(@JsonProperty("name") String name, 
                   @JsonProperty("stocks") Set<String> stocks) {
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
