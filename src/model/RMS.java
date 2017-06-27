package model;

public class Rms {
	protected int id;
	protected String name;
	protected int supplysize;
	protected Material material;
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

	public int getSupplysize() {
		return supplysize;
	}
	public void setSupplysize(int supplysize) {
		this.supplysize = supplysize;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	@Override
	public String toString() {
		return "RMS [id=" + id + ", name=" + name + ", size=" + supplysize + ", material=" + material.name + "]";
	}
	
}
