package utils;

import java.text.*;
import java.util.Date;

import service.*;

public class MyException extends Exception {
	public void isregisterexception(String username, UserManager usermanager) throws IsRegisterException {
		if(!(usermanager.users.containsKey(username))) {
			throw new IsRegisterException();
		}
	}
	
	public void userpasswordexception(String username, String password, UserManager usermanager) throws UserPasswordException {
		if(!usermanager.users.get(username).equals(password)) {
			throw new UserPasswordException();
		}
	}
	
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
