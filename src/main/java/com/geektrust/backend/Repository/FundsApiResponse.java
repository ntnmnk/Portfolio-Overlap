package com.geektrust.backend.Repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import com.geektrust.backend.Entities.Funds;

class FundsApiResponse{
     private ArrayList<Funds> funds;

     public ArrayList<Funds> getFunds() {
          return funds;
     }

     public void setFunds(ArrayList<Funds> funds) {
          this.funds = funds;
     }

}