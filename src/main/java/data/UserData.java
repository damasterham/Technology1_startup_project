package data;//

import controller.Login;
import model.User;

import javax.sound.sampled.LineEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//Created by DaMasterHam on 19-09-2016.
//
public class UserData
{

    private static Logger logger = Logger.getLogger(Login.class.getName());

    private static final String db = "users.txt";

    public static User getUser(String username)
    {
        String msg = "";
        File folder = new File("/'");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                msg += "File " + listOfFiles[i].getName();
            } else if (listOfFiles[i].isDirectory()) {
                msg += "Directory " + listOfFiles[i].getName();
            }
        }

        logger.log(Level.INFO, msg);

        User user;

        user = null;

        try
        {
            File f = new File(db);

            Scanner s = new Scanner(f);
            while (s.hasNextLine())
            {
                String line = s.nextLine();
                String[] fields = line.split(";");

                if (fields[0].equals(username))
                {
                    user = new User(fields[0], fields[2]);
                    user.setPassword(fields[1]);

                    s.close();

                    return user;
                }
            }

        } catch (FileNotFoundException e)
        {
            System.out.print(e.getMessage());
        }

        return user;
    }

    public static User getUserWithCredentials(String username, String password)
    {
        User user;

        user = null;

        try
        {
            File f = new File(db);

            Scanner s = new Scanner(f);
            while (s.hasNextLine())
            {
                String line = s.nextLine();
                String[] userAndPass = line.split(";");

                if (userAndPass[0].equals(username) && userAndPass[1].equals(password))
                {
                    user = new User(userAndPass[0], userAndPass[2]);

                    s.close();

                    return user;
                }
            }

        } catch (FileNotFoundException e)
        {
            System.out.print(e.getMessage());
        }

        return user;
    }


}
