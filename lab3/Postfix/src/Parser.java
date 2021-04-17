import java.io.IOException;

/**
 * 递归下降语法分析器
 */
class Parser {
	/**
	 * 每次处理一个字符
	 */
	static int lookahead;
	/**
	 * 存放结果
	 */
	private StringBuilder res = new StringBuilder();
	
	/**
	 * 构造函数
	 * @throws IOException
	 */
	public Parser() throws IOException {
		do {
			lookahead = System.in.read();
		} while (!lexicalError());
	}
	
	/**
	 * Expr -> Term Rest
	 * @throws IOException
	 */
	void expr() throws IOException {
		term();
		rest();
		System.out.println(res);
	}
	
	/**
	 * Rest -> lackOperator Operators Term Rest | \epsilon
	 * @throws IOException
	 */
	void rest() throws IOException {
		while(true) {
			if (Character.isDigit((char)lookahead)) {
				lackOperator();
			}
			if (lookahead == '+') {
				match('+');
				term();
				res.append('+');
				continue;
			} else if (lookahead == '-') {
				match('-');
				term();
				res.append('-');
				continue;
			} else {
				break;
			}
		}
	}
	
	/**
	 * Term -> Digits | lackTerm
	 * @throws IOException
	 */
	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			res.append((char)lookahead);
			match(lookahead);
		}
		else  lackTerm();
	}
	
	/**
	 * lackTerm -> Operators
	 */
	void lackTerm() {
		if (lookahead == '+' || lookahead == '-') {
			System.out.println((char)lookahead+" lacks a left term!");
		}
		else  System.out.println("The final operator lacks a right term!");
	}
	
	/**
	 * lackOperator -> Term | \epsilon
	 * @throws IOException
	 */
	void lackOperator() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			System.out.println("An operator lacks before "+(char)lookahead);
			term();
		}
	}
	
	/**
	 * match动作
	 * @param t
	 * @throws IOException
	 */
	void match(int t) throws IOException {
		if (lookahead == t) {
			do {
				lookahead = System.in.read();
			} while (!lexicalError());
		}
		else  throw new Error("syntax error");
	}
	
	/**
	 * 判断输入是否存在词法错误
	 * Digits -> [0-9]
	 * Operator -> + | - | "\n"
	 * @return
	 */
	boolean lexicalError() {
		if (Character.isDigit((char)lookahead)) return true;
		else if (lookahead == '+' || lookahead == '-' || lookahead == 13 || lookahead == 10) return true;
		System.out.println((char)lookahead+" is an illegal input!");
		return false;
	}
}