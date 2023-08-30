package es.ucm.tp1.logic.instantActions;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class ExplosionAction implements InstantAction{
	
	private int x, y;
	
	public ExplosionAction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute(Game game) {	
		Collider o;
		for (int dx = -1; dx <= 1; ++dx) {
		    for (int dy = -1; dy <= 1; ++dy) {
		    	o = game.getObjectInPosition(x+dx, y+dy);
				if(o != null)
					o.receiveExplosion();
		    }
		}
	}
}
