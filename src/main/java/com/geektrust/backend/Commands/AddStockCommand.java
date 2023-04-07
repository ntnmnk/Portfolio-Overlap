package com.geektrust.backend.Commands;

import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Exceptions.StockNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;


public class AddStockCommand implements ICommand {
    
    
    private final IPortfolioService portfolioService;

    public AddStockCommand(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Override
    public void execute(List<String> tokens) throws CommandNotFoundException,FundNotFoundException,StockNotFoundException {
        
            if (tokens == null || tokens.isEmpty()) {
                throw new CommandNotFoundException("COMMAND_NOT_FOUND");
            }
            String commandName = tokens.get(0);
            if (!commandName.equals("ADD_STOCK")) {
                throw new CommandNotFoundException("COMMAND_NOT_FOUND");
            }
    
            try{
                String fundName = tokens.get(1);
                
                String stockName = (tokens.subList(2 , tokens.size())).stream().map(Object::toString).collect(Collectors.joining(" "));      
    
    
                portfolioService.addStocksToFund(fundName, stockName);
            }
           
    
            catch (CommandNotFoundException e) {
                System.out.println("COMMAND_NOT_FOUND");
            }
            catch(NullPointerException e){
                System.out.println("COMMAND_NOT_FOUND");
            }
    
        }
    }    
    

