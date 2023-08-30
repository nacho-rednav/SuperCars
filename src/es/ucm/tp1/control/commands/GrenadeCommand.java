package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.gameExceptions.CommandExecuteException;
import es.ucm.tp1.control.gameExceptions.CommandParseException;
import es.ucm.tp1.control.gameExceptions.InvalidPositionException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameObjects.Grenade;

public class GrenadeCommand extends Command implements Buyable {

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private static final String ERROR = "[ERROR]: Failed to add grenade";
	
	private static final String PARSE_ERROR = "[ERROR]: Incorrect number of arguments for grenade command: [g]renade <x> <y>";
	
	private static final int price = 3;
	
	private int x, y;
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	private void checkPosition(Game game) throws InvalidPositionException{
		if(!(game.objectIsVisible(x, y)
				&& game.positionEmpty(x, y))) {
			throw new InvalidPositionException("Invalid position.");
		}
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		x += game.getPlayerX();
		try {
			checkPosition(game);
			buy(game);
			Grenade g = new Grenade(game, x, y);
			game.forceAddObject(g);
			game.update();
			return true;
		}
		catch(CommandExecuteException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(ERROR);
		}
	}

	@Override
	public int cost() {
		return price;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException{
		
		if (matchCommandName(commandWords[0])) {
			if(commandWords.length == 3) {
				x = Integer.parseInt(commandWords[1]);
				y = Integer.parseInt(commandWords[2]);
				return this;
			}
			else {
				throw new CommandParseException (PARSE_ERROR);
			}
		}
		return null;
	}

}