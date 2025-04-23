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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** Main program class for launching your team's program. */
public class MainGame implements ActionListener {

    // main window frame
    JFrame frame; 

    // buttons
    JButton playGame;
    JButton quitGame;

    // picture for intro screen
    JLabel teamIcon;

    // used to get input from player about the user
    JTextField playerInfo;

    Board board;
    Player[] player;

    Font myFont = new Font("Roboto", Font.BOLD, 30);

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
        playGame.setFont(myFont);
        playGame.addActionListener(this);

        // creates and configures a button that exits the game
        quitGame = new JButton("Quit Game");
        quitGame.setBounds(550, 775, 250, 75);
        quitGame.setFont(myFont);
        quitGame.addActionListener(this);

        // imports and configures the background image
        teamIcon = new JLabel(new ImageIcon("src/main/java/edu/gonzaga/teamIcon.jpeg"));
        teamIcon.setBounds(0, -28, 1000, 1000);

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

        

    }
    
    public void printWelcomeScreen(){
        // left this blank because the constructor is technically the welcome screen
        // maybe delete this
    }

    public void playerWonScreen(){


    }

    public void playGame(){


    }

    public void getPlayerInfo(){
        // sets the window to be blank after a button is clicked
        playGame.setVisible(false);
        quitGame.setVisible(false);
        teamIcon.setVisible(false);

        // gets the number of players that will be playing (1-4/5)
        playerInfo = new JTextField("How many players?"); 
        playerInfo.setBounds(100, 200, 400, 75);
        playerInfo.setFont(myFont);
        playerInfo.setEditable(false);
        playerInfo.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(playerInfo);
        playerInfo.setVisible(true);
        // maybe create buttons with the number of players?? Will be easier to code
        // i dont know how to code it so it gets user input if we're making it a textbox

    }

    
}
