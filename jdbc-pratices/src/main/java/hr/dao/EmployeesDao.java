package hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.vo.EmployeeVo;

public class EmployeesDao {

	public List<EmployeeVo> findByFirstNameOrLastName(String name) {
		List<EmployeeVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://192.168.10.33:3306/employees?charset=utf8";
			connection = DriverManager.getConnection(url, "hr", "hr");
			
			//3. SQL 준비
			String sql =
				"  select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d')"
				+ " from employees"
				+ " where first_name like ?"
				+ " or last_name like ?"
				+ " order by hire_date"
				+" limit 0,100;"; // limitaion 100
			pstmt = connection.prepareStatement(sql);
			
			//4. Parameter Mapping
			pstmt.setString(1, "%"+name+"%");
			pstmt.setString(2, "%"+name+"%");
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String first_name = rs.getString(2);
				String last_name = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirst_name(first_name);
				vo.setLast_name(last_name);
				vo.setHiredate(hireDate);
				
				result.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}

	
	public List<EmployeeVo> findBySalary(int minSalary, int maxSalary) {
		List<EmployeeVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://192.168.10.33:3306/employees?charset=utf8";
			connection = DriverManager.getConnection(url, "hr", "hr");
			
			//3. SQL 준비
			String sql =
				"select a.emp_no, first_name,last_name,salary"
				+ " from employees a, salaries b"
				+ " where a.emp_no = b.emp_no"
				+ " and salary > ? and salary < ?"
				+ " order by salary;"; // limitaion 100
			pstmt = connection.prepareStatement(sql);
			
			//4. Parameter Mapping
			pstmt.setInt(1, minSalary);
			pstmt.setInt(2, maxSalary);
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String first_name = rs.getString(2);
				String last_name = rs.getString(3);
				int salary = rs.getInt(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirst_name(first_name);
				vo.setLast_name(last_name);
				vo.setSalary(salary);;
				
				result.add(vo);
			}
		} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패:" + e);
			} catch (SQLException e) {
				System.out.println("드라이버 로딩 실패:" + e);
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		return result;		
	}
	
	
}
