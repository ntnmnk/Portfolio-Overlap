package com.geektrust.backend;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor,true));
    }

    @Test
    @DisplayName("Integration Test #1")
    void runTest1() throws Exception{
        //Arrange
        String argument= "input.txt";
        List<String> input=new LinkedList<>(Arrays.asList(argument));
        String expectedOutput = "ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.37%\n"+
        "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.81%\n"+
        "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.41%\n"+
        "FUND_NOT_FOUND\n"+
        "ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.37%\n"+
        "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.68%\n"+
        "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.32%\n";
        //Act

        App.run(input);
            // TODO Auto-generated catch block
         
        //Assert
        Assertions.assertEquals(expectedOutput.trim(),outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Integration Test #2")
    void runTest2() throws Exception{
        //Arrange
        String argument= "sample_input/input1.txt";
        List<String> input=new LinkedList<>(Arrays.asList(argument));
        String expectedOutput = "MIRAE_ASSET_EMERGING_BLUECHIP AXIS_BLUECHIP 39.13%\n"+
        "MIRAE_ASSET_EMERGING_BLUECHIP ICICI_PRU_BLUECHIP 38.10%\n"+
        "MIRAE_ASSET_EMERGING_BLUECHIP UTI_NIFTY_INDEX 65.52%\n"+
        "MIRAE_ASSET_LARGE_CAP AXIS_BLUECHIP 43.75%\n"+
        "MIRAE_ASSET_LARGE_CAP ICICI_PRU_BLUECHIP 44.62%\n"+
        "MIRAE_ASSET_LARGE_CAP UTI_NIFTY_INDEX 95.00%\n"+
        "MIRAE_ASSET_EMERGING_BLUECHIP AXIS_BLUECHIP 38.71%\n"+
        "MIRAE_ASSET_EMERGING_BLUECHIP ICICI_PRU_BLUECHIP 38.10%\n"+
        "MIRAE_ASSET_EMERGING_BLUECHIP UTI_NIFTY_INDEX 65.52%\n";
        //Act

        App.run(input);
            
         
        //Assert
        Assertions.assertEquals(expectedOutput.trim(),outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {

        System.setOut(standardOut);

    }


}
