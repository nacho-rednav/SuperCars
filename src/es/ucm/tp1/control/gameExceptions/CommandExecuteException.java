package es.ucm.tp1.control.gameExceptions;

public class CommandExecuteException extends GameException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommandExecuteException(String msg, Throwable e){
		super(msg, e);
	}
	
	public CommandExecuteException(String msg){
		super(msg);
	}

}
