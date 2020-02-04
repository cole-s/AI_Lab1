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

    public static void checkMoves(BoardState currentboard){
        if(currentboard.getGeneration() == MAX_GEN && checkWinCondition(currentboard)){
            printCurrentBoard(currentboard);
            return;
        }

        for(int xcoor = 0; xcoor < currentboard.getBoard().length; xcoor++){
            for(int ycoor = 0; ycoor < currentboard.getBoard()[xcoor].length; ycoor++){
                if(currentboard.getBoard()[xcoor][ycoor] == 'E'){
                    checkUp(currentboard, xcoor, ycoor);
                    checkLeft(currentboard, xcoor, ycoor);
                    checkDown(currentboard, xcoor, ycoor);
                    checkRight(currentboard, xcoor, ycoor);
                }
            }
        }
    }

    private static boolean checkUp(BoardState board, int xcord, int ycord){
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

    private static int getValueOfState(){
        int value = 0;

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
}