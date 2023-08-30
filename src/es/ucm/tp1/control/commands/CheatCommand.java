package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.GameObjectGenerator;
import es.ucm.tp1.logic.gameObjects.GameObject;

public class CheatCommand extends Command {

	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "";

	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";
	
	private static final String WRONG_CHEAT = "Invalid argument for cheat command";
	
	private int id;

	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		game.clearLastColumn();
		game.addCheatObject(id);
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) {
		if(commandWords.length == 1) {
			try {
				id = Integer.parseInt(commandWords[0]);
				if(GameObjectGenerator.MIN_OBJ_ID <= id
						&& id <= GameObjectGenerator.MAX_OBJ_ID) {
					return this;
				}
				else {
					return null;
				}
			}
			catch(NumberFormatException e){
				return null;
			}
		}
		
		return null;
	}

}