package personaltax;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonalTaxModelTest {

	@Test
	public void test1()
	{
		PersonalTaxModel taxtable = new PersonalTaxModel();
		double salary = 4300;
		int level = 5;
		double start = 1600;
		double TaxNumber[] = new double[]{500, 2000, 5000, 20000};
		double TaxRate[] = new double[]{0.05, 0.1, 0.15, 0.2, 0.25};
	    assertTrue(taxtable.PersonalTaxCalculate(salary, level, start, TaxNumber, TaxRate)==280);
	}
	
	@Test
	public void test2()
	{
		PersonalTaxModel taxtable = new PersonalTaxModel();
		double salary = 4300;
		int level = 6;
		double start = 1000;
		double TaxNumber[] = new double[]{1000, 2000, 3000, 4000, 5000};
		double TaxRate[] = new double[]{0.01, 0.02, 0.03, 0.04, 0.05, 0.06};
	    assertTrue(taxtable.PersonalTaxCalculate(salary, level, start, TaxNumber, TaxRate)==72);
	}
}
