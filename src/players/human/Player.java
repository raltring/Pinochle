package src.players.human;
import java.util.Scanner;

import src.players.HandleCards;
import src.players.Playable;

public class Player extends HandleCards implements Playable
{
	Scanner in;
	
	public Player()
	{
		in = new Scanner(System.in);
	}
	
	public void displayHand()
	{
		System.out.print("Hand: ");
		for(String i : cards)
		{
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public String playCard(String[] trick)
	{
		String playedCard = in.next().toUpperCase();
		while(cards.indexOf(playedCard) == -1)
		{
			playedCard = in.next().toUpperCase();
		}
		cards.remove(playedCard);
		return playedCard;
	}
}
