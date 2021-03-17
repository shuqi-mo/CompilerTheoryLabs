package personaltax;

/**
 * 视图层类：在命令行打印用户指引菜单、所得税表与所得税计算结果
 */
public class PersonalTaxView {
	
	/**
	 * 打印基本指引菜单
	 */
	public void showBasicMenu()
	{
		System.out.println("输出1：查看与修改税率表");
		System.out.println("输出2：计算个人所得税");
		System.out.println("输出0：退出系统\n");
	}
	
	/**
	 * 打印所得税菜单
	 * @param level：所得税级别数
	 * @param start：所得税起征点
	 * @param number：不同级别对应的应纳税所得额
	 * @param rate：不同级别对应的税率
	 */
	public void showPersonalTaxMenu(int level, double start, double[] number, double[] rate)
	{
		for(int i = 1; i <= level; i++)
		{
			if(i == 1)
			{
				System.out.format("级别%d：不超过%.2f元的应纳税所得额税率为%.2f\n", i, number[i-1], rate[i-1]);
				continue;
			}
			if(i == level)
			{
				System.out.format("级别%d：超过%.2f元的应纳税所得额税率为%.2f\n", i, number[i-2], rate[i-1]);
				continue;
			}
			System.out.format("级别%d：超过%.2f元至%.2f元的应纳税所得额税率为%.2f\n", i, number[i-2], number[i-1], rate[i-1]);
		}
		System.out.format("个人所得税起征点为：%.2f元\n\n", start);
	}
	
	/**
	 * 打印修改所得税的指引菜单
	 */
	public void changePersonalTaxMenu()
	{
		System.out.println("输入1：修改个人所得税");
		System.out.println("输入2：返回上一步\n");
	}
	
	/**
	 * 打印应纳所得税计算结果
	 * @param result：应纳所得税
	 */
	public void showPersonalTaxResult(double result)
	{
		System.out.format("您应该缴纳的个人所得税金额为：%.2f\n\n", result);
	}
	
	/**
	 * 打印输入税率表级别的指引菜单
	 */
	public void changeLevelMenu()
	{
		System.out.println("请输入新的税率表级别：");
	}
	
	/**
	 * 打印输入起征点的指引菜单
	 */
	public void changeStartNumberMenu()
	{
		System.out.println("请输入新的起征点：");
	}
	
	/**
	 * 打印输入不同级别的应纳税所得额的指引菜单
	 * @param level：所得税级别数
	 */
	public void changeTaxNumberMenu(int level)
	{
		System.out.format("请按从小到大的顺序输入%d个应纳税所得额划分出%d个税率表级别：\n", level-1, level);
	}
	
	/**
	 * 打印输入不同级别税率的指引菜单
	 * @param level：所得税级别数
	 */
	public void changeTaxRateMenu(int level)
	{
		System.out.format("请输入%d个税率，对应%d个税率表级别：\n", level, level);
	}
}
