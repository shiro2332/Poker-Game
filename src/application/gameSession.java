//Optimized
package application;

public class gameSession {
	private static gameSession session = null;
	private static Player[] playerList;
	
	private gameSession() {
		
	}
	
	//Singleton method that return current game session
	public static gameSession getSession() {
		if(session == null) {
			session = new gameSession();
			cmdLogger.log("Game session created");
		}
		return session;
	}
	
	public void setPlayer(int numOfPlayer, Player... args) {
		playerList = new Player[numOfPlayer];
		for(int i = 0; i < args.length; i++) {
			playerList[i] = args[i];
		}
	}
	
	public Player[] fetchPlayer() {
		return playerList;
	}
}
