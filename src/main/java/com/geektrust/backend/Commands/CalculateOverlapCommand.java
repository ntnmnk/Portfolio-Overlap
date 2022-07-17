package com.geektrust.backend.Commands;

import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;
import com.geektrust.backend.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class CalculateOverlapCommand implements ICommand {

    
    private final PortfolioService portfolioService;
    
    @Autowired
    public CalculateOverlapCommand(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }


    @Override
    public void execute(List<String> tokens) throws NullPointerException,CommandNotFoundException{
        // TODO Auto-generated method stub
        try {
            String fundForCalculation = tokens.get(1);

            portfolioService.calculatePortfolioOverlap(fundForCalculation);
        } catch (NullPointerException e) {
            System.out.println("COMMAND_NOT_FOUND");
        }
        
    }
    
}
