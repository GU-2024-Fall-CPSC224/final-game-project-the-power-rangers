package edu.gonzaga;

import java.util.ArrayList;

public class Multiplayer
{
    //Head and tail and head of players still in play
    private Player head;
    private Player tail;
    private Player head_playing;

    //Curr Players and board
    private Player currentPlayer;
    private Player opponentPlayer;
    private int[][] currBoard = {{0, 0, 0}, {0, 0, 0},{0, 0, 0}};
    private Board visBoard;

    //number of players active
    private int num_players;

    // Constructor
    public Multiplayer()
    {
        head = null;
        tail = null;
        head_playing = head;

        //Curr Players and board
        currentPlayer = head;
        opponentPlayer = null;

        //number of players
        num_players = 0;
        visBoard = new Board(this);
    }
    public void startGame(){
        if(opponentPlayer == null){
            visBoard.createBoard();
        }
        nextMatch();
        // visBoard.actionPerformed();
    }

    //Setters
    public void AddPlayer(String playerName)
    {
        // Create new player
        Player newPlayer = new Player(playerName);
        num_players++;

        //Set tail
        if (tail != null)
        {
            tail.setNextPlayer(newPlayer);
        }
        tail = newPlayer;

        //Set head if not pressent
        if (head == null)
        {
            head = newPlayer;
            currentPlayer = head;
            head_playing = head;
        }
    }

    // Get functions
    public Player GetPlayer()
    {
        return currentPlayer;
    }
    public Player GetOpponent()
    {
        return opponentPlayer;
    }

    // Gameplay
    public int updateMove(int x, int y, int fig) // -1->error; 0->nothing; 1->win; 2->tie; 3->final winner; 4->final winner with tie
    {
        //Change board
        if (currBoard[x][y] != 0) //Error checking
        {
            System.out.println("Error: Current square contains a figure");
        }
        currBoard[x][y] = fig;
        
        // Check win conditions
        if (currBoard[(x+1)%3][y] == fig && currBoard[(x+2)%3][y] == fig // Horisintal
            || currBoard[x][(y+1)%3] == fig && currBoard[x][(y+2)%3] == fig // Vertical
            || (x+y)%2 == 0 && currBoard[(x+1)%3][(y+1)%3] == fig && currBoard[(x+2)%3][(y+1)%3] == fig) // Diagonal
        {
            //scoring
            if (fig == 1) // Player 1 win
            {
                currentPlayer.incScore(1);
                opponentPlayer.decScore(1);
            }
            else // Player 2 win
            {
                opponentPlayer.incScore(1);
                currentPlayer.decScore(1);
            }
            // Next match and return win
            if (nextMatch())
                return 3; //Final win
            return 1; //win
        }
        //Check for full board
        else if (currBoard[0][0] != 0 && currBoard[0][1] != 0 && currBoard[0][2] != 0 //First row full
            && currBoard[1][0] != 0 && currBoard[1][1] != 0 && currBoard[1][2] != 0 //Second row full
            && currBoard[2][0] != 0 && currBoard[2][1] != 0 && currBoard[2][2] != 0) //Third row full
        {
            //scoring
            if (currentPlayer.getScore() < opponentPlayer.getScore()) // Player 1 less elo
            {
                currentPlayer.incScore(1);
                opponentPlayer.decScore(1);
            }
            else // Player 2 less elo
            {
                opponentPlayer.incScore(1);
                currentPlayer.decScore(1);
            }

            // Next match and return tie
            if (nextMatch())
                return 4; //Final win with tie
            return 2; //Tie
        }

        return 0; //No win
    }
    private boolean nextMatch() // true only if final player
    {
        int[][] currBoard = {{0, 0, 0}, {0, 0, 0},{0, 0, 0}};
        this.currBoard = currBoard;
        //If first match
        if(opponentPlayer == null)
        {
            currentPlayer = head_playing;
        }
        else // Not first match
        {
            currentPlayer = opponentPlayer.getNextPlayer();
        }

        //Check if this bracket ended
        if (currentPlayer == null || currentPlayer.getNextPlayer() == null)
        {
            MakeNewBracket();
        }

        opponentPlayer = currentPlayer.getNextPlayer();

        //Check if last player
        if(opponentPlayer == null)
        {
            return true;
        }

        return false;
    }
    private void MakeNewBracket()
    {
        int neerest_power_2 = 1;
        while(neerest_power_2 * 2 <= num_players)
        {
            neerest_power_2 = neerest_power_2 * 2;
        }

        //sort
        currentPlayer = head;
        while (currentPlayer != null) {
            if (currentPlayer.getNextPlayer().equals(head_playing))
            {
                break;
            }
        }

        while (currentPlayer.getNextPlayer() != null) {
            Player min = currentPlayer;
            Player prev_index = currentPlayer.getNextPlayer();

            // Find the minimum element in the unsorted part of the list
            while (prev_index.getNextPlayer() != null)
            {
                if (prev_index.getNextPlayer().getScore() < min.getNextPlayer().getScore())
                {
                    min = prev_index;
                }
                prev_index = prev_index.getNextPlayer();
            }
            // Swap the minimum element with the current element
            Player temp = currentPlayer.getNextPlayer();
            currentPlayer.setNextPlayer(min.getNextPlayer());
            min.setNextPlayer(temp);
            Player temp_2 = currentPlayer.getNextPlayer().getNextPlayer();
            currentPlayer.getNextPlayer().setNextPlayer(temp.getNextPlayer());
            temp.setNextPlayer(temp_2);
            // Move to the next node
            currentPlayer = currentPlayer.getNextPlayer();
        }

        // get new bracket size
        currentPlayer = head_playing;
        if (neerest_power_2 == num_players)
        {
            num_players = num_players/2;
            neerest_power_2 = num_players;
        }
        else
        {
            num_players = neerest_power_2;
        }

        //Get the last numplayers players
        while (currentPlayer != null)
        {
            if(neerest_power_2 == 0)
            {
                head_playing = head_playing.getNextPlayer();
            }
            else
            {
                neerest_power_2--;
            }

            currentPlayer = currentPlayer.getNextPlayer();
            tail = currentPlayer;
        }

        //set new values
        currentPlayer = head_playing;
    }
}
