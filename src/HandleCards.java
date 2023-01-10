package src;
import java.util.ArrayList;

public class HandleCards 
{
	public ArrayList<String> cards;
	public int[] trumpValues;
	public int[][] mathHand;
	
	public HandleCards()
	{
		cards = new ArrayList<String>();
		trumpValues = new int[4];
		mathHand = new int[4][12];
	}
	
	public void getCard(String card)
	{
		cards.add(card);
	}
	
	public void organizeHand()
	{
		sortHand();
		prepareMathHand();
	}
	
	public void resetHand()
	{
		cards = new ArrayList<String>();
		mathHand = new int[4][12];
		trumpValues = new int[4];
	}
	
	private void sortHand()
	{
		boolean sorted = false;
		while(!sorted)
		{
			sorted = true;
			for(int i = 0; i < cards.size() - 1; i++)
			{
				if(cards.get(i).substring(1,2).compareTo(cards.get(i + 1).substring(1,2)) > 0)
				{
					cards.add(i + 1, cards.remove(i));
					sorted = false;
				}
			}
		}
		
		sorted = false;
		while(!sorted)
		{
			sorted = true;
			for(int i = 0; i < cards.size() - 1; i++)
			{
				if(cards.get(i).substring(1,2).equals(cards.get(i + 1).substring(1,2))
						 && cards.get(i).substring(0,1).compareTo(cards.get(i + 1).substring(0,1)) > 0)
				{
					cards.add(i + 1, cards.remove(i));
					sorted = false;
				}
			}
		}
	}
	
	public void prepareMathHand()
	{
		for(String s : cards)
		{
			String suit = s.substring(1,2);
			if(suit.equals("C"))
			{
				mathHand[0][trumpValues[0]] = Integer.parseInt(s.substring(0,1)); 
				trumpValues[0] += 1;
			}
			else if(suit.equals("D"))
			{
				mathHand[1][trumpValues[1]] = Integer.parseInt(s.substring(0,1)); 
				trumpValues[1] += 1;
			}
			else if(suit.equals("H"))
			{
				mathHand[2][trumpValues[2]] = Integer.parseInt(s.substring(0,1)); 
				trumpValues[2] += 1;
			}
			else
			{
				mathHand[3][trumpValues[3]] = Integer.parseInt(s.substring(0,1)); 
				trumpValues[3] += 1;
			}
		}
	}
}
