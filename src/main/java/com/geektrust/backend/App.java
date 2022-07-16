package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import com.geektrust.backend.AppConfig.ApplicationConfig;
import com.geektrust.backend.Commands.CommandInvoker;
import com.geektrust.backend.Exceptions.CommandNotFoundException;
import com.geektrust.backend.Repository.FundsRepository;
import com.geektrust.backend.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class App {
    // To run the application ./gradlew run --args="input1.txt"
    @Autowired
    private ApplicationContext appContext;

  

//    @Bean
//  @Autowired
//    public PortfolioService portfolioService(){
//        return new PortfolioService(fundsRepository());
//    }


    public static void main(String[] args) {
            

        System.out.println("hello");
    SpringApplication.run(App.class, args);
        
      

         List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));

         run(commandLineArgs);
       
    }

    // @Override
    // public void run(String... args) throws Exception {
    //     fundsRepository.printR();
    //     portfolioService.printR();
    //     String[] beans = appContext.getBeanDefinitionNames();
    //     Arrays.sort(beans);
    //     for (String bean : beans) {
    //         System.out.println(bean);
    //     }

    // }

    public static void run(List<String> commandLineArgs) {

        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();

        BufferedReader reader;
        String inputFile = commandLineArgs.get(0);
        commandLineArgs.remove(0);
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0), tokens);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | CommandNotFoundException e) {
            e.printStackTrace();
        }
    }

}

