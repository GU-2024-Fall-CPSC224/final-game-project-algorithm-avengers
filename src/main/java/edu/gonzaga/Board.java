package edu.gonzaga;
import java.util.Arrays;

public class Board {
    private String[][] entireBoard;

    public boolean fourConnected(){


        return false;
    }


    private void placeChip(){


    }

    public void printEntireBoard(){
        for(int i = 0; i < entireBoard[0].length; i++){
            
        }

    }

    public void createEntireBoard(char boardSize){
        // S = small | M = medium | L = large

        if(boardSize == 'S'){
            entireBoard = new String[7][6];
        }

        if(boardSize == 'M'){
            entireBoard = new String[8][7];
        }
        
        if(boardSize == 'L'){
            entireBoard = new String[9][8];
        }

        Arrays.fill(entireBoard, "  ");

    }

    public String[] getBoardColumn(){

        String test[] = null;
        return test;
    }
}
