package utils;

/**
 * 用户名与密码不匹配的异常处理
 */
public class UserPasswordException extends MyException {
	/**
	 * 异常处理
	 */
	public UserPasswordException() {
		System.out.println("用户名或密码错误");
	}
}
