package model;

public class Company {
	private int id;
	private String name;
	private float profit;
	private String address;
	private String phnumber;
	private SecOrg secorg;
	private RMS rms;
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
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
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
	public SecOrg getSecorg() {
		return secorg;
	}
	public void setSecorg(SecOrg secorg) {
		this.secorg = secorg;
	}
	public RMS getRms() {
		return rms;
	}
	public void setRms(RMS rms) {
		this.rms = rms;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", profit=" + profit + ", address=" + address + ", phnumber="
				+ phnumber + ", secorg=" + secorg.name + ", rms=" + rms.name + "]";
	}
	
}
