package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.gameExceptions.CommandExecuteException;
import es.ucm.tp1.control.gameExceptions.NotEnoughCoinsException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.instantActions.ShootAction;

public class ShootCommand extends Command implements Buyable {

	private static final String NAME = "shoot";

	private static final String DETAILS = "[s]hoot";

	private static final String SHORTCUT = "s";

	private static final String HELP = "shoot bullet";
	
	private static final String ERROR = "[ERROR]: Failed to shoot";
	
	private static final int price = 1;

	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			buy(game);
			game.executeInstantAction(new ShootAction());
			game.update();
			return true;
		}
		catch(NotEnoughCoinsException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(ERROR);
		}
	}

	@Override
	public int cost() {
		return price;
	}

}