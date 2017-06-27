<%@page import="webview.SelectTable"%>
<%@page import="model.Company"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CompanyList</title>
</head>
<body>
	<center>
		Companies: <select size="10" name="CompanyId">
			<%
				Class<Company> h = Company.class;
				for (Object x : SelectTable.getController().getObjectList(h)) {
					Company obj = (Company) x;
			%>
			<option>
				<%=obj.getId()+" Name="+obj.getName()%></option>
			<%
				}
			%>
		</select>
	</center>
</body>
</html>