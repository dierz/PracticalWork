package controller;

import javax.swing.table.TableModel;

public interface IController {

	public TableModel getModel(String className);
	public void edit(int id, Object obj);
	public void delete(int id, String className);
	void addCompany(Object obj);
	void addGuard(Object obj);
	void addMaterial(Object obj);
	void addProduct(Object obj);
	void addRms(Object obj);
	void addSecorg(Object obj);
	void addSm(Object obj);
}