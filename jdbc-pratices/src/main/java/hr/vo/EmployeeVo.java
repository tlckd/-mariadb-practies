package hr.vo;

public class EmployeeVo {
	private Long no;
	private String birthDate;
	private String first_name;
	private String last_name;
	private String genger;
	private String hiredate;
	private int salary;
	
	public Long getNo() {
		return no;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGenger() {
		return genger;
	}
	public void setGenger(String genger) {
		this.genger = genger;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	
	@Override
	public String toString() {
		return "EmployeeVo [no=" + no + ", birthDate=" + birthDate + ", first_name=" + first_name + ", last_name="
				+ last_name + ", genger=" + genger + ", hiredate=" + hiredate + ", salary=" + salary + "]";
	}
	
}
