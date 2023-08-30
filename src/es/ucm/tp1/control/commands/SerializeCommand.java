package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GameSerializer;

public class SerializeCommand extends Command{

	private static final String NAME = "serialize";
	
	private static final String DETAILS = "[z]";

	private static final String SHORTCUT = "z";

	private static final String HELP = "serialize objects";

	public SerializeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(GameSerializer.getSerializeInfo(game));
		return false;
	}
	
}