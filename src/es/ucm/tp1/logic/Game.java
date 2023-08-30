package es.ucm.tp1.logic;

import java.util.Random;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.control.Record;
import es.ucm.tp1.control.gameExceptions.InputOutputRecordException;
import es.ucm.tp1.logic.gameObjects.GameObject;
import es.ucm.tp1.logic.gameObjects.Player;
import es.ucm.tp1.logic.instantActions.InstantAction;

public class Game {	
	private final String GOAL = "¦";
	private Player player;
	private Level level;
	private Record record;
	private long seed;
	private Random rand;
	private int cycle;
	boolean testEnabled;
	GameObjectContainer objects;
	
	
	//INITIALIZING FUNCTIONS
	public Game(long seed, Level level, Record record) {
		initializeGame(seed, level, record);	
		init();
	}
	
	public void initializeGame(long seed, Level level, Record record){
		this.level = level;
		this.seed = seed;
		this.record = record;
		rand = new Random(seed);
		player = new Player(this, 0, level.getRoadWidth()/2);
		objects = new GameObjectContainer();
		cycle = 0;
	}
	
	public void init() {
		GameObjectGenerator.generateGameObjects(this);
	}
	
	
	public void tryToAddObject(GameObject object, double frequency) {
		if((rand.nextDouble() < frequency)
				&& positionEmpty(object.getX(), object.getY())) {
			objects.add(object);
		}
	}
	
	public boolean positionEmpty(int x, int y) {
		return !(objects.objectInPosition(x, y));
	}
	
	public int getRandomLane() { 
		return (int) (getRandomNumber() * level.getRoadWidth()); 
	}
	
	public int getRandomColumn() { 
		return (int) (getRandomNumber() * level.getVisibility()); 
	}
	
	private Double getRandomNumber() { 
		return rand.nextDouble(); 
	}
	// -----
	
	
	//GETTERS
	public String getRecordName() {
		return record.level.name();
	}
	public long getRecordTime() {
		return record.time;
	}
	public GameObject getObjectInPosition(int x, int y) {
		return objects.getObjectInPosition(x, y);
	}
	
	public int getPlayerX() {
		return player.getX();
	}
	
	public int getPlayerY() {
		return player.getY();
	}
	
	public String getPlayerSymbol() {
		return player.toString().trim();
	}
	
	public int getPlayerCoins() {
		return player.getCoins();
	}
	
	public int getCycle() {
		return cycle;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public long getTimer() {
		return player.getTime();
	}
	
	public boolean getTestEnabled() {
		return testEnabled;
	}
	// ------
	
	
	//PLAYER RELATED FUNCTIONS
	public void movePlayerForward() {
		player.moveForward();
	}
	public void movePlayerUp() {
		player.moveUp();
	}
	public void movePlayerDown() {
		player.moveDown();
	}
	public void playerPays(int cost) {
		player.pay(cost);
	}
	public void rewardPlayer(int price) {
		player.catchCoin(price);
	}
	public void playerLosesCoins() {
		player.loseCoins();
	}

	public String playerSerialize() {
		return player.receiveSerialize();
	}
	// ---------
	
	
	//COMMAND RELATED FUNCTIONS
	public void reset() {
		reset(level,seed, record);
	}
	
	public void reset(Level level, long seed, Record record) {
		initializeGame(seed, level, record);
		GameObjectGenerator.reset();
		init();
	}
	
	public void enableTest() {
		testEnabled = true;
	}
	public void clear() {
		objects.clear();
		GameObjectGenerator.reset();
	}
	
	public void executeInstantAction(InstantAction action) {
		action.execute(this);
	}
	
	public void addCheatObject(int id) {
		GameObjectGenerator.forceAdvanceObject(this, id, getLastLane());
	}
	
	public void clearLastColumn() {
		objects.clearColumn(getLastLane(), level.getRoadWidth());
		objects.delete();
	}
	//----------
	
	
	//OTHER FUNCTIONS
	public void toggleTest() {
		testEnabled = true;
	}
	
	private int getLastLane() {
		return player.getX() + level.getVisibility() - 1;
	}
	
	public boolean inRoadLimits(int y) {
		return ((y >= 0) && (y < level.getRoadWidth()));
	}
	
	public boolean objectIsVisible(int x, int y) {
		return (getPlayerX() < x
				&& x <= getPlayerX() + level.getVisibility() - 1
				&& 0 <= y && y < level.getRoadWidth());
	}
	
	public void forceAddObject(GameObject o) {
		objects.add(o);
	}
	
	public void update() {
		objects.update();
		cycle += 1;
		GameObjectGenerator.generateRuntimeObjects(this);
		objects.delete();
	}
	//-------------
	
	
	//EXIT AND GAME OVER
	public boolean gameOver() {
		return playerCrashed() || playerWon() || playerExit();
	}
	
	public void exit()  {
		player.doExit();
	}
	
	public boolean playerExit() {
		return player.checkExit();
	}
	
	public boolean playerWon() {
		return player.isWinner();
	}
	
	public boolean playerCrashed() {
		return !player.isAlive();
	}
	public void saveNewRecord() {
		record.saveNewRecord(getTimer());
	}
	public boolean newRecord() {
		return record.getNewRecord(getTimer());
	}
	public boolean realMatchFinished() {
		return !testEnabled && playerWon();
	}
	//---------
	
	
	//VIEW RELATED FUNCTIONS
	public int advanceCamera(int x) {
		return x += player.getX();
	}

	public String positionToString(int x, int y) {
		x = advanceCamera(x);
		StringBuilder output = new StringBuilder();
		
		output.append(player.positionToString(x, y));
		output.append(objects.positionToString(x, y));
		
		if(x == level.getRoadLength())
			output.append(GOAL);
			
		return output.toString().trim();
	}	
	//------
}

