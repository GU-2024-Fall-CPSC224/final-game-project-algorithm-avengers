/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * A traditional game of Connect Four, but instead of two players, the game
 * will be able to handle up to 4 players, each with their own chosen icon/token
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

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** Main program class for launching your team's program. */
public class MainGame {

    static JFrame frame;
    static JButton playGame;
    static JFrame game;
    static JLabel teamIcon;

    static Font myFont = new Font("Roboto", Font.BOLD, 30);

    public static void main(String[] args) {
        
        // creates and configures a frame for the game to load
        frame = new JFrame("Ultimate Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(1000, 1000); 
        frame.setLayout(null); 
        frame.setResizable(false);        

        // creates and configures a button that beings the game
        playGame = new JButton("Play Game");
        playGame.setBounds(400, 775, 250, 75);
        playGame.setFont(myFont);

        // imports and configures the background image
        teamIcon = new JLabel(new ImageIcon("src/main/java/edu/gonzaga/teamIcon.jpeg"));
        teamIcon.setBounds(0, -28, 1000, 1000);

        // makes it so a new window will pop up if the "play game" button is clicked (will improve later)
        playGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                printWelcomeScreen();

            }
        });

        // adds everything the the actual frame so it will be visible
        frame.add(playGame);
        frame.add(teamIcon);
        frame.setVisible(true);

    }
    
    static void printWelcomeScreen(){

        // new frame is created after "play game" button is clicked
        // CHANGE THIS LATER
        game = new JFrame("Figure this out later");
                    
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        game.setSize(1000, 1000);           
        game.setLayout(null);              
        game.setResizable(false);        
        game.setVisible(true);

    }

    void playerWonScreen(){


    }

    void playGame(){


    }

    void getPlayerInfo(){


    }
}
