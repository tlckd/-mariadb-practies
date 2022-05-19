package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BookVo;
import vo.CategoryVo;

public class BookDao {

		
		public boolean insert(BookVo vo) {
			boolean result = false;
			Connection connection = null;
			PreparedStatement pstmt = null;
			
			try {
				connection = new MyConnection().getConnection();
				
				String sql = "insert into book  values (null,?,?,?);";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setLong(2, vo.getPrice());
				pstmt.setLong(3, vo.getCategoryNo());
				
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
		
		public boolean insert(String title, Long price, Long categoryNo) {
			BookVo vo = new BookVo();
			vo.setTitle(title);
			vo.setPrice(price);
			vo.setCategoryNo(categoryNo);
			
			boolean result=false;
			
			result=insert(vo);
			
			return result;	
		}
	
		
		public List<BookVo> findBookAndCategory() {
			List<BookVo> result = new ArrayList<>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				connection = new MyConnection().getConnection();

				String sql ="select a.no,a.title,a.price,b.category_name from book a, category b where a.category_no = b.no;";
				pstmt = connection.prepareStatement(sql);			
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					Long price = rs.getLong(3);
					String categoriName=rs.getString(4);
					
					BookVo vo = new BookVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setPrice(price);
					vo.setCategoryName(categoriName);
					
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
