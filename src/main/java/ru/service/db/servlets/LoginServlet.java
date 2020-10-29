package ru.service.db.servlets;

import ru.service.db.dao.DBService;
import ru.service.db.dao.UserProfile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;




@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private String passwordForAllUsers = "qwerty007";

  private DBService dbService = new DBService();

  @Override
  public void init() throws ServletException {

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter("user_name");
    String password = req.getParameter("password");



    UserProfile user = dbService.getUser(userName);

    if (dbService.getUser(userName) != null && password.equals(user.getPassword())) {
      // создаем для него сессию
      HttpSession session = req.getSession();
      // кладем в атрибуты сессии атрибут user с именем пользователя
      session.setAttribute("UserName",req.getParameter("user_name"));
      session.setAttribute("user", user);
      // перенаправляем на страницу home
      req.getServletContext().getRequestDispatcher("/directory").forward(req, resp);
    } else {
      resp.sendRedirect(req.getContextPath() + "/login");
    }
  }
}
