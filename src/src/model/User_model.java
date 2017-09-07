/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


//import java.io.File;
//import java.util.ArrayList;
//import javax.xml.parsers.DocumentBuilderFactory;
import entity.User_entity;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import Martial_Art_xml_Entity.Studemt_entity;
import java.util.ArrayList;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Navneet
 */
public class User_model
{
    /**
     * 
     */
    public static ArrayList<User_entity> AllUsers = new ArrayList<User_entity>();
    /**
     * 
     */
    public static ArrayList<String> AllUserKeys=new ArrayList<String>();
    //private static Studemt_entity s = new Studemt_entity();


    /**
     * 
     * @param SPath
     * @return
     */
    public static boolean createFiles(String SPath)
    {
        boolean status=false;
        try
        {
          File f=new File(SPath);
         //create file
            f.createNewFile();
          //JOptionPane.showMessageDialog(null,"File created...2)", "Message", JOptionPane.INFORMATION_MESSAGE);

          //parse n Create Root Node
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();

          //New documents
            Document UserXml =  db.newDocument();
            Document UserKeyXml =  db.newDocument();

          //Root UserXml
            Element rootnd = (Element) UserXml.createElement("User_information");
            UserXml.appendChild(rootnd);

//            Element UserNd = (Element) UserXml.createElement("User");
//
//           //------------------using attributes
//            //String id =String.valueOf(_s.getId());
//            //String contNo=String.valueOf(_s.getCon_no());
//
//            //default
//            UserNd.setAttribute("name","root");
//            UserNd.setAttribute("password","root");
//
//            rootnd.appendChild(UserNd);

          //Root User Key
            Element rootKey = (Element) UserKeyXml.createElement("User_Key_information");
            UserKeyXml.appendChild(rootKey);




          //Saving..UserXml
            Transformer transformer1 = TransformerFactory.newInstance().newTransformer();
            transformer1.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result1 = new StreamResult(SPath+"User.xml");
            DOMSource source1 = new DOMSource(UserXml);
            transformer1.transform(source1, result1);

          //Saving..UserKeyXml
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            transformer2.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result2 = new StreamResult(SPath+"UserKey.xml");
            DOMSource source2 = new DOMSource(UserKeyXml);
            transformer2.transform(source2, result2);

            status=true;
        }
        catch(Exception ex)
        {
            status=false;
            JOptionPane.showMessageDialog(null,"Exception in File creation......", "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        return status;
    }

    /**
     * 
     * @param _u
     * @param SPath
     * @return
     */
    public static boolean delete(User_entity _u,String SPath)
    {
        boolean status=false;
        try
        {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xmlDoc = db.parse(SPath + "User.xml");
        Element rootnd = (Element) xmlDoc.getFirstChild();

        rootnd.normalize();

        NodeList allUsers =  rootnd.getElementsByTagName("User");
        int total = allUsers.getLength();

        for (int index = 0; index <  total; index++)
               {

                   //System.out.println("index:" + index);
                   Node single_user = allUsers.item(index);
                   if (single_user.getNodeType() == Node.ELEMENT_NODE)
                   {
                        Element User_Element = (Element) single_user;

                        if(User_Element.getAttribute("name").compareTo(_u.getName())==0)
                        {
                         rootnd.removeChild((Node)single_user);
                        //Integer ID=_s.getId();
                         //System.out.println(AllUsers.remove(_s));
                         //status=true;//making status true entry is deleted...
                         break;
                        }
                   }
                }

          Transformer transformer = TransformerFactory.newInstance().newTransformer();
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //initialize StreamResult with File object to save to file

          StreamResult result = new StreamResult(SPath+"User.xml");

          DOMSource source = new DOMSource(xmlDoc);
          transformer.transform(source, result);
          status=true;

        }
        catch(Exception ex)
        {
        status=false;
            JOptionPane.showMessageDialog(null, "Error while deleting in delete() model !!"+"\n"+ex.toString(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        return status;
    }

    /**
     * 
     * @param _u
     * @param SPath
     * @return
     */
    public static boolean add(User_entity _u,String SPath)
    {
        boolean status=false;

        try
        {
          //Create and parse
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc =  db.parse(SPath+"User.xml");

            Element rootnd=(Element) xmlDoc.getFirstChild();





            Element UserNd = (Element) xmlDoc.createElement("User");

           //------------------using attributes
            //String id =String.valueOf(_s.getId());
            //String contNo=String.valueOf(_s.getCon_no());

            //UserNd.setAttribute("",id);
            UserNd.setAttribute("name", _u.getName());
            UserNd.setAttribute("password", _u.getPassword());
//            UserNd.setAttribute("ContactNo", contNo);
//            UserNd.setAttribute("Fitness", _s.getFitness());
            UserNd.setAttribute("rname", _u.getrName());
            UserNd.setAttribute("photo", _u.getPhoto());





//            //attribute..
//           String id =String.valueOf(_s.getId());
//
//           UserNd.setAttribute("id",id);
//            Element namend=(Element) xmlDoc.createElement("Name");
//            namend.appendChild(xmlDoc.createTextNode(_s.getName()));
//            UserNd.appendChild(namend);
//
//            Element addnd=(Element) xmlDoc.createElement("Address");
//            addnd.appendChild(xmlDoc.createTextNode(_s.getAddress()));
//            UserNd.appendChild(addnd);
//
//           Element Cnond=(Element) xmlDoc.createElement("ContactNo");
//            Cnond.appendChild(xmlDoc.createTextNode(String.valueOf(_s.getCon_no())));
//            UserNd.appendChild(Cnond);
//
//            Element fitnessnd=(Element) xmlDoc.createElement("Fitness");
//            fitnessnd.appendChild(xmlDoc.createTextNode(_s.getFitness()));
//            UserNd.appendChild(fitnessnd);
//
//            Element Beltnd=(Element) xmlDoc.createElement("Belt");
//            Beltnd.appendChild(xmlDoc.createTextNode(_s.getBelt()));
//            UserNd.appendChild(Beltnd);

           rootnd.appendChild(UserNd);






        //saving
          Transformer transformer = TransformerFactory.newInstance().newTransformer();
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //initialize StreamResult with File object to save to file

          StreamResult result = new StreamResult(SPath+"User.xml");

          DOMSource source = new DOMSource(xmlDoc);
          transformer.transform(source, result);

          //JOptionPane.showMessageDialog(null,"Values are stored...tank u..last!!", "Message", JOptionPane.INFORMATION_MESSAGE);
          status=true;
        }
        catch(Exception e)
        {
          status=false;
        }
        return status;
    }


    /**
     * 
     * @param name
     * @param SPath
     * @return
     */
    public static boolean addKey(String name,String SPath)
    {
        boolean status=false;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(SPath+"UserKey.xml");

            Element root = (Element) xmlDoc.getFirstChild();
            Element key=(Element) xmlDoc.createElement("Key");


            key.setAttribute("value",name);

//            Element value=(Element) xmlDoc.createElement("value");
//            value.appendChild(xmlDoc.createTextNode(String.valueOf(id)));
//            key.appendChild(value);


            root.appendChild(key);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(SPath+"UserKey.xml");
            DOMSource source = new DOMSource(xmlDoc);
            transformer.transform(source, result);

            status=true;
        }
        catch (Exception ex)
        {
            status=false;
            JOptionPane.showMessageDialog(null,"Error while adding key in addkey in model!!"+ex.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        return status;
    }

    /**
     * 
     * @param name
     * @param SPath
     * @return
     */
    public static boolean deleteKey(String name,String SPath)
    {
        boolean status=false;
        try
        {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xmlDoc = db.parse(SPath + "UserKey.xml");
        Element rootnd = (Element) xmlDoc.getFirstChild();

        rootnd.normalize();

        NodeList allUserKeys =  rootnd.getElementsByTagName("Key");
        int total = allUserKeys.getLength();

        for (int index = 0; index <  total; index++)
               {

                   Node single_User_keys = allUserKeys.item(index);
                   if (single_User_keys.getNodeType() == Node.ELEMENT_NODE)
                   {
                        Element User_Key = (Element) single_User_keys;

                        if(User_Key.getAttribute("value").compareTo(name) == 0)
                        {
                            rootnd.removeChild((Node)single_User_keys);
                            //Integer ID=id;
                            //System.out.println("Arry key" + AllUserKeys.remove(ID));
                            //status=true;
                            break;
                        }
                   }
         }

          Transformer transformer = TransformerFactory.newInstance().newTransformer();
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //initialize StreamResult with File object to save to file

          StreamResult result = new StreamResult(SPath+"UserKey.xml");

          DOMSource source = new DOMSource(xmlDoc);
          transformer.transform(source, result);
          status=true;
        }
        catch(Exception ex)
        {
            System.out.println("Exception in delete key function" +
            ex.toString());
            status=false;
        }
        return status;
    }

    /**
     * 
     * @param SPath
     */
    public static void loadAllEntries(String SPath)
    {

        AllUsers.clear();//removing all the key entries..

        File f = new File(SPath + "User.xml");

        if (f.exists()) {
            System.out.println("Loading entries..");

            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document UserXml = db.parse(SPath + "User.xml");

                Element rootUser = (Element) UserXml.getDocumentElement();
                rootUser.normalize();

                NodeList allUsers = rootUser.getElementsByTagName("User");
                int total = allUsers.getLength();
                System.out.println("User enties:"+total);
                for (int index = 0; index < total; index++)
                {
                    //count =0;
                    //new Studemt_entity();
                    Node single_User = allUsers.item(index);
                    if (single_User.getNodeType() == Node.ELEMENT_NODE)
                    {

                        Element User_Element = (Element) single_User;
                       // int id = Integer.parseInt(User_Element.getAttribute("id"));
                        //long cNo = Long.parseLong(User_Element.getAttribute("ContactNo"));

                        AllUsers.add(new User_entity(User_Element.getAttribute("name"),User_Element.getAttribute("password"),User_Element.getAttribute("rname"),User_Element.getAttribute("photo")));
                    }
                }

            } catch (Exception ex)
            {
                System.out.println("Problem while loading Users..in model");
            }


        }


    }

    /**
     * 
     * @param SPath
     */
    public static void loadAllKeys(String SPath)
    {

        AllUserKeys.clear();//removing all the entries...

        File f = new File(SPath + "UserKey.xml");

        if (f.exists())
        {

            try
            {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document UserXml = db.parse(SPath + "UserKey.xml");

                Element rootUser = (Element) UserXml.getDocumentElement();
                rootUser.normalize();

                NodeList allUserKeys = rootUser.getElementsByTagName("Key");
                int total = allUserKeys.getLength();
                System.out.println("User keys:"+total);

                for (int index = 0; index < total; index++)
                {
                    //count =0;
                    //new Studemt_entity();
                    Node single_User_Key = allUserKeys.item(index);
                    if (single_User_Key.getNodeType() == Node.ELEMENT_NODE)
                    {

                        Element User_Key = (Element) single_User_Key;

                        AllUserKeys.add(User_Key.getAttribute("value"));

//                        int id = Integer.parseInt(User_Key.getAttribute("id"));
//                        long cNo = Long.parseLong(User_Key.getAttribute("ContactNo"));
                    }
                }
            }
            catch(Exception ex)
            {
                 System.out.println("Problem while loading User keys..in model");
            }
        }



}

}
