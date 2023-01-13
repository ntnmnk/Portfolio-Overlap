package com.geektrust.backend.Commands;

import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;


public class AddStockCommand implements ICommand {
    
    
    private IPortfolioService portfolioService;

    public AddStockCommand(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Override
    public void execute(List<String> tokens) throws CommandNotFoundException {
        try {
            String fundName = tokens.get(1);

            String stockName = (tokens.subList(2, tokens.size())).stream().map(Object::toString)
                    .collect(Collectors.joining(" "));

            portfolioService.addStocksToFund(fundName, stockName);
        } catch (CommandNotFoundException e) {
            System.out.println("COMMAND_NOT_FOUND");
        }
        catch(NullPointerException e){
            System.out.println("COMMAND_NOT_FOUND");
        }

    }
}
