package com.geektrust.backend.Repository;

import java.util.Set;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;


public interface IFundsRepository {

    Set<String> getStocksFromFund(String fundName) throws FundNotFoundException;
  
    Set<String> addStocksToFund(String fundName, String stockName) throws FundNotFoundException, StockNotFoundException;

   
}
