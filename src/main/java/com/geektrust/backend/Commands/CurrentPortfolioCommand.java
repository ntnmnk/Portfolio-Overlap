package com.geektrust.backend.Commands;


import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;

public class CurrentPortfolioCommand implements ICommand {

    
    private final IPortfolioService portfolioOverlapService;

    
    public CurrentPortfolioCommand(IPortfolioService portfolioService) {
        this.portfolioOverlapService = portfolioService;
    }
    

    // Execute the registered Command
    @Override
    public void execute(List<String> tokens) throws CommandNotFoundException, NullPointerException {
        if(tokens==null) 
           throw new CommandNotFoundException("FUND_NOT_FOUND");
        
       
        try {
          
            String[] temp = new String[tokens.size()];
            tokens.toArray(temp);
            String[] fundList = Arrays.copyOfRange(temp, 1, temp.length);

           

            portfolioOverlapService.currentPortfolioStocks(fundList);
        } catch (FundNotFoundException e) {
            System.out.println("FUND_NOT_FOUND");
        }
        catch(NullPointerException e)
        {
            System.out.println("COMMAND_NOT_FOUND");
        }
        catch (CommandNotFoundException e)
        {
            System.out.println("COMMAND_NOT_FOUND");
        
        }
    }



}


