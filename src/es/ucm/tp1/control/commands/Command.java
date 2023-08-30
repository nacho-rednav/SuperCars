package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.control.gameExceptions.CommandExecuteException;
import es.ucm.tp1.control.gameExceptions.CommandParseException;
import es.ucm.tp1.control.gameExceptions.InputOutputRecordException;
import es.ucm.tp1.logic.Game;

public abstract class Command {

	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
		
	private static final String UNKNOWN_COMMAND_MSG = "[ERROR]: Unknown command";


	/* @formatter:off */
	private static final Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new MoveUpCommand(),
		new MoveDownCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new TestCommand(),
		new ShootCommand(),
		new GrenadeCommand(),
		new WaveCommand(),
		new ClearCommand(),
		new CheatCommand(),
		new SerializeCommand(),
		new SaveCommand(),
		new DumpCommand(),
		new ShowRecordCommand()
	};
	/* @formatter:on */

	public static Command getCommand(String[] commandWords, Level level) throws CommandParseException{
		Command command = null;
		int i = 0;
		
		try {
			while(i < AVAILABLE_COMMANDS.length && command == null) {
			command = AVAILABLE_COMMANDS[i].parse(commandWords);
			i++;
		}
		}
		
		catch(CommandParseException ex){
			throw new CommandParseException(ex.getMessage());
		}
	
		if(command == null) {
			throw new CommandParseException (UNKNOWN_COMMAND_MSG); 
		}
		
		return command;
	}

	private final String name;

	private final String shortcut;

	private final String details;

	private final String help;

	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}

	public abstract boolean execute(Game game) throws CommandExecuteException, InputOutputRecordException;

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", name,
						INCORRECT_NUMBER_OF_ARGS_MSG));
			} else {
				return this;
			}
		}
		return null;
	}

	protected static Command[] getAvailableCommands() {
		return AVAILABLE_COMMANDS;
	}
	
	protected String getHelp() {
		return help;
	}
	
	protected String getDetails() {
		return details;
	}

}
