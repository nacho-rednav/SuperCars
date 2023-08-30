package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.gameExceptions.CommandExecuteException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.View;

public class ShowRecordCommand extends Command{

	private static final String NAME = "show record";

	private static final String DETAILS = "rec[o]rd";

	private static final String SHORTCUT = "o";

	private static final String HELP = "show level record.";
	
	public ShowRecordCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		long vIn = game.getRecordTime();
		StringBuilder buffer = new StringBuilder();
		buffer.append(game.getRecordName() + " record is ");
		buffer.append(View.formatTime(vIn));
		System.out.println(buffer.toString());
		
	return false;
	}
}