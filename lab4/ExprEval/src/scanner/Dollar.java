package scanner;

public class Dollar extends Token {
	protected String tag;
	/**
	 * 构造函数：设置终止符Token的类型与标记
	 */
	public Dollar() {
		type = "Dollar";
		tag = "$";
	}
	/**
	 * 获取终结符在OPP表中的标记。
	 * @return 终结符在OPP表中的标记
	 */
	public String getTag() {
		return tag;
	}
	
}
