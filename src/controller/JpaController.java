package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Company;
import model.Guard;
import model.IModel;
import model.Material;
import model.Product;
import model.Rms;
import model.Secorg;
import model.Sm;

public class JpaController implements IController {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("practic");

	public List getObjectList(Class clazz) {
		EntityManager em = emf.createEntityManager();
		String queryName = clazz.getSimpleName() + "." + "findAll";
		List list = em.createNamedQuery(queryName).getResultList();
		em.close();
		return list;
	}
	@Override
	public void add(Object obj) {
		Class clazz = obj.getClass();
		Object st=null;
		if (exist((IModel) obj))
			return;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("practic");
		EntityManager em = emf.createEntityManager();
		if (obj instanceof Company) {
			Company comp=new Company();
			comp.setName(((Company) obj).getName());
			comp.setYearprofit(((Company) obj).getYearprofit());
			comp.setAddress(((Company) obj).getAddress());
			comp.setPhnumber(((Company) obj).getPhnumber());
			comp.setSecorg(((Company) obj).getSecorg());
			comp.setRm(((Company) obj).getRm());
			st=(Object)comp;
		} else if (obj instanceof Guard) {
			Guard g=new Guard();
			g.setFio(((Guard) obj).getFio());
			g.setAge(((Guard) obj).getAge());
			g.setExp(((Guard) obj).getExp());
			g.setRank(((Guard) obj).getRank());
			g.setSecorg(((Guard) obj).getSecorg());
			st=(Object)g;
		} else if (obj instanceof Material) {
			Material m=new Material();
			m.setName(((Material) obj).getName());
			m.setDensity(((Material) obj).getDensity());
			m.setWeight(((Material) obj).getWeight());
			m.setRms(((Material) obj).getRms());
			st=(Object)m;
		} else if (obj instanceof Product) {
			Product p=new Product();
			p.setName(((Product) obj).getName());
			p.setAmount(((Product) obj).getAmount());
			p.setCompany(((Product) obj).getCompany());
			p.setSm(((Product) obj).getSm());
			st=(Object)p;
		} else if (obj instanceof Rms) {
			Rms rms=new Rms();
			rms.setName(((Rms) obj).getName());
			rms.setSupplysize(((Rms) obj).getSupplysize());
			rms.setMaterial(((Rms) obj).getMaterial());
			st=(Object)rms;
		} else if (obj instanceof Secorg) {
			Secorg sec=new Secorg();
			sec.setName(((Secorg) obj).getName());
			sec.setAddress(((Secorg) obj).getAddress());
			sec.setHead(((Secorg) obj).getHead());
			st=(Object)sec;
		} else if (obj instanceof Sm) {
			Sm sm=new Sm();
			sm.setName(((Sm) obj).getName());
			sm.setAddress(((Sm) obj).getAddress());
			sm.setWebsite(((Sm) obj).getWebsite());
			st=(Object)sm;
		}
		if(st==null){return;}
		em.getTransaction().begin();
		em.persist(st);
		em.getTransaction().commit();
	}

	@Override
	public void edit(int id, Object obj) {
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = obj.getClass();
			if (exist((IModel) obj))
				return;
			Object editObj = em.find(clazz, id);
			em.getTransaction().begin();
			((IModel) editObj).updateWith(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	@Override
	public void delete(int id, String className) {
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = Class.forName("model." + className);
			Object delObj = em.find(clazz, id);
			em.getTransaction().begin();
			em.remove(delObj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	@Override
	public TableModel getModel(String className) {
		try {
			Class clazz = Class.forName("model." + className);
			IModel obj = (IModel) clazz.newInstance();
			String[] header = obj.getTableHeaders();
			List list = getObjectList(clazz);
			if (list == null || list.size() == 0)
				return new DefaultTableModel(null, header);
			Object[][] array = new Object[list.size()][header.length];
			int i = 0;
			for (Object s : list)
				array[i++] = ((IModel) s).getTableRowData();
			return new DefaultTableModel(array, header);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean exist(IModel obj) {
		Class clazz = obj.getClass();
		List list = getObjectList(clazz);
		if (list != null && list.size() != 0)
			for (Object current : list)
				if (current.equals(obj))
					return true;
		return false;
	}
	public void operateObject(IModel obj, int operation) {
		
		if(operation==1){
			add(obj);
		}
		else if(operation==2){
			edit(obj.getId(),obj);
		}
		else if(operation==4){
			delete(obj.getId(),obj.getClass().getSimpleName());
		}
	}

}
