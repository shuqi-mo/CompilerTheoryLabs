import java.io.*;

class Parser {
	static int lookahead;
	private int operatorcnt;
	private int digitcnt;
	private int len;
	private boolean error;
	private StringBuffer result = new StringBuffer();

	public Parser() throws IOException {
		this.operatorcnt = 0;
		this.digitcnt = 0;
		this.len = 0;
		do {
			len++;
			lookahead = System.in.read();
		} while(!legalInput());
		if(Character.isDigit((char)lookahead)) digitcnt++;
		else if(lookahead == '+' || lookahead == '-') operatorcnt++;
	}

	void expr() throws IOException {
		term();
		rest();
		System.out.println("后缀表达式输出结果："+result);
	}

	void rest() throws IOException {
		while(true) {
			if (lookahead == '+') {
				match('+');
				term();
				if(!error)
					result.append('+');
				continue;
			} else if (lookahead == '-') {
				match('-');
				term();
				if(!error)
					result.append('-');
				continue;
			} else {
				break;
			}
		}
	}

	void term() throws IOException {
		error = false;
		if(operatorcnt > digitcnt) {
			System.out.println("语法错误：输入字符串中的第"+len+"个字符缺少左运算量，输出结果将跳过该字符");
			operatorcnt--;
			match(lookahead);
		}
		else if(operatorcnt == digitcnt) {
			System.out.println("语法错误：输入字符串中的第"+(len-1)+"个字符缺少右运算量，输出结果将跳过该字符");
			error = true;
			match(lookahead);
		}
		if(Character.isDigit((char)lookahead)) {
			result.append((char)lookahead);
			match(lookahead);
		}
	}

	void match(int t) throws IOException {
		if (lookahead == t) {
			do {
				len++;
				lookahead = System.in.read();
			} while(!legalInput());
			if(Character.isDigit((char)lookahead)) digitcnt++;
			else if(lookahead == '+' || lookahead == '-') operatorcnt++;
			if(digitcnt - 2 >= operatorcnt) {
				System.out.println("语法错误：输入字符串中的第"+(len-1)+"个字符与第"+len+"个字符之间缺少运算符，输出结果将跳过后一字符");
				digitcnt--;
				match(lookahead);
			}
		}
		else
			throw new Error("syntax error");
	}
	
	boolean legalInput() {
		if(Character.isDigit((char)lookahead))
			return true;
		else if(lookahead == '+' || lookahead == '-' || lookahead == 13 || lookahead == 10)
			return true;
		else {
			System.out.println("词法错误：输入字符串中的第"+len+"个字符不是合法字符，输出结果将跳过该字符");
			return false;
		}
	}
}

public class Postfix {
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		new Parser().expr();
		System.out.println("End of program.");
	}
}
