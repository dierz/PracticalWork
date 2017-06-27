package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GUARD database table.
 * 
 */
@Entity
@NamedQuery(name="Guard.findAll", query="SELECT g FROM Guard g")
public class Guard implements Serializable, IModel {
	@Override
	public String toString() {
		return "Guard [id=" + id + ", age=" + age + ", exp=" + exp + ", fio=" + fio + ", rank=" + rank + ", secorg="
				+ secorg.getName() + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int age;

	private int exp;

	private String fio;

	private String rank;

	//bi-directional many-to-one association to Secorg
	@ManyToOne
	@JoinColumn(name="IDSECORG")
	private Secorg secorg;

	public Guard() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getExp() {
		return this.exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getFio() {
		return this.fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Secorg getSecorg() {
		return this.secorg;
	}

	public void setSecorg(Secorg secorg) {
		this.secorg = secorg;
	}
	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","FIO","Age","Exp","Rank","SecOrg"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, fio,age,exp,rank,secorg.getName()};
	}
	@Override
	public void updateWith(Object mask) {
		Guard g=(Guard)mask;
		fio=g.getFio();
		age=g.getAge();
		exp=g.getExp();
		rank=g.getRank();
	}
	@Override
	public void setObjectId(int id) {
		setId(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + exp;
		result = prime * result + ((fio == null) ? 0 : fio.hashCode());
		result = prime * result + id;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guard other = (Guard) obj;
		if (age != other.age)
			return false;
		if (exp != other.exp)
			return false;
		if (fio == null) {
			if (other.fio != null)
				return false;
		} else if (!fio.equals(other.fio))
			return false;
		if (id != other.id)
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		return true;
	}
	
	@Override
	public int getObjectId() {
		return id;
	}
}