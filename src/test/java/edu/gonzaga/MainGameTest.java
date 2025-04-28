package edu.gonzaga;

import javax.swing.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MainGameTest {
    
    @Test
    void testPlayerNames(){
        // starts the program
        MainGame mg = new MainGame();
        mg.playGame.doClick();

        mg.numPlayersOptions[0].doClick(); // sets the game to be 2 players

        mg.playerNamesText[0] = new JTextField("GU Student"); // input player 1's name
        mg.playerNamesText[1] = new JTextField(); // input player 2's name

        mg.doneButton.doClick(); // set the player's names

        // checks the names are set
        Assertions.assertEquals("GU Student", mg.player[0].getPlayerName());
        Assertions.assertEquals("Player 2", mg.player[1].getPlayerName());
    }

    @Test
    void testPlayerTokens(){
        // starts the program
        MainGame mg = new MainGame();
        mg.playGame.doClick();

        mg.numPlayersOptions[0].doClick(); // sets the game to be 2 players

        mg.doneButton.doClick(); // set the player's names to advance to token options

        mg.playerTokensOption[0].doClick(); // sets player 1's token to be "X"
        mg.playerTokensOption[1].doClick(); // sets player 1's token to be "O"

        Assertions.assertEquals("X", mg.player[0].getPlayerToken());
        Assertions.assertEquals("O", mg.player[1].getPlayerToken());

    }

    @Test
    void testBoardSize(){
        // starts the program
        MainGame mg = new MainGame();
        mg.playGame.doClick();

        mg.numPlayersOptions[0].doClick(); // sets the game to be 2 players
        mg.doneButton.doClick(); // set the player's names to advance to token options

        mg.playerTokensOption[0].doClick(); // sets the first player's token
        mg.playerTokensOption[1].doClick(); // sets the second player's token so the game can advance to choosing board size

        mg.boardSizeOptions[0].doClick(); // sets the board size to be small

        // checks to see if board dimensions are correct
        Assertions.assertEquals(7, mg.board.getBoardArray().length);
        Assertions.assertEquals(6, mg.board.getBoardArray()[0].length);
    }

    @Test
    void testPlayerWins(){
        // starts the program
        MainGame mg = new MainGame();

        mg.playGame.doClick();

        mg.playerIndex = 0;

        mg.numPlayersOptions[0].doClick(); // sets the game to be 2 players
        mg.doneButton.doClick(); // set the player's names to advance to token options

        mg.playerTokensOption[0].doClick(); // sets the first player's token
        mg.playerTokensOption[1].doClick(); // sets the second player's token so the game can advance to choosing board size

        mg.boardSizeOptions[0].doClick(); // sets the board size to be small

        mg.board.setMainGame(mg); // not sure why but test needs for this to be set to run, even though it is already called within MainGame.java and set in Board.java
        
        // adds all tiles so 4 tokens are connected (for player 1, vertically in column 1)
        mg.board.getColumnButtons()[0].doClick();
        mg.board.getColumnButtons()[1].doClick();
        mg.board.getColumnButtons()[0].doClick();
        mg.board.getColumnButtons()[1].doClick();
        mg.board.getColumnButtons()[0].doClick();
        mg.board.getColumnButtons()[1].doClick();
        mg.board.getColumnButtons()[0].doClick();

        Assertions.assertEquals("Player 1 Won!", mg.playerWin.getText()); // checks to see if correct player wins
    }
}