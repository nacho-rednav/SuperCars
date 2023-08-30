package es.ucm.tp1.logic;

import es.ucm.tp1.logic.gameObjects.*;
import es.ucm.tp1.logic.instantActions.ThunderAction;

public class GameObjectGenerator {
	
	public final static int MAX_OBJ_ID = 5;
	public final static int MIN_OBJ_ID = 1;

	
	public static void generateGameObjects(Game game) {
		for(int x = game.getLevel().getVisibility() /2; x < game.getLevel().getRoadLength(); x++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), 
					game.getLevel().obstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), 
					game.getLevel().coinFrequency());
			
			if (game.getLevel().hasAdvancedObjects()) {
				game.tryToAddObject(new Wall(game, x, game.getRandomLane()), 
						game.getLevel().advancedObjectsFrequency());
				game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), 
						game.getLevel().advancedObjectsFrequency());
				if (!SuperCoin.isPresent()) {
					game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()),
							game.getLevel().advancedObjectsFrequency());
				}
				game.tryToAddObject(new Truck(game, x, game.getRandomLane()), 
						game.getLevel().advancedObjectsFrequency());
				game.tryToAddObject(new Pedestrian(game, x, 0), 
						game.getLevel().advancedObjectsFrequency());
			}
		}
	}
	
	public static void reset() {
			Obstacle.reset ();
			Coin.reset ();
			SuperCoin.reset();
	}
	
	public static void forceAdvanceObject(Game game, int id, int x) {
		GameObject o = null;
		switch (id) {
		case 1:
		o = new Wall(game, x, game.getRandomLane());
		break;
		case 2:
		o = new Turbo(game, x, game.getRandomLane());
		break;
		case 3:
		o = new SuperCoin(game, x, game.getRandomLane());
		break;
		case 4:
		o = new Truck(game, x, game.getRandomLane());
		break;
		case 5:
		o = new Pedestrian(game, x, 0);
		break;
		}
		game.forceAddObject(o);
	}
	
	public static void generateRuntimeObjects(Game game) {
		if (game.getLevel().hasAdvancedObjects()) {
			game.executeInstantAction(new ThunderAction());
		}
	}
}
