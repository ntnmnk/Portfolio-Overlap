package com.geektrust.backend.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Util.IPortfolioOverlapCalculator;
import com.geektrust.backend.Util.PortfolioOverlapCalculator;


public class PortfolioService implements IPortfolioService {

    private String[] fundNames;

    private final IFundsRepository fundsRepository;

    private  IPortfolioOverlapCalculator portfolioOverlapCalculator = new PortfolioOverlapCalculator();


    public PortfolioService(IFundsRepository fundsRepository ) {
        this.fundsRepository = fundsRepository;
       
    }


    @Override
    public void currentPortfolioStocks(String[] fundList) throws FundNotFoundException {
        // Adding given funds to user's portfolio
        if (fundList == null||fundList.length==0) {
           
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
        fundNames = fundList;
    }

    @Override
    public List<String> calculatePortfolioOverlap(String fundForCalculation) {
        List<String> overlapList = new ArrayList<>();
        for (String fund : this.fundNames) {
            Set<String> currentFundStocks = fundsRepository.getStocksFromFund(fund);
            Set<String> fundForCalculationStocks = fundsRepository.getStocksFromFund(fundForCalculation);
            String overlapPercentage = portfolioOverlapCalculator.overlap(currentFundStocks, fundForCalculationStocks);
    
            if (Double.parseDouble(overlapPercentage) > 0) {
                overlapList.add(fundForCalculation + " " + fund + " " + overlapPercentage + "%");
            }
        }
        if (overlapList.isEmpty()) {
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
        return overlapList;
        }      
    


    @Override
    public void addStocksToFund(String fundName, String stockName)
            throws FundNotFoundException {
        // Adding the fund name to which the new stock will be added and the name of the new
        // stock.
        try {
            fundsRepository.addStocksToFund(fundName, stockName);
        } 
        catch (FundNotFoundException e) 
        {
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
    }


    public String[] getFundNames() {

        for (String string : fundNames) {
            System.out.println(string);
        }
        return fundNames;
    }
}
