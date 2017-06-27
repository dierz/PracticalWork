package model;

public class Guard {
	protected int id;
	protected String fio;
	protected int age;
	protected int exp;
	protected String rank;
	protected Secorg secorg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Secorg getSecorg() {
		return secorg;
	}
	public void setSecorg(Secorg secorg) {
		this.secorg = secorg;
	}
	@Override
	public String toString() {
		return "Guard [id=" + id + ", fio=" + fio + ", age=" + age + ", exp=" + exp + ", rank=" + rank + ", secorg="
				+ secorg.name + "]";
	}
	

}
