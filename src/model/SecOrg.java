package model;

public class SecOrg {
	protected int id;
	protected String name;
	protected String address;
	protected String head;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	@Override
	public String toString() {
		return "SecOrg [id=" + id + ", name=" + name + ", address=" + address + ", head=" + head + "]";
	}
	

}
