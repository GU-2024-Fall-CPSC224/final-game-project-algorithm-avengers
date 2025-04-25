package edu.gonzaga;

public class Player {
    private String playerName;
    private char playerToken = 't'; // set to 't' to let the program know the player's token hasn't been set yet

    public String getPlayerName(){

        return playerName;
    }

    public char getPlayerToken(){

        return playerToken;
    }

    public void setPlayerName(String name){
        playerName = name;

    }

    public void setPlayerToken(char token){
        playerToken = token;
        
    }
}
