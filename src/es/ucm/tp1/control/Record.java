package es.ucm.tp1.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import es.ucm.tp1.control.gameExceptions.InputOutputRecordException;

public class Record {

	public Level level;
	public long time;
	private ArrayList<String> info;
	
	public Record(Level level) throws InputOutputRecordException{
		this.level = level;
		info = new ArrayList<String>();
		try {
			readFile();
			time = getTime();
		}
		catch(InputOutputRecordException ex) {
			throw ex;
		}
	}
	
	public boolean getNewRecord(long time) {
		return time < this.time;
	}
	
	private void readFile() throws InputOutputRecordException{
		try(BufferedReader br = new BufferedReader(new FileReader("record.txt"))) {
		    String line = br.readLine();
		    while(line != null) {
		    	info.add(line);
			    line = br.readLine();
		    }
		}
		catch(IOException ex) {
			throw new InputOutputRecordException("An error ocurred on record file.\n");
		}
	}
	
	public void saveNewRecord(long time) {
		String newInfo;
		if(getNewRecord(time)) {
			newInfo = prepareNewInfo(time);
			try {
				writeRecord(newInfo);
			}
			catch(InputOutputRecordException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	private String prepareNewInfo(long time) {
		StringBuilder buff = new StringBuilder();
		String[] lineSplit;
		for(String s: info) {
			lineSplit = s.trim().split(":");
			if(level != Level.valueOfIgnoreCase(lineSplit[0])) {
				buff.append(s + "\n");
			}
		}
		buff.append(String.format("%s:%s\n", level.name(), String.valueOf(time)));
		return buff.toString();
	}
	
	private void writeRecord(String newInfo) throws InputOutputRecordException {
		StringBuilder aux = new StringBuilder();
		aux.append(newInfo);
		try(BufferedWriter out = new BufferedWriter(new FileWriter("record.txt"))) {
			out.write(aux.toString());
		}
		catch(IOException ex) {
			throw new InputOutputRecordException("Couldn`t save new record.\n");
		}
	}
	
	private long getTime(){
		String [] lineSplit;
		for(String s : info) {
			lineSplit = s.trim().split(":");
			if(level == Level.valueOfIgnoreCase(lineSplit[0]))
				return Long.parseLong(lineSplit[1]);
		}
	    return Long.MAX_VALUE;
	}
}
