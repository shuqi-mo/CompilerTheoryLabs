package personaltax;

/**
 * 通过运行主函数计算个人所得税
 */
public class Main {
	public static void main(String[] args)
	{
		PersonalTaxService service = new PersonalTaxService();
		service.run();
	}
}
