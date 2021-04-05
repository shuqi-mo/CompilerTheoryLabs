package command;

import java.util.Iterator;
import java.util.Set;

import service.*;
import utils.*;

/**
 * Clear命令类：清空用户的会议安排
 * clear [username][password]
 */
public class Clear implements CmdBase {
	public void resInfo(String args[], UserManager usermanager, MeetingManager meetingmanager) throws MyException {
		int length = args.length;
		if(length != 3) {
			System.out.println("参数错误");
			return;
		}
		
		String username = args[1];
		String password = args[2];
		
		MyException e = new MyException();
		try {
			e.isregisterexception(username, usermanager);
			e.userpasswordexception(username, password, usermanager);
		} catch(Exception e1) {
			return;
		}
		
		// 删除用户的所有会议安排
		Set findMeeting = meetingmanager.findMeetingByUsername(username);
		if (!findMeeting.isEmpty()) {
			Iterator find = findMeeting.iterator();
			while (find.hasNext()) {
				Meeting temp = (Meeting) find.next();
				meetingmanager.meetings.remove(temp);
			}
		}
	}
}