package utils;

import java.text.*;
import java.util.Date;

import service.*;

/**
 * 自定义异常类
 */
public class MyException extends Exception {
	/**
	 * 判断用户是否已注册
	 * @param username
	 * @param usermanager
	 * @throws IsRegisterException
	 */
	public void isregisterexception(String username, UserManager usermanager) throws IsRegisterException {
		if(!(usermanager.users.containsKey(username))) {
			throw new IsRegisterException();
		}
	}
	
	/**
	 * 判断用户名与密码是否匹配
	 * @param username
	 * @param password
	 * @param usermanager
	 * @throws UserPasswordException
	 */
	public void userpasswordexception(String username, String password, UserManager usermanager) throws UserPasswordException {
		if(!usermanager.users.get(username).equals(password)) {
			throw new UserPasswordException();
		}
	}
	
	/**
	 * 判断时间格式是否正确
	 * @param start
	 * @param end
	 * @throws TimeFormatException
	 */
	public void timeformatexception(String start, String end) throws TimeFormatException {
		try {
			DateFormat sf = new SimpleDateFormat("MM-dd-hh:mm");
			Date startTime = (Date) sf.parse(start);
			Date endTime = (Date) sf.parse(end);
		} catch(Exception e) {
			throw new TimeFormatException();
		}
	}
}
