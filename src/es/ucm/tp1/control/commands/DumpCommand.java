package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.gameExceptions.CommandExecuteException;
import es.ucm.tp1.control.gameExceptions.CommandParseException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GameSerializer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DumpCommand extends Command{

	private static final String NAME = "dump";

	private static final String DETAILS = "[d]ump <filename>";

	private static final String SHORTCUT = "d";

	private static final String HELP = "Shows the content of a saved file.";
	
	private static final String VERSION = "3.0";
	
	private static final String WELCOME_MSG = String.format("Super cars %s\n", VERSION);
	
	
	private String filename;
	
	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if(words.length == 2) {
				filename = words[1];
				return this;
			}
			else
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME,
						INCORRECT_NUMBER_OF_ARGS_MSG));
		}
		
		return null;
	}

	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		String file = filename + ".txt";
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    sb.append(WELCOME_MSG);
		    while (line != null) {
		        sb.append(System.lineSeparator());
		        sb.append(line);
		        line = br.readLine();
			}
		    String everything = sb.toString();
		    System.out.println(everything);
		}
		catch(IOException ex) {
			throw new CommandExecuteException("An error ocurred on reading a file.\n");
		}
	return false;
	}
}