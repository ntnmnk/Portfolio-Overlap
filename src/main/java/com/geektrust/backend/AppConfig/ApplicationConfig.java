package com.geektrust.backend.AppConfig;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.geektrust.backend.Commands.AddStockCommand;
import com.geektrust.backend.Commands.CalculateOverlapCommand;
import com.geektrust.backend.Commands.CommandInvoker;
import com.geektrust.backend.Commands.CurrentPortfolioCommand;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Services.IPortfolioService;
import com.geektrust.backend.Services.PortfolioService;


@AllArgsConstructor
@NoArgsConstructor
public class ApplicationConfig {

        
        private CommandInvoker commandInvoker=new CommandInvoker();

        
        private final IFundsRepository fundsRepository = new FundsRepository();

        private final IPortfolioService portfolioService =
                        new PortfolioService(fundsRepository);


        private AddStockCommand addStockCommand=new AddStockCommand(portfolioService) ;

         
        private CalculateOverlapCommand calculateOverlapCommand =new CalculateOverlapCommand(
                        portfolioService);

                      
        private CurrentPortfolioCommand currentPortfolioCommand=new CurrentPortfolioCommand(portfolioService) ;


        public CommandInvoker getCommandInvoker() {

                commandInvoker.register("CURRENT_PORTFOLIO", currentPortfolioCommand);
                commandInvoker.register("CALCULATE_OVERLAP", calculateOverlapCommand);
                commandInvoker.register("ADD_STOCK", addStockCommand);

                return commandInvoker;
        }

}
