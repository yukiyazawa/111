package study.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.bean.DataAccessBean;
import study.bean.UserInfo;
import study.erros.DuplicateEmailException;

@SuppressWarnings("serial")
@WebServlet(name="RegistServlet", urlPatterns="/regist")
public class RegistServlet extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		try {
			//ユーザーが入力した値を取得する
			request.setCharacterEncoding("utf-8");
				String name=request.getParameter("name");
				String yomi=request.getParameter("yomi");
				String zip=request.getParameter("zip");
				String address=request.getParameter("address");
				String tel=request.getParameter("tel");
				String email=request.getParameter("email");

			//名前とアドレスはnot nullなので、入力されていない場合「入力してください」と表示する
				if(name==null ||name.length()<1) {
					request.setAttribute("message", "名前を入力してください");
					RequestDispatcher rd =request.getRequestDispatcher("/regist.jsp");
					rd.forward(request, response);
					return;
			}
				if(email==null||email.length()< 1) {
					request.setAttribute("message", "メールアドレスを入力してください。");
					RequestDispatcher rd= request.getRequestDispatcher("/regist.jsp");
					rd.forward(request, response);
					return;
			}

			//UserInfoオブジェクトにユーザーが入力した値を格納する
           UserInfo userInfo = new UserInfo();
           	userInfo.setName(name);
           	userInfo.setYomi(yomi);
           	userInfo.setZip(zip);
           	userInfo.setAddress(address);
           	userInfo.setTel(tel);
           	userInfo.setEmail(email);

           	DataAccessBean dab = new DataAccessBean();
           	dab.registUserInfo(userInfo);
           	response.sendRedirect(request.getContextPath()+"/findall");

		}catch (SQLException e) {
			e.printStackTrace();

			// 例外が発生した場合はエラーページへ遷移する
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request,
            response);
			}
			catch(DuplicateEmailException e) {
			request.setAttribute("messege","このメールアドレスはすでに登録されています。");
			RequestDispatcher rd = request.getRequestDispatcher("/regist.jsp");
			rd.forward(request, response);

		}
     }
  }

