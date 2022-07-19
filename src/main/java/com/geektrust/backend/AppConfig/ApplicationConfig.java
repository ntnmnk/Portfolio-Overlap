package com.geektrust.backend.AppConfig;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.geektrust.backend.Commands.AddStockCommand;
import com.geektrust.backend.Commands.CalculateOverlapCommand;
import com.geektrust.backend.Commands.CommandInvoker;
import com.geektrust.backend.Commands.CurrentPortfolioCommand;
import com.geektrust.backend.Repository.FundsRepository;
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

        @Autowired
        private CommandInvoker commandInvoker;

        // @Autowired
        // private FundsRepository fundsRepository;
        
        
        // @Autowired
        // private PortfolioService portfoliopService;

        @Autowired
        private AddStockCommand addStockFundCommand ;

         @Autowired
        private CalculateOverlapCommand calculateOverlapCommand ;

        @Autowired                
        private CurrentPortfolioCommand currentPortfolioCommand ;



        public CommandInvoker getCommandInvoker() {

                commandInvoker.register("CURRENT_PORTFOLIO", currentPortfolioCommand);
                commandInvoker.register("CALCULATE_OVERLAP", calculateOverlapCommand);
                commandInvoker.register("ADD_STOCK", addStockFundCommand);

                return commandInvoker;
        }

}
