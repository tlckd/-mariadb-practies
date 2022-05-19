package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BookVo;
import vo.CartVo;

public class CartDao {
	
	
	public boolean insert(CartVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = new MyConnection().getConnection();
			
			String sql = "insert into cart values(?,?,?);";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, vo.getBookNo());
			pstmt.setLong(2, vo.getCutomerNo());
			pstmt.setLong(3, vo.getCount());
			
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
	
	public boolean insert(Long bookNo, Long customerNo, Long count) {
		CartVo vo = new CartVo();
		vo.setBookNo(bookNo);
		vo.setCutomerNo(customerNo);
		vo.setCount(count);
		
		boolean result=false;
		
		result=insert(vo);
		
		return result;	
	}
	
	public List<CartVo> findCartList() {
		List<CartVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = new MyConnection().getConnection();

			String sql ="select  c.name, b.title, a.count from cart a, book b, member c where a.book_no=b.no and a.member_no =c.no;";
			pstmt = connection.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString(1);
				String title = rs.getString(2);
				Long count = rs.getLong(3);
		
				CartVo vo = new CartVo();
				vo.setName(name);
				vo.setTitle(title);
				vo.setCount(count);
				
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
