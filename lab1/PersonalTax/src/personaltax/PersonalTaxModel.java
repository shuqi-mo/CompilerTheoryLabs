package personaltax;

/**
 * 模型层类：存储税率表，定义了更新税率表与所得税计算的方法
 * @param level：所得税级别数
 * @param startnumber：起征点
 * @param TaxNumber：不同级别对应的应纳税所得额
 * @param TaxRate：不同级别对应的税率
 */
public class PersonalTaxModel {
	public int level;
	public double startnumber;
	public double TaxNumber[];
	public double TaxRate[];
	
	/**
	 * 构造函数：参数值初始化
	 */
	public PersonalTaxModel()
	{
		level = 5;
		startnumber = 1600;
		TaxNumber = new double[]{500, 2000, 5000, 20000};
		TaxRate = new double[] {0.05, 0.1, 0.15, 0.2, 0.25};
	}
	
	/**
	 * 更新税率表
	 * @param newlevel：新的所得税级别数
	 * @param newstartnumber：新的起征点
	 * @param newTaxNumber：不同级别对应的应纳税所得额
	 * @param newTaxRate：不同级别对应的税率
	 */
	public void update(int newlevel, double newstartnumber, double[] newTaxNumber, double[] newTaxRate)
	{
		this.level = newlevel;
		this.startnumber = newstartnumber;
		this.TaxNumber = newTaxNumber;
		this.TaxRate = newTaxRate;
	}
	
	/**
	 * 计算应缴纳的所得税
	 * @param salary：工资
	 * @param level：所得税级别数
	 * @param start：起征点
	 * @param number：不同级别对应的应纳税所得额
	 * @param rate：不同级别对应的税率
	 * @return：应缴纳的所得税
	 */
	public double PersonalTaxCalculate(double salary, int level, double start, double[] number, double[] rate)
	{
		double result = 0;
		double payable;
		if(salary > start)
		{
			payable = salary - start;
			for(int i = level; i >= 2; i--)
			{
				if(payable >= number[i-2])
				{
					result += (payable - number[i-2]) * rate[i-1];
					payable -= payable - number[i-2];
				}
			}
			result += payable * rate[0];
		}
		return result;
	}
}