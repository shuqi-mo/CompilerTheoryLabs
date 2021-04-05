package command;

import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import service.*;
import utils.*;

public class Add implements CmdBase{
	public void resInfo(String args[], UserManager usermanager, MeetingManager meetingmanager) throws ParseException, MyException {
		int length = args.length;
		if(length != 7) {
			System.out.println("参数错误");
			return;
		}
		
		String username = args[1];
		String password = args[2];
		String other = args[3];
		String start = args[4];
		String end = args[5];
		String title = args[6];
		
		MyException e = new MyException();
		try {
			e.isregisterexception(username, usermanager);
			e.isregisterexception(other, usermanager);
			e.userpasswordexception(username, password, usermanager);
			e.timeformatexception(start, end);
		} catch(Exception e1) {
			return;
		}
		
		// 判断添加会议是否合法
		Set findMeeting = meetingmanager.findMeetingByUsername(username);
		int test = 0;
		DateFormat sf = new SimpleDateFormat("MM-dd-hh:mm");
		Date startTime = (Date) sf.parse(start);
		Date endTime = (Date) sf.parse(end);
		if (!findMeeting.isEmpty()) {
			Iterator find = findMeeting.iterator();
			while (find.hasNext()) {
				Meeting temp = (Meeting) find.next();
				Date tempStart = (Date) sf.parse(temp.getStart());
				Date tempEnd = (Date) sf.parse(temp.getEnd());
				if ((startTime.after(tempEnd)) || (endTime.before(tempStart))) 
					test = 0;
				else test = test + 1;
			}
			if(test != 0) {
				System.out.println("会议时间冲突");
				return;
			}
		}
		meetingmanager.add(username, password, other, start, end, title);
		System.out.println("会议"+title+"添加成功！");
	}
}