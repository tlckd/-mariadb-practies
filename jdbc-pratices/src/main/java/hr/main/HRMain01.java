package hr.main;

import java.util.List;
import java.util.Scanner;

import hr.dao.EmployeesDao;
import hr.vo.EmployeeVo;

public class HRMain01 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름>");
		String name = scanner.nextLine();
		
		EmployeesDao dao =new EmployeesDao();
		List<EmployeeVo> list =dao.findByFirstNameOrLastName(name);
		
		for(EmployeeVo vo : list) {
			System.out.println(vo.getNo() + ":" +vo.getFirst_name() + ":" + vo.getLast_name()+ ":"+ vo.getHiredate());
		}
		
		scanner.close();

	}

}
