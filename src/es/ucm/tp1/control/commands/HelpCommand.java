package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;

public class HelpCommand extends Command {

	private static final String NAME = "help";

	private static final String DETAILS = "[h]elp";

	private static final String SHORTCUT = "h";

	private static final String HELP = "show this help";
	
	

	public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		StringBuilder buffer = new StringBuilder("Available commands:");

		for(int i = 0; i < super.getAvailableCommands().length; i++) {
			buffer.append(System.getProperty("line.separator"));
			buffer.append(super.getAvailableCommands()[i].getDetails());
			buffer.append(": ");
			buffer.append(super.getAvailableCommands()[i].getHelp());
		}

		System.out.println(buffer.toString());

		return false;
	}

}
