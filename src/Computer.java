package src;

public class Computer extends HandleCards implements Playable
{
	private String trumpSuit;
	private int handValue;
	int strongSuit;
	
	public Computer()
	{
		trumpSuit = "";
		handValue = 0;
		strongSuit = 0;
	}
	
	public String returnTrump()
	{
		return trumpSuit;
	}
	
	public String playCard(String[] trick)
	{
		boolean isFirst = true;
		for(int i = 0; i < trick.length; i++)
		{
			if(trick[i] != null){isFirst = false;}
		}
		
		if(isFirst)
		{
			return cards.remove(cards.size()-1);
		}
		else
		{
			return cards.remove(0);
		}
	}
	
	private void calculateHandValue()
	{
		calculateTrump();
		prepareMathHand();
		int sum = 0;
		int hasNine = 0;
		int hasTen = 0;
		int hasJack = 0;
		int hasQueen = 0;
		int hasKing = 0;
		int hasAce = 0;
		
		for(int j = 0; j < trumpValues[strongSuit]; j++)
		{	
			switch(mathHand[strongSuit][j])
			{
				case 1: hasNine++;
						break;
				case 2: hasJack++;
						break;
				case 3: hasQueen++;
						break;
				case 4: hasKing++;
						break;
				case 5: hasTen++;
						break;
				case 6: hasAce++;
						break;
			}
		}
		if(hasTen > 0 && hasJack > 0 && hasQueen > 0 && hasKing > 0 && hasAce > 0)
		{
			sum += 15;
			hasTen--;
			hasJack--;
			hasQueen--;
			hasKing--;
			hasAce--;
		}
		
		if(hasQueen > 0 && hasKing > 0)
		{
			sum += 4;
			hasQueen--;
			hasKing--;
		}
		
		if(hasNine > 0)
		{
			sum += hasNine;
		}
		handValue = sum;
	}
	
	private void calculateTrump()
	{
		int strongSuitValue = 0;
				
		for(int i = 0; i < 4; i++)
		{
			int hasNine = 0;
			int hasTen = 0;
			int hasJack = 0;
			int hasQueen = 0;
			int hasKing = 0;
			int hasAce = 0;
			int sum = 0;
			for(int j = 0; j < trumpValues[i]; j++)
			{
				switch(mathHand[i][j])
				{
					case 1: hasNine++;
							break;
					case 2: hasJack++;
							break;
					case 3: hasQueen++;
							break;
					case 4: hasKing++;
							break;
					case 5: hasTen++;
							break;
					case 6: hasAce++;
							break;
				}
			}
			if(hasTen > 0 && hasJack > 0 && hasQueen > 0 && hasKing > 0 && hasAce > 0)
			{
				sum += 20;
				hasTen--;
				hasJack--;
				hasQueen--;
				hasKing--;
				hasAce--;
			}
			
			if(hasAce > 0 && hasTen > 0 && hasJack > 0)
			{
				sum += 10;
				hasAce--;
				hasTen--;
				hasJack--;
			}
			
			if(hasAce > 0 && hasTen > 0)
			{
				sum += 8;
				hasAce--;
				hasTen--;
			}
			
			if(hasQueen > 0 && hasKing > 0)
			{
				sum += 6;
				hasQueen--;
				hasKing--;
			}
			
			sum += hasNine + hasJack + hasQueen * 2 + hasKing * 2 + hasTen * 3 + hasAce * 4;
			
			if(sum > strongSuitValue)
			{
				strongSuitValue = sum;
				strongSuit = i;
			}
		}
		
		switch(strongSuit)
		{
			case 0: trumpSuit = "C";
					break;
			case 1: trumpSuit = "D";
					break;
			case 2: trumpSuit = "H";
					break;
			case 3: trumpSuit = "S";
					break;
		}
	}
}
