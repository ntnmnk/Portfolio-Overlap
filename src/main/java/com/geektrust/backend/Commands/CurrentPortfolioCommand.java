package com.geektrust.backend.Commands;


import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;

public class CurrentPortfolioCommand implements ICommand {

    
    private IPortfolioService portfolioOverlapService;

    
    public CurrentPortfolioCommand(IPortfolioService portfolioService) {
        this.portfolioOverlapService = portfolioService;
    }

    // Execute the registered Command
    @Override
    public void execute(List<String> tokens) throws CommandNotFoundException, NullPointerException {

        try {
            String[] temp = new String[tokens.size()];
            tokens.toArray(temp);
            String[] stocksList = Arrays.copyOfRange(temp, 1, temp.length);

            portfolioOverlapService.currentPortfolioStocks(stocksList);
        } catch (FundNotFoundException e) {
            throw new FundNotFoundException("FUND_NOT_FOUND");
        }
    }



}


