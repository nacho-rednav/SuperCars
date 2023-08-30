package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Game;

public class Pedestrian extends Obstacle{
	
	private boolean goDown;
	public final static String INFO = "[PEDESTRIAN] person crossing the road up and down";
	private final static String infoDiretionDown = "down";
	private final static String infoDiretionUp = "up";
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		goDown = true;
		symbol = "☺";
		speed = 1;
	}
	
	private void killedByPlayerActions() {
		game.playerLosesCoins();
		alive = false;
	}
	
	private void movement() {
		if(y == game.getLevel().getRoadWidth()-1)
			goDown = false;
		if(y == 0)
			goDown = true;
		
		if(goDown)
			y += speed;
		else
			y -= speed;
	}
	

	@Override
	public boolean receiveCollision(Player player) {
		player.crash();
		killedByPlayerActions();
		return false;
	}

	@Override
	public boolean receiveShoot() {
		alive = false;
		killedByPlayerActions();
		return true;
	}

	@Override
	public void update() {
		movement();
	}

	@Override
	public boolean receiveExplosion() {
		receiveShoot();
		return true;
	}
	
	@Override
	public boolean receiveThunder() {
		System.out.print(" -> " + symbol);
		alive = false;
		return true;
	}
	
	@Override
	public String receiveSerialize() {
		if(goDown)
			return super.receiveSerialize() + infoDiretionDown;
		else
			return super.receiveSerialize() + infoDiretionUp;
	}
}
