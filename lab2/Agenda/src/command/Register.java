package command;

import java.io.IOException;
import service.UserManager;
import service.MeetingManager;

public class Register implements CmdBase{
	public void resInfo(String[] args, UserManager usermanager, MeetingManager meetingmanager) throws IOException {
		int length = args.length;
		if(length != 3) {
			System.out.println("命令错误");
			return;
		}
		String name = args[1];
		String password = args[2];
		// 判断用户是否已注册
		if(!usermanager.users.containsKey(name)) {
			usermanager.register(name, password);
			System.out.println("注册成功！");
		}
		else
			System.out.println("用户已存在");
	}
}
