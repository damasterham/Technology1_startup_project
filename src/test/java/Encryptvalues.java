import bussinesslogic.Crypto;

import java.util.Random;

//Created by DaMasterHam on 06-10-2016.
//
public class Encryptvalues
{
    public static void main(String[] args)
    {
        Random r = new Random();
        byte[] salt = new byte[16*6];

        String[] names = new String[6];
        String[][] passSalt = new String[6][2];
        for (int i = 1; i <= 5; i++)
        {
            names[i] = Crypto.encrypt("dude" + i);
            r.nextBytes(salt);

            passSalt[i][0] = Crypto.hash("pass"+i, "salt"+i);
            passSalt[i][1] = "salt"+i;
        }
        System.out.println(names);
    }
}
