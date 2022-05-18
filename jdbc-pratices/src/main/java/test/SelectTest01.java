package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectTest01 {

	public static void main(String[] args) {
		List<DepartmentVo> result = findAll();
		for(DepartmentVo vo : result) {
			System.out.println(vo);
		}
	}

	private static List<DepartmentVo> findAll() {
		List<DepartmentVo> result = new ArrayList<>();
		// 리스트를 null로 하는것보단 아무것도 안들어있는 리스트를 넣어주는게 좋음 
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs=null;
		
		try {
			//1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://192.168.10.33:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 생성
			stmt = connection.createStatement();
			
			//4. SQL 실행
			String sql = "select no,name from department order by no desc";
			rs = stmt.executeQuery(sql);
			
			//결과처리 
			while(rs.next()) { 
				Long no =rs.getLong(1); // DB는 1부터 시작 
				String name = rs.getString(2); 
				
				DepartmentVo vo = new DepartmentVo();
				vo.setNo(no);;
				vo.setName(name);
				
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
				if(stmt != null) {
					stmt.close();
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
