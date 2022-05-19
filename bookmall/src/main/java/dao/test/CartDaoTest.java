package dao.test;

import dao.CartDao;

public class CartDaoTest {
	public static void main(String[] args) {
		testInsert();
	}
	
	public static void testInsert() {
		
		CartDao dao = new CartDao();

		dao.insert(2L,1L,1L);
	}
}
