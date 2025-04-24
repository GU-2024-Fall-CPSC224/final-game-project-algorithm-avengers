package edu.gonzaga;

public class Board {
    private String[][] entireBoard;

    public boolean fourConnected(){


        return false;
    }


    private void placeChip(){


    }

    public void printEntireBoard(){
        for(int i = 0; i < entireBoard.length; i++){
            for(int j = 0; j < entireBoard[i].length; j++){
                System.out.print(entireBoard[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void createEntireBoard(int boardSize){

        System.out.println("hello");
        if(boardSize == 0){
            System.out.println("1");
            entireBoard = new String[7][6];
            System.out.println("2");
            for(int i = 0; i < 7; i++){
                for(int j = 0; j < 6; j++){
                    entireBoard[i][j] = " - ";
                }
            }
        }
        System.out.println("3");

        if(boardSize == 2){
            entireBoard = new String[8][7];
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 7; j++){
                    entireBoard[i][j] = " - ";
                }
            }
        }
        
        if(boardSize == 3){
            entireBoard = new String[9][8];
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 8; j++){
                    entireBoard[i][j] = " - ";
                }
            }
        }

        

    }

    public String[] getBoardColumn(){

        String test[] = null;
        return test;
    }
}
