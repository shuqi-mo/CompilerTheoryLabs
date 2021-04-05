package service;

import java.util.*;

/**
 * 用户管理类
 */
public class UserManager {
	public Map users = new HashMap();
	
	/**
	 * 用户注册
	 * @param name
	 * @param password
	 */
	public void register(String name, String password) {
		users.put(name, password);
	}
}
