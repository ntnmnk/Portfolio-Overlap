package com.geektrust.backend.Commands;

import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;

public class CalculateOverlapCommand implements ICommand {

    
    private  IPortfolioService portfolioService;
    
    
    public CalculateOverlapCommand(IPortfolioService portfolioService2) {
        this.portfolioService = portfolioService2;
    }


    @Override
    public void execute(List<String> tokens) throws NullPointerException,CommandNotFoundException{
        try {
            String fundForCalculation = tokens.get(1);

            portfolioService.calculatePortfolioOverlap(fundForCalculation);
        } catch (NullPointerException e) {
            throw new CommandNotFoundException();
        }
        
    }
    
}
