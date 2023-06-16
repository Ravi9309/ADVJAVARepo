package com.nt.jdbc.ps;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PsAgeCalculator_JavaLogic {
	public static final String GET_DOB_BY_ID = "SELECT DOB FROM CITIZEN_DETAILS WHERE CID=?";
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Scanner sc = null;
		int no = 0;
		
		try {
			//read inputs
			sc = new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter citizen number");
				no = sc.nextInt();
			}
			/*//LOad JDBC driver class
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 ///established the connection
			 con = DriverManager.getConnection("jdbc:mysql:///NTAJ115db1","root","root");
			 */
			
			//Load jdbc class obj
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//create PreparedStatement obj
			if(con!=null)
				ps = con.prepareStatement(GET_DOB_BY_ID);
			
			//set values to query params
			if(ps!=null)
				ps.setInt(1, no);
			
			//execute the pre-compiled SQL query
			if(ps!=null)
				rs = ps.executeQuery();
			
			//process the result
			if(rs!=null) {
				if(rs.next()) {
				java.sql.Date sqdob = rs.getDate(1);
				long dobMs = sqdob.getTime();
				long sysDateMS = System.currentTimeMillis();  
				//or  long sysDateMs = new Date().getTime();
				float age = (sysDateMS-dobMs)/(1000.0f*60.0f*60.0f*24.0f*365.25f);
				
				System.out.println("Person age: "+age);
				}
				else {
					System.out.println("person not found");
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();	
			}
		}
	}
}
