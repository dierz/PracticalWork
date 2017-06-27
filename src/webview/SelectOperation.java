package webview;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectOperation
 */
@WebServlet("/SelectOperation")
public class SelectOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		operation = Integer.parseInt(request.getParameter("operation"));
		String tableClassName = SelectTable.className;
		String formName = "";
		if( operation == 4)
			formName = "getId.html";
		else if(tableClassName.equals("Company")){
			if(operation==1)
				formName = "addCompany.html";
			else if( operation==2)
				formName = "editCompany.html";
		}
		else if(tableClassName.equals("Guard")){
			if(operation==1)
				formName = "addGuard.html";
			else if( operation==2)
				formName = "editGuard.html";
		}
		else if(tableClassName.equals("Material")){
			if(operation==1)
				formName = "addMaterial.html";
			else if( operation==2)
				formName = "editMaterial.html";
		} else if(tableClassName.equals("Product")){
			if(operation==1)
				formName = "addProduct.html";
			else if( operation==2)
				formName = "editProduct.html";
		} else if(tableClassName.equals("Rms")){
			if(operation==1)
				formName = "addRMS.html";
			else if( operation==2)
				formName = "editRMS.html";
		} else if(tableClassName.equals("Secorg")){
			if(operation==1)
				formName = "addSecOrg.html";
			else if( operation==2)
				formName = "editSecOrg.html";
		} else if(tableClassName.equals("Sm")){
			if(operation==1)
				formName = "addSM.html";
			else if( operation==2)
				formName = "editSM.html";
		}
		request.getRequestDispatcher(formName).forward(request, response);
}	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static int operation;
	
	
}
