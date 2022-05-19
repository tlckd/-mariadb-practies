package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import vo.CategoryVo;

public class CategoryDao {
	
	
	public boolean insert(CategoryVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = new MyConnection().getConnection();
			
			String sql = "insert into category values (null,?);";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getCategory_name());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
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
	
	public boolean insert(String categoryName) {
		CategoryVo vo = new CategoryVo();
		vo.setCategory_name(categoryName);
		boolean result=false;
		
		result=insert(vo);
		
		return result;	
	}
	
	public List<CategoryVo> findAll() {
		List<CategoryVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = new MyConnection().getConnection();

			String sql ="select no, category_name from category;";
			pstmt = connection.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String categoryName = rs.getString(2);
				
				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setCategory_name(categoryName);
				
				result.add(vo);
			}
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
