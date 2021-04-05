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
