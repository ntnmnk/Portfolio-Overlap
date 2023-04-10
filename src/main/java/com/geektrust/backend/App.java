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


public class App {
    // To run the application ./gradlew run --args="input.txt"
   
    
    static ApplicationConfig applicationConfig=new ApplicationConfig();


    public static void main(String[] args) throws Exception {
        
        // app.run(args);
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        run(commandLineArgs);

    }

    
    public static void run(List<String> commandLineArgs) throws Exception {
     
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



