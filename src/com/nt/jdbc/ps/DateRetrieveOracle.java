package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateRetrieveOracle {
	public static final String INSERT_CITIZEN_DETAILS = "INSERT INTO CITIZEN_DETAILS VALUES(?,?,?,?,?,?)";
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		Scanner sc = null;
		
		try {
			sc = new Scanner(System.in);
			int cno = 0;
			String name = null;
			String addrs = null;
			String sdob = null, sdom = null, sdoj = null;
			
			if(sc!=null) {
				System.out.println("Enter citizen id");
				cno = sc.nextInt();
				
				System.out.println("Enter citizen name");
				name = sc.next();
				
				System.out.println("Enter citizen addrs");
				addrs = sc.next();
				
				System.out.println("Enter citizen sdob(dd-MM-yyyy)");
				sdob = sc.next();
				
				System.out.println("Enter citizen sdom(MM-dd-yyyy");
				sdom = sc.next();
				
				System.out.println("Enter citizen sdoj(yyyy-MM-dd)");
				sdoj = sc.next();
			}
			//convert sdob (dd-MM-yyyy) to java.sql.Date class obj
			SimpleDateFormat sdf1 = new SimpleDateFormat();
			java.util.Date udob = sdf1.parse(sdob);
			long ms = udob.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);
			
			//convert sdom (MM-dd-yyyy) to java.sal.Date class obj
			SimpleDateFormat sdf2 = new SimpleDateFormat();
			java.util.Date udom = sdf2.parse(sdom);
			java.sql.Date sqdom = new java.sql.Date(udom.getTime());
			
			//convert sdoj (yyyy-MM-dd) to java.sql.Date class obj
			java.sql.Date sqdoj =  java.sql.Date.valueOf(sdoj);
			
			//Load jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//established  the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//create preparedStatement obj
			if(con!=null)
				ps = con.prepareStatement(INSERT_CITIZEN_DETAILS);
			
			//set the query parameter 
			if(ps!=null) {
				ps.setInt(1, cno);
				ps.setString(2, name);
				ps.setString(3, addrs);
				ps.setDate(4, sqdob);
				ps.setDate(5, sqdom);
				ps.setDate(6, sqdoj);
			}
			//execute the pre-compiled sql query
			int result = ps.executeUpdate();
			
			if(result==0)
				System.out.println("record insertion is wasted");
			
			else
				System.out.println("Record is successfully inserted");
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
		}
	}
}
