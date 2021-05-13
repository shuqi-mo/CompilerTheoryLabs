package token;

public class OperatorToken extends Token {
	protected String value;
	protected String tag;
	/**
	 * 构造函数：初始化操作符token
	 * @param Oper
	 */
	public OperatorToken(String Oper) {
		type = "Operator";
		value = Oper;
		if(Oper.equals("+") || Oper.equals("-") ) {
			tag = "pm";
		}
		else if(Oper.equals("*") || Oper.equals("/")) {
			tag = "md";
		}
		else if(Oper.equals("minus")) {
			tag = "-";
		}
		else if (Oper.equals("=") || Oper.equals("<") || Oper.equals(">") || Oper.equals("<=") || Oper.equals(">=") || Oper.equals("<>")) {
			tag = "cmp";
		}
		else {
			tag = Oper;
		}
	}
	/**
	 * 获取操作符
	 * @return
	 */
	public String getValue() {
		return value;
	}	
	/**
	 * 获取操作符在符号表中的标记
	 * @return
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * 获取操作符是几目运算符
	 * @return
	 */
	public int getNum() {
		int num = 0;
				
		if (tag.equals("-") || tag.equals("!")) {
			num = 1;
		}
		if (tag.equals("pm") || tag.equals("md") || tag.equals("^") ||
				tag.equals("&") || tag.equals("|") || tag.equals("cmp")) {
			num = 2;
		}
		if (tag.equals("?") || tag.equals(":")) {
			num = 3;
		}
		
		return num;			
	}
}
