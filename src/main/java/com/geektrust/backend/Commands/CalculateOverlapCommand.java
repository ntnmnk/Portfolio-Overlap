package com.geektrust.backend.Commands;

import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Exceptions.FundNotFoundException;
import com.geektrust.backend.Services.IPortfolioService;

public class CalculateOverlapCommand implements ICommand {

    
        
    private  IPortfolioService portfolioService;
    
    
    public CalculateOverlapCommand(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }
    

    @Override
    public void execute(List<String> tokens) throws NullPointerException,CommandNotFoundException{

        List<String> overlapList =new ArrayList<>();
        try {
            String fundForCalculation = tokens.get(1);

            overlapList = portfolioService.calculatePortfolioOverlap(fundForCalculation);
           
            overlapList .stream().forEach(s -> System.out.println(s));


        }
        catch(FundNotFoundException e){
            System.out.println("FUND_NOT_FOUND");
        }
        catch (NullPointerException e) {
            System.out.println("COMMAND_NOT_FOUND");
        }
        catch(Exception e){
            System.out.println("COMMAND_NOT_FOUND");

        }
       
    }
    
}
