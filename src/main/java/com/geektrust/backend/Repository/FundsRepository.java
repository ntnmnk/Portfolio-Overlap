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
    private Map<String, Set<String>> fundsAndStockMap;


    public FundsRepository(String url) {
        this.urlString=url;
        this.deserialisationOfJsonData();
    }

   
    // Converting Json data to java readable object
    public void deserialisationOfJsonData() {
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL url = new URL(this.urlString);
            FundsResponse response = objectMapper.readValue(url, FundsResponse.class);
            this.fundsAndStockMap = response.getFunds().stream()
                    .collect(Collectors.toMap(Fund::getName, Fund::getStocks));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Set<String>> getFundAndStockMap() {
        return fundsAndStockMap;
    }


    @Override
    public Set<String> getStocksFromFund(String fundName) throws FundNotFoundException {
        Set<String> stockListOfFund = this.fundsAndStockMap.get(fundName);
        if (stockListOfFund == null) {
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
