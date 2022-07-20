package com.geektrust.backend;

import org.springframework.boot.Banner;
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
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App implements CommandLineRunner {
    // To run the application ./gradlew run --args="input.txt"
   
    
    @Autowired
    ApplicationConfig applicationConfig;

    

   

    //    @Bean
    //  @Autowired
    //    public PortfolioService portfolioService(){
    //        return new PortfolioService(fundsRepository());
    //    }


    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.OFF);
        
        app.run(args);

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
    @Override
    public void run(String... args) throws Exception {
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));

        
        // String[] beans = appContext.getBeanDefinitionNames();
        // Arrays.sort(beans);
        // for (String bean : beans) {
        // System.out.println(bean);
        // }
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

    // @Bean
    // public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    //     return args -> {

    //         System.out.println("Let's inspect the beans provided by Spring Boot:");

    //         String[] beanNames = ctx.getBeanDefinitionNames();
    //         Arrays.sort(beanNames);
    //         for (String beanName : beanNames) {
    //             System.out.println(beanName);
    //         }

    //     };
    // }

}



