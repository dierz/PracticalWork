package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SM database table.
 * 
 */
@Entity
@NamedQuery(name="Sm.findAll", query="SELECT s FROM Sm s")
public class Sm implements Serializable, IModel {
	@Override
	public String toString() {
		return "Sm [id=" + id + ", address=" + address + ", name=" + name + ", website=" + website + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String name;

	private String website;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="sm")
	private List<Product> products;

	public Sm() {
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

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setSm(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setSm(null);

		return product;
	}
	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","Name","Address","Website"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, address,website};
	}

	@Override
	public void updateWith(Object mask) {
		Sm sm=(Sm)mask;
		name=sm.getName();
		address=sm.getAddress();
		website=sm.getWebsite();
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
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Sm other = (Sm) obj;
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
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
	@Override
	public int getObjectId() {
		return id;
	}

}