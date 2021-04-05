package service;

import java.util.*;

/**
 * 用户管理类
 */
public class MeetingManager {
	public Set meetings = new HashSet();
	
	/**
	 * 添加会议
	 * @param name
	 * @param password
	 * @param other
	 * @param start
	 * @param end
	 * @param title
	 */
	public void add(String name, String password, String other, String start, String end, String title) {
		Meeting newmeeting = new Meeting(start, end, title);
		newmeeting.members.add(name);
		newmeeting.members.add(other);
		meetings.add(newmeeting);
	}
	
	/**
	 * 查找用户的会议安排
	 * @param name 输入用户名
	 * @return 返回用户参与的会议集合
	 */
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
