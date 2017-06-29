package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SECORG database table.
 * 
 */
@Entity
@NamedQuery(name="Secorg.findAll", query="SELECT s FROM Secorg s")
public class Secorg implements Serializable, IModel {
	@Override
	public String toString() {
		return "Secorg [id=" + id + ", address=" + address + ", head=" + head + ", name=" + name + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String head;

	private String name;

	//bi-directional many-to-one association to Company
	@OneToMany(mappedBy="secorg")
	private List<Company> companies;

	//bi-directional many-to-one association to Guard
	@OneToMany(mappedBy="secorg")
	private List<Guard> guards;

	public Secorg() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHead() {
		return this.head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Company addCompany(Company company) {
		getCompanies().add(company);
		company.setSecorg(this);

		return company;
	}

	public Company removeCompany(Company company) {
		getCompanies().remove(company);
		company.setSecorg(null);

		return company;
	}

	public List<Guard> getGuards() {
		return this.guards;
	}

	public void setGuards(List<Guard> guards) {
		this.guards = guards;
	}

	public Guard addGuard(Guard guard) {
		getGuards().add(guard);
		guard.setSecorg(this);

		return guard;
	}

	public Guard removeGuard(Guard guard) {
		getGuards().remove(guard);
		guard.setSecorg(null);

		return guard;
	}
	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","Name","Address","Head"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, address,head};
	}

	@Override
	public void updateWith(Object mask) {
		Secorg sec=(Secorg)mask;
		name=sec.getName();
		address=sec.getAddress();
		head=sec.getHead();
	}

	@Override
	public void setObjectId(int id) {
		setId(id);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Secorg other = (Secorg) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int getObjectId() {
		return id;
	}

}