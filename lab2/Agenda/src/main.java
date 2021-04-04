import java.io.*;

import command.CmdBase;
import command.CmdFactory;
import service.UserManager;
import service.MeetingManager;

public class main {
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
	
	public static void handle(String[] args, UserManager usermanager, MeetingManager meetingmanager) {
		try {
			CmdBase cmd;
			cmd = CmdFactory.builder(args[0].toLowerCase());
			cmd.resInfo(args, usermanager, meetingmanager);
		} catch (Exception e) {
			System.out.print("命令错误\n");
		}
	}
}
