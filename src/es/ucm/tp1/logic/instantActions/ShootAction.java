package es.ucm.tp1.logic.instantActions;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class ShootAction implements InstantAction{

	@Override
	public void execute(Game game) {
		int x = game.getPlayerX(), y = game.getPlayerY();
		Collider o = game.getObjectInPosition(x, y);
		while(x < game.getPlayerX() + game.getLevel().getVisibility()) {
			if(o != null) {
				if(o.receiveShoot())
					break;
			}
			x++;
			o = game.getObjectInPosition(x, y);
		}
	}
}