package dao.test;

import java.util.List;

import dao.CategoryDao;
import dao.MemberDao;
import vo.CategoryVo;
import vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		 testInsert();
	}
	
	public static void testInsert() {
		
		MemberDao dao = new MemberDao();
		dao.insert("테스트2","123124","ㅁㄴㅇ@ㅁㄴㅇ","1234");
	}
	
	private static void testFindAll() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}
	
}
