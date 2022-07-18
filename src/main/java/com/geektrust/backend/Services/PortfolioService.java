package com.geektrust.backend.Services;

import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Util.OverlapCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Service
public class PortfolioService implements IPortfolioService {

    private String[] fundNames;
    
    private FundsRepository fundsRepository;

    @Autowired
    private OverlapCalculator portfolioOverlapCalculator;
        
    public void printR() {
        System.out.println("I m in the Portfolio");
    }
    
   @Autowired
    public PortfolioService(FundsRepository fundsRepository){
        this.fundsRepository = fundsRepository;
    }

    @Override
    public void currentPortfolioStocks(String[] fundList) throws FundNotFoundException {
        // TODO: adding given funds to user's portfolio
        if (fundList == null) {
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
        this.fundNames = fundList;
    }

    @Override
    public void calculatePortfolioOverlap(String fundsToCompare) {
        // TODO: calculating overlap and overlap percent of given funds with the funds user having
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
        // TODO:Adding the fund name to which the new stock will be added and the name of the new
        // stock.
        try {
            fundsRepository.addStocksToFund(fundName, stockName);
        } catch (RuntimeException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public String[] getFundName() {

        for (String string : fundNames) {
            System.out.println(string);
        }
        return fundNames;
    }



}
