package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.gameExceptions.CommandExecuteException;
import es.ucm.tp1.control.gameExceptions.CommandParseException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GameSerializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command{

	private static final String NAME = "save";

	private static final String DETAILS = "sa[v]e <filename>";

	private static final String SHORTCUT = "v";

	private static final String HELP = "Save the state of the game to a file.";
	
	private String filename;
	
	public SaveCommand() {
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
		String data = GameSerializer.getSerializeInfo(game);
		try(BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
			out.write(data);
			System.out.println(String.format("Game successfully saved to file %s.txt", filename));
		}
		catch(IOException ex) {
			throw new CommandExecuteException("An error ocurred on writing file");
		}
		return false;
	}
}
