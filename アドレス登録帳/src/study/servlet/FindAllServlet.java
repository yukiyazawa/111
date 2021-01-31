package study.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.bean.DataAccessBean;
import study.bean.UserInfo;

@SuppressWarnings("serial")
@WebServlet(name="FindAllServlet",urlPatterns="/findall")
public class FindAllServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {

		try {
		DataAccessBean dab= new DataAccessBean();
    	Collection<UserInfo>userInfoList = dab.findAllUserInfo();
    	//入力された情報をuaserInfoLIstという名前のスコープに格納する
    	request.setAttribute("userInfoList", userInfoList);
    	//listページに遷移
    	RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/list.jsp");
		rd.forward(request, response);
		}catch (SQLException e) {
		//SQLExceptionをキャッチしたらerrorページに遷移
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request,response);
		}
	}
}





