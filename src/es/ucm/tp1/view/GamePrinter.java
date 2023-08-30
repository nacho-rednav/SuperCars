package es.ucm.tp1.view;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameObjects.*;
import es.ucm.tp1.utils.*;


public class GamePrinter {

	private static final String SPACE = " ";

	private static final String VERTICAL_DELIMITER = "|";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;

	private String indentedRoadBorder;

	private String indentedLlanesSeparator;

	private String margin;
	

	private static final String CRASH_MSG = String.format("Player crashed!%n");

	private static final String WIN_MSG = String.format("Player wins!%n");

	private static final String DO_EXIT_MSG = "Player leaves the game"; 
	
	private static final String GAME_OVER_MSG = "[GAME OVER] "; 
	
	private static final String NEW_RECORD_MSG = "New record!: ";
	
	public String newLine; 

	protected Game game;
	

	public GamePrinter(Game game) {
		this.game = game;
		

		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		newLine =  System.getProperty("line.separator");
	}
	
	private void addInfo(StringBuilder info, String input) {
		info.append(input);
		info.append(System.getProperty("line.separator"));
	}
	
	private String getInfo() {
		
		StringBuilder info = new StringBuilder();
		String aux = "Distance: " + (game.getLevel().getRoadLength()- game.getPlayerX());
		addInfo(info, aux);
		aux = "Coins: " + game.getPlayerCoins();
		addInfo(info, aux);
		aux = "Cycle: " + game.getCycle();
		addInfo(info, aux);
		aux = "Total obstacles: " + Obstacle.getNumObstacles();
		addInfo(info, aux);
		aux = "Total coins: " + Coin.getNumCoins();
		info.append(aux);
		if(SuperCoin.isPresent()) {
			info.append(System.getProperty("line.separator"));
			aux = "Supercoin is present";
			info.append(aux);
		}
		if(!game.getTestEnabled()) {
			info.append(System.getProperty("line.separator"));
			aux = "Elapsed Time: " + View.formatTime(game.getTimer());
			info.append(aux);
		}
		return info.toString();
	}
	
	public void getRoad() {
		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) *  game.getLevel().getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE,  game.getLevel().getVisibility() - 1) + laneDelimiter + SPACE;
		indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		getRoad();
		str.append(getInfo());
		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getLevel().getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = 0; x < game.getLevel().getVisibility(); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE))
						.append(verticalDelimiter);
			}
			if (y <  game.getLevel().getRoadWidth() - 1) {
				str.append(this.indentedLlanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);

		return str.toString();
	}
	
	public static String getObjectsInfo() {
		StringBuilder info = new StringBuilder();
		info.append(System.getProperty("line.separator"));
		info.append(Player.info());
		info.append(System.getProperty("line.separator"));
		info.append(Coin.INFO);
		info.append(System.getProperty("line.separator"));
		info.append(Obstacle.INFO);
		info.append(System.getProperty("line.separator"));
		info.append(Grenade.INFO);
		info.append(System.getProperty("line.separator"));
		info.append(Wall.INFO);
		info.append(System.getProperty("line.separator"));
		info.append(Turbo.INFO);
		info.append(System.getProperty("line.separator"));
		info.append(SuperCoin.INFO);
		info.append(System.getProperty("line.separator"));
		info.append(Truck.INFO);
		info.append(System.getProperty("line.separator"));
		info.append(Pedestrian.INFO);

		return info.toString();
	}
	
	public String endMessage(){
		
		StringBuilder endMsg = new StringBuilder();
		String s = GAME_OVER_MSG;
		endMsg.append(s);
				
		if(game.playerCrashed()) {
			endMsg.append(CRASH_MSG);
		}
		else if(game.playerWon()) {
			endMsg.append(WIN_MSG);
			if(!game.getTestEnabled() && game.newRecord()) {
				endMsg.append(System.getProperty("line.separator"));
				s =  NEW_RECORD_MSG + View.formatTime(game.getTimer());
				endMsg.append(s);
			}
		}
		else if(game.playerExit()) {
			endMsg.append(DO_EXIT_MSG);
		}
		return endMsg.toString();
	}
}
