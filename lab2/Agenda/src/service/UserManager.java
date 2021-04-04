package service;

import java.util.*;

public class UserManager {
	public Map users = new HashMap();
	
	public void register(String name, String password) {
		users.put(name, password);
	}
}
