<%@page import="javax.swing.table.TableModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>showTable</title>
</head>
<body>
	<%
		String className = (String) request.getAttribute("className");
		TableModel model = (TableModel) request.getAttribute("model");
	%>
	<center>
		<b> Таблиця <%=className%></b>
		<table border="1">
			<tr>
				<%
					int nCol = model.getColumnCount();
					for (int i = 0; i < nCol; i++) {
						String h = model.getColumnName(i);
				%>
				<th width="100"><%=h%></th>
				<% } %>
			</tr>
			<%
				int nRow = model.getRowCount();
				for (int r = 0; r < nRow; r++) {
			%>
			<tr>
				<%
					for (int j = 0; j < nCol; j++) {
							String str = model.getValueAt(r, j).toString();
				%>
				<td width="100" align="center"> <%=str%></td>
				 <% } %>
			</tr>
			<% } %>
		</table>
</body>
</html>
