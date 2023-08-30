package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public abstract class GameObject implements Collider{
	protected int x, y;
	protected Game game;
	protected String symbol;
	boolean alive;
	
	public GameObject(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
		alive = true;
	}
	public int getX() { 
		return x; 
	}
	public int getY() { 
		return y; 
	}
	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public void setDead() {
		alive = false;
	}
	
	public abstract void onEnter();
	public abstract void update();
	public abstract void onDelete();
	public abstract boolean isAlive();

	@Override
	public String toString() {
		return symbol;
	}
	
	public String receiveSerialize() {
		return symbol + " (" + this.x + ", " + this.y + ") ";
	}
}