    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controler;


import entity.Student_entity;
import java.io.*;

import javax.swing.JOptionPane;
import model.Student_model;


/**
 *
 * @author admin
 */
public class Student_control 
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
            File f=new File(SPath+"Student.xml");
        
            if(!f.exists())
            {
                if(Student_model.createFiles(SPath))
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
  * @param id
  * @param SPath
  * @return
  */
 public boolean chechEntry(int id,String SPath)
    {
         boolean status = false;

         for(Integer i:Student_model.AllStudentKeys)
         {
          if(i==id)
          {
              status=true;
              break;
          }
         }
         return status;
 }

 /**
  * 
  * @param id
  * @param SPath
  * @return
  */
 public boolean addKey(int id,String SPath)
    {
        boolean status=false;
        status=Student_model.addKey(id, SPath);
        return status;
    }

 /**
  * 
  * @param id
  * @param SPath
  * @return
  */
 public boolean deleteKey(int id,String SPath)
 {
     boolean status=false;
     status=Student_model.deleteKey(id, SPath);
     return status;
 }

 /**
  * 
  * @param _S
  * @param SPath
  * @return
  */
 public  boolean  Add(Student_entity _S,String SPath)
    {
     boolean SKey=false,SStud=false;

        checkXmlFile(SPath);
        boolean status=false;
        if (first_flag == 1)//XML file is newly created....
        {
            first_flag = 0;
            SKey=addKey(_S.getId(), SPath);
            SStud=Student_model.add(_S, SPath);

            if(SKey==true && SStud==true)//both opearations are sucessful
            {
                //add to both arraylist
                Integer ID=_S.getId();
                Student_model.AllStudentKeys.add(ID);
                Student_model.AllStudents.add(new Student_entity(_S.getId(),_S.getName(),_S.getaddress(),_S.getcon(),_S.getage(),_S.getPhoto()));
                System.out.println("first student is added");
                status=true;
            }

        } else//XML file file is already created....
        {
            if (chechEntry(_S.getId(), SPath))//checking for student already present or not
            {
                JOptionPane.showMessageDialog(null, "Student entry already present!!", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            else//Student not present
            {
                SKey = addKey(_S.getId(), SPath);
                SStud = Student_model.add(_S, SPath);
                if (SKey == true && SStud == true)//both operations are successful...
                {
                   Integer ID=_S.getId();
                    Student_model.AllStudentKeys.add(ID);
                   Student_model.AllStudents.add(new Student_entity(_S.getId(),_S.getName(),_S.getaddress(),_S.getcon(),_S.getage(),_S.getPhoto()));
                    System.out.println("student is added...");
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
 public boolean delete(Student_entity _s,String SPath)
    {
        boolean status=false,SKey=false,SStud=false;

        int id=_s.getId();
        Integer ID=id;
        System.out.println(SKey=deleteKey(id, SPath));
        System.out.println(SStud=Student_model.delete(_s, SPath));
       if(SKey==true && SStud==true)
       {
            Student_model.AllStudentKeys.remove(ID);
            Student_model.AllStudents.remove(_s);
            status=true;
            System.out.println("Student deleted..");
       }
        
    return status;
    }

 /**
  * 
  * @param _s
  * @param SPath
  * @return
  */
 public boolean update(Student_entity _s,String SPath)
 {
     boolean status=false,sadd=false,sdelete=false;
     sdelete=delete(_s,SPath);
     sadd=Add(_s,SPath);
     if(sdelete==true && sadd==true)
     {
         System.out.println("student entry is updated");
     }
     return status;
 }


    
}
