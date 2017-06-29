package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.JpaController;
import model.Company;
import model.Guard;
import model.IModel;
import model.Material;
import model.Product;
import model.Rms;
import model.Secorg;
import model.Sm;

public class JpaControllerTest {
	static EntityManagerFactory emf;
	static EntityManager em;
	static JpaController controller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("practic");
		em = emf.createEntityManager();
		controller=new JpaController();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		em.close();
		emf.close();
		controller=null;
	}

	@Test
	public void testGetObjectList() {
		Class clz=Material.class;
		List<Material> list=new ArrayList<Material>();
		Material mat=new Material();
		mat.setId(6);mat.setDensity(4);mat.setWeight(5);
		mat.setName("Wood");
		list.add(mat);
		Material mat1=new Material();
		mat1.setId(7);mat1.setDensity(6);
		mat1.setName("Iron");
		mat1.setWeight(120);
		list.add(mat1);
		assertEquals(list, controller.getObjectList(clz));
	}

	@Test
	public void testAddCompany() {
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
		Company n=(Company)controller.getObjectList(Company.class).get(controller.getObjectList(Company.class).size()-1);
		assertTrue(comp.equals(n));
	controller.DeleteObject(n);
	}

	@Test
	public void testAddGuard() {
		Guard g=new Guard();
		g.setFio("Test");
		g.setAge(88);
		g.setExp(22);
		g.setRank("Test");
		List<Secorg> list=controller.getObjectList(Secorg.class);
		g.setSecorg(list.get(0));
		controller.addGuard(g);
		Guard n=(Guard)controller.getObjectList(Guard.class).get(controller.getObjectList(Guard.class).size()-1);
		assertTrue(g.equals(n));
		controller.DeleteObject(n);
	}

	@Test
	public void testAddMaterial() {
		Material mat=new Material();
		mat.setName("Test");
		mat.setDensity(5);
		mat.setWeight(5);
		controller.addMaterial(mat);
		Material n=(Material)controller.getObjectList(Material.class).get(controller.getObjectList(Material.class).size()-1);
		assertTrue(mat.equals(n));
		controller.DeleteObject(n);
	}

	@Test
	public void testAddProduct() {
		Product p=new Product();
		p.setName("test");
		p.setAmount(34);
		List<Company> list=controller.getObjectList(Company.class);
		p.setCompany(list.get(0));
		List<Sm> list1=controller.getObjectList(Sm.class);
		p.setSm(list1.get(0));
		controller.addProduct(p);
		Product n=(Product)controller.getObjectList(Product.class).get(controller.getObjectList(Product.class).size()-1);
		assertTrue(p.equals(n));
		controller.DeleteObject(n);
	}

	@Test
	public void testAddRms() {
		Rms rms=new Rms();
		rms.setName("Test");
		rms.setSupplysize(234);
		List<Material> list=controller.getObjectList(Material.class);
		rms.setMaterial(list.get(0));
		controller.addRms(rms);
		Rms n=(Rms)controller.getObjectList(Rms.class).get(controller.getObjectList(Rms.class).size()-1);
		assertTrue(rms.equals(n));
		controller.DeleteObject(n);
	}

	@Test
	public void testAddSecorg() {
		Secorg secorg=new Secorg();
		secorg.setName("test");
		secorg.setAddress("test");
		secorg.setHead("test");
		controller.addSecorg(secorg);
		Secorg n=(Secorg)controller.getObjectList(Secorg.class).get(controller.getObjectList(Secorg.class).size()-1);
		assertTrue(secorg.equals(n));
		controller.DeleteObject(n);
	}

	@Test
	public void testAddSm() {
		Sm sm=new Sm();
		sm.setName("test");
		sm.setAddress("test");
		sm.setWebsite("test");
		controller.addSm(sm);
		Sm n=(Sm)controller.getObjectList(Sm.class).get(controller.getObjectList(Sm.class).size()-1);
		assertTrue(sm.equals(n));
		controller.DeleteObject(n);
	}

	@Test
	public void testEdit() {
		Sm sm=new Sm();
		sm.setName("test");
		sm.setAddress("test");
		sm.setWebsite("test");
		controller.addSm(sm);
		Sm n=(Sm)controller.getObjectList(Sm.class).get(controller.getObjectList(Sm.class).size()-1);
		sm.setName("AAA");
		sm.setAddress("AAA");
		sm.setWebsite("AAA");
		controller.edit(n.getId(),sm);
		n=(Sm)controller.getObjectList(Sm.class).get(controller.getObjectList(Sm.class).size()-1);
		assertEquals(n,sm);
		controller.DeleteObject(n);
	}

	@Test
	public void testDelete() {
		boolean f=true;
		Sm sm=new Sm();
		sm.setName("test");
		sm.setAddress("test");
		sm.setWebsite("test");
		controller.addSm(sm);
		Sm n=(Sm)controller.getObjectList(Sm.class).get(controller.getObjectList(Sm.class).size()-1);
		controller.delete(n.getId(), "Sm");
		List list=controller.getObjectList(Sm.class);
		for(Object curr:list){
			if(curr.equals(n)){
				f=false;
			}
		}
		assertTrue(f);
	}
	@Test
	public void testExist() {
		boolean f=false;
		Sm sm=new Sm();
		sm.setName("test");
		sm.setAddress("test");
		sm.setWebsite("test");
		controller.addSm(sm);
		Sm n=(Sm)controller.getObjectList(Sm.class).get(controller.getObjectList(Sm.class).size()-1);
		List list=controller.getObjectList(Sm.class);
		for(Object curr:list){
			if(curr.equals(n)){
				f=true;
			}
		}
		assertEquals(f,controller.exist(sm));
		controller.DeleteObject(n);
	}

	@Test
	public void testEditObject() {
		Sm sm=new Sm();
		sm.setName("test");
		sm.setAddress("test");
		sm.setWebsite("test");
		controller.addSm(sm);
		Sm n=(Sm)controller.getObjectList(Sm.class).get(controller.getObjectList(Sm.class).size()-1);
		sm.setName("AAA");
		sm.setAddress("AAA");
		sm.setWebsite("AAA");
		sm.setId(n.getId());
		controller.EditObject(sm);
		n=(Sm)controller.getObjectList(Sm.class).get(controller.getObjectList(Sm.class).size()-1);
		assertEquals(n,sm);
		controller.DeleteObject(n);
	}

	@Test
	public void testDeleteObject() {
		boolean f=true;
		Sm sm=new Sm();
		sm.setName("test");
		sm.setAddress("test");
		sm.setWebsite("test");
		controller.addSm(sm);
		Sm n=(Sm)controller.getObjectList(Sm.class).get(controller.getObjectList(Sm.class).size()-1);
		controller.DeleteObject(n);
		List list=controller.getObjectList(Sm.class);
		for(Object curr:list){
			if(curr.equals(n)){
				f=false;
			}
		}
		assertTrue(f);
	}

}
