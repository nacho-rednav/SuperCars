package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Game;

public class Truck extends Obstacle{
	
	public final static String INFO = "[TRUCK] moves towards the player";
	
	public Truck(Game game, int x, int y) {
		super(game, x, y);
		symbol = "←";
		speed = 1;
	}

	@Override
	public void update() {
		x -= speed;
	}

}
