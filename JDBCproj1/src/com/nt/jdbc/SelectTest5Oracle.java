package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SelectTest5Oracle {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			if(con!=null)
				st=con.createStatement();
			
			String query= "SELECT  COUNT(*) FROM DEPT";
			
			if(st!=null)
				rs=st.executeQuery(query);
			
			if(rs!=null)
				rs.next();
				System.out.println("cound of record::"+rs.getInt("COUNT(*)"));
				
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(con!=null)
				con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(rs!=null)
					rs.close();
			}	
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
