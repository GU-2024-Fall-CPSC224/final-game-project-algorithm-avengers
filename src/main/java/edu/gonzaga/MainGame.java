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
 * Contributors: McKinely Martsolf, Katie Park, Farhan Moustfa, Liya Tekie, and Daniel Medina
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
    JButton[] numberOfPlayer;
    JButton sizeSmall;
    JButton sizeMedium;
    JButton sizeLarge;
    JButton doneButton;

    // picture for intro screen
    JLabel teamIcon;

    // used to get input from player about the user
    JTextField playerInfo;
    JTextField[] playerNames;
    JTextField[] playerNums;

    // objects of other classes
    Board board;
    Player[] player;

    // other 
    int numPlayers;

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

        // imports and configures the background image
        teamIcon = new JLabel(new ImageIcon("src/main/java/edu/gonzaga/teamIcon.jpeg"));
        teamIcon.setBounds(0, -28, 1000, 1000);

        // imports and configures a text box to ask how many players will be playing
        playerInfo = new JTextField("How many players?"); 
        playerInfo.setBounds(100, 150, 800, 100);
        playerInfo.setFont(largeFont);
        playerInfo.setEditable(false);
        playerInfo.setHorizontalAlignment(SwingConstants.CENTER);
        playerInfo.setVisible(false);
        frame.add(playerInfo);

        // imports and configures the options to choose how many players will be playing
        numberOfPlayer = new JButton[3];
        for(int i = 0; i < 3; i++){
            numberOfPlayer[i] = new JButton((i + 2) + " Player");
            numberOfPlayer[i].setBounds((300 * i) + 100, 300, 200, 75);
            numberOfPlayer[i].setFont(mainFont);
            numberOfPlayer[i].setVisible(false);
            numberOfPlayer[i].addActionListener(this);
            frame.add(numberOfPlayer[i]);
        }

        // imports and configures textfields for player names to be inputted
        playerNames = new JTextField[4];
        playerNums = new JTextField[5];
        for(int i = 0; i < 4; i++){
            playerNums[i] = new JTextField("Player " + (i + 1) + ": ");
            playerNums[i].setBounds(100, 300 + (100 * (i + 1)), 200, 75);
            playerNums[i].setEditable(false);
            playerNums[i].setFont(mainFont);
            playerNums[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerNums[i].setVisible(false);
            frame.add(playerNums[i]);

            playerNames[i] = new JTextField();
            playerNames[i].setBounds(294, 300 + (100 * (i + 1)), 600, 75);
            playerNames[i].setEditable(true);
            playerNames[i].setFont(smallFont);
            playerNames[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerNames[i].setVisible(false);
            frame.add(playerNames[i]);
        }

        // imports and configures a button for when all the names are inputted
        doneButton = new JButton("Done");
        doneButton.setBounds(400, 800, 200, 75);
        doneButton.setFont(mainFont);
        doneButton.setVisible(false);
        doneButton.addActionListener(this);
        frame.add(doneButton);

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

        if(e.getSource() == numberOfPlayer[0]){
            player = new Player[2];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();

        }
        if(e.getSource() == numberOfPlayer[1]){
            player = new Player[3];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();
        }
        if(e.getSource() == numberOfPlayer[2]){
            player = new Player[4];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();
        }

        if(e.getSource() == doneButton){
            for(int i = 0; i < player.length; i++){
                player[i].setPlayerName(playerNames[i].getText());
            }
            getBoardSize();
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
            numberOfPlayer[i].setVisible(true);
        }
        
    }

    public void getPlayerNames(){
        
        // sets the player name fields visible for user to actually input their name
        for(int i = 0; i < player.length; i++){
            playerNums[i].setVisible(true);
            playerNames[i].setVisible(true);
        }

        // sets the "Done" button visible
        // once the user clicks "Done," the names will be set
        doneButton.setVisible(true);

    }

    public void getBoardSize(){

    }
    
}
