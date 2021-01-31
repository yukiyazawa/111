package study.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.bean.DataAccessBean;

@SuppressWarnings("serial")
@WebServlet(name="DeleteServlet" , urlPatterns="/delete")
public class DeleteServlet extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest request,
       HttpServletResponse response) throws ServletException, IOException {
		try {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		DataAccessBean  dad = new DataAccessBean();
		dad .deleteUserInfo(email);
		response.sendRedirect(request.getContextPath()+"/findall");
	   }catch (SQLException e) {
            e.printStackTrace();
            // 例外が発生した場合はエラーページへ遷移する
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
	}
}