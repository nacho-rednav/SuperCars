package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.gameExceptions.CommandExecuteException;
import es.ucm.tp1.control.gameExceptions.NotEnoughCoinsException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.instantActions.WaveAction;

public class WaveCommand extends Command implements Buyable {

	private static final String NAME = "wave";

	private static final String DETAILS = "[w]ave";

	private static final String SHORTCUT = "w";

	private static final String HELP = "do wave";
	
	private static final String ERROR = "[ERROR]: Failed to wave\n";

	
	private static final int price = 5;

	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			buy(game);
			game.executeInstantAction(new WaveAction());
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