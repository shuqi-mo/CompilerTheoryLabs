package command;

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
            case "quit":
            	cmd  = new Quit();
            	break;
        }
        return cmd;
    }
}
