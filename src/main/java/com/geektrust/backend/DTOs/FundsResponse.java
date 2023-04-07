package com.geektrust.backend.DTOs;

import java.util.List;
import com.geektrust.backend.Entities.Fund;

public class FundsResponse{
     private List<Fund> funds;

     public FundsResponse() {}
 
    
     public List<Fund> getFunds() {
          return funds;
      }
  
      public void setFunds(List<Fund> funds) {
          this.funds = funds;
      }
  

     
    
     

}