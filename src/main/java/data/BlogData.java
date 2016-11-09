package data;//

import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import model.BlogPost;
import model.User;

import java.io.*;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspApplicationContext;

//Created by DaMasterHam on 25-09-2016.
//
public class BlogData
{
    private static final String db = "/WEB-INF/blogposts.txt";


    public static List<BlogPost> getAllPosts(ServletContext context)
    {
        List<BlogPost> list = new ArrayList<BlogPost>();

        try
        {
            InputStream in = context.getResourceAsStream(db);

            //File f = new File(db);

            Scanner s = new Scanner(in);
            while (s.hasNextLine())
            {
                String line = s.nextLine();
                String[] post = line.split(";");

                list.add(new BlogPost(post[0],post[1],post[2]));
            }

        } catch (Exception e)
        {
            System.out.print(e.getMessage());
        }

        return list;
    }

    public static void writePost(User user, String title, String content, ServletContext context) throws IOException
    {
        String format = "\n" + user.getName() + ";" + title + ";" + content;

        BufferedWriter out;
        try
        {
            out = new BufferedWriter(new FileWriter(context.getRealPath(db), true ));

            out.write(format);
            out.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

//        File tmpdir = (File)context.getAttribute("javax.servlet.context.tempdir");
//
//        if(null == tmpdir)
//            throw new IllegalStateException("Container does not provide a temp dir"); // Or handle otherwise
//
//        File targetFile = new File(tmpdir, "my-temp-filename.txt");
//        BufferedWriter out = null;
//
//        try {
//            out = new BufferedWriter(new OutputStreamWriter());
//
//            // write to output stream
//        } finally {
//            if(null != out) try { out.close(); }
//            catch (IOException ioe) { /* log this */ }
//        }


    }

//    public static void writePostAppEnigne(User user, String title, String content, ServletContext context) throws IOException
//    {
//        GcsService service = GcsServiceFactory.createGcsService();
//        String format = user.getName() + ";" + title + ";" + content;
//
//        GcsFileOptions opt = new GcsFileOptions.Builder()
//                .mimeType("text/txt")
//                .acl("public-read")
//                .addUserMetadata("user",user.getName())
//                .addUserMetadata("title", title)
//                .addUserMetadata("content", content)
//                .build();
//
//        GcsFilename fileName = new GcsFilename("technologystartup-143211.appspot.com", "blogposts.txt");
//
//        GcsOutputChannel channel = service.createOrReplace(fileName, opt);
//        PrintWriter writer = new PrintWriter(Channels.newWriter(channel, "UTF-8"));
//
//        writer.println(format);
//        writer.flush();
//
////
////        File f = new File(db);
////        BufferedReader in = new BufferedReader(new FileReader(db));
////
////        PrintWriter out = new PrintWriter(db);
////        out.print(in);
////        out.println(format);
//
//    }
}
