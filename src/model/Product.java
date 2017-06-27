package model;

public class Product {
	protected int id;
	protected String name;
	protected int amount;
	protected Company company;
	protected Sm sm;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Sm getSm() {
		return sm;
	}
	public void setSm(Sm sm) {
		this.sm = sm;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", amount=" + amount + ", company=" + company + ", sm=" + sm.name
				+ "]";
	}
	
}
