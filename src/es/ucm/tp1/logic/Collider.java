package es.ucm.tp1.logic;

import es.ucm.tp1.logic.gameObjects.Player;

public interface Collider {

	boolean doCollision();

	boolean receiveCollision(Player player);
	
	boolean receiveShoot();
	
	boolean receiveExplosion();
	
	boolean receiveThunder();
	
	void receiveWave();

}
