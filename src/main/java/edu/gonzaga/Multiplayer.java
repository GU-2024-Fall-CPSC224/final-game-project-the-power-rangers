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
    private Menu startMenu;
    private ArrayList<Player> playersList = new ArrayList<Player>();
    private ArrayList<Player> tourneyList = new ArrayList<Player>();
    private int roundNum = -1;

    //number of players active
    private int num_players;


    // Constructor
    public Multiplayer(Menu startMenu)
    {
        head = null;
        tail = null;
        head_playing = head;

        //Curr Players and board
        currentPlayer = head;
        opponentPlayer = null;

        //number of players
        num_players = 0;
        visBoard = new Board(this, startMenu);
        this.startMenu = startMenu;
    }
    public void startGame(){
        if(playersList.size() > 1){
            nextMatch();
            System.out.println("Flag 17: {Player names}" + currentPlayer.getName() + " vs. "+ opponentPlayer.getName());
            visBoard.createBoard();
        }
        
        // visBoard.actionPerformed();
    }

    //Setters
    public void AddPlayer(String playerName)
    {
        // Create new player
        Player newPlayer = new Player(playerName);
        num_players++;

        playersList.add(newPlayer);
        tourneyList.add(newPlayer);

        

        // //Set tail
        // if (tail != null)
        // {
        //     tail.setNextPlayer(newPlayer);
        // }
        // tail = newPlayer;

        // //Set head if not pressent
        // if (head == null)
        // {
        //     head = newPlayer;
        //     currentPlayer = head;
        //     head_playing = head;
        // }
    }

    // Get functions
    public Player getPlayer(int pos)
    {
        return playersList.get(pos);
    }
    public Player getOpponent()
    {
        return playersList.get((roundNum + 1) % playersList.size());
    }
    public Player getCurrentPlayer()
    {
        return playersList.get(roundNum % playersList.size());
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
                System.out.println("Flag 1 " + fig);
                currentPlayer.incScore(1);
                opponentPlayer.decScore(1);
                playersList.remove(opponentPlayer);
            }
            else // Player 2 win
            {
                System.out.println("Flag 2 " + fig);
                opponentPlayer.incScore(1);
                currentPlayer.decScore(1);
                playersList.remove(currentPlayer);
            }
            System.out.println("Flag 5");
            // Next match and return win
            // if (nextMatch())
            //     return 3; //Final win
            System.out.println("Flag 7  There is no final game");
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
            // if (nextMatch())
            //     return 4; //Final win with tie
            return 2; //Tie
        }

        return 0; //No win
    }
    public void clearBoard(){
        for(int i = 0; i < this.currBoard.length; i++){
            for(int j = 0; j < this.currBoard[0].length; j++){
                this.currBoard[i][j] = 0;
            }
        }
    }
    public boolean nextMatch() // true only if final player
    {
        int[][] currBoard = {{0, 0, 0}, {0, 0, 0},{0, 0, 0}};
        this.currBoard = currBoard;
        roundNum++;
        if(playersList.size() > 1){
            System.out.println();
            currentPlayer = getCurrentPlayer();
            opponentPlayer = getOpponent();
            
            System.out.println(roundNum);
            if(roundNum > 0){
                System.out.println("Clear the Board");
                visBoard.clearBoard();
            }
        }
        else{
            System.out.println("FLAG 19: GAME OVER");
            visBoard.tourneyWinner(playersList.get(0), tourneyList);
        }
        
        return true;
    }
}