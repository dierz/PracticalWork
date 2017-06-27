<%@page import="webview.SelectTable"%>
<%@page import="model.Sm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SMList</title>
</head>
<body>
	<center>
		SMs: <select size="10" name="SMId">
			<%
				Class<Sm> h = Sm.class;
				for (Object x : SelectTable.getController().getObjectList(h)) {
					Sm obj = (Sm) x;
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