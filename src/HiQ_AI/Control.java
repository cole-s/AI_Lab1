package HiQ_AI;

import java.util.*;
import java.lang.StackOverflowError;
import java.lang.Error;

public class Control {
    private static BoardState root;
    private static boolean win = false;
    private final static int MAX_GEN = 31;
    private final static char PEG = 'P';
    private final static char EMPTY = 'E';
    private final static char BLANK = 'B';
    private final static int SIZE = 7;

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
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {1, 1, 2, 3, 2, 1, 1},
            {1, 2, 3, 5, 3, 2, 1},
            {1, 1, 2, 3, 2, 1, 1},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0}
    };

    public static void startGame(){
        Control.root = new BoardState(defaultboard, 0);
        root.peg_num = 32;
        checkMoves(root);
    } // end of startGame method

    private static boolean checkMoves(BoardState currentboard){
        try {
            // base case
            if ((currentboard.getGeneration() == MAX_GEN || currentboard.peg_num == 1) && checkWinCondition(currentboard)) {
                printCurrentBoard(currentboard);
                return true;
            } else if (currentboard.getGeneration() == MAX_GEN) {
                System.out.println("Generation: " + currentboard.getGeneration());
                printCurrentBoard(currentboard);
                return false;
            }

            if(currentboard.getGeneration() == MAX_GEN) {
                System.out.println("GEN: " + currentboard.getGeneration() + " + PEGNUM: " + currentboard.peg_num + " = " + (currentboard.getGeneration() + currentboard.peg_num));
            }
            for (int xcoor = 0; xcoor < currentboard.getBoard().length; xcoor++) {
                for (int ycoor = 0; ycoor < currentboard.getBoard()[xcoor].length; ycoor++) {
                    if (currentboard.getBoard()[xcoor][ycoor] == EMPTY) {
                        checkUp(currentboard, xcoor, ycoor);
                        checkLeft(currentboard, xcoor, ycoor);
                        checkDown(currentboard, xcoor, ycoor);
                        checkRight(currentboard, xcoor, ycoor);
                    } // end of if statement
                } // end of for loop
            } // end of for loop

            sortMoves(currentboard);
            boolean unnecessary = false;
            while (!currentboard.getMoves().isEmpty() && !unnecessary) {
                nextGeneration(currentboard);
                if (checkMoves(currentboard.getNextState())) {
                    printCurrentBoard(currentboard);
                    return true;
                } else if(currentboard.getGeneration() == 0){
                    unnecessary = true;
                }
            } // end of while loop
        } catch(StackOverflowError err){
            System.out.println("Fail");
            printCurrentBoard(currentboard);
            System.out.println("Generation: " + currentboard.getGeneration());
            throw new Error("Something went Wrong");
        } catch(Error err){
            return false;
        }
        return false; // path is a dead end
    }

    private static void checkUp(BoardState board, int xcord, int ycord){
//        if(ycord+2 == 2 && xcord == 2){
//            System.out.println("Ham");
//        }
        if((ycord+2 < board.getBoard()[xcord].length) && (ycord+1 < board.getBoard().length)) {
            if (board.getBoard()[xcord][ycord + 1] == PEG && board.getBoard()[xcord][ycord + 2] == PEG) {
                Config temp = new Config(xcord, ycord + 2, xcord, ycord + 1, xcord, ycord);
                temp.setValue(getValueOfState(board, temp));
                temp.dir = 'U';
                board.getMoves().add(temp);
            } // end of if statement
        } // end of if statement
        return;
    } // end of checkUp method

    private static void checkLeft(BoardState board, int xcord, int ycord){
//        if(ycord == 2 && xcord-2 == 2){
//            System.out.println("Bacon");
//        }
        if((xcord-2 >= 0) && (xcord-1 >= 0)) {
            if (board.getBoard()[xcord - 1][ycord] == PEG && board.getBoard()[xcord - 2][ycord] == PEG) {
                Config temp = new Config(xcord - 2, ycord, xcord - 1, ycord, xcord, ycord);
                temp.setValue(getValueOfState(board, temp));
                temp.dir = 'L';
                board.getMoves().add(temp);
            } // end of if statement
        } // end of if statement
        
        return;
    } // end of checkLeft method

    private static void checkDown(BoardState board, int xcord, int ycord){
//        if(ycord-2 == 2 && xcord == 2){
//            System.out.println("Salami");
//        }
        if((ycord-2 >= 0) && (ycord-1 >= 0)) {
            if (board.getBoard()[xcord][ycord - 1] == PEG && board.getBoard()[xcord][ycord - 2] == PEG) {
                Config temp = new Config(xcord, ycord - 2, xcord, ycord - 1, xcord, ycord);
                temp.setValue(getValueOfState(board, temp));
                temp.dir = 'D';
                board.getMoves().add(temp);
            } // end of if statement
        } // end of if statement

        return;
    } // end of checkDown method

    private static void checkRight(BoardState board, int xcord, int ycord){
//        if(ycord == 2 && xcord+2 == 2){
//            System.out.println("Pork");
//        }
        if((xcord+2 < board.getBoard().length) && (xcord+1 < board.getBoard().length)) {
            if (board.getBoard()[xcord + 1][ycord] == PEG && board.getBoard()[xcord + 2][ycord] == PEG) {
                Config temp = new Config(xcord + 2, ycord, xcord + 1, ycord, xcord, ycord);
                temp.setValue(getValueOfState(board, temp));
                temp.dir = 'R';
                board.getMoves().add(temp);
            } // end of if statement
        } // end of if statement

        return;
    } // end of checkRight method

    private static int getValueOfState(BoardState board, Config move){
        int value = 0;
        for(int xcord = 0; xcord < pointBoard.length; xcord++){
            for(int ycord = 0; ycord < pointBoard[xcord].length; ycord++){
                if(board.getBoard()[xcord][ycord] == PEG) {
                    value += pointBoard[xcord][ycord];
                }
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
                // end of if statement
            } // end of for loop
        } // end of for loop
        Control.win = true;
        return true;
    } // end of checkWinCondition method

    private static void printCurrentBoard(BoardState board){
        for(int ycoor = SIZE-1; ycoor >= 0; ycoor--){
            for(int xcoor = 0; xcoor < SIZE; xcoor++){
                System.out.print(board.getBoard()[xcoor][ycoor] + " ");
            }
            System.out.println();
        }
        System.out.println();
    } // end of printCurrentBoard method

    private static void sortMoves(BoardState currentboard){
        boolean sorted = false;

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

    private static void  nextGeneration(BoardState currentboard){
//        if(currentboard.getMoves().get(0).getFromY() == 2 && currentboard.getMoves().get(0).getFromX() == 2){
//            System.out.println("DIR: " + currentboard.getMoves().get(0).dir);
//        }

        if(!currentboard.getMoves().isEmpty()) {
            BoardState next = new BoardState(currentboard.getBoard(), currentboard.getGeneration() + 1);
            int fromx = currentboard.getMoves().get(0).getFromX();
            int fromy = currentboard.getMoves().get(0).getFromY();
            int removx = currentboard.getMoves().get(0).getRemovX();
            int removy = currentboard.getMoves().get(0).getRemovY();
            int tox = currentboard.getMoves().get(0).getToX();
            int toy = currentboard.getMoves().get(0).getToY();

            next.getBoard()[fromx][fromy] = EMPTY;
            next.getBoard()[removx][removy] = EMPTY;
            next.getBoard()[tox][toy] = PEG;
            next.peg_num = currentboard.peg_num - 1;

            currentboard.setNextState(next);
//            System.out.println(next.peg_num + " " + checkPegs(next));
//
//            if(next.peg_num != checkPegs(next)){
//                System.out.println("FROM: " + fromx + ", " + fromy);
//                System.out.println("REMOVE: " + removx + ", " + removy);
//                System.out.println("TO: " + tox + ", " + toy);
//                System.out.println("Direction: " + currentboard.getMoves().get(0).dir);
//                System.out.println("Current Board: " + currentboard.getGeneration());
//                printCurrentBoard(currentboard);
//                System.out.println("Next Board: ");
//                printCurrentBoard(next);
//            }
//            System.out.println("Next Gen: " + currentboard.getNextState().getGeneration());
//            printCurrentBoard(currentboard.getNextState());
            currentboard.getMoves().remove(0);

        }
    } // end of nextGeneration method

    private static int checkPegs(BoardState board){
        int pegs = 0;
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                if(board.getBoard()[x][y] == PEG){
                    pegs++;
                }
            }
        }

        return pegs;
    }
} // end of Control class