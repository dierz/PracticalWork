package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable, IModel {
	@Override
	public String toString() {
		return "Product [id=" + id + ", amount=" + amount + ", name=" + name + ", company=" + company.getName() + ", sm=" + sm.getName()
				+ "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int amount;

	private String name;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="IDCOMPANY")
	private Company company;

	//bi-directional many-to-one association to Sm
	@ManyToOne
	@JoinColumn(name="IDSM")
	private Sm sm;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Sm getSm() {
		return this.sm;
	}

	public void setSm(Sm sm) {
		this.sm = sm;
	}
	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","Name","Amount","Company","SM"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, amount,company.getName(),sm.getName()};
	}

	@Override
	public void updateWith(Object mask) {
		Product p=(Product)mask;
		name=p.getName();
		amount=p.getAmount();
	}

	@Override
	public void setObjectId(int id) {
		setId(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + id;
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
		Product other = (Product) obj;
		if (amount != other.amount)
			return false;
		if (id != other.id)
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