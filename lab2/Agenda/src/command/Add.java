package command;

import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import service.UserManager;
import service.MeetingManager;
import service.Meeting;

public class Add implements CmdBase{
	public void resInfo(String args[], UserManager usermanager, MeetingManager meetingmanager) throws IOException, ParseException {
		int length = args.length;
		if(length != 7) {
			System.out.println("命令错误");
			return;
		}
		String username = args[1];
		String password = args[2];
		String other = args[3];
		String start = args[4];
		String end = args[5];
		String title = args[6];
		// 判断用户是否已注册
		if(!(usermanager.users.containsKey(username) && usermanager.users.containsKey(other))) {
			System.out.println("用户名错误");
			return;
		}
		// 判断用户名与密码是否匹配
		if(!usermanager.users.get(username).equals(password)) {
			System.out.println("用户名或密码错误");
			return;
		}
		// 判断输入的时间格式是否正确
		try {
			DateFormat sf = new SimpleDateFormat("MM-dd-hh:mm");
			Date startTime = (Date) sf.parse(start);
			Date endTime = (Date) sf.parse(end);
		} catch (Exception e) {
			System.out.println("输入时间格式错误");
			return;
		}
		// 判断会议是否已存在
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
		System.out.println("成功添加会议！");
	}
}