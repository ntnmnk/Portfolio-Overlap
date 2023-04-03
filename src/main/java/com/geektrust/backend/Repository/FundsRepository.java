package com.geektrust.backend.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geektrust.backend.Entities.Fund;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.DTOs.*;


public class FundsRepository implements IFundsRepository {
    

    private final String urlString;
    private final Map<String, Set<String>> fundsAndStockMap;


    public FundsRepository(String url) {
        this.urlString=url;
        this.fundsAndStockMap = deserializeFundsResponse();

    }

   
    // Converting Json data to java readable object
    public Map<String, Set<String>>  deserializeFundsResponse()  {
        Map<String, Set<String>> map;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL url = new URL(this.urlString);
            FundsResponse response = objectMapper.readValue(url, FundsResponse.class);
            map = response.getFunds().stream()
                    .collect(Collectors.toMap(Fund::getName, Fund::getStocks));
    
        } catch (IOException e) {
            throw new FundNotFoundException("FUND_NOT_FUND");
        }
        return map;

    }

    public Map<String, Set<String>> getFundAndStockMap() {
        return fundsAndStockMap;
    }


    @Override
    public Set<String> getStocksFromFund(String fundName) throws FundNotFoundException {
        Set<String> stockListOfFund = this.fundsAndStockMap.get(fundName);
        if (stockListOfFund == null || stockListOfFund.isEmpty()){
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
        return stockListOfFund;
}

    @Override
    public Set<String> addStocksToFund(String fundName, String stockName)
            throws FundNotFoundException, StockNotFoundException {
        Set<String> updatedStockList = getStocksFromFund(fundName);
        if (updatedStockList == null) {
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
        updatedStockList.add(stockName);
        return updatedStockList;
    }


}
