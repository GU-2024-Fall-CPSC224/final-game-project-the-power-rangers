package edu.gonzaga;

import javax.swing.ImageIcon;

public class Player
{
    private String name;
    private int score = 0;
    private Character piece;


    public Player(String name)
    {
        this.name = name;
    }
    public void incScore()
    {
        score++;
    }

    public String getName()
    {
        return name;
    }
    public int getScore()
    {
        return score;
    }
    public Character getPiece(){
        return piece;
    }
}