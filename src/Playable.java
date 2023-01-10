package src;

public interface Playable 
{
	void getCard(String card);
	
	void organizeHand();
	
	void resetHand();
	
	String playCard(String[] trick);
}
