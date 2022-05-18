package hr.dao.test;

import java.util.List;

import hr.dao.EmployeesDao;
import hr.vo.EmployeeVo;

public class EmployeeDaoTest {

	public static void main(String[] args) {
		
		//testFindByFirstNameOrLastName("ken");
		testFindBySalary(30000,40000);
	}

	public static void testFindByFirstNameOrLastName(String name) {
		List<EmployeeVo> list = new EmployeesDao().findByFirstNameOrLastName(name);
		
		for(EmployeeVo result : list) {
			System.out.println(result);
		}
	}
	
	public static void testFindBySalary(int min, int max) {
		List<EmployeeVo> list = new EmployeesDao().findBySalary(min, max);
		
		for(EmployeeVo result : list) {
			System.out.println(result);
		}
		
	}
}
