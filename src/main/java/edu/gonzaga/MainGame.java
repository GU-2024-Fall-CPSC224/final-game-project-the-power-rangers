/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * 
 * Contributors:
 * 
 * 
 * Copyright: 2023
 */
package edu.gonzaga;

import java.util.ArrayList;

/** Main program class for launching your team's program. */
public class MainGame
{
    public static void main(String[] args)
    {
        System.out.println("Hello Team Game");


        // Your code here. Good luck!
        Menu startMenu = new Menu();
        startMenu.optionScreen(names -> {
            System.out.println("closed");
            ArrayList<String> playerNames = startMenu.getPlayerNames();
            int numPlayers = startMenu.getPlayerCount();
            Multiplayer multiplayer = new Multiplayer(startMenu);
            for(int i = 0; i < numPlayers; i++){
                
                multiplayer.AddPlayer(playerNames.get(i));
            }
            multiplayer.startGame();
        });
        

        // Start the multiplayer 
        

        // multiplayer.startGame();

        // Board board = new Board();
        // board.createBoard();
        
        // for(int i = 0; i <= 2; i++){ 
        //     multiplayer.AddPlayer("Player " + i);
        // }
        

    }
}
