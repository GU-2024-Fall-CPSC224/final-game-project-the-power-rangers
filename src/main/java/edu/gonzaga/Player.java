package edu.gonzaga;

public class Player
{
    private String name;
    private int score = 0;

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
}