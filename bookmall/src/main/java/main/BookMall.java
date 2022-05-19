package main;

import java.util.List;

import dao.BookDao;
import dao.CartDao;
import dao.CategoryDao;
import dao.MemberDao;
import dao.OrdersDao;
import vo.BookVo;
import vo.CartVo;
import vo.CategoryVo;
import vo.MemberVo;
import vo.OrdersVo;

public class BookMall {

	public static void main(String[] args) {
		//데이터 삽입 
		InsertDate();
		
		
		System.out.println("1. 회원리스트");
		memberFindAll();
		System.out.println("========================");
		System.out.println("2. 카테고리 리스트");
		categoriFindAll();
		System.out.println("========================");
		System.out.println("3. 책 리스트");
		bookAndCategory();
		System.out.println("========================");
		System.out.println("3. 카트 리스트");
		cartList();
		System.out.println("========================");
		System.out.println("4. 주문 리스트");
		orderFindList();
		System.out.println("========================");
		System.out.println("5. 계산 리스트");
		orderBookList();
		
	}
	
	
	
	public static void InsertDate() {
		
		//Member 테이블 데이터 입력
		MemberDao memDao = new MemberDao();
		memDao.insert("둘리","000-0000-0000","둘리@이메일.com","1234");
		memDao.insert("마이콜","000-0000-0000","마이콜@이메일.com","1234");
		memDao.insert("앨리스","000-0000-0000","앨리스@이메일.com","1234");
		memDao.insert("스미스","000-0000-0000","스미스@이메일.com","1234");
		memDao.insert("멜로리","000-0000-0000","멜로리@이메일.com","1234");
		
		//Category 데이터 입력 
		CategoryDao catDao = new CategoryDao();
		catDao.insert("Novel");
		catDao.insert("Essay");
		catDao.insert("Humanities");
		catDao.insert("IT");
	
		//Book 테이블 데이터 입력 
		BookDao bookDao = new BookDao();
		bookDao.insert("노벨과 함께 사라지다", 1000L, 1L);
		bookDao.insert("수필과 함께 사라지다", 1000L, 2L);
		bookDao.insert("인문과 함께 사라지다", 1000L, 3L);
		bookDao.insert("컴퓨터와 함께 사라지다", 1000L, 4L);
		

		//Order 테이블 데이터 입력
		OrdersDao dao = new OrdersDao();
		dao.insert("20220519-0001",1L, "부산북구", 1L);
		dao.insert("20220519-0002", 1L, "부산진구", 2L);
		dao.insert("20220519-0003", 2L, "서울남구", 1L);
		dao.insert("20220519-0004", 2L, "서울남구", 3L);
		dao.insert("20220519-0005", 2L, "부산동구", 4L);
	
		
		//Cart 테이블 데이터 입력 
		CartDao cartDao = new CartDao();
		cartDao.insert(1L, 1L, 5L);
		cartDao.insert(1L, 2L, 4L);
		cartDao.insert(4L, 3L, 2L);
		cartDao.insert(2L, 4L, 1L);
		
		//Orders_book 테이블 데이터 입력 
		dao.insertOrderBook(1L, 1L, 1L);
		dao.insertOrderBook(3L, 2L, 1L);
		dao.insertOrderBook(2L, 1L, 1L);
		dao.insertOrderBook(1L, 1L, 2L);

			
	}
	
	
	private static void memberFindAll() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo.getNo()+": "+vo.getName() + ", " + vo.getEmail());
		}
	}
	
	private static void categoriFindAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo.getNo()+": " +vo.getCategory_name());
		}
	}
	
	private static void bookAndCategory() {
		List<BookVo> list = new BookDao().findBookAndCategory();
		for(BookVo vo : list) {
			System.out.println(vo.getNo()+": " +vo.getTitle() + " " + vo.getPrice() + ", " + vo.getCategoryName() );
		}
	}
	
	private static void cartList() {
		List<CartVo> list = new CartDao().findCartList();
		int count=1;
		for(CartVo vo : list) {
			
			System.out.println(count+": "+vo.getName() +" "+vo.getTitle() + " " +vo.getCount());
			count++;
		}
	}
	
	private static void orderFindList() {
		List<OrdersVo> list = new OrdersDao().findOrderList();
		int count=1;
		for(OrdersVo vo : list) {
			System.out.println(count + ": " + vo.getOrderNum()+" " +vo.getName()+"("+vo.getEmail()+")" +" "+ vo.getAmountOfPayment() +" "+ vo.getShippingAddress());
			count++;
		}
	}
	
	private static void orderBookList() {
		List<OrdersVo> list = new OrdersDao().findOrderBook();
		int count=1;
		for(OrdersVo vo : list) {
			System.out.println(count + ": " + vo.getTitle()+" " +vo.getCount()+" " + vo.getAmountOfPayment());
			count++;
		}
	}
	


}
