package ru.service.db.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {


  @Override
  public void init() throws ServletException {

  }

  // в случае GET-запроса следует просто отдать страницу home
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String param = req.getParameter("param");

    String filePath = param;
    File downloadFile = new File(filePath);
    FileInputStream inStream = new FileInputStream(downloadFile);

    // if you want to use a relative path to context root:
    String relativePath = getServletContext().getRealPath("");
    System.out.println("relativePath = " + relativePath);

    // obtains ServletContext
    ServletContext context = getServletContext();

    // gets MIME type of the file
    String mimeType = context.getMimeType(filePath);
    if (mimeType == null) {
      // set to binary type if MIME mapping not found
      mimeType = "application/octet-stream";
    }
    System.out.println("MIME type: " + mimeType);

    // modifies response
    resp.setContentType(mimeType);
    resp.setContentLength((int) downloadFile.length());

    // forces download
    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
    resp.setHeader(headerKey, headerValue);

    // obtains response's output stream
    OutputStream outStream = resp.getOutputStream();

    byte[] buffer = new byte[4096];
    int bytesRead = -1;

    while ((bytesRead = inStream.read(buffer)) != -1) {
      outStream.write(buffer, 0, bytesRead);
    }

    inStream.close();
    outStream.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect(req.getContextPath() + "/directory");
  }
}
