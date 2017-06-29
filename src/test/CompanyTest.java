package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Company;
import model.Material;
import model.Product;
import model.Rms;
import model.Secorg;
import model.Sm;

public class CompanyTest {
	private Company comp;
	private List<Product> products;
	private Rms rm;
	private Secorg secorg;
	private Material mat;
	private Product prod;
	private Sm sm;
	

	@Before
	public void setUp() throws Exception {
		comp=new Company();
		mat=new Material();
		mat.setName("Oak");mat.setId(1);mat.setDensity(3.5);mat.setWeight(230);
		rm=new Rms();
		rm.setId(1);rm.setName("Supp");rm.setSupplysize(120);
		rm.setMaterial(mat);
		secorg=new Secorg();
		secorg.setId(1);secorg.setName("CHOP");
		secorg.setAddress("1-Street");
		secorg.setHead("Ivanov I.C.");
		comp.setAddress("Street");
		comp.setId(1);
		comp.setName("Industry");
		comp.setPhnumber("39095");
		comp.setYearprofit(234);
		comp.setRm(rm);
		comp.setSecorg(secorg);
		
	}

	@After
	public void tearDown() throws Exception {
		comp=null;mat=null;rm=null;secorg=null;
	}

	@Test
	public void testSetId() {

		assertEquals(1, comp.getId());
	}
	@Test
	public void testGetId() {
		int k=comp.getId();
		assertEquals(1,k);
	}
	@Test
	public void testSetAddress() {
	
		assertEquals("Street", comp.getAddress());
	}
	@Test
	public void testGetAddress() {
		String adr=comp.getAddress();
		assertEquals("Street", adr);
	}

	@Test
	public void testSetName() {

		assertEquals("Industry", comp.getName());
	}

	@Test
	public void testGetName() {
		String name=comp.getName();
		assertEquals("Industry", name);
	}

	@Test
	public void testSetPhnumber() {
	
		assertEquals("39095", comp.getPhnumber());
	}
	
	@Test
	public void testGetPhnumber() {
		String num=comp.getPhnumber();
		assertEquals("39095", num);
	}

	@Test
	public void testSetYearprofit() {
	assertEquals(234.0, comp.getYearprofit(),0.5);
	}
	@Test
	public void testGetYearprofit() {
		double profit=comp.getYearprofit();
		assertEquals(234.0, profit,0.5);
	}

	@Test
	public void testSetRm() {
		
		assertSame(rm, comp.getRm());
	}
	@Test
	public void testGetRm() {
		Rms n=new Rms();
		n=comp.getRm();
		assertSame(rm, n);
	}
	@Test
	public void testSetSecorg() {
		

		assertSame(secorg, comp.getSecorg());
	}
	@Test
	public void testGetSecorg() {
		Secorg sec=new Secorg();
		sec=comp.getSecorg();
		assertSame(secorg, sec);
	}

	@Test
	public void testSetProducts() {
		
		comp.setProducts(products);
		assertSame(products, comp.getProducts());
	}
	@Test
	public void testGetProducts() {
		List<Product> list=comp.getProducts();
		assertSame(products, list);
	}



	@Test
	public void testGetTableHeaders() {
		String[] asd={"ID","Name","YearProfit","Address","Phnumber","SecOrg","RMS"};
		assertArrayEquals(asd, comp.getTableHeaders());
	}

	@Test
	public void testGetTableRowData() {
		Object[] obj ={ 1, "Industry", 234.0, "Street", "39095","CHOP","Supp"};
		assertArrayEquals(obj, comp.getTableRowData());
	}

	@Test
	public void testEqualsObject() {
		Company ad=new Company();
		ad.setId(1);ad.setName("Industry");
		ad.setAddress("Street");
		ad.setPhnumber("39095");
		ad.setYearprofit(234);
		assertTrue(comp.equals(ad));
	}

	@Test
	public void testToString() {
		String str="Company [id=1, address=Street, name=Industry, phnumber=39095, yearprofit=234.0, rm=Supp, secorg=CHOP]";
		assertEquals(str, comp.toString());
	}
	@Test
	public void testUpdateWith() {
		Company c=new Company();
		c.setName("OOO");
		c.setYearprofit(235);
		c.setAddress("GrowStreet");
		c.setPhnumber("1235");
		comp.updateWith(c);
		assertEquals(comp.getName(), c.getName());
		assertEquals(comp.getYearprofit(), c.getYearprofit(),0.5);
		assertEquals(comp.getAddress(), c.getAddress());
		assertEquals(comp.getPhnumber(), c.getPhnumber());
		
	}
}
