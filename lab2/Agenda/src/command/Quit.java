package command;

import java.io.*;
import service.UserManager;
import service.MeetingManager;

/**
 * Quit命令类：退出系统
 * quit
 */
public class Quit implements CmdBase{
	public void resInfo(String args[], UserManager usermanager, MeetingManager meetingmanager) {
		System.exit(0);
	}
}