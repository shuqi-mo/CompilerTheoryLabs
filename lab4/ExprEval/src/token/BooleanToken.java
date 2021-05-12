package token;

public class BooleanToken extends Token {
	protected Boolean value;
	/**
	 * 构造函数：初始化布尔对象
	 * @param temp
	 */
	public BooleanToken(String temp) {
		type = "Boolean";
		temp = temp.toLowerCase();
		if (temp.equals("true") )
			value = true;
		else {
			value = false;
		}
	}
	/**
	 * 返回布尔对象对应的布尔值
	 * @return
	 */
	public Boolean getValue() {
		return value;
	}
	/**
	 * 设置对象的布尔值
	 * @param newValue
	 */
	public void setValue(Boolean newValue) {
		value = newValue;
	}
}