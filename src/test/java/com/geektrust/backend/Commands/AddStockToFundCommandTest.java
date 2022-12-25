// package com.geektrust.backend.Commands;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.doThrow;
// import static org.mockito.Mockito.when;
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.util.ArrayList;
// import java.util.List;
// import com.geektrust.backend.Exceptions.CommandNotFoundException;
// import com.geektrust.backend.Exceptions.FundNotFoundException;
// import com.geektrust.backend.Services.PortfolioService;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// @DisplayName("Testing Command for adding Stock into user fund")
// @ExtendWith(MockitoExtension.class)
// public class AddStockToFundCommandTest {
//     private final PrintStream standardOut = System.out;
//     private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

//     @Mock
//     PortfolioService portOverlapServiceMock;

//     @InjectMocks
//     AddStockCommand addStockCommand;

//     @BeforeEach
//     public void setUp() {
//         System.setOut(new PrintStream(outputStreamCaptor));
//     }   

//     @Test
//     @DisplayName("execute method Should Print Error Message to Console")

//     public void execute_ShouldPrintErrorMessage()throws FundNotFoundException,NullPointerException{
        
//         String fundList = "AXIS_BLUECHI";
//         String stockName="HDFC BANK LIMITED";
        
//       //  doThrow(new CommandNotFoundException()).when(portOverlapServiceMock).currentPortfolioStocks(fundList);
//         when(portOverlapServiceMock.addStocksToFund(fundList,stockName)).thenThrow(new FundNotFoundException("FUND_NOT_FOUND"));

//         String expectedOutput = "FUND_NOT_FOUND";

//         addStockCommand.execute(new ArrayList<>(List.of("CANCEL","")));
//                 // List<String> tokens = null;
//         // addStockCommand.execute(tokens);

//         Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim());
//        // verify(portOverlapServiceMock).ad(ticketId);

//     }

//     @Test
//     public void addStockToFundCommandTestReturnMessage(){
        
//         String[] fundList = {"UTI_NIFTY_INDEX","AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP"};
//         portOverlapServiceMock.currentPortfolioStocks(fundList);

//         String fundName = "UTI_NIFTY_INDEX";
//         String stockName = "HDFC BANK LIMITED";

//         portOverlapServiceMock.addStocksToFund(fundName, stockName);


//         String fundForCalculation = "SBI_LARGE_&_MIDCAP";
//         portOverlapServiceMock.calculatePortfolioOverlap(fundForCalculation);


//         assertEquals("SBI_LARGE_&_MIDCAP UTI_NIFTY_INDEX 25.00%\n"+"SBI_LARGE_&_MIDCAP AXIS_MIDCAP 33.33%\n", outputStreamCaptor.toString());
//     }


//     @AfterEach
//     public void tearDown() {
//         System.setOut(standardOut);
//     }

// }
