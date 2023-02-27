package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2Oracle {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			sc = new Scanner(System.in);
			int dno = 0;
			
			if(sc!=null) {
				System.out.println("Enter Department nomber::");
				dno=sc.nextInt();
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			if(con!=null)
				st=con.createStatement();
			
			String query = "SELECT * FROM DEPT WHERE DEPTNO="+dno;
			
			if(st!=null)
				rs=st.executeQuery(query);
			
			if(rs!=null) {
				if(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				}
				else {
					System.out.println("Department not found");
				}
			}
		
		 }
		 catch(SQLException se){
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
				if(rs!=null)
					rs.close();
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
		}
	}
}
