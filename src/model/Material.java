package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MATERIAL database table.
 * 
 */
@Entity
@NamedQuery(name="Material.findAll", query="SELECT m FROM Material m")
public class Material implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Material [id=" + id + ", density=" + density + ", name=" + name + ", weight=" + weight + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double density;

	private String name;

	private double weight;

	//bi-directional many-to-one association to Rms
	@OneToMany(mappedBy="material")
	private List<Rms> rms;

	public Material() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDensity() {
		return this.density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public List<Rms> getRms() {
		return this.rms;
	}

	public void setRms(List<Rms> rms) {
		this.rms = rms;
	}

	public Rms addRm(Rms rm) {
		getRms().add(rm);
		rm.setMaterial(this);

		return rm;
	}

	public Rms removeRm(Rms rm) {
		getRms().remove(rm);
		rm.setMaterial(null);

		return rm;
	}
	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","Name","Density","Weight"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, density,weight};
	}

	@Override
	public void updateWith(Object mask) {
		Material m=(Material)mask;
		name=m.getName();
		density=m.getDensity();
		weight=m.getWeight();
	}

	@Override
	public void setObjectId(int id) {
		setId(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(density);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(weight);
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
		Material other = (Material) obj;
		if (Double.doubleToLongBits(density) != Double.doubleToLongBits(other.density))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

}