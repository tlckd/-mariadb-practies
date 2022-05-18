package hr.main;

import java.util.List;
import java.util.Scanner;

import hr.dao.EmployeesDao;
import hr.vo.EmployeeVo;

public class HRMain02 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("salary[min max]>");
		int minSalary = scanner.nextInt();
		int maxSalary = scanner.nextInt();
		
		List<EmployeeVo> list = new EmployeesDao().findBySalary(minSalary,maxSalary);
		
		// no: fristName:lastName:salary 
		// order 셀러리 큰순 
		
		for(EmployeeVo result : list) {
			System.out.println("No" + result.getNo() + " : "+result.getFirst_name() + "" + result.getLast_name() + "  | Salary : " + result.getSalary());
		}
		
		scanner.close();

	}

}
