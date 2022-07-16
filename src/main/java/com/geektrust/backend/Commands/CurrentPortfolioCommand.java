package com.geektrust.backend.Commands;


import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CurrentPortfolioCommand implements ICommand {

    @Autowired
    private final IPortfolioService portfolioOverlapService;
     
    
    public CurrentPortfolioCommand(IPortfolioService portfolioOverlapService) {
        this.portfolioOverlapService = portfolioOverlapService;
    }

    // Execute the registered Command
    @Override
    public void execute(List<String> tokens) throws CommandNotFoundException, NullPointerException {
    
        try {
            String[] temp = new String[tokens.size()];
            tokens.toArray(temp);
            String[] result = Arrays.copyOfRange(temp, 1, temp.length);

            portfolioOverlapService.currentPortfolioStocks(result);
        } catch (NullPointerException e) {
            System.out.println("There is no Command");
        }
    }



}


