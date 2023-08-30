package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Game;

public class Obstacle extends GameObject{
	
	private static int nObstacles = 0;
	public final static String INFO = "[Obstacle] hits car";
	protected int lifes, speed;

	
	public Obstacle(Game game, int x, int y) {
		super(game, x, y);
		symbol = "░";
		lifes = 1;
		speed = 0;
	}
	
	@Override
	public void onEnter() {
		nObstacles += 1;
	}
	@Override
	public void onDelete() {
		nObstacles -= 1;
	}
	@Override
	public boolean isAlive() {
		return alive;
	}
	@Override 
	public void update() {
		
	}
	
	public static void reset() {
		nObstacles = 0;
	}
	
	public static int getNumObstacles() {
		return nObstacles;
	}
	
	@Override
	public boolean doCollision() {
		
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		
		player.crash();
		
		return false;
	}

	@Override
	public boolean receiveShoot() {
		lifes -= 1;
		alive = lifes > 0;
		return true;
	}
	
	@Override
	public void receiveWave() {
		x += 1;
	}

	@Override
	public boolean receiveExplosion() {
		receiveShoot();
		return true;
	}
	
	@Override
	public boolean receiveThunder() {
		System.out.print(" -> " + symbol);
		
		receiveShoot();
		return true;
	}
}
