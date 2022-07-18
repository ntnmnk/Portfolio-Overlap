package com.geektrust.backend.Commands;


import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;
import com.geektrust.backend.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;

public class CurrentPortfolioCommand implements ICommand {

    @Autowired
    private PortfolioService portfolioOverlapService;

    @Autowired
    public CurrentPortfolioCommand(PortfolioService portfolioOverlapService) {
        this.portfolioOverlapService = portfolioOverlapService;
    }

    // Execute the registered Command
    @Override
    public void execute(List<String> tokens) throws CommandNotFoundException, NullPointerException {

        try {
            String[] temp = new String[tokens.size()];
            tokens.toArray(temp);
            String[] stocksList = Arrays.copyOfRange(temp, 1, temp.length);

            portfolioOverlapService.currentPortfolioStocks(stocksList);
        } catch (NullPointerException e) {
            throw new CommandNotFoundException("COMMAND NOT FOUND");
        }
    }



}


