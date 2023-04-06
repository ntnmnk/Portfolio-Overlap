package com.geektrust.backend.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Fund.class, new FundDeserializer());
        objectMapper.registerModule(module);
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
    private Map<String, Set<String>> mapFundsResponseDto(FundsResponse responseDto) {
        return responseDto.getFunds().stream()
                .collect(Collectors.toMap(Fund::getName, Fund::getStocks));
    }

    public Map<String, Set<String>> getFundAndStockMap() {
        return fundsAndStockMap;
    }


    @Override
    public Set<String> getStocksFromFund(String fundName) throws FundNotFoundException {
        Set<String> stockListOfFund = Optional.ofNullable(this.fundsAndStockMap.get(fundName))
        .orElse(Collections.emptySet());
        if (stockListOfFund.isEmpty()) {
               throw new FundNotFoundException("FUND_NOT_FOUND");
}
return stockListOfFund;

}

    @Override
    public Set<String> addStocksToFund(String fundName, String stockName)
            throws FundNotFoundException, StockNotFoundException ,StockAlreadyExistsException{
                 
                Set<String> updatedStockList = getStocksFromFund(fundName);
                if(updatedStockList == null){
                    throw new StockNotFoundException("STOCKS_NOT_FOUND");
                }
                updatedStockList.add(stockName);
                return updatedStockList;
            }
        


}
