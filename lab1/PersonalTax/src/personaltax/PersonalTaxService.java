package personaltax;
import java.util.*;

/**
 * 控制层类：连接视图层与模型层
 */
public class PersonalTaxService {
	/**
	 * 实现查看与修改税率表、计算应交纳所得税的功能
	 */
	public void run()
	{
		PersonalTaxView view = new PersonalTaxView();
		PersonalTaxModel taxtable = new PersonalTaxModel();
		Scanner in = new Scanner(System.in);
		view.showBasicMenu();
		int choice = in.nextInt();
		while(choice != 0)
		{
			if(choice == 1)
			{
				view.showPersonalTaxMenu(taxtable.level, taxtable.startnumber, taxtable.TaxNumber, taxtable.TaxRate);
				view.changePersonalTaxMenu();
				int changechoice = in.nextInt();
				if(changechoice == 1)
				{
					view.changeLevelMenu();
					int newlevel = in.nextInt();
					view.changeStartNumberMenu();
					double newstartnumber = in.nextDouble();
					view.changeTaxNumberMenu(newlevel);
					double[] newTaxNumber = new double[100];
					for(int i = 0; i < newlevel - 1; i++)
						newTaxNumber[i] = in.nextDouble();
					view.changeTaxRateMenu(newlevel);
					double[] newTaxRate = new double[100];
					for(int i = 0; i < newlevel; i++)
						newTaxRate[i] = in.nextDouble();
					taxtable.update(newlevel, newstartnumber, newTaxNumber, newTaxRate);
					view.showPersonalTaxMenu(taxtable.level, taxtable.startnumber, taxtable.TaxNumber, taxtable.TaxRate);
				}
			}
			if(choice == 2)
			{
				double result;
				System.out.println("请输入您的工资：");
				double salary = in.nextDouble();
				result = taxtable.PersonalTaxCalculate(salary, taxtable.level, taxtable.startnumber, taxtable.TaxNumber, taxtable.TaxRate);
				view.showPersonalTaxResult(result);
			}
			view.showBasicMenu();
			choice = in.nextInt();
		}
	}
}
