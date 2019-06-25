package application;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import application.util.DBconnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class PrintReport extends JFrame {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public PrintReport() throws HeadlessException{

	}
	
	public void showReport() throws JRException {
		try {
			con = DBconnection.getConnection();   //Database connection
			ps = null;
			rs = null;
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\ASUS\\eclipse-workspace\\GEBS - Final\\src\\application\\util\\payroll_report.jrxml");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
		JRViewer viewer = new JRViewer(jasperPrint);
		viewer.setOpaque(true);
		viewer.setVisible(true);
		
		this.add(viewer);
		this.setSize(900, 500);
		this.setVisible(true);
	}
	
		
}
