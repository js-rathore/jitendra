/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controler;

import entity.User_entity;
import java.io.File;
import javax.swing.JOptionPane;
import model.User_model;

/**
 *
 * @author Navneet
 */

public class User_control
{
     
    private int first_flag=0;//for checking file is created or not
  




 /**
 *to checkXmlFile whether d xml files r created or not...
 *
 *
     * @param SPath 
     */
 public void checkXmlFile(String SPath)
    {
        try
        {
            File f=new File(SPath+"User.xml");

            if(!f.exists())
            {
                if(User_model.createFiles(SPath))
                {
                first_flag=1;//newly created
                }
            }
        }
        catch(Exception E)
        {
           JOptionPane.showMessageDialog(null,E.getMessage()+E.toString(), "Exception.....", JOptionPane.INFORMATION_MESSAGE);
        }
 }

 /**
  * 
  * @param name
  * @param SPath
  * @return
  */
 public boolean chechEntry(String name,String SPath)
    {
         boolean status = false;

         for(String i:User_model.AllUserKeys)
         {
          if(i.compareTo(name)==0)
          {
              status=true;
              break;
          }
         }
         return status;
 }

 /**
  * 
  * @param name
  * @param SPath
  * @return
  */
 public boolean addKey(String name,String SPath)
    {
        boolean status=false;
        status=User_model.addKey(name, SPath);
        return status;
    }

 /**
  * 
  * @param name
  * @param SPath
  * @return
  */
 public boolean deleteKey(String name,String SPath)
 {
     boolean status=false;
     status=User_model.deleteKey(name, SPath);
     return status;
 }

 /**
  * 
  * @param _S
  * @param SPath
  * @return
  */
 public  boolean  Add(User_entity _S,String SPath)
    {
     boolean SKey=false,SStud=false;

        checkXmlFile(SPath);
        boolean status=false;
        if (first_flag == 1)//XML file is newly created....
        {
            first_flag = 0;
            SKey=addKey(_S.getName(), SPath);
            SStud=User_model.add(_S, SPath);

            if(SKey==true && SStud==true)//both opearations are sucessful
            {
                //add to both arraylist
                //teger ID=_S.getId();
                User_model.AllUserKeys.add(_S.getName());
                User_model.AllUsers.add(new User_entity(_S.getName(),_S.getPassword(),_S.getrName(),_S.getPhoto()));
                System.out.println("first user is added");
                status=true;
            }

        } else//XML file file is already created....
        {
            if (chechEntry(_S.getName(), SPath))//checking for student already present or not
            {
                JOptionPane.showMessageDialog(null, "User entry already present!!", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            else//Student not present
            {
                SKey = addKey(_S.getName(), SPath);
                SStud = User_model.add(_S, SPath);
                if (SKey == true && SStud == true)//both operations are successful...
                {
                   //Integer ID=_S.getId();
                    User_model.AllUserKeys.add(_S.getName());
                   User_model.AllUsers.add(new User_entity(_S.getName(),_S.getPassword(),_S.getrName(),_S.getPhoto()));
                    System.out.println("user is added...");
                    status = true;
                }
            }
        }
        return status;
    }

 /**
  * 
  * @param _s
  * @param SPath
  * @return
  */
 public boolean delete(User_entity _s,String SPath)
    {
        boolean status=false,SKey=false,SStud=false;

        //int id=_s.getId();
        //Integer ID=id;
        System.out.println(SKey=deleteKey(_s.getName(), SPath));
        System.out.println(SStud=User_model.delete(_s, SPath));
       if(SKey==true && SStud==true)
       {
            User_model.AllUserKeys.remove(_s.getName());
            User_model.AllUsers.remove(_s);
            status=true;
            System.out.println("User deleted..");
       }

    return status;
    }

 /**
  * 
  * @param _s
  * @param SPath
  * @return
  */
 public boolean update(User_entity _s,String SPath)
 {
     boolean status=false,sadd=false,sdelete=false;
     sdelete=delete(_s,SPath);
     sadd=Add(_s,SPath);
     if(sdelete==true && sadd==true)
     {
         System.out.println("User entry is updated");
     }
     return status;
 }


}

