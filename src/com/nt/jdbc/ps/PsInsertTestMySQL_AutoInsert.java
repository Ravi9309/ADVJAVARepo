package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsInsertTestMySQL_AutoInsert {
	public static final String INSERT_CITIZEN_DETAILS = "INSERT INTO CITIZEN_DETAILS(CNAMEl,CADD,DOB,DOM,DOJ) VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		
	
		try {
			//read inputs
			sc = new Scanner(System.in);
			
			String name = null;
			String addrs = null;
			String sdob = null, sdom = null, sdoj = null;
			
			if(sc!=null) {
				System.out.println("Enter citizen name::");
				name = sc.next();
				
				System.out.println("Enter citizen address");
				addrs = sc.next();
				
				System.out.println("Enter DOB(dd-MM-yyyy)");
				sdob = sc.next();
				
				System.out.println("Enter DOM(MM-dd-yyyy)");
				sdom = sc.next();
				
				System.out.println("Enter DOJ(yyyy-MM-dd)");
				sdoj = sc.next();
			}
			//convert DOB ((dd-MM-yyyy) to java.sql.Date class obj
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob = sdf1.parse(sdob);
			long ms = udob.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);
			
			//convert DOM (MM-dd-yyyy) to java.sql.Date class obj
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date udom = sdf2.parse(sdom);
			java.sql.Date sqdom = new java.sql.Date(udom.getTime());
			
			//convert DOJ (yyyy-MM-dd) to java.sql.Date class obj
			java.sql.Date sqdoj = java.sql.Date.valueOf(sdoj);
			
			//Load JDBC driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//established the connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ115DB1","root","root");
			
			//create PreparedStatement obj having INSERT SQL QUERY  as pre-compiled query
			if(con!=null)
				ps=con.prepareStatement(INSERT_CITIZEN_DETAILS);
			
			//set the query parameter values
			if(ps!=null) {
				ps.setString(1, name);
				ps.setString(2, addrs);
				ps.setDate(3, sqdob);
				ps.setDate(4, sqdom);
				ps.setDate(5, sqdoj);
			}
			//execute the pre-compiled sql query
			int result = ps.executeUpdate();
			
			//process the rsult
			if(result==0)
				System.out.println("record insertion is wasted");
			
			else
				System.out.println("record insertion is done");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}
}
