package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import vo.MemberVo;

public class MemberDao {
	
	public boolean insert(MemberVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = new MyConnection().getConnection();
			
			String sql = "insert into member values (null,?,?,?,?);";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTell());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());
			
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
	
	public boolean insert(String name, String tell, String email, String password) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setTell(tell);
		vo.setEmail(email);
		vo.setPassword(password);
		
		boolean result=false;
		
		result=insert(vo);
		
		return result;	
	}
	
	public List<MemberVo> findAll() {
		List<MemberVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = new MyConnection().getConnection();

			String sql ="select no,name,tell,email,password from member;";
			pstmt = connection.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String tell = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				
				MemberVo vo = new MemberVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setTell(tell);
				vo.setEmail(email);
				vo.setPassword(password);
				
				
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
