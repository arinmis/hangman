import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  This program is game named hangman. Program
 *  will pull the words from "hangman.txt" and 
 *  player try to guess what it is.
 *
 *
 *  @author Mustafa Arinmis
 *  @since 15.11.2020
 * */

public class Hangman {
    public static void main(String[] args) {

        ArrayList<String> words = new ArrayList<>();
        File file = new File("hangman.txt");


        try{
            Scanner input = new Scanner(file);

            while(input.hasNext()) {
                words.add(input.next());
            }
        }catch (FileNotFoundException ex) {
            System.out.println("hangman.txt does not exist");        }

        Scanner input = new Scanner(System.in);

        StringBuffer wordOutput;
        char guess;


        for(int i=0; i<words.size(); i++) {
            int numOfMistake = 0;
            wordOutput = new StringBuffer(createBuffer(words.get(i).length()));

            while(true) {
                System.out.println("enter you guess letter in the string : "
                        + wordOutput);
                guess = input.next().charAt(0);

                if(!makeAppear(words.get(i), wordOutput, guess)) {
                    System.out.println(guess + " is not in the word");
                    numOfMistake++;
                }


                if( wordOutput.toString().equals(words.get(i))) {
                    System.out.println("The word is " + words.get(i) +
                            " you missed " + numOfMistake+ " time");
                    break;
                }

            }

            if(i == words.size() - 1) {
                System.out.println("I don't have anymore word");
                break;
            }

            System.out.println("Do you want to guess another word? Enter y or n>");

            if(!input.next().equals("y"))
                break;
        }
    }

    public static boolean makeAppear(String string, StringBuffer stringBuffer, char chr ) {

        boolean isEdited = false;
        for(int i=0;i<string.length(); i++) {
            if(string.charAt(i) == chr) {
                stringBuffer.setCharAt(i, chr);
                isEdited = true;
            }
        }

        return isEdited;
    }

    public static String createBuffer(int len) {
        // create target string's unknown representation
        // Example: word --> ****
        String temp ="";
        for(int i=0; i<len; i++) {
            temp += "*";
        }

        return temp;

    }
}
