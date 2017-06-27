<%@page import="webview.SelectTable"%>
<%@page import="model.Secorg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SecOrgList</title>
</head>
<body>
	<center>
		SecOrgs: <select size="10" name="SecOrgId">
			<%
				Class<Secorg> h = Secorg.class;
				for (Object x : SelectTable.getController().getObjectList(h)) {
					Secorg obj = (Secorg) x;
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