/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Student_entity;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import java.util.ArrayList;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Student_model {

    /**
     * 
     */
    public static ArrayList<Student_entity> AllStudents = new ArrayList<Student_entity>();
    /**
     * 
     */
    public static ArrayList<Integer> AllStudentKeys = new ArrayList<Integer>();
    //private static Studemt_entity s = new Studemt_entity();

    /**
     * 
     * @param SPath
     * @return
     */
    public static boolean createFiles(String SPath) {
        boolean status = false;
        try {
            File f = new File(SPath);
            //create file
            f.createNewFile();
            //JOptionPane.showMessageDialog(null,"File created...2)", "Message", JOptionPane.INFORMATION_MESSAGE);

            //parse n Create Root Node
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();

            //New documents
            Document StudentXml = db.newDocument();
            Document StudentKeyXml = db.newDocument();

            //Root StudentXml
            Element rootnd = (Element) StudentXml.createElement("Student_information");
            StudentXml.appendChild(rootnd);

            //Root Student Key
            Element rootKey = (Element) StudentKeyXml.createElement("Student_Key_information");
            StudentKeyXml.appendChild(rootKey);


            //Saving..StudentXml
            Transformer transformer1 = TransformerFactory.newInstance().newTransformer();
            transformer1.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result1 = new StreamResult(SPath + "Student.xml");
            DOMSource source1 = new DOMSource(StudentXml);
            transformer1.transform(source1, result1);

            //Saving..StudentKeyXml
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            transformer2.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result2 = new StreamResult(SPath + "StudentKey.xml");
            DOMSource source2 = new DOMSource(StudentKeyXml);
            transformer2.transform(source2, result2);

            status = true;
        } catch (Exception ex) {
            status = false;
            JOptionPane.showMessageDialog(null, "Exception in File creation......", "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        return status;
    }

    /**
     * 
     * @param _s
     * @param SPath
     * @return
     */
    public static boolean delete(Student_entity _s, String SPath) {
        boolean status = false;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(SPath + "Student.xml");
            Element rootnd = (Element) xmlDoc.getFirstChild();

            rootnd.normalize();

            NodeList allStudents = rootnd.getElementsByTagName("Student");
            int total = allStudents.getLength();

            for (int index = 0; index < total; index++) {

                //System.out.println("index:" + index);
                Node single_Student = allStudents.item(index);
                if (single_Student.getNodeType() == Node.ELEMENT_NODE) {
                    Element Student_Element = (Element) single_Student;

                    if (Integer.parseInt(Student_Element.getAttribute("id")) == _s.getId()) {
                        rootnd.removeChild((Node) single_Student);
                        //Integer ID=_s.getId();
                        //System.out.println(AllStudents.remove(_s));
                        //status=true;//making status true entry is deleted...
                        break;
                    }
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //initialize StreamResult with File object to save to file

            StreamResult result = new StreamResult(SPath + "Student.xml");

            DOMSource source = new DOMSource(xmlDoc);
            transformer.transform(source, result);
            status = true;

        } catch (Exception ex) {
            status = false;
            JOptionPane.showMessageDialog(null, "Error while deleting in delete() model !!" + "\n" + ex.toString(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        return status;
    }

    /**
     * 
     * @param _s
     * @param SPath
     * @return
     */
    public static boolean add(Student_entity _s, String SPath) {
        boolean status = false;

        try {
            //Create and parse
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(SPath + "Student.xml");

            Element rootnd = (Element) xmlDoc.getFirstChild();





            Element StudentNd = (Element) xmlDoc.createElement("Student");

            //------------------using attributes
            String id = String.valueOf(_s.getId());
            String contNo = String.valueOf(_s.getcon());
            String age = String.valueOf(_s.getage());
            StudentNd.setAttribute("id", id);
            StudentNd.setAttribute("name", _s.getName());
            StudentNd.setAttribute("address", _s.getaddress());
            StudentNd.setAttribute("con", contNo);
            StudentNd.setAttribute("age", age);
            //StudentNd.setAttribute("year", _s.getyr());
            StudentNd.setAttribute("photo", _s.getPhoto());





//            //attribute..
//           String id =String.valueOf(_s.getId());
//
//           StudentNd.setAttribute("id",id);
//            Element namend=(Element) xmlDoc.createElement("Name");
//            namend.appendChild(xmlDoc.createTextNode(_s.getName()));
//            StudentNd.appendChild(namend);
//
//            Element addnd=(Element) xmlDoc.createElement("Address");
//            addnd.appendChild(xmlDoc.createTextNode(_s.getcast()));
//            StudentNd.appendChild(addnd);
//
//           Element Cnond=(Element) xmlDoc.createElement("ContactNo");
//            Cnond.appendChild(xmlDoc.createTextNode(String.valueOf(_s.getdd())));
//            StudentNd.appendChild(Cnond);
//
//            Element fitnessnd=(Element) xmlDoc.createElement("Fitness");
//            fitnessnd.appendChild(xmlDoc.createTextNode(_s.getbr()));
//            StudentNd.appendChild(fitnessnd);
//
//            Element Beltnd=(Element) xmlDoc.createElement("Belt");
//            Beltnd.appendChild(xmlDoc.createTextNode(_s.getyr()));
//            StudentNd.appendChild(Beltnd);

            rootnd.appendChild(StudentNd);






            //saving
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //initialize StreamResult with File object to save to file

            StreamResult result = new StreamResult(SPath + "Student.xml");

            DOMSource source = new DOMSource(xmlDoc);
            transformer.transform(source, result);

            //JOptionPane.showMessageDialog(null,"Values are stored...tank u..last!!", "Message", JOptionPane.INFORMATION_MESSAGE);
            status = true;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    /**
     * 
     * @param id
     * @param SPath
     * @return
     */
    public static boolean addKey(int id, String SPath) {
        boolean status = false;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(SPath + "StudentKey.xml");

            Element root = (Element) xmlDoc.getFirstChild();
            Element key = (Element) xmlDoc.createElement("Key");


            key.setAttribute("value", String.valueOf(id));

//            Element value=(Element) xmlDoc.createElement("value");
//            value.appendChild(xmlDoc.createTextNode(String.valueOf(id)));
//            key.appendChild(value);


            root.appendChild(key);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(SPath + "StudentKey.xml");
            DOMSource source = new DOMSource(xmlDoc);
            transformer.transform(source, result);

            status = true;
        } catch (Exception ex) {
            status = false;
            JOptionPane.showMessageDialog(null, "Error while adding key in addkey in model!!" + ex.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        return status;
    }

    /**
     * 
     * @param id
     * @param SPath
     * @return
     */
    public static boolean deleteKey(int id, String SPath) {
        boolean status = false;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(SPath + "StudentKey.xml");
            Element rootnd = (Element) xmlDoc.getFirstChild();

            rootnd.normalize();

            NodeList allStudentsKeys = rootnd.getElementsByTagName("Key");
            int total = allStudentsKeys.getLength();

            for (int index = 0; index < total; index++) {

                Node single_Student_keys = allStudentsKeys.item(index);
                if (single_Student_keys.getNodeType() == Node.ELEMENT_NODE) {
                    Element Student_Key = (Element) single_Student_keys;

                    if (Integer.parseInt(Student_Key.getAttribute("value")) == id) {
                        rootnd.removeChild((Node) single_Student_keys);
                        //Integer ID=id;
                        //System.out.println("Arry key" + AllStudentKeys.remove(ID));
                        //status=true;
                        break;
                    }
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //initialize StreamResult with File object to save to file

            StreamResult result = new StreamResult(SPath + "StudentKey.xml");

            DOMSource source = new DOMSource(xmlDoc);
            transformer.transform(source, result);
            status = true;
        } catch (Exception ex) {
            System.out.println("Exception in delete key function"
                    + ex.toString());
            status = false;
        }
        return status;
    }

    /**
     * 
     * @param SPath
     */
    public static void loadAllEntries(String SPath) {

        AllStudents.clear();//removing all the key entries..

        File f = new File(SPath + "Student.xml");

        if (f.exists()) {

            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document StudentXml = db.parse(SPath + "Student.xml");

                Element rootStudent = (Element) StudentXml.getDocumentElement();
                rootStudent.normalize();

                NodeList allStudents = rootStudent.getElementsByTagName("Student");
                int total = allStudents.getLength();
                System.out.println("student enties:" + total);
                for (int index = 0; index < total; index++) {
                    //count =0;
                    //new Studemt_entity();
                    Node single_Student = allStudents.item(index);
                    if (single_Student.getNodeType() == Node.ELEMENT_NODE) {

                        Element Student_Element = (Element) single_Student;
                        int id = Integer.parseInt(Student_Element.getAttribute("id"));
                        long cNo = Long.parseLong(Student_Element.getAttribute("con"));
                        int age = Integer.parseInt(Student_Element.getAttribute("age"));
                        AllStudents.add(new Student_entity(id, Student_Element.getAttribute("name"), Student_Element.getAttribute("address"), cNo, age, Student_Element.getAttribute("photo")));
                    }
                }

            } catch (Exception ex) {
                System.out.println("Problem while loading students navneet.. this is photo in model");
            }


        }


    }

    /**
     * 
     * @param SPath
     */
    public static void loadAllKeys(String SPath) {

        AllStudentKeys.clear();//removing all the entries...

        File f = new File(SPath + "StudentKey.xml");

        if (f.exists()) {

            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document StudentXml = db.parse(SPath + "StudentKey.xml");

                Element rootStudent = (Element) StudentXml.getDocumentElement();
                rootStudent.normalize();

                NodeList allStudKeys = rootStudent.getElementsByTagName("Key");
                int total = allStudKeys.getLength();
                System.out.println("Student keys:" + total);

                for (int index = 0; index < total; index++) {
                    //count =0;
                    //new Studemt_entity();
                    Node single_Student_Key = allStudKeys.item(index);
                    if (single_Student_Key.getNodeType() == Node.ELEMENT_NODE) {

                        Element Student_Key = (Element) single_Student_Key;

                        AllStudentKeys.add(Integer.parseInt(Student_Key.getAttribute("value")));

//                        int id = Integer.parseInt(Student_Key.getAttribute("id"));
//                        long cNo = Long.parseLong(Student_Key.getAttribute("ContactNo"));
                    }
                }
            } catch (Exception ex) {
                System.out.println("Problem while loading student keys..in model");
            }
        }



    }
}
