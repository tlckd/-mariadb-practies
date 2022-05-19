package dao.test;

import dao.BookDao;
import dao.CategoryDao;
import vo.BookVo;
import vo.CategoryVo;

public class BookDaoTest {
	
	public static void main(String[] args) {
		testInsert();
	}
	
	public static void testInsert() {
		
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		
		vo.setTitle("abc");
		vo.setPrice(1000);
		vo.setCategoryNo(1);
		dao.insert(vo);
	}
	
}
