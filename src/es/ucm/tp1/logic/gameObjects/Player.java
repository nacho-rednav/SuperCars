package es.ucm.tp1.logic.gameObjects;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class Player extends GameObject{
	
	private final static String INFO = "[Car] the racing car";
	
	private int playerState;
	private String playerStateList[];
	private int coins;
	private long startTime;
	private long endTime;
	private boolean wantsExit;
	
	public Player(Game game, int x, int y) {
		super(game, x, y);
		playerState = 0;
		playerStateList = new String[] {"> ", "@ "}; 
		coins = 5;
		startTime = 0;
		endTime = 0;
		wantsExit = false;
	}
	
	public static String info() {
		return INFO;
	}
	
	public boolean inPosition(int x, int y) {
		return (x == this.x && y == this.y);
	}
	
	public String positionToString(int x, int y) {
		String s = "";
		
		if(inPosition(x, y))
			s = toString();
		return s;
	}
	
	private void moveDir(int dx, int dy) {
		doCollision();
		if(alive) {
			if(game.inRoadLimits(y+dy)) {
				y += dy;
				x += dx;
			}
			doCollision();
		}
	}
	
	public void moveForward() {
		moveDir(1,0);
	}
	public void moveDown() {
		moveDir(1,1);
	}
	public void moveUp() {
		moveDir(1,-1);
	}

	public int getX() {
		return x;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public void crash() {
		playerState = 1;
	}
	
	public void catchCoin(int coins) {
		this.coins += coins;
	}
	
	public void loseCoins() {
		coins = 0;
	}
	
	public long getTime() {
		if(game.getCycle() == 0) {
			startTime = System.currentTimeMillis();
			return endTime;
		}
		else {
			
			endTime = System.currentTimeMillis() - startTime;
			return endTime;
		}
	}
	
	public boolean isWinner() {
		return x > game.getLevel().getRoadLength();
	}
	
	@Override
	public String toString() {
		return playerStateList[playerState];
	}

	public boolean checkExit() {
		return wantsExit;
	}

	public void doExit() {
		wantsExit = true;
	}
	
	public void pay(int cost) {
		coins -= cost;
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
		return playerState!=1;
	}

	@Override
	public boolean doCollision() {
		Collider other = game.getObjectInPosition(x, y);
		if (other != null) {
		return other.receiveCollision (this);
		}
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

	public void catchTurbo(int boost) {
		x += 3;
	}

	@Override
	public void receiveWave() {		
	}

	@Override
	public boolean receiveExplosion() {
		return false;
	}
	
	@Override
	public boolean receiveThunder() {
		return false;
	}
	
	public String receiveSerialize() {
		return toString() + " (" + this.x + ", " + this.y + ") ";
	}
}
