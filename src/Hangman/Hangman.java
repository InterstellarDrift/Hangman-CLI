package Hangman;

import java.util.Objects;

public class Hangman {

    public Hangman() {

    }

    int maxBadGuesses = 5;


    public Hangman(int maxBadGuesses) {
        this.maxBadGuesses = maxBadGuesses;
    }

    String[] wordList = {
            "SPACE",
            "MARTIANS",
            "LETTUCE",
            "PEPPERONI",
            "PIZZA",
            "ITALY",
            "FRANCE",
            "LARRY",
            "CUCUMBER"
    };

    private int randomWordIndex() {
        double random = Math.random();
        int listSize = wordList.length-1;

        return (int) Math.round(random*listSize);

    }

    private String getFilteredInput() {
        String finalInput = "";
        boolean safe = false;
        do {
            String input = InputHandler.promptInput("Please type in your guess letter: ").toUpperCase();

            char char1 = ' ';

            if(input.length() == 1) {
                char1 = input.charAt(0);
                safe = !Objects.equals(input, "-1")  && char1 >='A' && char1 <= 'Z';

            }


            finalInput = Character.toString(char1);

        } while (!safe);

        return finalInput;

    }



    public void runLoop() {
        printWelcome();

        String chosenWord = wordList[randomWordIndex()].toUpperCase();

        StringBuilder guessedLetters = new StringBuilder();

        int badGuessCount = 0;

        boolean[] guessMap = new boolean[chosenWord.length()];

        printRevealed(guessMap, chosenWord);


        while (true) {

            String input = getFilteredInput();


            if(chosenWord.contains(input)) {
                System.out.println("Good Guess!");

                for(int i = 0; i < chosenWord.length(); i++) {
                    if(Character.toString(chosenWord.charAt(i)).contains(input)) {
                        guessMap[i] = true;
                    }

                }
            } else {
                badGuessCount++;
                if(!guessedLetters.toString().contains(input)) {
                    guessedLetters.append(input+" ");
                }
                System.out.println("INCORRECT");
            }

            System.out.println("Incorrect Guess Count: "+ badGuessCount);
            System.out.println("Incorrect Guesses Remaining: "+ (maxBadGuesses-badGuessCount));

            printRevealed(guessMap, chosenWord);
            System.out.println("Incorrectly Guessed Letters: " + guessedLetters);


            if(isComplete(guessMap)) {
                System.out.println("YOU WIN!!!!");
                break;
            }


            if(badGuessCount >= maxBadGuesses) {
                System.out.println("GAME OVER");
                break;
            }



        }

        String promptedInput = InputHandler.promptInput("Play again? (y/*): ");

        if(Objects.equals(promptedInput, "y")) {
            runLoop();
        }



    }

    private void printWelcome() {

        System.out.println("Welcome to Terminal Hangman!");

    }


    private void printRevealed(boolean[] map, String word) {

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < word.length(); i++) {

            if(map[i]) {
                stringBuilder.append(word.charAt(i));
            } else {
                stringBuilder.append("_");
            }

        }

        System.out.println(stringBuilder);

    }

    private boolean isComplete(boolean[] map) {
        boolean isOK = true;
        for (boolean b : map) {
            if(!b) {
                isOK = false;
            }
        }

        return isOK;
    }

}
