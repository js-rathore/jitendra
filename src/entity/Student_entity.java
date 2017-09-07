/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Navneet
 */
public class Student_entity
{
    private int id;
    private String name;
    private String address;
    private long con;
    private int age;
   
    private String photo;
   





    /**
     * 
     */
    public Student_entity()
    {
        this.id = 0;
        this.name = "";
        this.address ="";
        this.con = 0;
        this.age =0;
        this.photo="";
    }

    
    




    /**
     * 
     * @param id
     * @param name
     * @param address
     * @param con
     * @param age
     * @param photo
     */
    public Student_entity(int id, String name, String address, long con, int age,String photo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.con = con;
        this.age = age;
        this.photo= photo;
    }

    /**
     * 
     * @return
     */
    public String getaddress() {
        return address;
    }//1

    /**
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }//2


    /**
     * 
     * @return
     */
    public long getcon() {
        return con;
    }//3

    /**
     * 
     * @param dd
     */
    public void setcon(long dd) {
        this.con = dd;
    }//4

    /**
     * 
     * @return
     */
    public int getage() {
        return age;
    }//5

    /**
     * 
     * @param age
     */
    public void setage(int age) {
        this.age = age;
    }//6

    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }//7

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }//8

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }//9

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }//10

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
