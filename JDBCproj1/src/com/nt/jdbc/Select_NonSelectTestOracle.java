package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Select_NonSelectTestOracle {
	public static void main(String[] args) {
		Scanner sc = null;
		String query = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//read inputs
			sc = new Scanner(System.in);
			
			if(sc!=null) {
				System.out.println("Enter STUDENT db table SELECT or NON-SELECT sql query");
				
				query=sc.nextLine();
			}//if
			//load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//create Statement
			if(con!=null)
				st=con.createStatement();
			
			//send and execute sql query in db s/w
			if(st!=null) {
				boolean flag=st.execute(query);
				if(flag) {
					System.out.println("SELECT sql query executed");
					
					//get ResultSet object
					rs=st.getResultSet();
					
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
						}//while
					}//if
				}//if
				else {
					System.out.println("NON-SELECT SQL query executed");
					int count=st.getUpdateCount();
					System.out.println(count+"no.of records are effected");
				}//else
						
			}//if
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1)
				System.out.println("Duplicate or empty value cannot inserted to pk column sno");
			
			else if(se.getErrorCode()==12899)
				System.out.println("given value is larger than col size");
			
			else if(se.getErrorCode()>=900 && se.getErrorCode()<=1000)
				System.out.println("unknown problem");
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