package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Game;

public class Coin extends GameObject{

	private static int nCoins = 0;
	public final static String INFO = "[Coin] gives 1 coin to the player";
	protected int reward;
	
	public Coin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "¢";
		reward = 1;
	}
	
	public static void reset() {
		nCoins = 0;
	}
	
	@Override
	public void onEnter() {
		nCoins += 1;
	}
	@Override
	public void onDelete() {
		nCoins -= 1;
	}
	@Override
	public boolean isAlive() {
		return alive;
	}
	@Override 
	public void update() {
		
	}
	
	public static int getNumCoins() {
		return nCoins;
	}

	@Override
	public boolean doCollision() {
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.catchCoin(reward);
		alive = false;
		
		return false;
	}

	@Override
	public boolean receiveShoot() {
		return false;
	}

	@Override
	public void receiveWave() {
		this.x += 1;
	}

	@Override
	public boolean receiveExplosion() {
		return false;
	}
	
	@Override
	public boolean receiveThunder() {
		return false;
	}
}
