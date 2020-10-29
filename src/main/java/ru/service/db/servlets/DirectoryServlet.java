package ru.service.db.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/directory")
public class DirectoryServlet extends HttpServlet {


  @Override
  public void init() throws ServletException {

  }

  // в случае GET-запроса следует просто отдать страницу home
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    String name = req.getParameter("name");
    name = "D:\\\\users\\\\"+ (String)session.getAttribute("UserName");
    try {
      File file1 = new File(name);
      file1.mkdir();
    }
    catch(NullPointerException e)
    {

    }

    req.setAttribute("name", name);
    req.setAttribute("time", LocalDateTime.now());

    File folder = new File(name);

    req.setAttribute("back", "http://localhost:8080/directory-example-0.1/directory?name=" + folder.getParentFile().getAbsolutePath().replaceAll("\\\\","/"));

    List<FileDir> fileDirs = new ArrayList<>();

    for (File file : folder.listFiles()) {
      if (file.isDirectory()) {
        fileDirs.add(new FileDir(true, "http://localhost:8080/directory-example-0.1/directory?name=" + file.getAbsolutePath().replaceAll("\\\\","/"), file.getName()));
      }
      else {
        fileDirs.add(new FileDir(false, "http://localhost:8080/directory-example-0.1/download?param=" + file.getAbsolutePath().replaceAll("\\\\","/"), file.getName()));
      }
    }

    req.setAttribute("dirs", fileDirs);

    req.getServletContext().getRequestDispatcher("/jsp/directory.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect(req.getContextPath() + "/directory");
  }
}
