package HiQ_AI;

import java.lang.StackOverflowError;
import java.lang.Error;

public class Lab1_Main {
    public static void main(String args[]){
        try {
            Control.startGame();
        } catch (Error err) {
            System.out.println("error");
        }
    }
}
