package utils;

/**
 * 用户未注册异常
 */
public class IsRegisterException extends MyException {
	/**
	 * 用户未注册的异常处理
	 */
	public IsRegisterException() {
		System.out.println("用户未注册");
	}
}
