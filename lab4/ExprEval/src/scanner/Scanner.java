package scanner;

import exceptions.*;
import token.Token;
import token.BooleanToken;
import token.OperatorToken;
import token.DecimalToken;
import token.FunctionToken;
import token.DollarToken;

public class Scanner {
	protected int index;
	protected String input;
	protected int len;
	protected final String opers = "+-*/^?:><=&|!(),";
	/**
	 * 构造函数：输入字符串的初始化处理
	 * @param expression  表达式字符串
	 */
	public Scanner(String expression) {
		input = expression.toLowerCase().replace(" ", "");
		index = 0;
		len = input.length();
	}	
	/**
	 * 生成数字token的状态自动机
	 * @return
	 * @throws LexicalException
	 * @throws ExpressionException
	 */
	public Token decimalDFA() throws LexicalException, ExpressionException {
		Boolean dotFlag = false;
		Boolean eFlag = false;
		int start = index;
		for (int i = start + 1; i < len; i++) {
			char peek = input.charAt(i);
			if (Character.isDigit(peek)) {
				index++;
				continue;
			}
			else if (peek == '.') {
				if (eFlag || dotFlag) 
					throw new IllegalDecimalException();
				else if (i + 1 >= len) 
					throw new IllegalDecimalException();
				else if (!Character.isDigit(input.charAt(i+1)))
					throw new IllegalDecimalException();
				else {
					index++;
					dotFlag = true;
				}					
			}
			else if (peek == 'e') {
				if (eFlag)
					throw new IllegalDecimalException();
				else if (i + 1 >= len) 
					throw new IllegalDecimalException();
				else if (!(Character.isDigit(input.charAt(i+1)) || 
						input.charAt(i+1) == '+' || input.charAt(i+1) == '-' ))
					throw new IllegalDecimalException();
				else {
					index++;
					eFlag = true;
				}
			}
			else if (peek == '-' || peek == '+') {
				if (!(input.charAt(i-1) == 'e') ) {
					break;
				}
				else if (i+1 == len)
					throw new IllegalDecimalException();
				else 
					index++;
			}
			else {
				break;
			}
		}
		index++; 
		return new DecimalToken(input.substring(start, index));
	}
	/**
	 * 生成布尔型token的状态自动机
	 * @param curChar
	 * @return
	 * @throws LexicalException
	 * @throws ExpressionException
	 */
	public Token booleanDFA(Character curChar) throws LexicalException, ExpressionException {
		String boolStr = "";
		if (curChar == 't') {
			boolStr = input.substring(index, index+4);
			if (boolStr.equals("true")) {
				index += 4;
				return new BooleanToken(boolStr);
			}
			else {
				throw new IllegalIdentifierException();
			}
		}
		else {
			boolStr = input.substring(index, index+5);
			if (boolStr.equals("false")) {
				index += 5;
				return new BooleanToken(boolStr);
			}
			else
				throw new IllegalIdentifierException();
		}
	}
	/**
	 * 生成预定义函数token的状态自动机
	 * @return
	 * @throws LexicalException
	 * @throws ExpressionException
	 */
	public Token functionDFA() throws LexicalException, ExpressionException {
		String funcLow = input.substring(index, index+3);
		if (funcLow.equals("sin") || funcLow.equals("cos") || funcLow.equals("max") || funcLow.equals("min")) {
			index += 3;
			return new FunctionToken(funcLow);
		}
		else {
			throw new IllegalIdentifierException();
		}
	}
	/**
	 * 生成操作符token的状态自动机
	 * @param curChar
	 * @return
	 * @throws LexicalException
	 * @throws ExpressionException
	 */
	public Token operDFA(Character curChar) throws LexicalException, ExpressionException {
		if (curChar == '>') {
			if (index < len - 1) {
				if (input.charAt(index+1) == '=') {
					index += 2;
					return new OperatorToken(">=");
				}
			}
		}
		if (curChar == '<') {
			if (index < len -1) {
				if (input.charAt(index+1) == '=') {
					index += 2;
					return new OperatorToken("<=");
				}
				if (input.charAt(index+1) == '>') {
					index += 2;
					return new OperatorToken("<>");
				}
			}
		}
		if (curChar == '-') {
			if (index - 1 >= 0) {
				//只有当-前为）或者数字时，它才代表减号，否则就是负号
				if (input.charAt(index - 1 ) == ')' || Character.isDigit(input.charAt(index -1))) {
					index += 1;
					return new OperatorToken("-");
				}
			}
			index += 1;
			return new OperatorToken("neg");
		}
		// 由于操作符和操作数不在同一个栈中，有些语法错误只能在词法分析中抛出
		if (curChar == ')') {
			if (index - 1 >= 0) {
				if (input.charAt(index - 1) == '(')
					throw new MissingOperandException();
			}
		}
		if (curChar == ':') {
			if (index - 1 >= 0) {
				if (input.charAt(index - 1) == '?')
					throw new MissingOperandException();
			}
		}
		index += 1;
		return new OperatorToken(curChar.toString());
	}
	/**
	 * 获取下一个词法单元
	 * @return 读取到的词法单元
	 * @throws LexicalException
	 * @throws ExpressionException
	 */
	public Token getNextToken() throws LexicalException, ExpressionException { 
		if (len == 0)	
			throw new EmptyExpressionException();
		if (index >= len)	
			return new DollarToken();
		
		Character curChar = input.charAt(index);
		if (Character.isDigit(curChar))
			return decimalDFA();
		else if (curChar == 't' || curChar == 'f')
			return booleanDFA(curChar);
		else if (curChar == 's' || curChar == 'c' || curChar == 'm')
			return functionDFA();
		else if (opers.indexOf(curChar) != -1)
			return operDFA(curChar);
		else {
			if (curChar == '.') {
				if (index + 1 < input.length()) {
					if (Character.isDigit(input.charAt(index+1)))
						throw new IllegalDecimalException();
					else 
						throw new LexicalException();
				}
				else 
					throw new LexicalException();
			}
			else if (Character.isAlphabetic(curChar)) {
				throw new IllegalIdentifierException();
			}
			else 
				throw new IllegalSymbolException();
		}
	}
}