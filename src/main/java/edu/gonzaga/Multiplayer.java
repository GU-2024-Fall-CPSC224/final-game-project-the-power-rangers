package edu.gonzaga;

import java.util.ArrayList;

public class Multiplayer
{
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int match = 0;

    public Multiplayer(){}

    public void AddPlayer(String playerName)
    {
        Player newPlayer = new Player(playerName);
        players.add(newPlayer);
    }

    public Player GetPlayer(String playerName)
    {
        return currentPlayer;
    }

    public void nextMatch(int loosing_player)
    {
        players.remove(match + loosing_player);
        match += 1;
        if (match >= players.size())
        {
            match = 0;
        }
    }
}
