package com.geektrust.backend.Commands;

import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class CalculateOverlapCommand implements ICommand {

    @Autowired
    private final IPortfolioService portfolioService;
    
    
    public CalculateOverlapCommand(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }


    @Override
    public void execute(List<String> tokens) throws NullPointerException,CommandNotFoundException{
        // TODO Auto-generated method stub
        try {
            String fundForCalculation = tokens.get(1);

            portfolioService.calculatePortfolioOverlap(fundForCalculation);
        } catch (NullPointerException e) {
            System.out.println("There is no Command");
        }
        
    }
    
}
