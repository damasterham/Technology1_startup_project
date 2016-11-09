package model;//

import java.io.Serializable;

//Created by DaMasterHam on 19-09-2016.
//
public class User implements Serializable
{
    private String name;
    private String password;
    private String salt;
    private String role;

    public User(String name, String password, String salt, String role)
    {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    public User(String name, String role)
    {
        this.name = name;
        this.role = role;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }
}
