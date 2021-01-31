<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<title>新規登録</title>
</head>
<link rel="stylesheet" href="stylesheet1.css">
<body>
	<a>新規登録</a>
	<br>
	<br> ${ message }
	<br>
	<br>

<!-- 入力フォームを作る　ここに値を入力してもらう -->
	<form action="${ pageContext.request.contextPath }/regist" method="post">
		<table>
			<tr>
				<th>名前</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>読み</th>
				<td><input type="text" name="yomi" /></td>
			</tr>
			<tr>
				<th>郵便番号</th>
				<td><input type="text" name="zip" /></td>
			</tr>
			<tr>
				<th>住所</th>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><input type="text" name="tel" /></td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td><input type="text" name="email" /></td>
			</tr>
		</table>
		<input type="submit" value="登録" />
	</form>

<!-- 一覧に遷移 -->
	<br>
	<a href="${ pageContext.request.contextPath }/findall">一覧ページへ</a>
</body>
</html>
