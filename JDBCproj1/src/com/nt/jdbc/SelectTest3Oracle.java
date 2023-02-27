package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class SelectTest3Oracle {
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
			
			try {
				sc=new Scanner(System.in);
				String lastchar = null;
			if(sc!=null) {
				System.out.println("Enter the empname last charecter");
				 lastchar=sc.next();
			}
	
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
		
			if(con!=null)
				st=con.createStatement();
			
				String query="SELECT * FROM EMP WHERE ENAME LIKE "+"'%"+lastchar+"'";

				if(st!=null)
				rs=st.executeQuery(query);
			
				if(rs!=null) {
					if(rs.next()) {
						System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
					}
					else {
						System.out.println("Record not found");
					}		
				}
			}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();	
			}
			try {
				if(st!=null)
					st.close();
			}		catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

