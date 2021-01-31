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


@SuppressWarnings("serial")
@WebServlet(name="LoginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
             //ユーザーフォームの入力値を取得
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            DataAccessBean dab = new DataAccessBean();
            // DataAccessBeanのauthenticationメソッドに入力値を渡して呼び出す
            if (dab.authentication(userName, password)) {
            //ユーザーネーム、パスワードが共に正しければ新規登録フォームに遷移する
                RequestDispatcher rd = request
                        .getRequestDispatcher("/regist.jsp");
                rd.forward(request, response);
            } else {
             //正しくない場合は「間違っています」と表示
                request.setAttribute("message", "ユーザ名またはパスワードが間違っています");
                // /login.jsp にForwardする
                RequestDispatcher rd = request
                        .getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}