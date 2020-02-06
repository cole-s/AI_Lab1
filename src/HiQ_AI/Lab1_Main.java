package HiQ_AI;

/**
 * @author Cole Schaar
 * CS-481 Artificial Intelligence
 *
 * Class: Lab1_Main
 *
 * Class used just to start the program
 */
public class Lab1_Main {
    public static void main(String[] args) {
        try {
            Control.startGame(); // start the program
        } catch (Error err) { // did something go terribly wrong
            System.out.println("error");
        } // end of try catch statements
        Control.runtime = Control.endtime - Control.starttime;
        System.out.println("Time taken: " + (Control.runtime/1000000000.0) + " seconds"); // prints result for user
    } // end of main method
} // end of Lab1_Main class
