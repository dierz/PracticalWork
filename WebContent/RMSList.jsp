<%@page import="webview.SelectTable"%>
<%@page import="model.Rms"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RMSList</title>
</head>
<body>
	<center>
		Materials: <select size="10" name="RMSId">
			<%
				Class<Rms> h = Rms.class;
				for (Object x : SelectTable.getController().getObjectList(h)) {
					Rms obj = (Rms) x;
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