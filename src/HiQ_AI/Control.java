package HiQ_AI;

import java.lang.StackOverflowError;
import java.lang.Error;
import java.util.Hashtable;

/**
 * @author Cole Schaar
 * CS-481 Artificial Intelligence
 *
 * Class: Control
 *
 * Class filled with static methods to act as a controller/brain for the entire program. Handles all other classes
 */
public class Control {
    private static BoardState root; // root of the tree/list
    private final static int MAX_GEN = 31; // max number of moves
    private final static char PEG = 'P'; // characters used when printing out board
    private final static char EMPTY = 'E';
    private final static char BLANK = ' ';
    private final static int BOARD_SIZE = 7; // size of the sides of the 2D array
    private final static int STARTING_POINTS = 48; // starting amount of points on the default board
    private static Hashtable exploredstates; // keeps track of board states already explored to avoid looping

    // the following variables are not needed for the functionality of the code, only purpose is to track performance
    public static double starttime; // keeps track of time when program started in nanoseconds
    public static double endtime; // keeps track of time when program ends in nanoseconds
    public static double runtime; // stores the total time taken for the program to run

    // Conditions for Start, End, and Points while moving through the tree
    private static char[][] defaultboard = {
            {BLANK, BLANK, PEG, PEG, PEG, BLANK, BLANK},
            {BLANK, BLANK, PEG, PEG, PEG, BLANK, BLANK},
            {PEG, PEG, PEG, PEG, PEG, PEG, PEG},
            {PEG, PEG, PEG, EMPTY, PEG, PEG, PEG},
            {PEG, PEG, PEG, PEG, PEG, PEG, PEG},
            {BLANK, BLANK, PEG, PEG, PEG, BLANK, BLANK},
            {BLANK, BLANK, PEG, PEG, PEG, BLANK, BLANK}
    };

    private static char[][] winBoard = {
            {BLANK, BLANK, EMPTY, EMPTY, EMPTY, BLANK, BLANK},
            {BLANK, BLANK, EMPTY, EMPTY, EMPTY, BLANK, BLANK},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, PEG, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLANK, BLANK, EMPTY, EMPTY, EMPTY, BLANK, BLANK},
            {BLANK, BLANK, EMPTY, EMPTY, EMPTY, BLANK, BLANK}
    };

    private static int[][] pointBoard = {
            {0, 0, 1, 1, 1, 0, 0}, // 3
            {0, 0, 1, 2, 1, 0, 0}, // 4 // 7
            {1, 1, 2, 3, 2, 1, 1}, // 11 // 18
            {1, 2, 3, 5, 3, 2, 1}, // 17 // 35
            {1, 1, 2, 3, 2, 1, 1}, // 11 // 46
            {0, 0, 1, 2, 1, 0, 0}, // 4 // 50
            {0, 0, 1, 1, 1, 0, 0}  // 3 // 53
    };

    /**
     * Method: startGame
     *
     * creates base conditions for the program
     */
    public static void startGame(){
        starttime = System.nanoTime();
        exploredstates = new Hashtable(); // initialize Hashtable object
        Control.root = new BoardState(defaultboard, 0, STARTING_POINTS);
        checkMoves(root);
    } // end of startGame method

    /**
     * Method: checkMoves
     * @param currentboard
     * @return true - solution was found
     *         false - solution was not found
     *
     * Recursive function that checks the available moves the program can take
     */
    private static boolean checkMoves(BoardState currentboard){
        try {
            // base cases
            if ((currentboard.getGeneration() == MAX_GEN) && checkWinCondition(currentboard)) {
                endtime = System.nanoTime();
                printCurrentBoard(currentboard);
                return true;
            } else if (currentboard.getGeneration() == MAX_GEN) {
                System.out.println("Generation: " + currentboard.getGeneration());
                printCurrentBoard(currentboard);
                return false;
            } else if (isExplored(currentboard)) {
                return false;
            } else {
                exploredstates.put(currentboard.toString(), 'E');
            } // end of if-else statements

            // end of base cases

            // check each square of the board for valid moves
            for (int xcoor = 0; xcoor < BOARD_SIZE; xcoor++) {
                for (int ycoor = 0; ycoor < BOARD_SIZE; ycoor++) {
                    if (currentboard.getBoard()[xcoor][ycoor] == EMPTY) {
                        checkUp(currentboard, xcoor, ycoor); // checks each direction from an EMPTY tile
                        checkLeft(currentboard, xcoor, ycoor);
                        checkDown(currentboard, xcoor, ycoor);
                        checkRight(currentboard, xcoor, ycoor);
                    } // end of if statement
                } // end of for loop
            } // end of for loop

            sortMoves(currentboard);    // sorts the list of moves available to the program based on value
            boolean unnecessary = false; // to ensure no unnecessary paths are taken
            // if we go back to root and pick new path something when wrong

            // while there are still moves/branches to explore
            while (!currentboard.getMoves().isEmpty() && !unnecessary) {
                nextGeneration(currentboard); // ready next branch
                if (checkMoves(currentboard.getNextState())) { // recursive call back
                    printCurrentBoard(currentboard);
                    return true; // solution was found
                }  // end of if statement
                if(currentboard.getGeneration() == 0){ // did we return to the root function
                    unnecessary = true;
                    System.out.println("Something went really wrong - went back to generation 0");
                } // end of if statement
            } // end of while loop
        } catch(StackOverflowError err){ // in-case there is a stack overflow error
            // print info of current failed state to figure out issue
            System.out.println("Fail");
            printCurrentBoard(currentboard);
            System.out.println("Generation: " + currentboard.getGeneration());
            throw new Error("Something went Wrong");
        } catch(Error err){ // to ensure no multiple prints of what went wrong
            return false;
        } // end of try-catch statements
        return false; // path is a dead end
    } // end of checkMoves method

    /**
     * Method(s): check___
     * @param board - current board being used to find legal moves
     * @param xcord - x coordinate on board
     * @param ycord - y coordinate on board
     *
     * these check methods check each position given to them in the form of x and y cordinates to find moves available
     * to the program.
     */
    private static void checkUp(BoardState board, int xcord, int ycord){

        if((ycord+2 < BOARD_SIZE) && (ycord+1 < BOARD_SIZE)) {
            if (board.getBoard()[xcord][ycord + 1] == PEG && board.getBoard()[xcord][ycord + 2] == PEG) {
                Config temp = new Config(xcord, ycord + 2, xcord, ycord + 1, xcord, ycord);
                temp.setValue(getValueOfState(board, temp));
                board.getMoves().add(temp);
            } // end of if statement
        } // end of if statement
        return;
    } // end of checkUp method

    private static void checkLeft(BoardState board, int xcord, int ycord){

        if((xcord-2 >= 0) && (xcord-1 >= 0)) {
            if (board.getBoard()[xcord - 1][ycord] == PEG && board.getBoard()[xcord - 2][ycord] == PEG) {
                Config temp = new Config(xcord - 2, ycord, xcord - 1, ycord, xcord, ycord);
                temp.setValue(getValueOfState(board, temp));
                board.getMoves().add(temp);
            } // end of if statement
        } // end of if statement

        return;
    } // end of checkLeft method

    private static void checkDown(BoardState board, int xcord, int ycord){

        if((ycord-2 >= 0) && (ycord-1 >= 0)) {
            if (board.getBoard()[xcord][ycord - 1] == PEG && board.getBoard()[xcord][ycord - 2] == PEG) {
                Config temp = new Config(xcord, ycord - 2, xcord, ycord - 1, xcord, ycord);
                temp.setValue(getValueOfState(board, temp));
                board.getMoves().add(temp);
            } // end of if statement
        } // end of if statement

        return;
    } // end of checkDown method

    private static void checkRight(BoardState board, int xcord, int ycord){

        if((xcord+2 < BOARD_SIZE) && (xcord+1 < BOARD_SIZE)) {
            if (board.getBoard()[xcord + 1][ycord] == PEG && board.getBoard()[xcord + 2][ycord] == PEG) {
                Config temp = new Config(xcord + 2, ycord, xcord + 1, ycord, xcord, ycord);
                temp.setValue(getValueOfState(board, temp));
                board.getMoves().add(temp);
            } // end of if statement
        } // end of if statement

        return;
    } // end of checkRight method

// END OF CHECK DIRECTION METHODS=======================================================================================

    /**
     * Method: getValueOfState
     * @param board - board being used to identify value of move
     * @param move - Config object that contains the information about next branch
     * @return value of the move that the AI could choose
     *
     * Looking at the current board state and using the point based board the value of a move would be calculated here
     */
    private static int getValueOfState(BoardState board, Config move){
        int value = board.getValue();
        // changing values based on where the movement takes place
        value -= pointBoard[move.getFromX()][move.getFromY()];
        value -= pointBoard[move.getRemovX()][move.getRemovY()];
        value += pointBoard[move.getToX()][move.getToY()];

        return value;
    } // end of getValueOfState method

    /**
     * Method: checkWinCondition
     * @param board - current board being checked for win conditions to be meet
     * @return false - board does not meet win condition
     * @return true - board does meet win condition
     *
     * Checks each value of the board to ensure if the program has reached a solution
     */
    private static boolean checkWinCondition(BoardState board){
        for(int xcoor = 0; xcoor < board.getBoard().length; xcoor++){
            for(int ycoor = 0; ycoor < board.getBoard()[xcoor].length; ycoor++){
                if(board.getBoard()[xcoor][ycoor] != winBoard[xcoor][ycoor])
                    return false;
                // end of if statement
            } // end of for loop
        } // end of for loop
        return true;
    } // end of checkWinCondition method

    /**
     * Method: printCurrentBoard
     * @param board - current board state
     *
     * prints current board state to the terminal
     */
    private static void printCurrentBoard(BoardState board){
        for(int ycoor = BOARD_SIZE -1; ycoor >= 0; ycoor--){
            for(int xcoor = 0; xcoor < BOARD_SIZE; xcoor++){
                System.out.print(board.getBoard()[xcoor][ycoor] + " ");
            } // end of for loop
            System.out.println();
        } // end of for loop
        System.out.println();
    } // end of printCurrentBoard method

    /**
     * Method: sortMoves
     * @param currentboard - current state/node of the search algorithm
     *
     * sorts the Config ArrayList by value
     */
    private static void sortMoves(BoardState currentboard){
        boolean sorted = false; // flag used to identify if the ArrayList is sorted

        // bubble sort
        do{
            sorted = true;
            for(int index = 0; index < currentboard.getMoves().size()-1; index++){
                if(currentboard.getMoves().get(index).getValue() < currentboard.getMoves().get(index+1).getValue()){
                    Config temp = new Config(currentboard.getMoves().get(index));
                    currentboard.getMoves().set(index, currentboard.getMoves().get(index+1));
                    currentboard.getMoves().set(index+1, temp);
                    sorted = false;
                } // end of if statement
            } // end of for loop
        } while(!sorted); // end of bubble sort

    } // end of sortMoves method

    /**
     * Method: nextGeneration
     * @param currentboard - board to be linked with next move/generation
     *
     * takes from the moves ArrayList from the BoardState class object the next branch of the tree is selected and used
     * to create the next board state in the tree.
     */
    private static void  nextGeneration(BoardState currentboard){

        if(!currentboard.getMoves().isEmpty()) {
            // gets needed information for the new board.
            BoardState next = new BoardState(currentboard.getBoard(), currentboard.getGeneration() + 1,
                    currentboard.getMoves().get(0).getValue());

            int fromx = currentboard.getMoves().get(0).getFromX();
            int fromy = currentboard.getMoves().get(0).getFromY();
            int removx = currentboard.getMoves().get(0).getRemovX();
            int removy = currentboard.getMoves().get(0).getRemovY();
            int tox = currentboard.getMoves().get(0).getToX();
            int toy = currentboard.getMoves().get(0).getToY();

            // generates the legal move for the next branch's board state
            next.getBoard()[fromx][fromy] = EMPTY;
            next.getBoard()[removx][removy] = EMPTY;
            next.getBoard()[tox][toy] = PEG;

            // sets next branch for recursive call
            currentboard.setNextState(next);
            currentboard.getMoves().remove(0); // removes already explored move/branch from ArrayList

        } // end of if statement
    } // end of nextGeneration method

    /**
     * Method: isExplored
     * @param board - current board state
     * @return  true if board state is already in exploredstates table
     *          false if board state is not in exploredstates table
     *
     * Checks to see if current board state has already been explored fully
     */
    private static boolean isExplored(BoardState board){
        return exploredstates.get(board.toString()) != null;
    } // end of isExplored method

} // end of Control class