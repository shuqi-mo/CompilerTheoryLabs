import java.io.IOException;

public class Postfix {
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		Parser parser = new Parser();
		parser.expr();
		System.out.println("End of program.");
	}
}
