package service;

import java.util.*;

/**
 * 会议类
 */
public class Meeting {
	private String start;
	private String end;
	private String title;
	public Set members = new HashSet();
	
	/**
	 * 构造函数
	 * @param start
	 * @param end
	 * @param title
	 */
	public Meeting(String start, String end, String title) {
		this.start = start;
		this.end = end;
		this.title = title;
	}
	
	/**
	 * 获取开始时间
	 * @return
	 */
	public String getStart() {
		return start;
	}
	
	/**
	 * 获取结束时间
	 * @return
	 */
	public String getEnd() {
		return end;
	}
	
	/**
	 * 获取会议标签
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 打印会议信息
	 */
	public void printinfo() {
		System.out.println("Meeting "+title+" is from: "+start+" to: "+end);
	}
}