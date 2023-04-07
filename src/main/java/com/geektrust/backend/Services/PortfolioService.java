package com.geektrust.backend.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Util.IPortfolioOverlapCalculator;
import com.geektrust.backend.Util.PortfolioOverlapCalculator;


public class PortfolioService implements IPortfolioService {

    private  String[] fundNames;

    private final IFundsRepository fundsRepository;

    private  final IPortfolioOverlapCalculator portfolioOverlapCalculator = new PortfolioOverlapCalculator();


    public PortfolioService(IFundsRepository fundsRepository ) {
        this.fundsRepository = fundsRepository;
       
    }

    @Override
    public void currentPortfolioStocks(String[] fundList) throws FundNotFoundException {
        // Adding given funds to user's portfolio
        Optional.ofNullable(fundList).filter(fl -> fl.length > 0).orElseThrow(() -> new FundNotFoundException("FUND_NOT_FOUND"));
        fundNames = fundList;

    }

    @Override
    public List<String> calculatePortfolioOverlap(String fundForCalculation) {
        List<String> overlapList = new ArrayList<>();
        Arrays.stream(this.fundNames).forEach(fund -> {
            String overlapPercentage = calculateOverlapPercentage(fund, fundForCalculation);
            Optional.ofNullable(overlapPercentage).filter(op -> Double.parseDouble(op) > 0)
                    .ifPresent(op -> overlapList.add(fundForCalculation + " " + fund + " " + op + "%"));
        });
        Optional.of(overlapList).filter(ol -> !ol.isEmpty())
                .orElseThrow(() -> new FundNotFoundException("FUND_NOT_FOUND"));
        return overlapList;

        }  

        private String calculateOverlapPercentage(String fund1, String fund2) {
            Set<String> fund1Stocks = fundsRepository.getStocksFromFund(fund1);
            Set<String> fund2Stocks = fundsRepository.getStocksFromFund(fund2);
            return portfolioOverlapCalculator.overlap(fund1Stocks, fund2Stocks);
        }
          
    


    @Override
    public void addStocksToFund(String fundName, String stockName)
            throws FundNotFoundException {
        // Adding the fund name to which the new stock will be added and the name of the new
        // stock.
         Optional.ofNullable(fundName)
        .orElseThrow(() -> new FundNotFoundException("FUND_NOT_FOUND"));

         Optional.ofNullable(stockName)
        .orElseThrow(() -> new StockNotFoundException("STOCK_NOT_FOUND"));

         fundsRepository.addStocksToFund(fundName, stockName);

    }


    public String[] getFundNames() {

        Arrays.stream(fundNames).forEach(System.out::println);
        return fundNames;

    }
}
