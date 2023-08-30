package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.instantActions.ExplosionAction;

public class Grenade extends GameObject{
	
	private int count;
	public final static String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";

	
	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		count = 4;
		symbol = "ð";
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(symbol);
		s.append("[");
		s.append(String.valueOf(count));
		s.append("]");
		return s.toString();
	}

	@Override
	public boolean doCollision() {
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
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
		count -= 1;
		if(count == 0)
			alive = false;
	}

	@Override
	public void onDelete() {
		game.executeInstantAction(new ExplosionAction(x, y));
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
	
	@Override
	public boolean receiveThunder() {
		return false;
	}
	
	@Override
	public String receiveSerialize() {
		return super.receiveSerialize() + count;
	}
}
