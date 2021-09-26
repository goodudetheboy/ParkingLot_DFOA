package com.vuongho.parkinglot;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class InputTest {

    @Test
    public void parkingLotTest() {
        evaluateParkingLot("./file_inputs_current.txt", "./file_outputs_current.txt");
    }

    /**
     * Batch evaluation for an opening hours file, with input time values and its corresponding correct answers.
     * This is successful if the evaluator return all correct answer
     * 
     * @param inputFile opening hours file
     * @param inputTimeFile input time value file
     * @param answerFile correct answer corresponding to each input time value
     */
    public static void evaluateParkingLot(String inputFile, String outputFile) {
        BufferedReader inputReader = null;
        BufferedReader outputReader = null;
        int lineNumInput = 1;
        ParkingLotMgr parkingLotManager = new ParkingLotMgr();
        boolean hasWrong = false;
        try {
            inputReader = new BufferedReader(new InputStreamReader(Utils.getFileFromResourceAsStream(inputFile), StandardCharsets.UTF_8));
            outputReader = new BufferedReader(new InputStreamReader(Utils.getFileFromResourceAsStream(outputFile), StandardCharsets.UTF_8));
            String input;
            String expectedOutput;
            while ((input = inputReader.readLine()) != null
                    &&(expectedOutput = outputReader.readLine()) != null) {
                String actualOutput = parkingLotManager.giveCommand(input);
                if(!actualOutput.equals(expectedOutput)) {
                    hasWrong = true;
                    System.out.println("Wrong answer at line " + lineNumInput + " with input \"" + input + "\": " + actualOutput);
                    System.out.println("Expected: " + expectedOutput);
                }
                lineNumInput++;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            fail("Null pointer exception occured, maybe some test cases doesn't have answer yet?");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("File not found exception occured");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            fail("IOexception occured");
        } finally {
            try { 
                inputReader.close();
                outputReader.close();
            } catch (IOException ioe) {
                fail("Error closing BufferedReader");
            }
        }
        if (hasWrong) fail("There's a wrong answer, check output for more info");
    }
}
