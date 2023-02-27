package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTestOracle {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			sc = new Scanner(System.in);
			int no = 0;
			String name = null,addrs = null;
			Float avg = 0.00f;
			if(sc!=null) {
				System.out.println("enter student number::");
				no = sc.nextInt();
				
				System.out.println("Enter student name");
				name=sc.next();
				
				System.out.println("Enter student addrs:");
				addrs = sc.next();
				
				System.out.println("Enter student average");
				avg = sc.nextFloat();
			}
			//convert input values as required from the sql query
			name="'"+name+"'";
			addrs="'"+addrs+"'";
			
			//Load the jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//create statement
			if(con!=null)
				st=con.createStatement();
			
			//prepare SQL query
			String query = "INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")"; 
			System.out.println(query);
			
			
			//send and execute sql query in db s/w
			int count = 0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process the Result
			if(count==0)
				System.out.println("Record not inserted");
			
			else
				System.out.println("Record inserted");
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1)
				System.out.println("duplicate or empty value can not inserted to pk column sno");
			
			else if(se.getErrorCode()==12899)
				System.out.println("given value larger than col size");
			
			else if(se.getErrorCode()>=900 && se.getErrorCode()<=1000)
				System.out.println("query syntax problem");
			
			else
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
		}//finally
	}//try
}//main

