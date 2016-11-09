package controller;//

import data.BlogData;
import model.BlogPost;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//Created by DaMasterHam on 25-09-2016.
//
public class Blog extends HttpServlet
    {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null)
        {
            // Show blog post list with titles
            List<BlogPost> allPosts = BlogData.getAllPosts(this.getServletContext());

            //allPosts.add(new BlogPost("Test","Test","Test"));

            if (user != null && user.getRole().equals("admin"))
            {
                // list with r/w
                String texbox = "<div id='blog-post'>"
                                + "<h2>Make a blog post</h2>"
                                + "<form method='POST' action='Blog'>"
                                + "<input type='text' name='title'></input>"
                                + "<textarea name='content'></textarea>"
                                + "<button type='submit'>Post</button>"
                                + "</form></div";
                request.setAttribute("adminstuff", texbox);
            }


            request.setAttribute("posts",allPosts);
            request.getRequestDispatcher("WEB-INF/jsp/blogposts.jsp").forward(request,response);
        }

        else
        {
            response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        User user = (User)request.getSession().getAttribute("user");


        if (user != null && user.getRole().equals("admin"))
        {
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            if (!title.equals("") && !content.equals(""))
            {
                BlogData.writePost(user,title,content, getServletContext());
            }
        }

        response.sendRedirect("/Blog");
    }
}
