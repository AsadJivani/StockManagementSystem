/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author M.ASAD
 */
public class Reportview4 extends JFrame{
    Connection conn = null;
 public void showReport(String reportfilename , String titles,int ACC,String DATEF,String DATET)
{


    try
        {
            
            //database connection here
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn =DriverManager.getConnection( "jdbc:odbc:precious","sa","sa9");
            
            
            Map parameters = new HashMap();
            
            parameters.put("AC/NO",ACC);
          parameters.put("FROM",DATEF);
            parameters.put("TO",DATET);
//            parameters.put("ino",ino);
//            JOptionPane.showMessageDialog(rootPane, pack);
            JasperDesign jasperDesign = JRXmlLoader.load(reportfilename);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            //for print 
            //parameters holds the parameter passs to the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, conn);
            //Viewer Window to show
            JRViewer viewer = new JRViewer(jasperPrint);
            viewer.setOpaque(true);
            viewer.setVisible(true);
            //make your JFrame visible
            this.add(viewer);
//            this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-100,Toolkit.getDefaultToolkit().getScreenSize().height-100);
            this.setExtendedState(MAXIMIZED_BOTH);
//            this.setSize(1270,750);
            this.setVisible(true);
            this.setTitle("PAYMENT MONTHLY REPORTS");
            }
    catch(ClassNotFoundException | SQLException | JRException ex)
    {
        JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());


} 
}   
}
