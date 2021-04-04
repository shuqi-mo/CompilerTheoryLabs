package command;

import service.MeetingManager;
import service.UserManager;

public class CmdFactory {
	
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
            case "quit":
            	cmd  = new Quit();
            	break;
        }
        return cmd;
    }
}
