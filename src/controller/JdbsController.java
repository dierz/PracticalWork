package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.TableModel;

import model.Company;
import model.Guard;
import model.Material;
import model.Product;
import model.Rms;
import model.Sm;
import model.Secorg;

public class JdbsController implements IController {

	MyConnector connector = new MyConnector();

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.IController#createDB()
	 */
	@Override
	public void createDB() {
		Connection conn = connector.getConnection();
		try {
			Statement st = conn.createStatement();
			st.addBatch("create table COMPANY (" + " ID integer generated always as identity,"
					+ " NAME varchar(255) default '' not null," + " YEARPROFIT float default 0 not null,"
					+ " ADDRESS varchar(255) default '' not null," + " PHNUMBER varchar(255) default '' not null,"
					+ " IDSECORG integer," + " IDRMS integer," + " primary key (ID)) ");
			st.addBatch("create table RMS (" + " ID integer generated always as identity,"
					+ " NAME varchar(255) default '' not null," + " SUPPLYSIZE integer default 0,"
					+ " IDMATERIAL integer," + " primary key (ID))");
			st.addBatch("create table PRODUCT (" + " ID integer generated always as identity,"
					+ " NAME varchar(255) default '' not null," + " AMOUNT integer,"
					+ " IDCOMPANY integer, IDSM integer," + " primary key (ID))");
			st.addBatch("create table MATERIAL (" + " ID integer generated always as identity,"
					+ " NAME varchar(255) default '' not null," + " DENSITY float, WEIGHT float,"
					+ " primary key (ID)) ");
			st.addBatch("create table GUARD (" + " ID integer generated always as identity,"
					+ " FIO varchar(255) default '' not null," + " AGE integer, EXP integer,"
					+ " RANK varchar(255) default '' not null," + " IDSECORG integer," + " primary key (ID)) ");
			st.addBatch("create table SECORG (" + " ID integer generated always as identity,"
					+ " NAME varchar(255) default '' not null," + " ADDRESS varchar(255) default '' not null,"
					+ " HEAD varchar(255) default '' not null," + " primary key (ID)) ");
			st.addBatch("create table SM (" + " ID integer generated always as identity,"
					+ " NAME varchar(255) default '' not null," + " ADDRESS varchar(255) default '' not null,"
					+ " WEBSITE varchar(255) default '' not null," + " primary key (ID)) ");
			st.addBatch("alter table COMPANY " + " add constraint FK_COMPANY_IDSECORG " + "	foreign key (IDSECORG)"
					+ " references SECORG (ID) on delete cascade");
			st.addBatch("alter table COMPANY " + " add constraint FK_COMPANY_RMS " + "	foreign key (IDRMS)"
					+ " references RMS (ID) on delete cascade");
			st.addBatch("alter table RMS " + " add constraint FK_RMS_IDMATERIAL " + "	foreign key (IDMATERIAL)"
					+ " references MATERIAL (ID) on delete cascade");
			st.addBatch("alter table PRODUCT " + " add constraint FK_PRODUCT_IDCOMPANY " + "	foreign key (IDCOMPANY)"
					+ " references COMPANY (ID) on delete cascade");
			st.addBatch("alter table PRODUCT " + " add constraint FK_PRODUCT_IDSM " + "	foreign key (IDSM)"
					+ " references SM (ID) on delete cascade");
			st.addBatch("alter table GUARD " + " add constraint FK_GUARD_IDSECORG " + "	foreign key (IDSECORG)"
					+ " references SECORG (ID) on delete cascade");

			st.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connector.closeConnection();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.IController#getModel(java.lang.String)
	 */
	@Override
	public JdbcTableModel getModel(String className) {
		String sql = null;
		if (className.equals("Company"))
			sql = "select COMPANY.ID, COMPANY.NAME, COMPANY.YEARPROFIT, COMPANY.ADDRESS, COMPANY.PHNUMBER,"
					+ " SECORG.NAME as SECORG, RMS.NAME as RMS from COMPANY, RMS, SECORG"
					+ " where COMPANY.IDSECORG=SECORG.ID and COMPANY.IDRMS=RMS.ID";
		else if (className.equals("Guard"))
			sql = "select GUARD.ID, GUARD.FIO, GUARD.AGE, GUARD.EXP, GUARD.RANK, SECORG.NAME as SECORG"
					+ " from GUARD, SECORG where GUARD.IDSECORG=SECORG.ID";
		else if (className.equals("Material"))
			sql = "select MATERIAL.ID, MATERIAL.NAME, MATERIAL.DENSITY, MATERIAL.WEIGHT" + " from MATERIAL";
		else if (className.equals("Product"))
			sql = "select PRODUCT.ID, PRODUCT.NAME, PRODUCT.AMOUNT, COMPANY.NAME as COMPANY, SM.NAME as SM"
					+ " from PRODUCT, SM, COMPANY where PRODUCT.IDCOMPANY=COMPANY.ID and PRODUCT.IDSM=SM.ID";
		else if (className.equals("RMS"))
			sql = "select RMS.ID, RMS.NAME, RMS.SUPPLYSIZE, MATERIAL.NAME as MATERIAL"
					+ " from RMS, MATERIAL where RMS.IDMATERIAL=MATERIAL.ID";
		else if (className.equals("SecOrg"))
			sql = "select SECORG.ID, SECORG.NAME, SECORG.ADDRESS, SECORG.HEAD" + " from SECORG";
		else if (className.equals("SM"))
			sql = "select SM.ID, SM.NAME, SM.ADDRESS, SM.WEBSITE" + " from SM";
		if (sql == null)
			return null;
		try {
			Connection conn = connector.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			return new JdbcTableModel(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connector.closeConnection();
		}
	}

	@Override
	public void add(Object obj) {

		if (obj == null)
			return;
		Connection conn = connector.getConnection();
		PreparedStatement pst = null;
		try {
			if (obj instanceof Company) {
				pst = conn.prepareStatement(
						"insert into COMPANY(NAME, YEARPROFIT, ADDRESS, PHNUMBER, IDSECORG, IDRMS) values(?,?,?,?,?,?)");
				pst.setString(1, ((Company) obj).getName());
				pst.setFloat(2, ((Company) obj).getYearprofit());
				pst.setString(3, ((Company) obj).getAddress());
				pst.setString(4, ((Company) obj).getPhnumber());
				pst.setInt(5, ((Company) obj).getSecorg().getId());
				pst.setInt(6, ((Company) obj).getRms().getId());
			} else if (obj instanceof Material) {
				pst = conn.prepareStatement("insert into MATERIAL(NAME, DENSITY, WEIGHT) values(?,?,?)");
				pst.setString(1, ((Material) obj).getName());
				pst.setFloat(2, ((Material) obj).getDensity());
				pst.setFloat(3, ((Material) obj).getWeight());
			} else if (obj instanceof Sm) {
				pst = conn.prepareStatement("insert into SM(NAME, ADDRESS, WEBSITE) values(?,?,?)");
				pst.setString(1, ((Sm) obj).getName());
				pst.setString(2, ((Sm) obj).getAddress());
				pst.setString(3, ((Sm) obj).getWebsite());
			} else if (obj instanceof Guard) {
				pst = conn.prepareStatement("insert into GUARD(FIO, AGE, EXP, RANK, IDSECORG) values(?,?,?,?,?)");
				pst.setString(1, ((Guard) obj).getFio());
				pst.setInt(2, ((Guard) obj).getAge());
				pst.setInt(3, ((Guard) obj).getExp());
				pst.setString(4, ((Guard) obj).getRank());
				pst.setInt(5, ((Guard) obj).getSecorg().getId());
			} else if (obj instanceof Product) {
				pst = conn.prepareStatement("insert into PRODUCT(NAME, AMOUNT, IDCOMPANY, IDSM) values(?,?,?,?)");
				pst.setString(1, ((Product) obj).getName());
				pst.setInt(2, ((Product) obj).getAmount());
				pst.setInt(3, ((Product) obj).getCompany().getId());
				pst.setInt(4, ((Product) obj).getSm().getId());
			} else if (obj instanceof Rms) {
				pst = conn.prepareStatement("insert into RMS(NAME, SUPPLYSIZE, IDMATERIAL) values(?,?,?)");
				pst.setString(1, ((Rms) obj).getName());
				pst.setInt(2, ((Rms) obj).getSupplysize());
				pst.setInt(3, ((Rms) obj).getMaterial().getId());
			} else if (obj instanceof Secorg) {
				pst = conn.prepareStatement("insert into SECORG(NAME, ADDRESS, HEAD) values(?,?,?)");
				pst.setString(1, ((Secorg) obj).getName());
				pst.setString(2, ((Secorg) obj).getAddress());
				pst.setString(3, ((Secorg) obj).getHead());
			}
			if (pst != null) {
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.closeConnection();
		}

	}

	public void edit(int id, Object obj) {
		if (obj == null)
			return;
		Connection conn = null;
		try {
			conn = connector.getConnection();
			PreparedStatement pst = null;
			if (obj instanceof Company) {
				pst = conn.prepareStatement(
						"update COMPANY set  NAME = ?, YEARPROFIT = ?, ADDRESS = ?, PHNUMBER = ? where ID = ?");
				pst.setString(1, ((Company) obj).getName());
				pst.setFloat(2, ((Company) obj).getYearprofit());
				pst.setString(3, ((Company) obj).getAddress());
				pst.setString(4, ((Company) obj).getPhnumber());
				pst.setInt(5, id);
			} else if (obj instanceof Guard) {
				pst = conn.prepareStatement("update GUARD set FIO = ?, AGE = ?, EXP = ?, RANK = ? where ID = ?");
				pst.setString(1, ((Guard) obj).getFio());
				pst.setInt(2, ((Guard) obj).getAge());
				pst.setInt(3, ((Guard) obj).getExp());
				pst.setString(4, ((Guard) obj).getRank());
				pst.setInt(5, id);
			} else if (obj instanceof Material) {
				pst = conn.prepareStatement("update MATERIAL set  NAME = ?, DENSITY = ?, WEIGHT = ? where ID = ?");
				pst.setString(1, ((Material) obj).getName());
				pst.setFloat(2, ((Material) obj).getDensity());
				pst.setFloat(3, ((Material) obj).getWeight());
				pst.setInt(4, id);
			} else if (obj instanceof Product) {
				pst = conn.prepareStatement("update PRODUCT set  NAME = ?, AMOUNT = ? where ID = ?");
				pst.setString(1, ((Product) obj).getName());
				pst.setInt(2, ((Product) obj).getAmount());
				pst.setInt(3, id);
			} else if (obj instanceof Rms) {
				pst = conn.prepareStatement("update RMS set  NAME = ?, SUPPLYSIZE = ? where ID = ?");
				pst.setString(1, ((Rms) obj).getName());
				pst.setInt(2, ((Rms) obj).getSupplysize());
				pst.setInt(3, id);
			} else if (obj instanceof Secorg) {
				pst = conn.prepareStatement("update SECORG set  NAME = ?, ADDRESS = ?, HEAD = ? where ID = ?");
				pst.setString(1, ((Secorg) obj).getName());
				pst.setString(2, ((Secorg) obj).getAddress());
				pst.setString(3, ((Secorg) obj).getHead());
				pst.setInt(4, id);
			} else if (obj instanceof Sm) {
				pst = conn.prepareStatement("update SM set  NAME = ?, ADDRESS = ?, WEBSITE = ? where ID = ?");
				pst.setString(1, ((Sm) obj).getName());
				pst.setString(2, ((Sm) obj).getAddress());
				pst.setString(3, ((Sm) obj).getWebsite());
				pst.setInt(4, id);
			}
			if (pst != null) {
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.closeConnection();
		}
	}

	public void delete(int id, String className) {
		Connection conn = null;
		try {
			conn = connector.getConnection();
			PreparedStatement pst = null;
			if (className.equals("Company"))
				pst = conn.prepareStatement("DELETE FROM COMPANY WHERE COMPANY.ID = ?");
			else if (className.equals("Guard"))
				pst = conn.prepareStatement("DELETE FROM GUARD WHERE GUARD.ID = ?");
			else if (className.equals("Material"))
				pst = conn.prepareStatement("DELETE FROM MATERIAL WHERE MATERIAL.ID = ?");
			else if (className.equals("Product"))
				pst = conn.prepareStatement("DELETE FROM PRODUCT WHERE PRODUCT.ID = ?");
			else if (className.equals("RMS"))
				pst = conn.prepareStatement("DELETE FROM RMS WHERE RMS.ID = ?");
			else if (className.equals("SecOrg"))
				pst = conn.prepareStatement("DELETE FROM SECORG WHERE SECORG.ID = ?");
			else if (className.equals("SM"))
				pst = conn.prepareStatement("DELETE FROM SM WHERE SM.ID = ?");
			if (pst != null) {
				pst.setInt(1, id);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connector.closeConnection();
		}
	}

	public TableModel doQuery1() {
		String sql = "select CLIENT.NAME as CLIENT," + " PLANET.NAME as PLANET, PLANET.TOTALAREA as PLANET_AREA,"
				+ " HOLD.AREA as HOLD_AREA from CLIENT,PLANET,HOLD" + " where CLIENT.IDHOLD = HOLD.ID "
				+ " and  HOLD.IDPLANET = PLANET.ID and PLANET.TOTALAREA < 5000";
		try {
			Connection conn = connector.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			return new JdbcTableModel(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connector.closeConnection();
		}
	}

	public TableModel doQuery2() {
		String sql = "select PLANET.NAME as PLANET_NAME,"
				+ " (SELECT COUNT(HOLD.ID) FROM HOLD WHERE PLANET.ID=HOLD.IDPLANET) as COUNT_HOLD, (SELECT COUNT(CLIENT.ID) FROM CLIENT, HOLD WHERE HOLD.ID=CLIENT.IDHOLD and PLANET.ID=HOLD.IDPLANET) as COUNT_CLIENT FROM PLANET";
		try {
			Connection conn = connector.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			return new JdbcTableModel(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			connector.closeConnection();
		}
	}

}
