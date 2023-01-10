package src;
import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game 
{
    private Random generator;
    private ArrayList<Playable> players;
    private int leadPlayer;
    private Player player;
    private final int INITIAL_MELD = 25;
    private DeckHandler deck;
    private int score;
    private final int NUM_PLAYERS = 4;
    private String[] trick;
    private String trump;
    private int numDecks;
    
    public Game(int decks)
    {
        numDecks = decks;
        deck = new DeckHandler(decks);
        players = new ArrayList<Playable>();
        createPlayers();
        generator = new Random();
    }
    
    private void createPlayers()
    {
        player = new Player();
        players.add(player);
        for(int i = 0; i < 3; i++)
        {
            players.add(new Computer());
        }
    }
    
    private void dealCards(int lead)        //Cycles through each of the players dealing 3 cards to
    {                                       //each player until all cards are delt.
        int position = 0;
        for(int i = 0; i < numDecks*12/3; i++)
        {
            for(Playable p : players)
            {
                for(int j = 0; j < 3; j++)
                {
                    p.getCard(deck.cards[position]);
                    position++;
                }
            }
        }
        for(Playable p : players)           //Automatically arranges the players' hands.
        {
            p.organizeHand();
        }
    }

    public void startGame()
    {
        leadPlayer = generator.nextInt(3);  //Lead player randomly chosen.
        score = 0;
        playGame(leadPlayer);
    }
    
    private void playGame(int leadPlayer)
    {
        int currentLead = leadPlayer;
        while(score < 150)
        {
            for(Playable p : players)
            {
                p.resetHand();
            }
            dealCards(leadPlayer);
            score += 10;
            playHand(currentLead);
            if(currentLead >= players.size() - 1){currentLead = 0;}
            else{currentLead++;}
            deck.shuffleDeck();
        }
        restartGame();
    }
    
    private void playHand(int leader)
    {
        trump = "C";
        while(player.cards.size() > 0)
        {
            player.displayHand();
            trick = new String[NUM_PLAYERS];
            
            for(int i = leader; i < NUM_PLAYERS; i++)
            {
                trick[i] = players.get(i).playCard(trick);
                System.out.println(trick[i]);
            }
            if(leader > 0)
            {
                for(int i = 0; i < leader; i++)
                {
                    trick [i] = players.get(i).playCard(trick);
                    System.out.println(trick[i]);
                }
            }
            leader = trickWinner(trick, leader);
        }
    }
    
    private int trickWinner(String[] trick, int leader)
    {
        int winner = leader;
        String leadingCard = trick[leader];
        boolean trumpPlayed;
        
        if(leadingCard.substring(1,2).equals(trump)){trumpPlayed = true;}
        else{trumpPlayed = false;}
        
        for(int i = leader; i < NUM_PLAYERS; i++)
        {
            if(trick[i].substring(1,2).equals(trump) && trumpPlayed)
            {
                if(trick[i].substring(0,1).compareTo(leadingCard.substring(0,1)) > 0)
                {
                    leadingCard = trick[i];
                    winner = i;
                }
            }
            else if(trick[i].substring(1,2).equals(trump))
            {
                leadingCard = trick[i];
                winner = i;
                trumpPlayed = true;
            }
            else
            {
                if(trick[i].substring(0,1).compareTo(leadingCard.substring(0,1)) > 0 && 
                        trick[i].substring(1,2).equals(leadingCard.substring(1,2)))
                {
                    leadingCard = trick[i];
                    winner = i;
                }
            }
        }
        if(leader > 0)
        {
            for(int i = 0; i < leader; i++)
            {
                if(trick[i].substring(1,2).equals(trump) && trumpPlayed)
                {
                    if(trick[i].substring(0,1).compareTo(leadingCard.substring(0,1)) > 0)
                    {
                        leadingCard = trick[i];
                        winner = i;
                    }
                }
                else if(trick[i].substring(1,2).equals(trump))
                {
                    leadingCard = trick[i];
                    winner = i;
                    trumpPlayed = true;
                }
                else
                {
                    if(trick[i].substring(0,1).compareTo(leadingCard.substring(0,1)) > 0 && 
                            trick[i].substring(1,2).equals(leadingCard.substring(1,2)))
                    {
                        leadingCard = trick[i];
                        winner = i;
                    }
                }
            }
        }
        return winner;
    }
    
    private void restartGame()
    {
        for(Playable p : players)
        {
            p.resetHand();
        }
        startGame();
    }
}
