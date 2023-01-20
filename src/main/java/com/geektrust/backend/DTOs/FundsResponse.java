package com.geektrust.backend.DTOs;

import java.util.ArrayList;
import com.geektrust.backend.Entities.Fund;

public class FundsResponse{
    
     public ArrayList<Fund> funds;

     public ArrayList<Fund> getFunds() {
          return funds;
     }

     public void setFunds(ArrayList<Fund> funds) {
          this.funds = funds;
     }

}