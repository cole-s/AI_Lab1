package HiQ_AI;

import java.lang.StackOverflowError;
import java.lang.Error;
/**
 * @author Cole Schaar
 * CS-481 Artificial Intelligence
 *
 * Class: Lab1_Main
 *
 * Class used just to start the program
 */
public class Lab1_Main {
    public static void main(String args[]){
        try {
            Control.startGame(); // start the program
        } catch (Error err) { // did something go terribly wrong
            System.out.println("error");
        } // end of try catch statements
    } // end of main method
} // end of Lab1_Main class
