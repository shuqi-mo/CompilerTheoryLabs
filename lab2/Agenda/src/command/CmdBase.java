package command;

import java.text.*;

import service.UserManager;
import service.MeetingManager;
import utils.MyException;

public interface CmdBase {
	void resInfo(String[] args, UserManager usermanager, MeetingManager meetingmanager) throws ParseException, MyException;
}
