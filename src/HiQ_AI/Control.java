package HiQ_AI;

import java.util.*;

public class Control {
    private static BoardState root;
    private static boolean win = false;
    private final static int MAX_GEN = 31;

    private static char defaultboard[][] = {
            {'B', 'B', 'P', 'P', 'P', 'B', 'B'},
            {'B', 'B', 'P', 'P', 'P', 'B', 'B'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'E', 'P', 'P', 'P'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'B', 'B', 'P', 'P', 'P', 'B', 'B'},
            {'B', 'B', 'P', 'P', 'P', 'B', 'B'}
    };

    private static char winBoard[][] = {
            {'B', 'B', 'E', 'E', 'E', 'B', 'B'},
            {'B', 'B', 'E', 'E', 'E', 'B', 'B'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'P', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'B', 'B', 'E', 'E', 'E', 'B', 'B'},
            {'B', 'B', 'E', 'E', 'E', 'B', 'B'}
    };

    private static int pointBoard[][] = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };

    public static void startGame(){
        Control.root = new BoardState(defaultboard, 0);
        checkMoves(root);
    }

    public static boolean checkMoves(BoardState currentboard){
        // base case
        if(currentboard.getGeneration() == MAX_GEN && checkWinCondition(currentboard)){
            printCurrentBoard(currentboard);
            return true;
        } // end of if statement

        for(int xcoor = 0; xcoor < currentboard.getBoard().length; xcoor++){
            for(int ycoor = 0; ycoor < currentboard.getBoard()[xcoor].length; ycoor++){
                if(currentboard.getBoard()[xcoor][ycoor] == 'E'){
                    checkUp(currentboard, xcoor, ycoor);
                    checkLeft(currentboard, xcoor, ycoor);
                    checkDown(currentboard, xcoor, ycoor);
                    checkRight(currentboard, xcoor, ycoor);
                } // end of if statement
            } // end of for loop
        } // end of for loop

        sortMoves(currentboard);

        while(!currentboard.getMoves().isEmpty()){
            nextGeneration(currentboard);
            if(checkMoves(currentboard.getNextState())){
                printCurrentBoard(currentboard);
                return true;
            } // end of if statement
        } // end of while loop

        return false; // path is a dead end
    }

    private static boolean checkUp(BoardState board, int xcord, int ycord){
        if(board.getBoard()[xcord][ycord+1] == 'P' && board.getBoard()[xcord][ycord+2] == 'P'){
            Config temp = new Config(xcord, ycord+2, xcord, ycord+1, xcord, ycord);
            temp.setValue(getValueOfState(board, temp));
        }
        return true;
    }

    private static boolean checkLeft(BoardState board, int xcord, int ycord){

        return true;
    }

    private static boolean checkDown(BoardState board, int xcord, int ycord){

        return true;
    }

    private static boolean checkRight(BoardState board, int xcord, int ycord){

        return true;
    }

    private static int getValueOfState(BoardState board, Config move){
        int value = 0;
        for(int xcord = 0; xcord < pointBoard.length; xcord++){
            for(int ycord = 0; ycord < pointBoard[xcord].length; ycord++){
                value += pointBoard[xcord][ycord];
            } // end of for loop
        } // end of for loop

        value -= pointBoard[move.getFromX()][move.getFromY()];
        value -= pointBoard[move.getRemovX()][move.getRemovY()];
        value += pointBoard[move.getToX()][move.getToY()];

        return value;
    }

    private static boolean checkWinCondition(BoardState board){
        for(int xcoor = 0; xcoor < board.getBoard().length; xcoor++){
            for(int ycoor = 0; ycoor < board.getBoard()[xcoor].length; ycoor++){
                if(board.getBoard()[xcoor][ycoor] != winBoard[xcoor][ycoor])
                    return false;
            }
        }
        Control.win = true;
        return true;
    }

    private static void printCurrentBoard(BoardState board){
        for(int xcoor = 0; xcoor < board.getBoard().length; xcoor++){
            for(int ycoor = 0; ycoor < board.getBoard()[xcoor].length; ycoor++){
                System.out.print(board.getBoard()[xcoor][ycoor] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void sortMoves(BoardState currentboard){

    }

    private static void  nextGeneration(BoardState currentboard){

    }
}