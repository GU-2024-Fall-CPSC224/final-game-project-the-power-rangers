package edu.gonzaga;

import java.util.ArrayList;

public class Multiplayer
{
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int match = 0;
    private int[][] currBoard = {{0, 0, 0}, {0, 0, 0},{0, 0, 0}};

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

    public boolean makeMove(int x, int y, int fig)
    {
        currBoard[x][y] = fig;

        if (currBoard[x][y] == currBoard[(x+1)%3][y] && currBoard[x][y] == currBoard[(x+2)%3][y])
        {
            return true;
        }
        else if (currBoard[x][y] == currBoard[x][(y+1)%3] && currBoard[x][y] == currBoard[x][(y+2)%3])
        {
            return true;
        }
        else if (currBoard[x][y] == currBoard[(x+1)%3][(y+1)%3] && currBoard[x][y] == currBoard[(x+2)%3][(y+1)%3])
        {
            return true;
        }
        return false;
    }

    public void nextMatch(int loosing_player)
    {
        Board newBoard = new Board();
        newBoard.createBoard();
        players.remove(match + loosing_player);
        match += 1;
        int[][] currBoard = {{0, 0, 0}, {0, 0, 0},{0, 0, 0}};
        if (match >= players.size())
        {
            match = 0;
        }
    }
}
