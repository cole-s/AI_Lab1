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
    public static void main(String args[]){
        long starttime = System.nanoTime(); // notes when program started
        try {
            Control.startGame(); // start the program
        } catch (Error err) { // did something go terribly wrong
            System.out.println("error");
        } // end of try catch statements
        long endtime = System.nanoTime(); // notes when program ended
        long timeelapsed = endtime - starttime; // figures out execution time of program
        System.out.println("Time taken: " + (timeelapsed/1000000000.0) + " seconds"); // prints result for user
    } // end of main method
} // end of Lab1_Main class
