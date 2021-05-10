package scanner;

public class DecimalToken extends Token {
	protected Double value;
	/**
	 * 构造函数：初始化数字token
	 * @param num
	 */
	public DecimalToken(String num) {
		type = "Decimal";
		String lowerNum = num.toLowerCase();
		int hasE = lowerNum.indexOf('e');
		if (hasE != -1) {
			double fraction = Double.parseDouble(lowerNum.substring(0, hasE));
			double exponent;
			switch (lowerNum.charAt(hasE+1)) {
			case '-':
				exponent = Double.parseDouble(lowerNum.substring(hasE+2, lowerNum.length()));
				value = fraction / Math.pow(10.0, exponent);
				break;
			case '+':
				exponent = Double.parseDouble(lowerNum.substring(hasE+2, lowerNum.length()));
				value = fraction * Math.pow(10.0, exponent);
				break;
			default:
				exponent = Double.parseDouble(lowerNum.substring(hasE+1, lowerNum.length()));
				value = fraction * Math.pow(10.0, exponent);
				break;
			}
		}
		else {
			value = Double.parseDouble(lowerNum);
		}
	}	
	/**
	 * 获取数值
	 * @return
	 */
	public Double getValue() {
		return value;
	}
	/**
	 * 设置数值
	 * @param newValue
	 */
	public void setValue(Double newValue) {
		value = newValue;
	}
}