
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ログイン</title>
</head>
<body>
${ message }
<form action="${ pageContext.request.contextPath }/login" method="POST">
<table>
	<tr>
		<td>ユーザ名</td>
		<td><input type="text" name="userName" /></td>
	</tr>
	<tr>
		<td>パスワード</td>
		<td><input type="password" name="password" /></td>
	</tr>
</table>
<input type="submit" value="ログイン" /></form>
</body>
</html>
