package src;
import java.util.Random;

public class DeckHandler 
{
    public String[] cards;
    private final int CARDS_IN_DECK = 48;
    private Random generator;
    
    public DeckHandler(int decks)
    {
        cards = new String[CARDS_IN_DECK * decks];
        generator = new Random();
        createDeck(decks);
    }
    
    private void createDeck(int decks)              //Generates each card of a pinochle deck by cycling first by card
    {                                               //value then by suite and does this for each deck.
        int position = 0;
        
        for(int i = 1; i < 7; i++)
        {
            for(int y = 0; y < decks * 2; y++)
            {
                for(int j = 0; j < 4; j++)
                {
                    cards[position] = i + "SCDH".substring(j, j + 1);
                    position++;
                }
            }
        }
        shuffleDeck();
    }
    
    public void shuffleDeck()                           //Shuffles deck in a pseudo real shuffling method.
    {                                                   //Splice is used to add some randomization by taking cards from
        int times = generator.nextInt(6) + 3;           //the top of the deck and adding to a random location in the 
                                                        //middle. Splicing is performed once every three shuffles.
        for(int i = 1; i <= times; i++)
        {
            if(i%3 == 0)
            {
                splice(generator.nextInt(cards.length / 4));
            }
            for(int j = 0; j < cards.length / 2; j++)
            {
                String temp = cards[0];
                for(int k = 1; k <= j + 24; k++)
                {
                    cards[k-1] = cards[k];
                }
                cards[j + 24] = temp;
            }
        }
        cutDeck(generator.nextInt(3));
    }
    
    private void splice(int placement)
    {
        for(int i = 0; i < 10; i++)
        {
            String temp = cards[0];
            for(int j = 1; j <= cards.length / 2 + i + placement; j++)
            {
                cards[j-1] = cards[j];
            }
            cards[cards.length / 2 + i + placement] = temp;
        }
    }
    
    private void cutDeck(int cutPos)                        //Cuts the deck and adds a random number of cards from the
    {                                                       //top of a deck to the bottom.
        for(int i = 0; i <= cutPos; i++)
        {
            String temp = cards[0];
            for(int j = 1; j < cards.length; j++)
            {
                cards[j-1] = cards[j];
            }
            cards[cards.length - 1] = temp;
        }
    }
}
