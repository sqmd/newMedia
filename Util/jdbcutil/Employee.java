public class Employee {
	private int id;
	private String name;
	private boolean sex;
	private double gross;
	
	public Employee() {
	}
	public Employee(int id, String name, boolean sex, double gross) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.gross = gross;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public double getGross() {
		return gross;
	}
	public void setGross(double gross) {
		this.gross = gross;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", sex=" + sex + ", gross=" + gross + "]";
	}
	
}
