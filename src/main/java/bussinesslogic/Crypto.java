package bussinesslogic;//

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

//Created by DaMasterHam on 03-10-2016.
//
public class Crypto
{
    public static final String KEY =        "WubbalubbaDubDub"; // 16 bytes key
    public static final String IV =         "ShowMeWhatYouGot"; // 16 bytes IV

    public static String encrypt(String key, String initVector, String value)
    {
        try
        {
            byte[] ivBytes = initVector.getBytes("UTF-8");
            byte[] keyBytes = key.getBytes("UTF-8");

            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            // Google app engine 1.9.4 has a problem with Base64 class (NoClassDefFoundError)
            System.out.println("Encrypted string: " + Base64.encodeBase64(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted)
    {

        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String value)
    {
        return encrypt(KEY, IV, value);
    }

    public static String decrypt(String encrypted)
    {
        return decrypt(KEY, IV, encrypted);
    }

    public static String hash(String value, String salt)
    {
        return encrypt(value+salt);
    }

    public static String[] hashWhat(String value, String salt)
    {
        try
        {
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(value.toCharArray(), salt.getBytes(),65536,128);
            SecretKey key = keyFac.generateSecret(spec);
            byte[] result = key.getEncoded();
            return new String[]{ new String(result), salt };

        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}