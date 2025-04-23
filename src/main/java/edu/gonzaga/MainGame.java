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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** Main program class for launching your team's program. */
public class MainGame implements ActionListener {

    // main window frame
    JFrame frame; 

    // buttons
    JButton playGame;
    JButton quitGame;
    JButton twoPlayer;
    JButton threePlayer;
    JButton fourPlayer;
    JButton sizeSmall;
    JButton sizeMedium;
    JButton sizeLarge;

    JPanel namePanel;

    // picture for intro screen
    JLabel teamIcon;

    // used to get input from player about the user
    JTextField playerInfo;
    JTextField[] playerNames;

    Board board;
    Player[] player;

    int numPlayers;

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

        namePanel = new JPanel();
        namePanel.setBounds(100, 500, 900, 500);
        namePanel.setLayout(new GridLayout(4,1,5,5));

        playerNames = new JTextField[4];
        for(int i = 0; i < 4; i++){
            playerNames[i] = new JTextField();
            playerNames[i].setBounds(200, 500*(i+1), 500, 75);
            playerNames[i].setEditable(true);
            playerNames[i].setFont(myFont);
            // playerNames[i].setVisible(false);
            namePanel.add(playerNames[i]);
            // frame.add(playerNames[i]);
        }

        namePanel.setVisible(false);
        frame.add(namePanel);

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

        if(e.getSource() == twoPlayer){
            numPlayers = 2;
            player = new Player[numPlayers];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();

        }
        if(e.getSource() == threePlayer){
            numPlayers = 3;
            player = new Player[numPlayers];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();
        }
        if(e.getSource() == fourPlayer){
            numPlayers = 4;
            player = new Player[numPlayers];
            for(int i = 0; i < numPlayers; i++){
                player[i] = new Player();
            }
            getPlayerNames();
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

        //pick player number
        twoPlayer = new JButton("2 Player");
        twoPlayer.setBounds(100, 400, 200, 75);
        twoPlayer.setFont(myFont);
        twoPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(twoPlayer);
        twoPlayer.setVisible(true);
        twoPlayer.addActionListener(this);

        threePlayer = new JButton("3 Player");
        threePlayer.setBounds(300, 400, 200, 75);
        threePlayer.setFont(myFont);
        threePlayer.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(threePlayer);
        threePlayer.setVisible(true);
        threePlayer.addActionListener(this);

        fourPlayer = new JButton("4 Player");
        fourPlayer.setBounds(500, 400, 200, 75);
        fourPlayer.setFont(myFont);
        fourPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(fourPlayer);
        fourPlayer.setVisible(true);
        fourPlayer.addActionListener(this);
        
    }

    public void getPlayerNames(){



        // JButton test = new JButton("test button");
        // test.setBounds(500, 500, 200, 75);
        // test.setFont(myFont);
        // test.setHorizontalAlignment(SwingConstants.CENTER);
        // frame.add(test);
        // frame.setComponentZOrder(test, 0);
        // test.setVisible(true);
        // System.out.println("hi");
        // frame.revalidate();
        // frame.repaint();

       //  playerNames = new JTextField[numPlayers];

        for(int i = 0; i < numPlayers; i++){
            playerNames[i].setVisible(true);
            // namePanel.setVisible(true);
            // playerNames[i] = new JTextField();
            // System.out.println("hi");
            // playerNames[i].setBounds(500, 800, 200, 75);
            // playerNames[i].setFont(myFont);
            // playerNames[i].setHorizontalAlignment(SwingConstants.CENTER);
            // frame.add(playerNames[i]);
            // frame.setComponentZOrder(playerNames[i], 1);
            // playerInfo.setEditable(true);
            // playerNames[i].setVisible(true);
            // frame.revalidate();
            // frame.repaint();
            
        }

    }
    
}
