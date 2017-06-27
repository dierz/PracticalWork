package model;

public class Company {
	private int id;
	private String name;
	private float yearprofit;
	private String address;
	private String phnumber;
	private Secorg secorg;
	private Rms rms;
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

	public float getYearprofit() {
		return yearprofit;
	}
	public void setYearprofit(float yearprofit) {
		this.yearprofit = yearprofit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhnumber() {
		return phnumber;
	}
	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}
	public Secorg getSecorg() {
		return secorg;
	}
	public void setSecorg(Secorg secorg) {
		this.secorg = secorg;
	}
	public Rms getRms() {
		return rms;
	}
	public void setRms(Rms rms) {
		this.rms = rms;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", profit=" + yearprofit + ", address=" + address + ", phnumber="
				+ phnumber + ", secorg=" + secorg.name + ", rms=" + rms.name + "]";
	}
	
}
