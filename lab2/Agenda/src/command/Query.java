package command;

import java.text.*;
import java.util.*;

import service.*;
import utils.*;

/**
 * Query命令类：查询用户的会议安排
 * query [username][password][start][end]
 */
public class Query implements CmdBase{
	public void resInfo(String args[], UserManager usermanager, MeetingManager meetingmanager) throws ParseException, MyException {
		int length = args.length;
		if(length != 5) {
			System.out.println("参数错误");
			return;
		}
		
		String username = args[1];
		String password = args[2];
		String start = args[3];
		String end = args[4];
		
		MyException e = new MyException();
		try {
			e.isregisterexception(username, usermanager);
			e.userpasswordexception(username, password, usermanager);
			e.timeformatexception(start, end);
		} catch(Exception e1) {
			return;
		}
		
		System.out.println("正在查询"+username+"的会议安排……");
		
		// 打印输入时间内的会议信息
		Set findMeeting = meetingmanager.findMeetingByUsername(username);
		Set legalMeeting = new HashSet();
		DateFormat sf = new SimpleDateFormat("MM-dd-hh:mm");
		Date startTime = (Date) sf.parse(start);
		Date endTime = (Date) sf.parse(end);
		if (!findMeeting.isEmpty()) {
			Iterator find = findMeeting.iterator();
			while (find.hasNext()) {
				Meeting temp = (Meeting) find.next();
				Date tempStart = (Date) sf.parse(temp.getStart());
				Date tempEnd = (Date) sf.parse(temp.getEnd());
				if ((tempStart.after(startTime)) && (tempEnd.before(endTime))) 
					legalMeeting.add(temp);
			}
			Iterator iterator = legalMeeting.iterator();
			while(iterator.hasNext()) {
				Meeting temp = (Meeting) iterator.next();
				temp.printinfo();
			}
		}
	}
}
