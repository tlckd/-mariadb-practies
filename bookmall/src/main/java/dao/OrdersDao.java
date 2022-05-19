package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVo;
import vo.OrdersVo;

public class OrdersDao {
	
	public boolean insert(OrdersVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = new MyConnection().getConnection();
			
			String sql = "insert into orders values(null,?,?,?,?);";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,vo.getOrderNum());
			pstmt.setLong(2, vo.getAmountOfPayment());
			pstmt.setString(3, vo.getShippingAddress());
			pstmt.setLong(4, vo.getMemberNo());
			
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
	
	public boolean insertOrderBook(OrdersVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = new MyConnection().getConnection();
			
			String sql = "insert into orders_book values(?,?,?);";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1,vo.getBookNo());
			pstmt.setLong(2,vo.getNo());
			pstmt.setLong(3,vo.getCount());

			
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
	
	public boolean insert(String orderNum,Long amountOfPayment, String shippingAddress, Long MemberNo) {
		OrdersVo vo = new OrdersVo();
		vo.setOrderNum(orderNum);
		vo.setAmountOfPayment(amountOfPayment);;
		vo.setShippingAddress(shippingAddress);;
		vo.setMemberNo(MemberNo);
		
		boolean result=false;
		
		result=insert(vo);
		
		return result;	
	}
	
	public boolean insertOrderBook(Long bookNo,Long orderNo,Long count) {
		
		OrdersVo vo = new OrdersVo();
		vo.setBookNo(bookNo);
		vo.setNo(orderNo);
		vo.setCount(count);
		
		boolean result=false;
		
		result=insertOrderBook(vo);
		
		return result;
	}
	
	public List<OrdersVo> findAll() {
		List<OrdersVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = new MyConnection().getConnection();

			String sql ="select no, order_num, amount_of_payment, shipping_address, member_no from orders;";
			pstmt = connection.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String orderNum = rs.getString(2);
				Long amountOfPayment = rs.getLong(3);
				String shippingAddress = rs.getString(4);
				Long memberNo = rs.getLong(5);
				
				OrdersVo vo = new OrdersVo();
				vo.setNo(no);
				vo.setOrderNum(orderNum);
				vo.setAmountOfPayment(amountOfPayment);
				vo.setShippingAddress(shippingAddress);
				vo.setMemberNo(memberNo);
				
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
	
	public List<OrdersVo> findOrderBook() {
		List<OrdersVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = new MyConnection().getConnection();

			String sql ="select b.title,a.count,c.amount_of_payment from orders_book a, book b, orders c where a.book_no=b.no and a.orders_no = c.no;";
			pstmt = connection.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString(1);
				Long count = rs.getLong(2);
				Long amountOfPayment = rs.getLong(3);
		
				OrdersVo vo = new OrdersVo();
				vo.setTitle(title);
				vo.setCount(count);
				vo.setAmountOfPayment(amountOfPayment);
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
	
	public List<OrdersVo> findOrderList() {
		List<OrdersVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = new MyConnection().getConnection();

			String sql ="select order_num, a.name, a.email, amount_of_payment, shipping_address from member a, orders b where a.no = b.member_no;";
			pstmt = connection.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String orderNum = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				Long amountOfPayment = rs.getLong(4);
				String shippingAddress = rs.getString(5);
				
				OrdersVo vo = new OrdersVo();
				vo.setOrderNum(orderNum);
				vo.setName(name);
				vo.setEmail(email);
				vo.setAmountOfPayment(amountOfPayment);
				vo.setShippingAddress(shippingAddress);
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
