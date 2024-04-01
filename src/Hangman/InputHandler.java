package Hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHandler {

    private static final BufferedReader INPUT_READER = new BufferedReader(
            new InputStreamReader(System.in));


    public static String promptInput(String prompt) {
        System.out.println();
        System.out.print(prompt);


        String input;
        try {
            input = INPUT_READER.readLine();

        } catch (IOException e) {
            input = "-1";

        }

        return input;
    }
    public static int getPromptedID() {
        int promptedID;
        try {
            promptedID = Integer.parseInt(promptInput("Please input an item ID: "));
        } catch(NumberFormatException e) {
            promptedID = -1;
        }

        return promptedID;
    }
}
