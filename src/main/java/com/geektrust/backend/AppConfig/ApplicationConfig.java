package com.geektrust.backend.AppConfig;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.geektrust.backend.Commands.AddStockCommand;
import com.geektrust.backend.Commands.CalculateOverlapCommand;
import com.geektrust.backend.Commands.CommandInvoker;
import com.geektrust.backend.Commands.CurrentPortfolioCommand;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Repository.IFundsRepository;
import com.geektrust.backend.Services.IPortfolioService;
import com.geektrust.backend.Services.PortfolioService;
import org.checkerframework.common.subtyping.qual.Unqualified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationConfig {

        
        private CommandInvoker commandInvoker=new CommandInvoker();

        
        private final IFundsRepository fundsRepository = new FundsRepository();

        private final IPortfolioService portfolioService =
                        new PortfolioService(fundsRepository);


        
        private AddStockCommand addStockFundCommand=new AddStockCommand(portfolioService) ;

         
        private CalculateOverlapCommand calculateOverlapCommand =new CalculateOverlapCommand(
                        portfolioService);

                      
        private CurrentPortfolioCommand currentPortfolioCommand=new CurrentPortfolioCommand(portfolioService) ;



        public CommandInvoker getCommandInvoker() {

                commandInvoker.register("CURRENT_PORTFOLIO", currentPortfolioCommand);
                commandInvoker.register("CALCULATE_OVERLAP", calculateOverlapCommand);
                commandInvoker.register("ADD_STOCK", addStockFundCommand);

                return commandInvoker;
        }

}
