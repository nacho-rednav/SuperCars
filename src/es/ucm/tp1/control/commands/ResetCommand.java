package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.control.Record;
import es.ucm.tp1.control.gameExceptions.CommandParseException;
import es.ucm.tp1.control.gameExceptions.IncorrectLevelException;
import es.ucm.tp1.control.gameExceptions.InputOutputRecordException;
import es.ucm.tp1.logic.Game;

public class ResetCommand extends Command {

	private static final String NAME = "reset";

	private static final String DETAILS = "[r]eset [<level> <seed>]";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";
	
	private static final String ERROR = "[ERROR]: Command r: ";
	
	private static final String WRONG_LEVEL_MSG = "Level must be one of: TEST, EASY, HARD, ADVANCED"; 
	private static final String WRONG_SEED_MSG = "the seed is not a proper long number";
	
	private static final String RESET_LEVEL_MSG = "Level: ";
	private static final String RESET_SEED_MSG = "Random generator initialized with seed: ";

	
	private Level level;
	private long seed;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	private void checkLevel(String level) throws IncorrectLevelException{
		this.level = Level.valueOfIgnoreCase(level);
		if(this.level == null) {
			throw new IncorrectLevelException(String.format(ERROR + WRONG_LEVEL_MSG));
		}
	}
	
	@Override
	public boolean execute(Game game) throws InputOutputRecordException{
		if(level == null)
			game.reset();
		else {
			try {
				Record record = new Record(level);
				game.reset(level, seed, record);
				System.out.println(RESET_LEVEL_MSG + level.name());
				System.out.println(RESET_SEED_MSG + seed);
			}
			catch(InputOutputRecordException ex) {
				throw ex;
			}
		}
		
		return true;
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if (words.length == 3) {
				try {
				checkLevel(words[1]);
				seed = Integer.parseInt(words[2]);
				return this;
				}
				catch(IncorrectLevelException ex) {
					throw new CommandParseException(ex.getMessage(), ex);
				}
				catch(NumberFormatException ex) {
					throw new CommandParseException(String.format(ERROR + WRONG_SEED_MSG), ex);
				}
			}
			else if (words.length == 1) {		
				return this;
			}
			else {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME,
						INCORRECT_NUMBER_OF_ARGS_MSG));
			} 
		}
		return null;
	}

}
