import java.io.*;

class Parser {
	static int lookahead;
	private int operatorcnt;
	private int digitcnt;
	private int len;
	private boolean error;

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
	}

	void rest() throws IOException {
		while(true) {
			if (lookahead == '+') {
				match('+');
				term();
				if(!error)
					System.out.write('+');
				continue;
			} else if (lookahead == '-') {
				match('-');
				term();
				if(!error)
					System.out.write('-');
				continue;
			} else {
				break;
			}
		}
	}

	void term() throws IOException {
		error = false;
		if(operatorcnt > digitcnt) {
			System.out.println("\nlackLeftItem");
			operatorcnt--;
			match(lookahead);
		}
		else if(operatorcnt == digitcnt) {
			System.out.println("\nlackRightItem");
			error = true;
			match(lookahead);
		}
		if(Character.isDigit((char)lookahead)) {
			System.out.write((char)lookahead);
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
				System.out.println("\nlackOperator");
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
			System.out.println("illegal input!");
			return false;
		}
	}
}

public class Postfix {
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		new Parser().expr();
		System.out.println("\nEnd of program.");
	}
}
