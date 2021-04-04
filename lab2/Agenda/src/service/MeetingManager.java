package service;

import java.util.*;

public class MeetingManager {
	public Set meetings = new HashSet();
	
	public void add(String name, String password, String other, String start, String end, String title) {
		Meeting newmeeting = new Meeting(start, end, title);
		newmeeting.members.add(name);
		newmeeting.members.add(other);
		meetings.add(newmeeting);
	}
	
	public Set findMeetingByUsername(String name) {
		Iterator iterator = meetings.iterator();
		Set findMeeting = new HashSet();
		while (iterator.hasNext()) {
			Meeting tempMeeting = (Meeting) iterator.next();
			if (tempMeeting.members.toString().contains(name))
				findMeeting.add(tempMeeting);
		}
		return findMeeting;
	}
}
