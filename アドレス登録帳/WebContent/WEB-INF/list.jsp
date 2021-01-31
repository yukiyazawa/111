
<%@ page pageEncoding="utf-8"
 contentType="text/html; charset=utf-8"%>

<!--JSTLの利用-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>一覧</title>
</head>
<body>
一覧
<table border="1">
	<tr>
		<th>名前</th>
		<th>読み</th>
		<th>郵便番号</th>
		<th>住所</th>
		<th>電話番号</th>
		<th>メールアドレス</th>
		<th>削除</th>
	</tr>

<!--userinfoに格納していたものを取り出す-->
	<c:forEach var = "userInfo" items="${userInfoList }">
	<tr>
		<td>${userInfo.name }</td>
		<td>${userInfo.yomi }</td>
		<td>${userInfo.zip }</td>
		<td>${userInfo.address }</td>
		<td>${userInfo.tel }</td>
		<td>${userInfo.email }</td>
		<td>
		 <form action="${pageContext.request.contextPath}/delete"method="post" >
		<input type="hidden" name="email" value="${userInfo.email}"/>
				<input type="submit" value="消去"/>
	     </form>
       </td>
   	</tr>
    </c:forEach>

</table>
<a href="${pageContext.request.contextPath }/regist.jsp">新規登録ページへ</a>
</body>
</html>
