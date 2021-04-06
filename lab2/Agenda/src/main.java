import java.io.*;

import command.*;
import service.*;

public class main {
	/**
	 * 主函数
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		boolean test;
		UserManager usermanager = new UserManager();
		MeetingManager meetingmanager = new MeetingManager();
		
		do {
			System.out.print("$ ");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String[] input = in.readLine().split("\\s");
			test = input[0].equals("quit");
			handle(input, usermanager, meetingmanager);
		} while (!test);
	}
	
	/**
	 * 判断命令类型，执行相关命令
	 * @param args
	 * @param usermanager
	 * @param meetingmanager
	 */
	public static void handle(String[] args, UserManager usermanager, MeetingManager meetingmanager) {
		try {
			String cmdpre = args[0].toLowerCase();
			char[] type = cmdpre.toCharArray();
			type[0] -= 32;
			CmdBase cmd = (CmdBase)Class.forName("command."+String.valueOf(type)).newInstance();
			cmd.resInfo(args, usermanager, meetingmanager);
		} catch (Exception e) {
			System.out.println("命令错误\n");
		}
	}
}
