package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.control.gameExceptions.NotEnoughCoinsException;

public interface Buyable {
	public static final String ERROR = "Not enough coins";
	public int cost();
	
	public default void buy(Game game) throws NotEnoughCoinsException{
		if(game.getPlayerCoins() >= cost()) {
			game.playerPays(cost());
		}
		else {
			throw new NotEnoughCoinsException(ERROR);
		}
	}
}