package es.ucm.tp1.control.gameExceptions;

public class InvalidPositionException extends CommandExecuteException{

	public InvalidPositionException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;

}
