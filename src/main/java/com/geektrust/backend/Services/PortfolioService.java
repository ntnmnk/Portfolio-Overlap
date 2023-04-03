package com.geektrust.backend.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Util.OverlapCalculator;


public class PortfolioService implements IPortfolioService {

    private String[] fundNames;

    private IFundsRepository fundsRepository;


    private OverlapCalculator portfolioOverlapCalculator = new OverlapCalculator();


    public PortfolioService(IFundsRepository fundsRepository) {
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
    public List<String> calculatePortfolioOverlap(String fundsToCompare) throws FundNotFoundException {

        List<String> overlaps = new ArrayList<>();

       
        Set<String> comparedStocks = fundsRepository.getStocksFromFund(fundsToCompare);
        if (comparedStocks.isEmpty()) {
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
    
        Arrays.stream(fundNames)
                .filter(fund -> !fund.equals(fundsToCompare))
                .forEach(fund -> {
                    try {
                        Set<String> fundStocks = fundsRepository.getStocksFromFund(fund);
                        String overlapPercentage = portfolioOverlapCalculator.overlap(fundStocks, comparedStocks);
                        if (Double.parseDouble(overlapPercentage) > 0.0) {
                            overlaps.add(fundsToCompare + " " + fund + " " + overlapPercentage + "%");
                        }
                    } catch (FundNotFoundException e) {
                        // Ignore the fund if it is not found
                    }
                });
    
        return overlaps;

    }

    @Override
    public void addStocksToFund(String fundName, String stockName)
            throws FundNotFoundException, StockNotFoundException {
        // Adding the fund name to which the new stock will be added and the name of the new
        // stock.
        try {
            fundsRepository.addStocksToFund(fundName, stockName);
        } catch (FundNotFoundException e) {
            System.out.println("FUND_NOT_FOUND");
        }
    }

    public String[] getFundNames() {

        for (String string : fundNames) {
            System.out.println(string);
        }
        return fundNames;
    }



}
