<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BELibrary</title>
	<link type="text/css" rel="stylesheet" href="CSS/styleFormat.css" />
	<link rel="shortcut icon" href="Images/Library.ico" />
	<script type="text/javascript" src="javascript/script.js"></script>
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	%>
	<div class="divLogin">
		<div class="divTitleLog">
		</div>
		
		<div class="divFormLog">
			<p>Chào mừng bạn tới với BELibrary</p>
			<form method="post" action="UserController">
				<table>
					<tbody>
						<tr>
							<td>Người sử dụng: </td>
							<td>
								<input id="idName" type="text" size="20" name="nameLog" value="${namePredefine}" />
								<a class="notifyLogin" id="nameNotify"></a>
							</td>
						</tr>
						
						<tr>
							<td>Mật khẩu: </td>
							<td>
								<input id="idPass" type="password" size="20" name="passLog" />
								<a class="notifyLogin" id="passNotify"></a>
							</td>
						</tr>
						
						<tr>
							<td>
								<input type="hidden" value="login" name="typeUser" />
							</td>
							<td align="right"><input onclick="return checkEmptyById('idName', 'nameNotify', '  *Làm ơn nhập tên người sử dụng') && checkEmptyById('idPass', 'passNotify', '   *Làm ơn nhập mật khẩu');" type="submit" value="Đăng nhập" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<p id="idError" class="${styleNotify}">${contentNotify}</p>
		</div>
	</div>
	
	<script type="text/javascript">
		checkOnStartup('idName', 'idError');
	</script>
</body>
</html>