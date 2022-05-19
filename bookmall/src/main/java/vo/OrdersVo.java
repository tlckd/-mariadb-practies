package vo;

public class OrdersVo {
	private long no;
	private String orderNum;
	private long amountOfPayment;
	private String shippingAddress; 
	private long memberNo;
	private long count;
	private String title;
	private long bookNo;
	private String name;
	private String email;
	
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getBookNo() {
		return bookNo;
	}


	public void setBookNo(long bookId) {
		this.bookNo = bookId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}

	public String getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}


	public long getNo() {
		return no;
	}


	public void setNo(long no) {
		this.no = no;
	}


	public long getAmountOfPayment() {
		return amountOfPayment;
	}


	public void setAmountOfPayment(long amountOfPayment) {
		this.amountOfPayment = amountOfPayment;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public long getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}


	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", orderNum=" + orderNum + ", amountOfPayment=" + amountOfPayment
				+ ", shippingAddress=" + shippingAddress + ", memberNo=" + memberNo + ", count=" + count + ", title="
				+ title + "]";
	}


	
}
