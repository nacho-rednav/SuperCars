package es.ucm.tp1.control.gameExceptions;

public class NotEnoughCoinsException extends CommandExecuteException{
	
	public NotEnoughCoinsException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;
}
