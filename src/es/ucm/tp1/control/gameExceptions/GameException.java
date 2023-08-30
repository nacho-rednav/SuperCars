package es.ucm.tp1.control.gameExceptions;

public class GameException extends Exception{
	
	
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public GameException(String msg, Throwable e) {
		this.msg = msg;
	}
	
	public GameException(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
