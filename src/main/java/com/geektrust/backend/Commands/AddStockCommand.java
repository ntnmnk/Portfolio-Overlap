package com.geektrust.backend.Commands;

import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;
import com.geektrust.backend.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



public class AddStockCommand implements ICommand {
    
    
    private IPortfolioService portfolioService;

    
    public AddStockCommand(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Override
    public void execute(List<String> tokens) throws CommandNotFoundException, NullPointerException {
        // TODO Auto-generated method stub
        try {
            String fundName = tokens.get(1);

            String stockName = (tokens.subList(2, tokens.size())).stream().map(Object::toString)
                    .collect(Collectors.joining(" "));


            portfolioService.addStocksToFund(fundName, stockName);
        } catch (NullPointerException e) {
            throw new CommandNotFoundException();
        }

    }
}
