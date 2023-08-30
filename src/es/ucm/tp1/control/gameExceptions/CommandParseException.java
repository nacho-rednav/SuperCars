package es.ucm.tp1.control.gameExceptions;

public class CommandParseException extends GameException{

	private static final long serialVersionUID = 1L;
	
	public CommandParseException(String msg, Throwable e){
		super(msg, e);
	}
	
	public CommandParseException(String msg){
		super(msg);
	}
}
