package edu.gonzaga;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board implements ActionListener {
    private String[][] entireBoardArray;
    private JTextArea board;
    private JLabel teamIcon;
    private JButton[] columnNums;

    private int boardSize;

    Font newFont = new Font("Roboto", Font.BOLD, 35);
    Font columnFont = new Font("Roboto", Font.BOLD, 20);

    Board(){

        // creates and initializes a text area that displays the board
        board = new JTextArea();
        board.setVisible(false);
        board.setFont(newFont);
        board.setEditable(false);

        // imports and configures the background image
        teamIcon = new JLabel(new ImageIcon("src/main/java/edu/gonzaga/Images/teamIcon.jpeg"));
        teamIcon.setBounds(0, -28, 1000, 1000);
        teamIcon.setVisible(true);

        // imports and configures the columns so the players can choose which column to add their tile to
        
        columnNums = new JButton[9];
        for(int i = 0; i < 9; i++){
            columnNums[i] = new JButton("" + (i + 1));
            columnNums[i].setVisible(false);
            columnNums[i].setFont(columnFont);
            columnNums[i].addActionListener(this);
            // columnNums[i].setBounds(50, 50,100, 100);
        }




    }

    public boolean fourConnected(){


        return false;
    }


    private void placeChip(){


    }

    public void printEntireBoard(){
        for(int i = 0; i < entireBoardArray.length; i++){
            for(int j = 0; j < entireBoardArray[i].length; j++){
                board.append("| " + entireBoardArray[i][j] + " ");
                
            }
            board.append("|\n");
        }

        for(int i = 0; i < entireBoardArray[1].length; i++){
            if(boardSize == 0){
                columnNums[i].setBounds((84*i) + 210 , 300,75, 75);
                columnNums[i].setVisible(true);
            }else if(boardSize == 1){
                columnNums[i].setBounds((85*i) + 155 , 260,75, 75);
                columnNums[i].setVisible(true);
            }else{
                columnNums[i].setBounds((84*i) + 110 , 215,75, 75);
                columnNums[i].setVisible(true);
            }
        }

        
        board.setVisible(true);


    }

    public void createEntireBoard(int size, JFrame frame){

        boardSize = size;

        if(boardSize == 0){
            entireBoardArray = new String[6][7];
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 7; j++){
                    entireBoardArray[i][j] = " - ";
                    
                }
            }

            board.setBounds(200, 400, 600, 260);

        }

        if(boardSize == 1){
            entireBoardArray = new String[7][8];
            for(int i = 0; i < 7; i++){
                for(int j = 0; j < 8; j++){
                    entireBoardArray[i][j] = " - ";
                }
            }
            board.setBounds(150, 350, 690, 310);
        }
        
        if(boardSize == 2){
            entireBoardArray = new String[8][9];
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 9; j++){
                    entireBoardArray[i][j] = " - ";
                }
            }
            board.setBounds(100, 300, 770, 360);
        }


        frame.add(board);   
        for(int i = 0; i < 9; i++){
            frame.add(columnNums[i]);
        }
        frame.add(teamIcon);

        

    }

    public String[] getBoardColumn(){

        String test[] = null;
        return test;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
