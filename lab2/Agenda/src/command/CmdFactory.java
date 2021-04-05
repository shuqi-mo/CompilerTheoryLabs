package command;

import service.MeetingManager;
import service.UserManager;

/**
 * 工厂模式下的命令工厂类
 */
public class CmdFactory {
	/**
	 * 针对不同命令建议不同的命令类
	 * @param cmdPre 参数数组的第一个元素
	 * @return
	 */
	public static CmdBase builder(String cmdPre) {
        CmdBase cmd = null;
        switch (cmdPre) {
            case "register":
                cmd = new Register();
                break;
            case "add":
            	cmd = new Add();
            	break;
            case "query":
            	cmd = new Query();
            	break;
            case "delete":
            	cmd = new Delete();
            	break;
            case "clear":
            	cmd = new Clear();
            	break;
            case "batch":
            	cmd = new Batch();
            	break;
            case "quit":
            	cmd  = new Quit();
            	break;
        }
        return cmd;
    }
}
