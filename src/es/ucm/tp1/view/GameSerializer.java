package es.ucm.tp1.view;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameObjects.*;

public class GameSerializer {

public static void displayPlayer(StringBuilder info, Game game) {
	info.append(game.playerSerialize());
	info.append(System.getProperty("line.separator"));

}

public static String getSerializeInfo(Game game) {
	GameObject o;
	StringBuilder info = new StringBuilder();
	boolean playerDisplayed = false;
	
	info.append("---- ROAD FIGHTER SERIALIZED ----");
	info.append(System.getProperty("line.separator"));
	info.append("Level: " + game.getLevel());
	info.append(System.getProperty("line.separator"));
	info.append("Cycles: " + game.getCycle());
	info.append(System.getProperty("line.separator"));
	info.append("Coins: " + game.getPlayerCoins());
	info.append(System.getProperty("line.separator"));
	info.append("Game Objects: ");
	info.append(System.getProperty("line.separator"));
	
	for (int x = 0; x < game.getLevel().getRoadLength(); x++) {
		for (int y = 0; y < game.getLevel().getRoadWidth(); y++) {
			o = game.getObjectInPosition(x, y);
			if(x == game.getPlayerX() && y == game.getPlayerY() && !playerDisplayed) {
				displayPlayer(info, game);
				playerDisplayed = true;
			}
			if(o != null) {
				info.append(o.receiveSerialize());
				info.append(System.getProperty("line.separator"));
			}
		}
	}
	if(!playerDisplayed)
		displayPlayer(info, game);
	
	return info.toString();
}
	
}		