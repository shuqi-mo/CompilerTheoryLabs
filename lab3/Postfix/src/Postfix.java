import java.io.*;

class Parser {
	static int lookahead;
	private StringBuilder res = new StringBuilder();

	public Parser() throws IOException {
		do {
			lookahead = System.in.read();
		} while (!lexicalError());
	}

	void expr() throws IOException {
		term();
		rest();
		System.out.println(res);
	}

	void rest() throws IOException {
		while(true) {
			if (Character.isDigit((char)lookahead)) {
				System.out.println("An operator lacks before "+(char)lookahead);
				term();
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

	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			res.append((char)lookahead);
			match(lookahead);
		}
		else  lackLeftTerm();
	}
	
	void lackLeftTerm() throws IOException {
		if (lookahead == '+' || lookahead == '-') {
			System.out.println((char)lookahead+" lacks a left term!");
		}
		else  System.out.println("The final operator lacks a right term!");
	}

	void match(int t) throws IOException {
		if (lookahead == t) {
			do {
				lookahead = System.in.read();
			} while (!lexicalError());
		}
		else  throw new Error("syntax error");
	}
	
	boolean lexicalError() {
		if (Character.isDigit((char)lookahead)) return true;
		else if (lookahead == '+' || lookahead == '-' || lookahead == 13 || lookahead == 10) return true;
		System.out.println((char)lookahead+" is an illegal input!");
		return false;
	}
}

public class Postfix {
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		new Parser().expr();
		System.out.println("End of program.");
	}
}
