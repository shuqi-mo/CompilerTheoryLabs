package command;

import java.io.*;
import java.util.ArrayList;

import service.*;

public class Batch implements CmdBase {
	public void resInfo(String args[], UserManager usermanager, MeetingManager meetingmanager) throws IOException {
		int length = args.length;
		if(length != 2) {
			System.out.println("参数错误");
			return;
		}
		
		String filename = args[1];
		
		ArrayList cmdinfile = new ArrayList();
		try {
			BufferedReader in = new BufferedReader(new FileReader("test/" + filename + ".txt"));
			String find =new String();
			while ((find = in.readLine()) != null){
				cmdinfile.add(find);
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在");
			e.printStackTrace();
		}
		
		String[] temp ;
		for (int i = 0;i < cmdinfile.size(); i++){
			temp = cmdinfile.get(i).toString().split("\\s");
			try {
				CmdBase cmd;
				cmd = CmdFactory.builder(temp[0].toLowerCase());
				cmd.resInfo(temp, usermanager, meetingmanager);
			} catch (Exception e) {
				System.out.print("命令错误\n");
			}
		}
	}
}