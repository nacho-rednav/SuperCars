package es.ucm.tp1.logic;

import java.util.ArrayList;
import es.ucm.tp1.logic.gameObjects.GameObject;


public class GameObjectContainer {
	private ArrayList<GameObject> gameobjects;
	
	public GameObjectContainer() {
	gameobjects = new ArrayList<>();
	}
	
	public void delete() {
		ArrayList<GameObject> aux = new ArrayList<>();
		
		for(GameObject object : gameobjects) {
			if(object.isAlive())
				aux.add(object);
			else object.onDelete();
		}
		gameobjects = aux;
	}
	
	public void add(GameObject o) {
		gameobjects.add(o);
		o.onEnter();
	}
	
	public boolean objectInPosition(int x, int y) {
		return (getObjectInPosition(x, y) != null);
	}
	
	public GameObject getObjectInPosition(int x, int y) {
		for(GameObject o : gameobjects) {
			if(o.isInPosition(x, y))
				return o;
		}
		return null;
	}
	
	public String positionToString(int x, int y) {
		String buffer = "";
		for(GameObject o : gameobjects) {
			if(o.isInPosition(x, y) && o.isAlive()) {
				buffer += o.toString();
				buffer += " ";
			}
		}
		
		return buffer;
	}
	
	public void clearColumn(int x, int width) {
		GameObject o;
		int y = 0;
		while(y < width) {
			o = getObjectInPosition(x, y);
			if(o != null)
				o.setDead();
			y++;
		}
	}
	
	public void clear() {
		for(GameObject o : gameobjects)
			o.onDelete();
		gameobjects = new ArrayList<>();
	}
	
	public void update() {
		for(GameObject o : gameobjects)
			o.update();
	}
}
