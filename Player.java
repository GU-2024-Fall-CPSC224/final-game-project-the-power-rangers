package edu.gonzaga;

public class Player
{
    // Player attributes
    private String name = "";
    private double score = 0;
    private double score_diff = 0;
    private Player next_player = null;

    // Constructors
    public Player(String name)
    {
        this.name = name;
    }

    // Setters
    public void setNextPlayer(Player next)
    {
        next_player = next;
    }
    private void calcScoreDiff(Player opponent)
    {
        // Get opp score
        double opp_score = opponent.getScore();

        // Set score_diff
        if (score < opp_score)
            score_diff =  (opp_score - score)/2.0;
        else
            score_diff = (score - opp_score)/2.0;

        //Round to 2 decimal spaces
        score_diff = Math.round(score_diff*100.0)/100.0;
    }

    // Score changes
    public void incScore(double state)
    {
        score += score_diff + state;
    }
    public void decScore(double state)
    {
        score -= score_diff + state;
    }

    // Get player attributes
    public String getName()
    {
        return name;
    }
    public double getScore()
    {
        return score;
    }
    public Player getNextPlayer()
    {
        return next_player;
    }
}