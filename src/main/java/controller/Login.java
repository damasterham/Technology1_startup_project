package controller;

import bussinesslogic.UserLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by faisaljarkass on 19/08/16.
 * mvn appengine:update
 * mvn appengine:devserver
 */
public class Login extends HttpServlet
{

    private static Logger logger = Logger.getLogger(Login.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.log(Level.INFO, "doPost start...");
        logger.log(Level.INFO, "Username: " + request.getParameter("username"));
        logger.log(Level.INFO, "Password: " + request.getParameter("password"));
        logger.log(Level.INFO, "Checkbox: " + request.getParameter("rememberMe"));

        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserLogic.login(username, password, request, this.getServletContext());

        if (request.getSession().getAttribute("user") != null)
        {
//            request.getRequestDispatcher("/result.jsp");

            PrintWriter out = response.getWriter();
            String title = "Using POST Method to Read Form Data";
            String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
                            "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<ul>\n" +
                    "  <li><b>Username</b>: "
                    + request.getParameter("username") + "\n" +
                    "  <li><b>Password</b>: "
                    + request.getParameter("password") + "\n" +
                    "</ul>\n" +
                    "</body></html>");
        }

        else
        {
            response.sendRedirect("index.html");
            logger.log(Level.INFO, "No user with that username and pas" +
                    "" +
                    "sword");
        }


        logger.log(Level.INFO, "doPost ended...");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.log(Level.INFO, "doGet started...");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Using POST Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>Username</b>: "
                + request.getParameter("username") + "\n" +
                "  <li><b>Password</b>: "
                + request.getParameter("password") + "\n" +
                "</ul>\n" +
                "</body></html>");
        logger.log(Level.INFO, "doGet ended...");
    }
}
