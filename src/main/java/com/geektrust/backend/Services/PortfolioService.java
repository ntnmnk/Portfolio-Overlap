package com.geektrust.backend.Services;

import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Util.OverlapCalculator;


public class PortfolioService implements IPortfolioService {

    private String[] fundNames;
    
    private IFundsRepository fundsRepository;

    private OverlapCalculator portfolioOverlapCalculator=new OverlapCalculator();
        
   
    public PortfolioService(IFundsRepository fundsRepository){
        this.fundsRepository = fundsRepository;
    }

   

    @Override
    public void currentPortfolioStocks(String[] fundList) throws FundNotFoundException {
        // Adding given funds to user's portfolio
        if (fundList == null) {
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
        this.fundNames = fundList;
    }

    @Override
    public void calculatePortfolioOverlap(String fundsToCompare) {
        //  Calculating overlap and overlap percent of given funds with the funds user having
        // currently
        try {
            for (String fund : this.fundNames) {

                String overlapPercentage = portfolioOverlapCalculator.overlap(
                        fundsRepository.getStocksFromFund(fund),
                        fundsRepository.getStocksFromFund(fundsToCompare));

                if (Double.parseDouble(overlapPercentage) > (double)0)
                    System.out.println(
                            fundsToCompare + " " + fund + " " + overlapPercentage + "%");
                else
                    continue;
            }
        } catch (FundNotFoundException e) {
            System.out.println("FUND_NOT_FOUND");
        }
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
