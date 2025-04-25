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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

    // pictures
    JLabel teamIcon;
    JLabel[] boardSizeImages;

    // used to get input from player about the user
    JTextField playerInfo;
    JTextField[] playerNamesText;
    JTextField[] numPlayersOptionsText;
    JTextField pickBoardSize;
    JTextField[] playerTokens;

    // objects of other classes
    Board board = new Board();
    Player[] player;

    // other 
    int numPlayers;
    int playerChoices = 0;

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

        // createsd and configures an exit button
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

        // adds everything the the actual frame so it will be visible
        frame.add(playGame);
        frame.add(quitGame);
        frame.add(teamIcon);
        frame.setVisible(true);

    }

    public static void main(String[] args){
        MainGame mainGame = new MainGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playGame){
            getPlayerInfo();
        }

        if(e.getSource() == quitGame){
            System.exit(0);
        }

        if(e.getSource() == exit){
            System.exit(0);
        }

        if(e.getSource() == numPlayersOptions[0]){ // 2 players
            numPlayers = 2;
            player = new Player[numPlayers];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();

        }
        if(e.getSource() == numPlayersOptions[1]){ // 3 players
            numPlayers = 3;
            player = new Player[numPlayers];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();
        }
        if(e.getSource() == numPlayersOptions[2]){ // 4 players
            numPlayers = 4;
            player = new Player[numPlayers];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();
        }

        if(e.getSource() == doneButton){
            playerInfo.setVisible(false);
            for(int i = 0; i < 4; i++){
                playerTokensOption[i].setVisible(true);
            }

            for(int i = 0; i < numPlayers; i++){ 
                if(playerNamesText[i].getText().length() == 0){
                    player[i].setPlayerName("Player " + (i + 1));
                }else{
                    player[i].setPlayerName(playerNamesText[i].getText());
                }
                playerTokens[i].setVisible(true);
                getPlayerPieces(i);
            }

        }

        if(e.getSource() == boardSizeOptions[0]){ // board size set to small
            board.createEntireBoard(0, frame);

            pickBoardSize.setVisible(false);
            for(int i = 0; i < 3; i++){
                boardSizeOptions[i].setVisible(false);
                boardSizeImages[i].setVisible(false);
            }
            teamIcon.setVisible(false);

            board.printEntireBoard();
        }

        if(e.getSource() == boardSizeOptions[1]){ // board size set to medium
            board.createEntireBoard(1, frame);

            pickBoardSize.setVisible(false);
            for(int i = 0; i < 3; i++){
                boardSizeOptions[i].setVisible(false);
                boardSizeImages[i].setVisible(false);
            }
            teamIcon.setVisible(false);

            board.printEntireBoard();
        }

        if(e.getSource() == boardSizeOptions[2]){ // board size set to large
            board.createEntireBoard(2, frame);

            pickBoardSize.setVisible(false);
            for(int i = 0; i < 3; i++){
                boardSizeOptions[i].setVisible(false);
                boardSizeImages[i].setVisible(false);
            }
            teamIcon.setVisible(false);
            
            board.printEntireBoard();
        }
        
        if(e.getSource() == playerTokensOption[0]){ // player chose "O" as their token
            playerChoices++;
            playerTokensOption[0].setVisible(false);
            for(int i = 0; i < numPlayers; i++){
                if(player[i].getPlayerToken() == 't'){
                    playerTokens[i].setVisible(false);
                    player[i].setPlayerToken('X');
                    
                    if(playerChoices == numPlayers){ // will get the board size once all players have chosen their token
                        getBoardSize();
                    }

                    break;
                }
            }
        }

        if(e.getSource() == playerTokensOption[1]){ // player chose "X" as their token
            playerChoices++;
            playerTokensOption[1].setVisible(false);
            for(int i = 0; i < numPlayers; i++){
                if(player[i].getPlayerToken() == 't'){
                    playerTokens[i].setVisible(false);
                    player[i].setPlayerToken('O');
                    
                    if(playerChoices == numPlayers){ // will get the board size once all players have chosen their token
                        getBoardSize();
                    }

                    break;
                }
            }
        }

        if(e.getSource() == playerTokensOption[2]){ // player chose "#" as their token
            playerChoices++;
            playerTokensOption[2].setVisible(false);
            for(int i = 0; i < numPlayers; i++){
                if(player[i].getPlayerToken() == 't'){
                    playerTokens[i].setVisible(false);
                    player[i].setPlayerToken('#');
                    
                    if(playerChoices == numPlayers){ // will get the board size once all players have chosen their token
                        getBoardSize();
                    }

                    break;
                }
            }
        }

        if(e.getSource() == playerTokensOption[3]){ // player chose "$" as their token
            playerChoices++;
            playerTokensOption[3].setVisible(false);
            for(int i = 0; i < numPlayers; i++){
                if(player[i].getPlayerToken() == 't'){
                    playerTokens[i].setVisible(false);
                    player[i].setPlayerToken('$');
                    
                    if(playerChoices == numPlayers){ // will get the board size once all players have chosen their token
                        getBoardSize();
                    }

                    break;
                }
            }
        }

    }

    public void playerWonScreen(){

    }

    public void playGame(){


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

        pickBoardSize.setVisible(true);

        // sets the board size options (and pictures) to be visible
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
}
