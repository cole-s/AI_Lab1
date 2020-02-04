package HiQ_AI;

import java.util.ArrayList;

/**
 *
 * @author casch
 */

public class BoardState {
    private final int SIZE = 7;
    private BoardState nextstate;
    private char board[][] = new char[SIZE][SIZE];
    private int value = 0;
    private int generation = 0;
    private ArrayList<Config> moves = new ArrayList<Config>();

    public BoardState(char board[][], int generation){
        copyBoard(board);
        this.generation = generation;
    }

    public BoardState getNextState() { return nextstate; }
    public void setNextState(BoardState nextstate) { this.nextstate = nextstate; }
    public char[][] getBoard() { return board; }
    public void setBoard(char[][] board) { this.board = board; }
    public int getGeneration() { return generation; }
    public void setGeneration(int generation) { this.generation = generation; }
    public ArrayList<Config> getMoves() { return moves; }
    public void setMoves(ArrayList<Config> moves) { this.moves = moves; }

    private boolean copyBoard(char board[][]){
        
        int xindex = 0;
        int yindex = 0;
        
        for(xindex = 0; xindex < board.length; xindex++){
            for(yindex = 0; yindex < board[xindex].length; yindex++){
                this.board[xindex][yindex] = board[xindex][yindex];
            }
        }
        
        return true;
    }

    public int getValue(int pointboard[][]){
        for(int xindex = 0; xindex < pointboard.length; xindex++){
            for(int yindex = 0; yindex < pointboard[0].length; yindex++){
                if (board[xindex][yindex] == 'P'){
                    this.value += pointboard[xindex][yindex];
                }
            }
        }

        return this.value;
    }



}
