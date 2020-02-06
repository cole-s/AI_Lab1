package HiQ_AI;

import java.util.ArrayList;

/**
 * @author Cole Schaar
 * CS-481 Artificial Intelligence
 *
 * Class: BoardState
 *
 * Class used to store the different states of the tree while trying to find a solution to the Hi-Q puzzle
 */

public class BoardState {
    private final int SIZE = 7; // max size of the board 2D array
    private BoardState nextstate; // next branch state
    private char board[][] = new char[SIZE][SIZE]; // peg board
    private int generation = 0; // generation number/move counter
    private ArrayList<Config> moves = new ArrayList<Config>(); // list of all possible moves at current state
    private int value;

    /**
     * Constructor BoardState
     * @param board - 2D array of values containing the new board state
     * @param generation - current generation number
     */
    public BoardState(char board[][], int generation, int value){
        copyBoard(board); // copies contents of the board to ensure no referencing of values/arrays
        this.generation = generation;
        this.value = value;
    } // end of constructor

    // Getters and setters used for the private variables of the object
    public BoardState getNextState() { return nextstate; }
    public void setNextState(BoardState nextstate) { this.nextstate = nextstate; }
    public char[][] getBoard() { return board; }
    public int getGeneration() { return generation; }
    public ArrayList<Config> getMoves() { return moves; }
    public int getValue() { return this.value; }

    /**
     * Method: copyBoard
     * @param board - board to be copied from
     * @return true - if successful (debug purposes)
     *
     * copies contents of board given to the board 2D array of the current object
     */
    private boolean copyBoard(char board[][]){

        int xindex = 0; // x coordinate of board
        int yindex = 0; // y coordinate of board

        for(yindex = SIZE-1; yindex >= 0; yindex--){
            for(xindex = 0; xindex <SIZE; xindex++){
                this.board[xindex][yindex] = board[xindex][yindex];
            } // end of for loop
        } // end of for loop

        return true;
    } // end of copyBoard method

    public String toString(){
        String ret = "";

        for(int yindex = SIZE-1; yindex >= 0; yindex--){
            for(int xindex = 0; xindex < SIZE; xindex++){
                ret += board[xindex][yindex];
            } // end of for loop
        } // end of for loop

        return ret;
    } // end of toString method
} // end of BoardState class