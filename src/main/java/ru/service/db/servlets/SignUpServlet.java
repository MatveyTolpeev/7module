package ru.service.db.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.service.db.dao.DBService;
import ru.service.db.dao.UserProfile;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

  private DBService dbService = new DBService();

  private static Logger logger = LoggerFactory.getLogger(SignUpServlet.class);

  @Override
  public void init() {

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    logger.info("Creating User");
    RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp");
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter("user_name");
    String password = req.getParameter("password");
    UserProfile userProfile = new UserProfile(userName, password);
    dbService.addUser(userProfile);
      logger.info("User Created");

    doGet(req, resp);
  }
}

