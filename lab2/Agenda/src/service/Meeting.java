package service;

import java.util.*;

public class Meeting {
	private String start;
	private String end;
	private String title;
	public Set members = new HashSet();
	
	public Meeting(String start, String end, String title) {
		this.start = start;
		this.end = end;
		this.title = title;
	}
	
	public String getStart() {
		return start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void printinfo() {
		System.out.println("Meeting "+title+" is from: "+start+" to: "+end);
	}
}