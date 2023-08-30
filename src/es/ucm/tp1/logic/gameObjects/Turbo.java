package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Game;

public class Turbo extends GameObject{
	
	public final static String INFO = "[TURBO] pushes the car: 3 columns";
	private static final int boost = 3;
	
	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		symbol = ">>>";
	}
	@Override
	public boolean doCollision() {
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.catchTurbo(boost);
		alive = false;
		return false;
	}

	@Override
	public boolean receiveShoot() {
		return false;
	}

	@Override
	public void onEnter() {

	}

	@Override
	public void update() {
		
	}

	@Override
	public void onDelete() {

	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void receiveWave() {
		x += 1;
	}
	@Override
	public boolean receiveExplosion() {
		return false;
	}
	public boolean receiveThunder() {
		return false;
	}
}

