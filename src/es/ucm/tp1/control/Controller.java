package es.ucm.tp1.control;

import java.util.Scanner;
import es.ucm.tp1.control.commands.Command;
import es.ucm.tp1.control.gameExceptions.GameException;
import es.ucm.tp1.control.gameExceptions.InputOutputRecordException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";
	
	private static final String EXECUTING = "[DEBUG] Executing: ";

	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
	}

	public void printGame() {
		System.out.println(printer);
	}
	

	public void printEndMessage() {
		System.out.println(printer.endMessage());
	} 

	public void run() {
		printGame();
		boolean refreshDisplay = false;
		
		while (!game.gameOver()){
			if (refreshDisplay) {
				printGame();
			}
			refreshDisplay = false;
			
			System.out.println(PROMPT);
			String s = scanner.nextLine();
			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println(EXECUTING + s);
			
			try {
				Command command = Command.getCommand(parameters, null);
				refreshDisplay = command.execute(game);
			}
			
			catch(InputOutputRecordException ex) {
				System.out.println(ex.getMessage());
				return;
			}
			
			catch(GameException ex) {
				System.out.format(ex.getMessage() + "\n\n");
			}
			
		}		
		if (refreshDisplay) {
			printGame();
		}
		printEndMessage();
	}

}