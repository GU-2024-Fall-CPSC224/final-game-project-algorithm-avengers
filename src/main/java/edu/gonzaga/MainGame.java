/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * A traditional game of Connect Four, but instead of two players, the game
 * will be able to handle up to 4 players, and it can change sizes. Every player will be able to chosen icon/token
 * to represent the disk in the real life game.
 * 
 * Otherwise, the game rules will adhere to the traditional game of Connect Four,
 * where a player wins if they connect four of their tokens in 4 successive spots,
 * either horizontally, vertically, or diagonally. 
 * 
 * Contributors: McKinley Martsolf, Katie Park, Farhan Moustfa, Liya Tekie, and Daniel Medina
 * 
 * 
 * Copyright: 2025
 */

package edu.gonzaga;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

/** Main program class for launching your team's program. */
public class MainGame implements ActionListener {

    // main window frame
    JFrame frame; 

    // buttons
    JButton playGame;
    JButton quitGame;
    JButton[] numPlayersOptions;
    JButton[] boardSizeOptions;
    JButton doneButton;
    JButton exit;
    JButton[] playerTokensOption;
    JButton playAgain;

    // pictures
    JLabel teamIcon;
    JLabel[] boardSizeImages;

    // used to get input from player about the user
    JTextField playerInfo;
    JTextField[] playerNamesText;
    JTextField[] numPlayersOptionsText;
    JTextField pickBoardSize;
    JTextField[] playerTokens;
    JTextField playerTurnText;
    JTextField playerWin;

    // objects of classes
    static MainGame mainGame; // so Board class can access the JFrame
    Board board = new Board();
    Player[] player;

    // other 
    int numPlayers;
    int playerChoices = 0;
    int playerIndex = 0;

    // fonts
    Font mainFont = new Font("Roboto", Font.BOLD, 30);
    Font largeFont = new Font("Roboto", Font.BOLD, 50);
    Font smallFont = new Font("Roboto", Font.PLAIN, 20);

    MainGame() { // constructor
        
        // creates and configures a frame for the game to load
        frame = new JFrame("Ultimate Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(1000, 1000); 
        frame.setLayout(null); 
        frame.setResizable(false);
        board.setFrame(frame);        

        // creates and configures a button that beings the game
        playGame = new JButton("Play Game");
        playGame.setBounds(200, 775, 250, 75);
        playGame.setFont(mainFont);
        playGame.addActionListener(this);

        // creates and configures a button that exits the game
        quitGame = new JButton("Quit Game");
        quitGame.setBounds(550, 775, 250, 75);
        quitGame.setFont(mainFont);
        quitGame.addActionListener(this);

        // creates and configures an exit button
        exit = new JButton("X");
        exit.setBounds(900, 20, 50, 50);
        exit.setFont(smallFont);
        exit.setVisible(true);
        frame.add(exit);
        exit.addActionListener(this);

        // imports and configures the background image
        teamIcon = new JLabel(new ImageIcon("src/main/java/edu/gonzaga/Images/teamIcon.jpeg"));
        teamIcon.setBounds(0, -28, 1000, 1000);

        // imports and configures a text box to ask how many players will be playing
        playerInfo = new JTextField("How many players?"); 
        playerInfo.setBounds(100, 150, 800, 100);
        playerInfo.setFont(largeFont);
        playerInfo.setEditable(false);
        playerInfo.setHorizontalAlignment(SwingConstants.CENTER);
        playerInfo.setVisible(false);
        frame.add(playerInfo);

        // imports and configures buttons and images so user can choose what size board they want to play
        boardSizeOptions = new JButton[3];
        boardSizeOptions[0] = new JButton("Small");
        boardSizeOptions[1] = new JButton("Medium");
        boardSizeOptions[2] = new JButton("Large");

        boardSizeImages = new JLabel[3];
        boardSizeImages[0] = new JLabel(new ImageIcon("src/main/java/edu/gonzaga/Images/smallBoard.png"));
        boardSizeImages[1] = new JLabel(new ImageIcon("src/main/java/edu/gonzaga/Images/mediumBoard.png"));
        boardSizeImages[2] = new JLabel(new ImageIcon("src/main/java/edu/gonzaga/Images/largeBoard.png"));

        for(int i = 0; i < 3; i++){
            boardSizeOptions[i].setBounds((300 * i) + 100, 375, 200, 75);
            boardSizeOptions[i].setFont(mainFont);
            boardSizeOptions[i].setVisible(false);
            boardSizeOptions[i].addActionListener(this);
            frame.add(boardSizeOptions[i]);

            boardSizeImages[i].setBounds((300 * i) + 75, 400, 250, 500);
            boardSizeImages[i].setVisible(false);
            frame.add(boardSizeImages[i]);

        }

        // imports and configures the options to choose how many players will be playing
        numPlayersOptions = new JButton[3];
        for(int i = 0; i < 3; i++){
            numPlayersOptions[i] = new JButton((i + 2) + " Player");
            numPlayersOptions[i].setBounds((300 * i) + 100, 300, 200, 75);
            numPlayersOptions[i].setFont(mainFont);
            numPlayersOptions[i].setVisible(false);
            numPlayersOptions[i].addActionListener(this);
            frame.add(numPlayersOptions[i]);
        }

        // imports and configures textfields for player names to be inputted
        playerNamesText = new JTextField[4];
        numPlayersOptionsText = new JTextField[5];
        for(int i = 0; i < 4; i++){
            numPlayersOptionsText[i] = new JTextField("Player " + (i + 1) + ": ");
            numPlayersOptionsText[i].setBounds(100, 300 + (100 * (i + 1)), 200, 75);
            numPlayersOptionsText[i].setEditable(false);
            numPlayersOptionsText[i].setFont(mainFont);
            numPlayersOptionsText[i].setHorizontalAlignment(SwingConstants.CENTER);
            numPlayersOptionsText[i].setVisible(false);
            frame.add(numPlayersOptionsText[i]);

            playerNamesText[i] = new JTextField();
            playerNamesText[i].setBounds(294, 300 + (100 * (i + 1)), 600, 75);
            playerNamesText[i].setEditable(true);
            playerNamesText[i].setFont(smallFont);
            playerNamesText[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerNamesText[i].setVisible(false);
            frame.add(playerNamesText[i]);
        }

        // imports and configures a button for when all the names are inputted
        doneButton = new JButton("Done");
        doneButton.setBounds(400, 800, 200, 75);
        doneButton.setFont(mainFont);
        doneButton.setVisible(false);
        doneButton.addActionListener(this);
        frame.add(doneButton);

        // imports and configures a textfield for the user to pick board size
        pickBoardSize = new JTextField("What size board do you want to play on?");
        pickBoardSize.setBounds(100, 150, 800, 100);
        pickBoardSize.setFont(mainFont);
        pickBoardSize.setEditable(false);
        pickBoardSize.setHorizontalAlignment(SwingConstants.CENTER);
        pickBoardSize.setVisible(false);
        frame.add(pickBoardSize);

        // imports and configures a textfield for the user to choose their token
        playerTokens = new JTextField[4];
        for(int i = 0; i < 4; i++){
            playerTokens[i] = new JTextField();
            playerTokens[i].setBounds(100, 150, 800, 100);
            playerTokens[i].setFont(mainFont);
            playerTokens[i].setEditable(false);
            playerTokens[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerTokens[i].setVisible(false);
            frame.add(playerTokens[i]);
        }

        // imports and configures buttons for individual tokens
        playerTokensOption = new JButton[4];
        playerTokensOption[0] = new JButton("X");
        playerTokensOption[1] = new JButton("O");
        playerTokensOption[2] = new JButton("#");
        playerTokensOption[3] = new JButton("$");

        for (int i = 0; i < 4; i++){
            playerTokensOption[i].setBounds((220 * i) + 125, 500, 75, 75);
            playerTokensOption[i].setFont(smallFont);
            playerTokensOption[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerTokensOption[i].setVisible(false);
            frame.add(playerTokensOption[i]);
            playerTokensOption[i].addActionListener(this);

        }

        // imports and configures a text field letting the player know whose turn it is
        playerTurnText = new JTextField("");
        playerTurnText.setBounds(100, 150, 800, 100);
        playerTurnText.setFont(mainFont);
        playerTurnText.setEditable(false);
        playerTurnText.setHorizontalAlignment(SwingConstants.CENTER);
        playerTurnText.setVisible(false);
        frame.add(playerTurnText);

        // imports and configures a text field letting the player know who won the game
        playerWin= new JTextField("");
        playerWin.setBounds(100, 150, 800, 100);
        playerWin.setFont(mainFont);
        playerWin.setEditable(false);
        playerWin.setHorizontalAlignment(SwingConstants.CENTER);
        playerWin.setVisible(false);
        frame.add(playerWin);
        
        // imports and configures a button which allows the user to restart the game
        playAgain = new JButton("Play Again?");
        playAgain.setBounds(200, 795, 250, 75); 
        playAgain.setFont(mainFont);
        playAgain.setVisible(false);
        playAgain.addActionListener(this);
        frame.add(playAgain);


        // adds everything the the actual frame so it will be visible
        frame.add(playGame);
        frame.add(quitGame);
        frame.add(teamIcon);
        frame.setVisible(true);

    }
    
    public static void main(String[] args){
        mainGame = new MainGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playGame){ // the user selects "Play Game" from the intro screen
            getPlayerInfo();
        }

        if(e.getSource() == quitGame){ // the user selects "Quit Game" from the intro screen
            System.exit(0);
        }

        if(e.getSource() == exit){ // the user selects "X" (exit) shown throughout the program
            System.exit(0);
        }

        for(int i = 0; i < 3; i++){ // the user selects the number of players playing the game
            if(e.getSource() == numPlayersOptions[i]){
                numPlayers = i + 2;
                player = new Player[numPlayers];
                for(int j = 0; j < numPlayers; j++){
                    player[j] = new Player();
                }
                getPlayerNames();
            }
        }

        if(e.getSource() == doneButton){ // the user selects "Done" after inputting their names
            playerInfo.setVisible(false);
            for(int i = 0; i < 4; i++){
                playerTokensOption[i].setVisible(true);
            }

            for(int i = 0; i < numPlayers; i++){ 
                if(playerNamesText[i].getText().length() == 0){ // if the player didn't input anything
                    player[i].setPlayerName("Player " + (i + 1));
                }else{
                    player[i].setPlayerName(playerNamesText[i].getText());
                }
                playerTokens[i].setVisible(true);
                getPlayerPieces(i);
            }

        }

        for(int i = 0; i < 3; i++){ // the user selects board size the players will be playing
            if(e.getSource() == boardSizeOptions[i]){
                board.createEntireBoard(i);
                pickBoardSize.setVisible(false);
                for(int j = 0; j < 3; j++){
                    boardSizeOptions[j].setVisible(false);
                    boardSizeImages[j].setVisible(false);
                }
                board.setMainGame(mainGame);
                playGame(playerIndex);
            }
        }

        for(int i = 0; i < 4; i++){ // the players each choose which token that will represent them
            if(e.getSource() == playerTokensOption[i]){
                playerChoices++;
                playerTokensOption[i].setVisible(false); // once a token has been selected, the button disappears
                for(int j = 0; j < numPlayers; j++){ // each player gets a turn choosing their token
                    if(player[j].getPlayerToken().equals("t")){ // if the player doesn't have an assigned token
                        playerTokens[j].setVisible(false);
                        
                        if(i == 0){player[j].setPlayerToken("X");}
                        if(i == 1){player[j].setPlayerToken("O");}
                        if(i == 2){player[j].setPlayerToken("#");}
                        if(i == 3){player[j].setPlayerToken("$");}

                        if(playerChoices == numPlayers){
                            getBoardSize();
                        }
                        break;
                    }
                }
            }
        }

        if (e.getSource() == playAgain){ // if the user wants to play the game again

            frame.dispose(); // restarts the game
            main(null);
        }
    }

    public void getPlayerInfo(){
        // sets the window to be blank after a button is clicked
        playGame.setVisible(false);
        quitGame.setVisible(false);
        
        // sets the text field asking for the number of players to be visible
        playerInfo.setVisible(true);

        // sets the options for the number of players (buttons) visible for the user to click
        for(int i = 0; i < 3; i++){
            numPlayersOptions[i].setVisible(true);
        }
        
    }

    public void getPlayerNames(){
        
        // sets the player name fields visible for user to actually input their name
        for(int i = 0; i < player.length; i++){
            numPlayersOptionsText[i].setVisible(true);
            playerNamesText[i].setVisible(true);
        }

        // sets the "Done" button visible
        // once the user clicks "Done," the names will be set
        doneButton.setVisible(true);

    }

    public void getBoardSize(){
        // sets the frame to be blank (except the background image)
        playerInfo.setVisible(false);

        for(int i = 0; i < numPlayers; i++){
            numPlayersOptionsText[i].setVisible(false);
            playerNamesText[i].setVisible(false);
            playerTokens[i].setVisible(false);
        }

        for(int i = 0; i < 4; i++){
            playerTokensOption[i].setVisible(false);
        }

        for(int i = 0; i < 3; i++){
            numPlayersOptions[i].setVisible(false);
        }

        doneButton.setVisible(false);

        // sets the board size options (and pictures) to be visible
        pickBoardSize.setVisible(true);

        for(int i = 0; i < 3; i++){
            boardSizeOptions[i].setVisible(true);
            boardSizeImages[i].setVisible(true);
        }

    }

   public void getPlayerPieces(int index){
        // sets the frame to be blank (except the background image)
        for(int i = 0; i < 4; i++){
            playerNamesText[i].setVisible(false);
            numPlayersOptionsText[i].setVisible(false);
        }

        for(int i = 0; i < 3; i++){
            numPlayersOptions[i].setVisible(false);
        }

        doneButton.setVisible(false);

        // text field to let the player know whose turn it is to choose their token (will change based on which player's turn it is)
        playerTokens[index].setText(player[index].getPlayerName() + ", Pick Your Token!");
    }

    public void playGame(int index){

        playerIndex++; // player index indicates which player's turn it is
        board.setToken(player[index].getPlayerToken()); // so the board can know which token is specific to the player
        
        playerTurnText.setText(player[index].getPlayerName() + ", it's your Turn!");
        playerTurnText.setVisible(true);
        
        board.printEntireBoard();
        
    }

    public void playerWonScreen(int index){
        // gets rid of the text field that lets the user know which player's turn it is
        playerTurnText.setVisible(false);
        
        // clears the board
        try{
            board.getDoc().remove(0, board.getDoc().getLength());
        }catch(BadLocationException e){
            e.printStackTrace();
        }

        // prints the final board, without the column buttons
        for(int i = 0; i < board.getBoardArray()[0].length; i++){
            for(int j = 0; j < board.getBoardArray().length; j++){
                try{
                    board.getDoc().insertString(board.getDoc().getLength(), "|", board.getNonTokenFont());
                    if(board.getBoardArray()[j][i].equals(" _ ")){
                        board.getDoc().insertString(board.getDoc().getLength(), board.getBoardArray()[j][i], board.getNonTokenFont());
                    }else{
                        board.getDoc().insertString(board.getDoc().getLength(), board.getBoardArray()[j][i], board.getTokenFont());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            try{
                board.getDoc().insertString(board.getDoc().getLength(), "|\n", board.getNonTokenFont());
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        if(index == -1){
            playerWin.setText("It's a Tie!");
        }else{
            playerWin.setText(player[index].getPlayerName() + " Won!");
        }
        
        playerWin.setVisible(true);
        quitGame.setBounds(550, 795, 250, 75);
        quitGame.setVisible(true);
        playAgain.setVisible(true);

        
    }

}

