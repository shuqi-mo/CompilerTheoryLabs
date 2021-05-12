package parser;

public class OPP {
	public static final int MISSINGLEFTPARENTHESIS = -7;	// 缺少左括号
	public static final int SYNTACTICEXCEPTION = -6;	// 语法错误
	public static final int MISSINGOPERAND = -5;	// 缺少操作数
	public static final int TYPEMISMATCH = -4;	// 类型错误
	public static final int FUNCTIONCALL = -3;	// 函数语法错误
	public static final int MISSINGRIGHTPARENTHESIS = -2;	// 缺少右括号
	public static final int TRINARYOPERATION = -1;	// 三元运算符异常
	public static final int SHIFT = 0;	// 移入
	public static final int RDUNAOPER = 1;	// 单目运算
	public static final int RDBINAOPER = 2;	// 双目运算
	public static final int RDTRINAOPER = 3;	// 三目运算
	public static final int RDPARENTHESIS = 4;	// 括号运算
	public static final int ACCEPT = 5;	// 接受
	
	public static final int table[][] = {
	/*栈顶*/ /*(  ) func - ^  md pm cmp ! &  |  ?  :  ,  $    读入字符*/
	/*(*/    {0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0,-2},
	/*)*/    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
	/*func*/ {0,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3},
	/*-*/    {0, 4, 0, 0, 1, 1, 1, 1,-6,-4,-4, 1, 1, 1, 1},
	/*^*/    {0, 4, 0, 0, 0, 2, 2, 2,-6,-4,-4, 2, 2, 2, 2},
	/*md*/   {0, 4, 0, 0, 0, 2, 2, 2,-6,-4,-4, 2, 2, 2, 2},
	/*pm*/   {0, 4, 0, 0, 0, 0, 2, 2,-6,-4,-4, 2, 2, 2, 2},
	/*cmp*/  {0, 4, 0, 0, 0, 0, 0,-4,-6, 2, 2, 2,-1,-3, 2},
	/*!*/    {0, 4,-4,-4,-4,-4,-4, 0, 0, 1, 1, 1,-1,-3, 1},
	/*&*/    {0, 4,-4,-4,-4,-4,-4, 0, 0, 2, 2, 2,-1,-3, 2},
	/*|*/    {0, 4,-4,-4,-4,-4,-4, 0, 0, 0, 2, 2,-1,-3, 2},
	/*?*/    {0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1},
	/*:*/    {0, 4, 0, 0, 0, 0, 0,-1,-1,-1,-1, 0,-1,-1, 3},
	/*,*/    {0, 4, 0, 0, 0, 0, 0,-3,-3,-3,-3, 0,-1, 0,-3},
	/*$*/    {0,-7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-3, 5}
	};
}
