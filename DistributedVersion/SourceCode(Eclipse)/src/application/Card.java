//Optimized
package application;

class Card {
	private String suit;
	private String face;
	private int point;
	private String imageURL;

	public Card(String suit, String face) {
		this.suit = suit;
		this.face = face;
		imageURL = suit + face + ".png";
		
		switch(face) {
		case "Blank": point = 0; break;
		case "A": point = 1; break;
		case "X": 
		case "J":
		case "Q":
		case "K": point = 10; break;
		default: point = Integer.parseInt(face);
		}
	}
	
	public String fetchCard(){
	    return suit + face;
	}
	
	public String fetchSuit(){
	    return suit;
	}
	
	public String fetchFace(){
	    return face;
	}
	
	public String fetchImageURL(){
	    return imageURL;
	}

	public int fetchPoint() {
		return point;
	}
}