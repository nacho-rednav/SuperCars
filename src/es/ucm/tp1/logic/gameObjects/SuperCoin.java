package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Game;

public class SuperCoin extends Coin{
	
	public static boolean present = false;
	public final static String INFO = "[SUPERCOIN] gives 1000 coins";
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		symbol = " $";
		reward = 1000;
	}
	
	public static boolean isPresent() {
		return present;
	}
	
	public static void reset() {
		present = false;
	}

	@Override
	public void onEnter() {
		present = true;
	}

	@Override
	public void onDelete() {
		present = false;	
	}

}
