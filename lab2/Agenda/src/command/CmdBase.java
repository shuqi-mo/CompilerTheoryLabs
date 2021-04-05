package command;

import java.text.*;
import java.io.*;

import service.UserManager;
import service.MeetingManager;
import utils.MyException;

/**
 * 策略模式下的命令接口
 */
public interface CmdBase {
	/**
	 * 命令方法
	 * @param args 命令参数
	 * @param usermanager
	 * @param meetingmanager
	 * @throws IOException
	 * @throws ParseException
	 * @throws MyException 自定义异常
	 */
	void resInfo(String[] args, UserManager usermanager, MeetingManager meetingmanager) throws IOException, ParseException, MyException;
}
