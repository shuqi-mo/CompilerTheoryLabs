package token;

public class FunctionToken extends Token {
	protected String value;
	protected String tag;
	/**
	 * 构造函数
	 * @param func
	 */
	public FunctionToken(String func) {
		type = "Function";
		value = func.toLowerCase();
		tag = "func";
	}
	/**
	 * 获取函数名
	 * @return
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 获取词法单元在算符优先级表中的标记
	 * @return
	 */
	public String getTag() {
		return tag;
	}
}