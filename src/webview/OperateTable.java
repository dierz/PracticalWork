package webview;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Guard;
import model.IModel;
import model.Material;
import model.Product;
import model.Rms;
import model.Secorg;
import model.Sm;

/**
 * Servlet implementation class OperateTable
 */
@WebServlet("/OperateTable")
public class OperateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperateTable() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String className = SelectTable.className;
		int operation = SelectOperation.operation;
		IModel obj = null;
		try {
			obj = (IModel) Class.forName("model."+className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (operation == 4) {
			String id = request.getParameter("id");
			obj.setObjectId(Integer.parseInt(id));
		} else if (obj instanceof Company) {
			((Company) obj).setName(request.getParameter("CompanyName"));
			((Company) obj).setYearprofit(Float.parseFloat(request.getParameter("CompanyYearProfit")));
			((Company) obj).setAddress(request.getParameter("CompanyAddress"));
			((Company) obj).setPhnumber(request.getParameter("CompanyPhNumber"));
			if (operation==2) {
				int id = Integer.parseInt(request.getParameter("CompanyId"));
				obj.setObjectId(id);}
				else if(operation==1){
				Secorg sec=(Secorg)findObject(Secorg.class,Integer.parseInt(request.getParameter("SecOrgId")));
				((Company)obj).setSecorg(sec);
				Rms rms=(Rms)findObject(Rms.class,Integer.parseInt(request.getParameter("RMSId")));
				((Company)obj).setRm(rms);
					}
		} else if (obj instanceof Guard) {
			((Guard) obj).setFio(request.getParameter("GuardFIO"));
			((Guard) obj).setAge(Integer.parseInt(request.getParameter("GuardAge")));
			((Guard) obj).setExp(Integer.parseInt(request.getParameter("GuardExp")));
			((Guard) obj).setRank(request.getParameter("GuardRank"));
			if(operation==2){
				int id = Integer.parseInt(request.getParameter("GuardId"));
				obj.setObjectId(id);}
			else if(operation==1){
				Secorg sec=(Secorg)findObject(Secorg.class,Integer.parseInt(request.getParameter("SecOrgId")));
				((Guard)obj).setSecorg(sec);}
		} else if (obj instanceof Rms) {
			((Rms) obj).setName(request.getParameter("RMSName"));
			((Rms) obj).setSupplysize(Integer.parseInt(request.getParameter("RMSSize")));
			if(operation==2){
				int id = Integer.parseInt(request.getParameter("RMSId"));
				obj.setObjectId(id);}
			else if(operation==1){
				Material m=(Material)findObject(Material.class,Integer.parseInt(request.getParameter("MaterialId")));
				((Rms)obj).setMaterial(m);}
		} else if (obj instanceof Product) {
			((Product) obj).setName(request.getParameter("ProductName"));
			((Product) obj).setAmount(Integer.parseInt(request.getParameter("ProductAmount")));
			if(operation==2){
				int id = Integer.parseInt(request.getParameter("ProductId"));
				obj.setObjectId(id);}
			else if(operation==1){
				Sm sm=(Sm)findObject(Sm.class,Integer.parseInt(request.getParameter("SMId")));
				((Product)obj).setSm(sm);
				Company c=(Company)findObject(Company.class,Integer.parseInt(request.getParameter("CompanyId")));
				((Product)obj).setCompany(c);
		}
		} else if (obj instanceof Secorg) {
			((Secorg) obj).setName(request.getParameter("SecOrgName"));
			((Secorg) obj).setAddress(request.getParameter("SecOrgAddress"));
			((Secorg) obj).setHead(request.getParameter("SecOrgHead"));
			if (operation == 2) {
				int id = Integer.parseInt(request.getParameter("SecOrgId"));
				obj.setObjectId(id);
			} else if (operation == 1) {
			}
		} else if (obj instanceof Material) {
			((Material) obj).setName(request.getParameter("MaterialName"));
			((Material) obj).setDensity(Float.parseFloat(request.getParameter("MaterialDens")));
			((Material) obj).setWeight(Float.parseFloat(request.getParameter("MaterialWeight")));
			if (operation == 2) {
				int id = Integer.parseInt(request.getParameter("MaterialId"));
				obj.setObjectId(id);
			} else if (operation == 1) {
			}
		} else if (obj instanceof Sm) {
			((Sm) obj).setName(request.getParameter("SMName"));
			((Sm) obj).setAddress(request.getParameter("SMAddress"));
			((Sm) obj).setWebsite(request.getParameter("SMWebsite"));
			if (operation == 2) {
				int id = Integer.parseInt(request.getParameter("SMId"));
				obj.setObjectId(id);
			} else if (operation == 1) {
			}
		}
		SelectTable.getController().operateObject(obj, operation);
		request.setAttribute("model", SelectTable.getTableModel(className));
		request.setAttribute("className", className);
		request.getRequestDispatcher("showTable.jsp").forward(request, response);

	}
	private IModel findObject(Class clazz, int id) {
		IModel obj = null;
		for (Object x : SelectTable.getController().getObjectList(clazz)) {
			obj = (IModel) x;
			if (obj.getObjectId() == id)
				return obj;
		}
		return null;
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
