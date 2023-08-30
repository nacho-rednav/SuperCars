package es.ucm.tp1.logic.instantActions;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class ThunderAction implements InstantAction{
	
	private int x, y;
	
	private static final String HIT_POSITION_MSG = "Thunder hit position: ";
	
	@Override
	public void execute(Game game) {
		x = game.getRandomColumn();
		y = game.getRandomLane();
		String coords = String.format("(%d , %d)", x, y);
		System.out.print(HIT_POSITION_MSG + coords);
		
		Collider o = game.getObjectInPosition(x+game.getPlayerX(), y);
		if(o != null) {
			o.receiveThunder();
			}
		System.out.println();
	}
}