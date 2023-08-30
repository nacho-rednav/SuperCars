package es.ucm.tp1.view;

public class View {
	
	public View() {
		
	}
	
	public static String formatTime(long time) {
		return String.format("%.2f", (double)time / 1000) + " s";
	}
}
