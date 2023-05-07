import java.lang.Thread.State;
import java.sql.*;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.w3c.dom.css.RGBColor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;

import com.mysql.cj.protocol.Resultset;

class ConnectionManager{
    public Connection conn=null;
    Statement s1;
    int r1;
    public void initiate() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver"); 
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartcity","root","Fllbcksql");
        s1 = conn.createStatement();
    }
    
    
    public void insertInitialValues(String tableName){
        if(tableName.equalsIgnoreCase("citizen")){
            
            try {
                
                
                String[][] citizenValues = {{"Dev M Kumar","dev@gmail.com","123456789","1","Resident","BT1"},
                                            {"Gokul Krishnan","gokul@gmail.com","234567891","2","Resident","BT2"},
                                            {"KV Dhruthi Rao","dhruthi@gmail.com","234567231","3","Resident","BT3"},
                                            {"Gayatri Prabhala","gayatri@gmail.com","234521891","4","Resident","BT4"},
                                            {"Darth Vader","dvader@gmail.com","345666123","5","Tourist","BT5"}};
                for(String[] a : citizenValues){
                    String cname = a[0];
                    String email = a[1];
                    String phno = a[2];
                    String citizen_id = a[3];
                    String citizen_type = a[4];
                    String bank_id = a[5];
                    r1=s1.executeUpdate("insert into citizen values("+"\""+cname+"\""+","+"\""+email+"\""+","+"\""+phno+"\""+","+"\""+citizen_id+"\""+","+"\""+citizen_type+"\""+","+"\""+bank_id+"\""+")");
                }
                System.out.println("inserted succesfully into citizen table");
                s1.close();
            } 
            catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("error");
                System.out.println(e);
                e.printStackTrace();
            }
            
        }
        else if(tableName.equalsIgnoreCase("locations")){
            try {
                Statement s1;
                s1 = conn.createStatement();
                String[][] locationValues = {{"kanakpura","FORUM Mall","A new, luxury mall with premium brands opening their stores. Shop now","4.5"},
                                            {"rajajinagar","Sheraton Grand Hotel","This hotel is within the Brigade Gateway complex and is a good choice for a night out","4.3"},
                                            {"bellandur","Ecospace","This is a highly developed IT tech park","4.6"},
                                            {"rrnagar","Glen's Bakehouse","This is a cute cake shop","4.4"}};
                for(String[] a : locationValues){
                    String address = a[0];
                    String LName = a[1];
                    String Descript = a[2];
                    String review = a[3];
                    r1=s1.executeUpdate("insert into locations values("+"\""+address+"\""+","+"\""+LName+"\""+","+"\""+Descript+"\""+","+"\""+review+"\""+")");
                }
                System.out.println("inserted succesfully into location table");
                s1.close();
            } 
            catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println(e);
                e.printStackTrace();
            }
        }
        else if(tableName.equalsIgnoreCase("bank")){
            try {
                Statement s1;
                s1 = conn.createStatement();
                String[][] bankValues = {{"BT1","1","20000"},
                                        {"BT2","2","10000"},
                                        {"BT3","3","30000"},
                                        {"BT4","4","40000"},
                                        {"BT5","5","50000"}};
                                                    
                for(String[] a : bankValues){
                    String bank_id = a[0];
                    String citizen_id = a[1];
                    String amount = a[2];
                    r1=s1.executeUpdate("insert into bank values("+"\""+bank_id+"\""+","+"\""+citizen_id+"\""+","+amount+")");
                }
                System.out.println("inserted succesfully into bank table");
                s1.close();
            } 
            catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
}




public class p1{
    public static void main(String[] args) throws IOException, SQLException {
        ConnectionManager cnm1 = new ConnectionManager();
        Citizen c1 = new Citizen();
        CitizenView cv = new CitizenView();
        try {
            //cnm1.initiate();
            c1.initiate();
            c1.login();
            //c1.listLocs();
            //c1.utilCalc();
            //c1.listBank();
            //c1.makePayment();
            //c1.listBank();
            //c1 = cv.loginFrame();
            cv.mainFrame(c1);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //cnm1.insertInitialValues("citizen");
        //cnm1.insertInitialValues("locations");
        //cnm1.insertInitialValues("bank");

    }
}