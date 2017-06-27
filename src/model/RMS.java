package model;

public class RMS {
	protected int id;
	protected String name;
	protected int size;
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	@Override
	public String toString() {
		return "RMS [id=" + id + ", name=" + name + ", size=" + size + ", material=" + material.name + "]";
	}
	
}
