package command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import service.*;
import utils.*;

/**
 * Delete命令类：根据会议标签删除用户的会议安排
 * delete [username][password][title]
 */
public class Delete implements CmdBase {
	public void resInfo(String args[], UserManager usermanager, MeetingManager meetingmanager) throws MyException {
		int length = args.length;
		if(length != 4) {
			System.out.println("参数错误");
			return;
		}
		
		String username = args[1];
		String password = args[2];
		String title = args[3];
		
		MyException e = new MyException();
		try {
			e.isregisterexception(username, usermanager);
			e.userpasswordexception(username, password, usermanager);
		} catch(Exception e1) {
			return;
		}
		
		// 删除指定标签的会议安排
		Set findMeeting = meetingmanager.findMeetingByUsername(username);
		if (!findMeeting.isEmpty()) {
			Iterator find = findMeeting.iterator();
			while (find.hasNext()) {
				Meeting temp = (Meeting) find.next();
				if(temp.getTitle().equals(title))
					meetingmanager.meetings.remove(temp);
			}
		}
	}
}
