package com.geektrust.backend.Repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import com.geektrust.backend.Entities.FundEntity;

class FundsApiResponse{
     private ArrayList<FundEntity> funds;

     public ArrayList<FundEntity> getFunds() {
          return funds;
     }

     public void setFunds(ArrayList<FundEntity> funds) {
          this.funds = funds;
     }

    
        
     
     


}