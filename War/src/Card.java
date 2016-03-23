// Card.java

public class Card implements Comparable<Card> {
	private String mySymbol;
	private int myRank;
	private char mySuit;
	
	public Card (char rank, char suit) {
		// Note how switch works.  It's really useful.
		// Note also the character arithmetic.  Remember, a char has a Unicode
		//    value and that needs to be converted.
		switch (rank) {
			case 'A': myRank = 14; break;
			case 'K': myRank = 13; break;
			case 'Q': myRank = 12; break;
			case 'J': myRank = 11; break;
			case 'T': myRank = 10; break;
			default: myRank = rank - '0';
		}
		mySuit = suit;
		mySymbol = "" + rank + suit;
	}
	
	public int getRank() { return myRank; }
	
	public char getSuit() { return mySuit; }
	
	public String getSymbol() { return mySymbol; }

	public int compareTo(Card card) {
		// YOU WRITE THIS
		
		//returns 1 if the initial card is greater than the other card. 
		//returns -1 if the initial card is less than the other card.
		//returns 0 if both cards are of equal rank. 
		if(this.myRank - card.myRank > 0){
			return 1;
		}
		else if(this.myRank - card.myRank < 0){
			return -1;
		}
		return 0;
	}
	
	public boolean equals(Card card) {
		// YOU WRITE THIS
		
		//reliant on the previous method. 
		if(this.compareTo(card) == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String toString() {
		return mySymbol;
	}
}