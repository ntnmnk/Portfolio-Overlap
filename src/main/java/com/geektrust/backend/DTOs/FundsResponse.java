package com.geektrust.backend.DTOs;

import java.util.ArrayList;
import java.util.Set;
import com.geektrust.backend.Entities.Funds;

public class FundsResponse{
    
     public ArrayList<Funds> funds;

     public ArrayList<Funds> getFunds() {
          return funds;
     }

     public void setFunds(ArrayList<Funds> funds) {
          this.funds = funds;
     }

}