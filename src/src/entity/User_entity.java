/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Navneet
 */
public class User_entity
{
    private String name;
    private String password;
    private String rname;
    private String photo;
    /**
     * 
     */
    public User_entity()
    {
name="";
password="";
rname="";
photo="";
    }

    /**
     * 
     * @param name
     * @param password
     * @param rname
     * @param photo  
     */
    public User_entity(String name, String password,String rname,String photo)
    {
        this.name = name;
        this.password = password;
        this.rname = rname;
         this.photo= photo;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 
     * @return
     */
    public String getrName() {
        return rname;
    }

    /**
     *
     * @param rname 
     */
    public void setrName(String rname) {
        this.rname = rname;
    }
    /**
     * 
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 
     * @return
     */
    public String getPhoto() {
        return photo;
    }//11

    /**
     * 
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }//12
}
