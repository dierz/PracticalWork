<%@page import="webview.SelectTable"%>
<%@page import="model.Material"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MaterialList</title>
</head>
<body>
	<center>
		Materials: <select size="10" name="SecOrgId">
			<%
				Class<Material> h = Material.class;
				for (Object x : SelectTable.getController().getObjectList(h)) {
					Material obj = (Material) x;
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