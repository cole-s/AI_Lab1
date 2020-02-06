package HiQ_AI;

/**
 * @author Cole Schaar
 * CS-481 Artificial Intelligence
 *
 * Class: Lab1_Main
 *
 * Class used just to start the program that is used to solve the game of Hi-Q in real time using a depth-first search
 * approach on a tree data-structure.
 */
public class Lab1_Main {
    public static void main(String[] args) {
        try {
            Control.startGame(); // start the program
        } catch (Error err) { // did something go terribly wrong
            System.out.println("error");
        } // end of try catch statements

        // code here just to print out run time with variables not important to the functionality of the program
        Control.runtime = Control.endtime - Control.starttime;
        // prints the time taken for the program to start at the base board state and find the path to the goal state
        System.out.println("Time taken: " + (Control.runtime/1000000000.0) + " seconds");
    } // end of main method
} // end of Lab1_Main class
