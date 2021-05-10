package parser;

import java.util.*;
import exceptions.*;
import scanner.*;
import scanner.Scanner;

public class Parser {
	private final String[] TAG = {"(", ")", "func", "-", "^", "md", "pm", "cmp", "!", "&", "|", "?", ":", ",", "$"};
	private Stack<Token> operators = new Stack<Token>();	
	private Stack<Token> operands = new Stack<Token>();
	private Token curToken = new Token();
	private Token topToken = new Token();
	private static final Double ERROR = 0.00000001;
	/**
	 * 构造函数：初始化两个堆栈，将Dollar符号压入操作符堆栈中
	 */
	public Parser() {
		operators.clear();
		operands.clear();
		Dollar dollar = new Dollar();
		operators.push(dollar);				
	}
	/**
	 * 获取相应词法单元的tag
	 * @param temp  词法单元
	 * @return 该词法单元在OPP符号表中所对应的标记
	 */
	private String getTag(Token temp) {
		String tempType = temp.getType();
		if (tempType.equals("Function")) {
			return ((FunctionToken)temp).getTag();
		}
		if (tempType.equals("Operator")) {
			return ((OperatorToken)temp).getTag();
		}
		return ((Dollar)temp).getTag();
	}
	/**
	 * 获取词法单元tag在TAG中对应的下标
	 * @param label  符号表中的词法单元标记
	 * @return 词法单元在符号表中对应的下标
	 */
	private int getIndex(String tag) {
		return Arrays.asList(TAG).indexOf(tag);
	}
	/**
	 * 预定义函数：sin,cos,max,min
	 * @param cnt  逗号的数量，用来确定max和min函数所用的操作数个数
	 * @param func  函数的名字
	 * @throws SyntacticException
	 */
	private void doFunction(int cnt, String func) throws SyntacticException {
		if (operands.size() == 0) {
			throw new MissingOperandException();
		}
		Token tempOperand = operands.pop();
		if (tempOperand.getType().intern() != "Decimal") {
			throw new FunctionCallException();
		}
		double ansValue = ((DecimalToken)tempOperand).getValue();
		if (cnt > 0) {
			if (operands.size() < cnt)
				throw new MissingOperandException();
			if (func.equals("sin") || func.equals("cos"))
				throw new FunctionCallException();			
			double topValue = 0.0;
			for (int i = 0; i < cnt; i++) {
				tempOperand = operands.pop();
				topValue = ((DecimalToken)tempOperand).getValue();
				if (func.equals("max") && topValue > ansValue) {
					ansValue = topValue; 
				}
				if (func.equals("min") && topValue < ansValue) {
					ansValue = topValue;
				}
			}
		}
		else {
			Double radians = ((DecimalToken)tempOperand).getValue();
			if (func.equals("sin")) {
				ansValue = Math.sin(radians);
			}
			else if (func.equals("cos")) {
				ansValue = Math.cos(radians);
			}
			else {
				throw new MissingOperandException();
			}
		}
		operands.push(new DecimalToken(Double.toString(ansValue)));
	}
	/**
	 * 将词法单元压入operators堆栈中
	 * @param oper  符号词法单元
	 */
	private void shift(Token oper) {
		operators.push(oper);
	}
	/**
	 * 单目运算的归约:!,-，取一个操作数
	 * @throws SyntacticException
	 */
	private void reduceUnary() throws SyntacticException{
		if (operands.empty())
			throw new MissingOperandException();
		OperatorToken tempOperator = (OperatorToken)operators.pop();
		Token tempOperand = operands.pop();
		if (tempOperator.getTag().equals("-")) {
			Double tempValue = ((DecimalToken)tempOperand).getValue();
			tempValue = 0 - tempValue;
			((DecimalToken)tempOperand).setValue(tempValue);
		}
		if (tempOperator.getTag().equals("!")) {
			Boolean tempValue = ((BooleanToken)tempOperand).getValue();
			tempValue = !tempValue;
			((BooleanToken)tempOperand).setValue(tempValue);			
		}
		operands.push(tempOperand);
	}
	/**
	 * 双目运算归约:加，减，乘，除，取幂，关系运算，取与，取或这些二元算符归约，取两个操作数
	 * @throws TypeMismatchedException 
	 * @throws DividedByZeroException 
	 * @throws SyntacticException
	 */
	private void reduceBinary() throws TypeMismatchedException, SyntacticException, DividedByZeroException {
		if (operands.size() < 2)
			throw new MissingOperandException();
		OperatorToken tempOperator = (OperatorToken)operators.pop();
		Token operandB = operands.pop();
		Token operandA = operands.pop();
		if (tempOperator.getTag().equals("pm") || tempOperator.getTag().equals("md") || tempOperator.getTag().equals("^")) {
			if (operandA.getType().equals("Decimal") && operandB.getType().equals("Decimal")) {
				Double valueA = ((DecimalToken)operandA).getValue();
				Double valueB = ((DecimalToken)operandB).getValue();
				Double valueC = 0.0;
				switch (tempOperator.getValue().charAt(0)) {
				case '+':
					valueC = valueA + valueB;
					break;
				case '-':
					valueC = valueA - valueB; 
					break;
				case '*':
					valueC = valueA * valueB; 
					break;			
				case '/':
					if (Math.abs(valueB - 0.0) < ERROR)
						throw new DividedByZeroException();
					valueC = valueA / valueB; 
					break;
				case '^':
					valueC = Math.pow(valueA, valueB); 
					break;
				default:
					break;
				}
				operands.push(new DecimalToken(Double.toString(valueC)));
			}
			else {
				throw new TypeMismatchedException();
			}
		}
		if (tempOperator.getTag().equals("&") || tempOperator.getTag().equals("|")) {
			if (operandA.getType().equals("Boolean") && operandB.getType().equals("Boolean")) {
				Boolean boolA = ((BooleanToken)operandA).getValue();
				Boolean boolB = ((BooleanToken)operandB).getValue();
				Boolean boolC = false;
				switch (tempOperator.getValue().charAt(0)) {
				case '&':
					boolC = boolA & boolB; 
					break;
				case '|':	
					boolC = boolA | boolB; 
					break;
				default:
					break;
				}
				operands.push(new BooleanToken(Boolean.toString(boolC)));
			}
			else {
				throw new TypeMismatchedException();
			}
		}
		if (tempOperator.getTag().equals("cmp")) {
			if (operandA.getType().equals("Decimal") && operandB.getType().equals("Decimal")) {
				Double valueA = ((DecimalToken)operandA).getValue();
				Double valueB = ((DecimalToken)operandB).getValue();
				Boolean boolC = false;
				String operLexeme = tempOperator.getValue();
				if ( (operLexeme.equals(">") && valueA > valueB) || 
					 (operLexeme.equals("<") && valueA < valueB) || 
					 (operLexeme.equals("=") && (Math.abs(valueA - valueB) < ERROR) ) || 
					 (operLexeme.equals(">=")&& ( (valueA > valueB) || (Math.abs(valueA - valueB) < ERROR)) ) ||
					 (operLexeme.equals("<=")&& ( (valueA < valueB) || (Math.abs(valueA - valueB) < ERROR)) ) || 
					 (operLexeme.equals("<>")&& (Math.abs(valueA - valueB) >= ERROR)) ) {
					boolC = true;
				}
				operands.push(new BooleanToken(Boolean.toString(boolC)));
			}
			else {
				throw new TypeMismatchedException();
			}
		}		
	}
	/**
	 * 三目运算归约：执行选择运算(?:)三元运算的归约，取3个操作数
	 * @throws TrinaryOperationException
	 * @throws TypeMismatchedException
	 * @throws SyntacticException
	 */
	private void reduceTrinary() throws TrinaryOperationException, TypeMismatchedException, SyntacticException {
		if (operands.size() < 3 || operators.size() < 3) {
			throw new MissingOperandException();
		}
		OperatorToken operatorB = (OperatorToken)operators.pop();
		OperatorToken operatorA = (OperatorToken)operators.pop();
		Token operandC = operands.pop();
		Token operandB = operands.pop();
		Token operandA = operands.pop();
		if (operatorA.getValue().equals("?") && operatorB.getValue().equals(":")) {
			if (operandA.getType().equals("Boolean") && operandB.getType().equals("Decimal") 
					&& operandC.getType().equals("Decimal")) {
				Double valueD = 0.0;
				if ( ((BooleanToken)operandA).getValue() ) {
					valueD = ((DecimalToken)operandB).getValue(); 
				}
				else {
					valueD = ((DecimalToken)operandC).getValue();
				}
				operands.push(new DecimalToken(Double.toString(valueD)));
			}
			else {
				throw new TypeMismatchedException();
			}
		}
		else {
			throw new TrinaryOperationException();
		}
	}
	/**
	 * 括号运算以及函数运算归约。当读到右括号时，执行此归约。最后判断左括号的左边是否有函数符号，有则通过调用doFunction函数来继续执行函数运算归约。
	 * @throws TypeMismatchedException
	 * @throws SyntacticException
	 * @throws DividedByZeroException 
	 * @see Parser#doFunction(int, String)
	 */
	private void matchReduce() throws TypeMismatchedException, SyntacticException, DividedByZeroException {	
		Token tempOperator = operators.peek();
		int cntComma = 0;
		Boolean matchCompleted = false;
		while (!matchCompleted) {
			if (tempOperator.getType().equals("Dollar")) {
				throw new MissingLeftParenthesisException();
			}
			else {
				if (((OperatorToken)tempOperator).getValue().equals("(")) {
					operators.pop();
					matchCompleted = true;
					break;
				}
				int tempNum = ((OperatorToken)tempOperator).getNum();
				if (cntComma == 0 && tempNum == 1) {
					reduceUnary();
				}
				else if (cntComma == 0 && tempNum == 2) {
					reduceBinary();
				}
				else if (cntComma == 0 && tempNum == 3) {
					reduceTrinary();
				}
				else if (((OperatorToken)tempOperator).getValue().equals(",")) { //该操作符是,
					operators.pop();
					cntComma++;
				}
				else {
					throw new SyntacticException();
				}			
			}
			tempOperator = operators.peek();
		}
		tempOperator = operators.peek();
		if (tempOperator.getType().equals("Function")) {
			doFunction(cntComma, ((FunctionToken)tempOperator).getValue());
			operators.pop();
		}		
		
	}
	/**
	 * 执行语法分析和语义动作。
	 * @param expression  表达式字符串
	 * @return 表达式运算结果
	 * @throws ExpressionException
	 */
	public Double parsing(String expression) throws ExpressionException {
		Scanner scanner = new Scanner(expression);
		curToken = scanner.getNextToken();
		Boolean completed = false;
		int action = 0;
		int lableStackIndex;
		int lableReadIndex;
				
		while(!completed) {
			topToken = operators.peek();
			if (curToken.getType().equals("Boolean") || curToken.getType().equals("Decimal")) {
				operands.push(curToken);
				curToken = scanner.getNextToken();
				continue;
			}
			else {
				lableReadIndex = getIndex(getTag(curToken));
				lableStackIndex = getIndex(getTag(topToken));
				action = OPP.table[lableStackIndex][lableReadIndex];
				switch (action) {
				case OPP.ACCEPT:
					completed = true;
					break;
				case OPP.SHIFT:
					shift(curToken);
					curToken = scanner.getNextToken();
					break;
				case OPP.RDUNAOPER:
					reduceUnary();
					break;
				case OPP.RDBINAOPER:
					reduceBinary();
					break;
				case OPP.RDTRINAOPER:
					reduceTrinary();
					break;
				case OPP.RDMATCH:
					matchReduce();
					curToken = scanner.getNextToken();
					break;
				case OPP.ERRLEFTPAR:
					throw new MissingLeftParenthesisException();
				case OPP.ERRSYN:
					throw new SyntacticException();
				case OPP.ERROPERAND:
					throw new MissingOperandException();
				case OPP.ERRTYPE:
					throw new TypeMismatchedException();
				case OPP.ERRFUNCSYN:
					throw new FunctionCallException();
				case OPP.ERRRIGHTPAR:
					throw new MissingRightParenthesisException();
				case OPP.ERRTRINA:
					throw new TrinaryOperationException();
				default:
					break;
				}
			}
		}
		if (completed) {
			if (operands.size() == 1) {
				if (operands.peek().getType().equals("Decimal"))
					return ((DecimalToken)operands.peek()).getValue();
				else
					throw new TypeMismatchedException();
			}
			else {
				throw new MissingOperatorException();
			}
		}
		else {
			throw new SyntacticException();
		}		
	}
}