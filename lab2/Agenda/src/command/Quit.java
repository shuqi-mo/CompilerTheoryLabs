package command;

import java.io.*;
import service.UserManager;
import service.MeetingManager;

public class Quit implements CmdBase{
	public void resInfo(String args[], UserManager usermanager, MeetingManager meetingmanager) {
		System.exit(0);
	}
}