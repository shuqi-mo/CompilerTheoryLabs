package command;

import java.io.IOException;
import java.text.ParseException;

import service.UserManager;
import service.MeetingManager;

public interface CmdBase {
	void resInfo(String[] args, UserManager usermanager, MeetingManager meetingmanager) throws IOException, ParseException;
}
