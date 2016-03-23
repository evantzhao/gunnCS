public class Main {

	//Creates two players and a board to play upon.
	static Player p1 = new Player();
	static Player p2 = new Player();
	static Board b1 = new Board();
	static Board b2 = new Board();
	//Creates a new, unique deck to play with.
	static Deck d = new Deck();
	//The Pacifist
	static boolean peace = true;
	
	//Holds the value of 8, so I can use the 'compareTo' and 'equals' function. 
	static Card win = new Card('8', 'C');
	
	//Creates arrays to hold plays
	static Player[] hands = new Player[] {p1, p2};
	static Board[] plays = new Board[] {b1, b2};
	
	public static void main(String[] args) {
		playWar();
	}
	
	private static void playWar() {
		//Deals cards to each player.
		deal();
		
		//Counter that keeps track of the rounds the game has progressed through.
		int rounds = 0;
		
		//While there is yet no winner, the game will go through another round. 
		while(checkwin() == -1){
			playRound();
			rounds++;
		}
		
		//When there is a winner, this logic sequence parses out who the winner is, and prints out. 
		if(checkwin() == 1){
			System.out.println("Player 2 wins in " + rounds + " rounds!");
		}
		else if (checkwin() == 0){
			System.out.println("Player 1 wins in " + rounds + " rounds!");
		}
		
		//Resetting for a new game of War.
		b2.clear();
		b1.clear();
		p1.clear();
		p2.clear();
		d.init();
	}
	
	//Quick function that sees if there is a winner yet. Returns 1 if the winner is player 1, returns 0 if the winner is player two. Returns -1 if no winner is yet detected. 
	private static int checkwin() {
		if(p1.size() == 0){
			return 1;
		}
		else if(p2.size() == 0){
			return 0;
		}
		return -1;
	}

	//Places one card from each player on their respective boards.
	private static void placeCards(){
		for(int i = 0; i < plays.length; i++){ //Cycles through however many players there are. 
			if(!hands[i].isEmpty()){ //If the player has a card in his/her hand...
				plays[i].put(hands[i].get()); //Take it from their hand, and place it on the board.
			}
		}
	}
	
	//Determines if there is a card of value 2 in play. Returns true if the top cards the board contain two. 
	private static boolean isTwo(){
		//Random holder for 2, so that I can compare this.
		Card cc = new Card('2', 'D');
		
		for(int i = 0; i < plays.length; i++){
			if(plays[i].peek().equals(cc)){
				return true;
			}
		}
		return false;
	}
	
	//A boolean that indicates if the conditions are ripe for war. 
	private static boolean checkWar(){
		if(b1.peek().equals(b2.peek())){ //If two cards are of equal value, then declares war.
			return true;
		}
		else{
			return isTwo() && peace; //The only other possible case for a war, is if a two exists. If not, then no war. 
		}
	}
	
	private static void playRound() {
		//Each player places one card on their respective boards. If the person has no more cards, a card is not placed.
		placeCards();
		
		//Displays the competing cards
		System.out.println(b1.peek() + " vs. " + b2.peek());

		//Determining if there is war. 
		if(checkWar()){ //If there is war, print out the "At war" status and change the peace boolean.
			System.out.println("WAR!");
			peace = false;
			war(); //Declare war!
		}
		else{
			if(isEight()){
				if(b1.peek().equals(win)){
					roundLoop(p1);
				}
				else{
					roundLoop(p2);
				}
			}
			else{
				if(b1.peek().compareTo(b2.peek()) > 0){ //player 1 wins and gets all cards on board.
					roundLoop(p1);
				}
				else if(b2.peek().compareTo(b1.peek()) > 0){ //player 2 wins and gets all the cards on the board
					roundLoop(p2);
				}
			}
		}
	}
	
	private static boolean isEight() {
		for(int i = 0; i < plays.length; i++){
			if(plays[i].peek().equals(win)){
				return true;
			}
		}
		return false;
	}

	//Ends this round. 
	private static void roundLoop(Player p){
		cashMoney(p); //Give the victor his/her cards, which were won in this round.
		peace = true; //Keepin' the peace alive.	
		System.out.println("Player 1: " + p1.size() + " cards, Player 2: " + p2.size() + " cards.");
	}
	
	//Method that gives the victorious player all cards that reside on the board.
	private static void cashMoney(Player winner){
		while(!b1.isEmpty() || !b2.isEmpty()){
			if(!b1.isEmpty()){
				winner.put(b1.get());
			}
			if(!b2.isEmpty()){
				winner.put(b2.get());
			}
		}
	}

	private static void war() {
		// Each player places 3 cards down on their respective boards. 
		for(int i = 0; i < 3; i++){
			placeCards();
		}
		System.out.print("Down Cards for P1: ");
		printArray(b1);
		System.out.print("Down Cards for P2: ");
		printArray(b2);
		
		playRound();
	}
	
	public static void printArray(Board arr){
		int limit;
		
		if(arr.size() > 3){
			limit = 3;
		}
		else{
			limit = arr.size();
		}
		
		for(int i = 0; i < limit; i++){
			System.out.print(arr.get(i) + " ");
		}
		System.out.println();
	}

	private static void deal(){
		while(d.isEmpty() == false){
			for(int i = 0; i < hands.length; i++){
				hands[i].put(d.getCard());
			}
		}		
	}
}