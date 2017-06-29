package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the RMS database table.
 * 
 */
@Entity
@NamedQuery(name="Rms.findAll", query="SELECT r FROM Rms r")
public class Rms implements Serializable, IModel {
	@Override
	public String toString() {
		return "Rms [id=" + id + ", name=" + name + ", supplysize=" + supplysize + ", material=" + material.getName() + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int supplysize;

	//bi-directional many-to-one association to Company
	@OneToMany(mappedBy="rm")
	private List<Company> companies;

	//bi-directional many-to-one association to Material
	@ManyToOne
	@JoinColumn(name="IDMATERIAL")
	private Material material;

	public Rms() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSupplysize() {
		return this.supplysize;
	}

	public void setSupplysize(int supplysize) {
		this.supplysize = supplysize;
	}

	public List<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Company addCompany(Company company) {
		getCompanies().add(company);
		company.setRm(this);

		return company;
	}

	public Company removeCompany(Company company) {
		getCompanies().remove(company);
		company.setRm(null);

		return company;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","Name","Supplysize","Material"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, supplysize,material.getName()};
	}

	@Override
	public void updateWith(Object mask) {
		Rms rms=(Rms)mask;
		name=rms.getName();
		supplysize=rms.getSupplysize();
	}

	@Override
	public void setObjectId(int id) {
		setId(id);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + supplysize;
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
		Rms other = (Rms) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (supplysize != other.supplysize)
			return false;
		return true;
	}

	@Override
	public int getObjectId() {
		return id;
	}
	
}