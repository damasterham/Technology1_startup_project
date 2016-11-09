package bussinesslogic;//

import data.UserData;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

//Created by DaMasterHam on 19-09-2016.
//
public class UserLogic
{
    public static void login(String username, String password, HttpServletRequest request, ServletContext context)
    {
        User user;


        user = UserData.getUser(username, context);

        if (user != null)
        {
            if (user.getPassword().equals(password))
            {
                user.setPassword("Secret");
                request.getSession().setAttribute("user", user);
            }
        }
    }

//    public static void login(String username, String password, HttpServletRequest request)
//    {
//        User user;
//
//        String nameHash = Crypto.encrypt(username);
//        String passHash;
//
//        user = UserData.getUser(nameHash);
//
//        if (user != null)
//        {
//            passHash = Crypto.hash(password, user.getSalt());
//
//            if (user.getPassword().equals(passHash))
//            {
//                user.setName(Crypto.decrypt(user.getName()));
//
//                request.getSession().setAttribute("user", user);
//
//            }
//        }
//    }
}
