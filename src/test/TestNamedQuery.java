package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import controller.JpaController;
import model.Company;
import model.Guard;
import model.IModel;
import model.Material;
import model.Rms;
import model.Secorg;

public class TestNamedQuery {

	public static void main(String[] args) {
		JpaController controller=new JpaController();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("practic");
		EntityManager em=emf.createEntityManager();
		Query q=em.createNamedQuery("Material.findAll");
		Company comp=new Company();
		comp.setName("Test");
		comp.setYearprofit(12345);
		comp.setAddress("Test");
		comp.setPhnumber("Test");
		List<Secorg> list=controller.getObjectList(Secorg.class);
		comp.setSecorg(list.get(0));
		List<Rms> list1=controller.getObjectList(Rms.class);
		comp.setRm(list1.get(0));
		controller.addCompany(comp);
		controller.DeleteObject((IModel)controller.getObjectList(Company.class).get(controller.getObjectList(Company.class).size()-1));
		
	}

}
