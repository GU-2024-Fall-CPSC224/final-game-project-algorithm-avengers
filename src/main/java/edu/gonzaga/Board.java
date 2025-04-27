package edu.gonzaga;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;


public class Board implements ActionListener {
    private JFrame frame;
    private JButton[] columnNums;
    private JTextPane board;
    private StyledDocument doc;
    
    private String[][] entireBoardArray;
    private String token;
    private int boardSize;
    
    private MainGame mainGame;

    private Font columnFont = new Font("Roboto", Font.BOLD, 20);

    private Style nonTokenFont;
    private Style tokenFont;

    Board(){

        // imports and configures the columns so the players can choose which column to add their tile to
        columnNums = new JButton[9];
        for(int i = 0; i < 9; i++){
            columnNums[i] = new JButton("" + (i + 1));
            columnNums[i].setVisible(false);
            columnNums[i].setFont(columnFont);
            columnNums[i].addActionListener(this);
        }

        // imports and configures the board
        board = new JTextPane();
        doc = board.getStyledDocument();
        
        // imports and configures the fonts
        nonTokenFont = board.addStyle("nonTokenFont", null);
        StyleConstants.setFontFamily(nonTokenFont, "Roboto");
        StyleConstants.setFontSize(nonTokenFont, 45);
        StyleConstants.setBold(nonTokenFont, true);

        tokenFont = board.addStyle("tokenFont", null);
        StyleConstants.setFontFamily(tokenFont, "Roboto");
        StyleConstants.setFontSize(tokenFont, 38);
        StyleConstants.setBold(tokenFont, true);

        board.setVisible(false);
        board.setEditable(false);
        board.setVisible(false);
        

    }

    // returns needed variables
    public JTextPane getBoard(){return board;}

    public StyledDocument getDoc(){return doc;}

    public String[][] getBoardArray(){return entireBoardArray;}

    public Style getNonTokenFont(){return nonTokenFont;}

    public Style getTokenFont(){return tokenFont;}

    public JButton[] getColumnButtons(){return columnNums;} // needed for testing

    public void setMainGame(MainGame mg){ // so the board class can have access to the JFrame
        mainGame = mg;
    }

    public void setFrame(JFrame myFrame){ // adds the board to the frame
        frame = myFrame;
        frame.add(board);


        for(int i = 0; i < 9; i++){
            frame.add(columnNums[i]);
        }
    }

    public void setToken(String myToken){ // so the program knows which player's token gets added
        token = myToken;
    }

    public boolean fourConnected(){

        // 4 connected vertically
        for(int i = 0; i < entireBoardArray.length; i++){
            for(int j = 0; j < entireBoardArray[i].length - 3; j++){
                if((entireBoardArray[i][j].contains(token)) && (entireBoardArray[i][j + 1].contains(token)) && (entireBoardArray[i][j + 2].contains(token)) && (entireBoardArray[i][j + 3].contains(token))){
                    return true;
                }
            }
        }

        // 4 connected horizontally
        for(int i = 0; i < entireBoardArray.length - 3; i++){
            for(int j = 0; j < entireBoardArray[i].length; j++){
                if((entireBoardArray[i][j].contains(token)) && (entireBoardArray[i + 1][j].contains(token)) && (entireBoardArray[i + 2][j].contains(token)) && (entireBoardArray[i + 3][j].contains(token))){
                    return true;
                }
            }
        }

        // 4 connected diagonally with negative slope
        for(int i = 0; i < entireBoardArray.length - 3; i++){
            for(int j = 0; j < entireBoardArray[i].length - 3; j++){
                if((entireBoardArray[i][j].contains(token)) && (entireBoardArray[i + 1][j + 1].contains(token)) && (entireBoardArray[i + 2][j + 2].contains(token)) && (entireBoardArray[i + 3][j + 3].contains(token))){
                    return true;
                }
            }
        }

        // 4 connected diagonally with positive slope
        for(int i = 0; i < entireBoardArray.length - 3; i++){
            for(int j = entireBoardArray[i].length - 1; j > 2; j--){
                if((entireBoardArray[i][j].contains(token)) && (entireBoardArray[i + 1][j - 1].contains(token)) && (entireBoardArray[i + 2][j - 2].contains(token)) && (entireBoardArray[i + 3][j - 3].contains(token))){
                    return true;
                }
            }
        }
        
        return false;
    }
    


    private void placeToken(int column){

        for(int i = 0; i < entireBoardArray[column].length - 1; i++){
            if(!(entireBoardArray[column][i + 1].equals(" _ "))){ // gets to the end of the array to add a token
                entireBoardArray[column][i] = " " + token + " ";
                break;
            }else if(entireBoardArray[column][entireBoardArray[column].length - 1].equals(" _ ")){ // if the column has no tokens added, adds one to the end of the array
                entireBoardArray[column][entireBoardArray[column].length - 1] = " " + token + " ";
                break;
            }
        }
    }

    public void printEntireBoard(){

        try{ // resets the board so it can be "refreshed"
            doc.remove(0, doc.getLength());
        }catch(BadLocationException e){
            e.printStackTrace();
        }
        

        // adds the board columns and tokens to the board
        for(int i = 0; i < entireBoardArray[0].length; i++){
            for(int j = 0; j < entireBoardArray.length; j++){
                try{
                    doc.insertString(doc.getLength(), "|", nonTokenFont);
                    if(entireBoardArray[j][i].equals(" _ ")){
                        doc.insertString(doc.getLength(), entireBoardArray[j][i], nonTokenFont);
                    }else{
                        doc.insertString(doc.getLength(), entireBoardArray[j][i], tokenFont);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            try{
                doc.insertString(doc.getLength(), "|\n", nonTokenFont);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        board.setVisible(true);

        // adds the buttons for the players to select
        for(int i = 0; i < entireBoardArray.length; i++){
            if(boardSize == 0){
                columnNums[i].setBounds((71*i) + 250 , 290,65, 65);
                columnNums[i].setVisible(true);
            }else if(boardSize == 1){
                columnNums[i].setBounds((71*i) + 205 , 290,65, 65);
                columnNums[i].setVisible(true);
            }else{
                columnNums[i].setBounds((71*i) + 180 , 255,65, 65);
                columnNums[i].setVisible(true);
            }
        }

    }

    public void createEntireBoard(int size){ // initializes the board depending on which size the user selected

        boardSize = size;

        if(boardSize == 0){ // small board size
            entireBoardArray = new String[7][6];
            for(int i = 0; i < 7; i++){
                for(int j = 0; j < 6; j++){
                    entireBoardArray[i][j] = " _ ";
                    
                }
            }
            board.setBounds(240, 365, 525, 340);

        }

        if(boardSize == 1){ // medium board size
            entireBoardArray = new String[8][7];
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 7; j++){
                    entireBoardArray[i][j] = " _ ";
                }
            }
            board.setBounds(195, 365, 605, 400);
        }
        
        if(boardSize == 2){ // large board size
            entireBoardArray = new String[9][8];
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 8; j++){
                    entireBoardArray[i][j] = " _ ";
                }
            }
            board.setBounds(170, 330, 665, 450);
        }

    }

    public boolean gameOver(){ // if the board is completely filled up with no 4 connected
        for(int i = 0; i < entireBoardArray.length; i++){
            for(int j = 0; j < entireBoardArray[i].length; j++){
                if(entireBoardArray[i][j].equals(" _ ")){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < 9; i++){ // if the player selects a column number
            if(e.getSource() == columnNums[i]){
                placeToken(i);
                if(!fourConnected() && !gameOver()){ // this is what allows the code to reiterate through the players
                    if(mainGame.playerIndex == mainGame.numPlayers){  // 1st players turn or player index goes above the number of players playing
                        mainGame.playerIndex = 0;
                        mainGame.playGame(0);
                    }else if(mainGame.playerIndex == 1){ // 2nd player's turn
                        mainGame.playGame(1);
                    }else if(mainGame.playerIndex == 2){ // 3rd player's turn
                        mainGame.playGame(2);
                    }else if(mainGame.playerIndex == 3){ // 4th player's turn
                        mainGame.playGame(3);
                    }
                }else{ // if 4 tokens are connected
                    for(int j = 0; j < 9; j++){ 
                        columnNums[j].setVisible(false); // sets the column buttons to be invisible
                    }
                    if(fourConnected()){
                        mainGame.playerWonScreen(mainGame.playerIndex - 1); // goes to the winner's screen   
                    }else if(gameOver()){
                        mainGame.playerWonScreen(-1); // goes to winner's screen with -1 value, indicating no player has won
                    }
                     
                    break;
                }   
            }                
        }
        
    }
}

