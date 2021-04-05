package command;

import java.io.IOException;
import service.UserManager;
import utils.MyException;
import service.MeetingManager;

/**
 * Register命令类：用户注册
 * register [username][password]
 */
public class Register implements CmdBase{
	public void resInfo(String[] args, UserManager usermanager, MeetingManager meetingmanager) {
		int length = args.length;
		if(length != 3) {
			System.out.println("参数错误");
			return;
		}
		
		String username = args[1];
		String password = args[2];
		
		// 判断用户是否已注册
		if(!usermanager.users.containsKey(username)) {
			usermanager.register(username, password);
			System.out.println("用户"+username+"注册成功！");
		}
		else
			System.out.println("用户已存在");
	}
}
