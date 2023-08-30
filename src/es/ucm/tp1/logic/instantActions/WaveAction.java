package es.ucm.tp1.logic.instantActions;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class WaveAction implements InstantAction{

	@Override
	public void execute(Game game) {
		Collider o;
		
		for(int y = 0; y < game.getLevel().getRoadWidth(); y++) {
			for(int x = game.getPlayerX() + game.getLevel().getVisibility() - 2; x >= game.getPlayerX(); x--) {
				o = game.getObjectInPosition(x, y);
				if(o != null && game.positionEmpty(x+1, y)) {
					o.receiveWave();
				}
			}
		}
				
	}
}
