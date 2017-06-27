package model;

public class Material {
	protected int id;
	protected String name;
	protected float density;
	protected float weight;
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
	public float getDensity() {
		return density;
	}
	public void setDensity(float density) {
		this.density = density;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Material [id=" + id + ", name=" + name + ", density=" + density + ", weight=" + weight + "]";
	}
	
}
