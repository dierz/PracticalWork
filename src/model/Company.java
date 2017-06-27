package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COMPANY database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable, IModel {
	@Override
	public String toString() {
		return "Company [id=" + id + ", address=" + address + ", name=" + name + ", phnumber=" + phnumber
				+ ", yearprofit=" + yearprofit + ", rm=" + rm.getName() + ", secorg=" + secorg.getName() + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String name;

	private String phnumber;

	private double yearprofit;

	//bi-directional many-to-one association to Rms
	@ManyToOne
	@JoinColumn(name="IDRMS")
	private Rms rm;

	//bi-directional many-to-one association to Secorg
	@ManyToOne
	@JoinColumn(name="IDSECORG")
	private Secorg secorg;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="company")
	private List<Product> products;

	public Company() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhnumber() {
		return this.phnumber;
	}

	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}

	public double getYearprofit() {
		return this.yearprofit;
	}

	public void setYearprofit(double yearprofit) {
		this.yearprofit = yearprofit;
	}

	public Rms getRm() {
		return this.rm;
	}

	public void setRm(Rms rm) {
		this.rm = rm;
	}

	public Secorg getSecorg() {
		return this.secorg;
	}

	public void setSecorg(Secorg secorg) {
		this.secorg = secorg;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCompany(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCompany(null);

		return product;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","Name","YearProfit","Address","Phnumber","SecOrg","RMS"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, yearprofit, address, phnumber,secorg.getName(),rm.getName()};
	}

	@Override
	public void updateWith(Object mask) {
		Company comp=(Company)mask;
		name=comp.getName();
		yearprofit=comp.getYearprofit();
		address=comp.getAddress();
		phnumber=comp.getPhnumber();
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
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phnumber == null) ? 0 : phnumber.hashCode());
		long temp;
		temp = Double.doubleToLongBits(yearprofit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Company other = (Company) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phnumber == null) {
			if (other.phnumber != null)
				return false;
		} else if (!phnumber.equals(other.phnumber))
			return false;
		if (Double.doubleToLongBits(yearprofit) != Double.doubleToLongBits(other.yearprofit))
			return false;
		return true;
	}
    
}