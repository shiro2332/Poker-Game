package application;

class Card implements Comparable<Card>{
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
	
	public String fetchImageURL(){
	    return imageURL;
	}

	public int fetchPoint() {
		return point;
	}
	
	@Override
    public int compareTo(Card o) {
        return this.fetchCard().compareTo(o.fetchCard());
    }
}