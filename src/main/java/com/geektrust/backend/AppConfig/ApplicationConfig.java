package com.geektrust.backend.AppConfig;

import com.geektrust.backend.Commands.AddStockCommand;
import com.geektrust.backend.Commands.CalculateOverlapCommand;
import com.geektrust.backend.Commands.CommandInvoker;
import com.geektrust.backend.Commands.CurrentPortfolioCommand;
import com.geektrust.backend.Global.Constants;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Services.IPortfolioService;
import com.geektrust.backend.Services.PortfolioService;


public class ApplicationConfig {

        
        private final CommandInvoker commandInvoker=new CommandInvoker();

        String url=Constants.url;
        
        private final IFundsRepository fundsRepository = new FundsRepository(url);
        
        
        private final IPortfolioService portfolioService =
                        new PortfolioService(fundsRepository);


        private final AddStockCommand addStockCommand=new AddStockCommand(portfolioService) ;

         
        private final CalculateOverlapCommand calculateOverlapCommand =new CalculateOverlapCommand(
                        portfolioService);

                      
        private final CurrentPortfolioCommand currentPortfolioCommand=new CurrentPortfolioCommand(portfolioService) ;


        public final CommandInvoker getCommandInvoker() {

                commandInvoker.register("CURRENT_PORTFOLIO", currentPortfolioCommand);
                commandInvoker.register("CALCULATE_OVERLAP", calculateOverlapCommand);
                commandInvoker.register("ADD_STOCK", addStockCommand);

                return commandInvoker;
        }

}
