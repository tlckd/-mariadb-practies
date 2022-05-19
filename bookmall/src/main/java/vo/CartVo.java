package vo;

public class CartVo {
	
	private long bookNo;
	private long cutomerNo;
	private long count;
	private String name;
	private String title;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	public long getCutomerNo() {
		return cutomerNo;
	}
	public void setCutomerNo(long cutomerNo) {
		this.cutomerNo = cutomerNo;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartVo [bookNo=" + bookNo + ", cutomerNo=" + cutomerNo + ", count=" + count + "]";
	}
	
	
	
}
