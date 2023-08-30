package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Game;

public class Wall extends Obstacle{

	public final static String INFO = "[WALL] hard obstacle";
	private static int price = 5;
	private String wallStateList[];
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
		wallStateList = new String[] {"░","▒","█"};
		lifes = 3;
		symbol = "█";
	}
	

	@Override
	public void onDelete() {
		game.rewardPlayer(price);
		super.onDelete();
	}
	
	@Override
	public void update() {
		symbol = wallStateList[lifes-1];
	}

	@Override
	public boolean receiveExplosion() {
		alive = false;
		return true;
	}
	
	@Override
	public boolean receiveThunder() {
		System.out.print(" -> " + symbol);
		receiveExplosion();
		return true;
	}
	
	@Override
	public String receiveSerialize() {
		return super.receiveSerialize() + lifes;
	}
}
