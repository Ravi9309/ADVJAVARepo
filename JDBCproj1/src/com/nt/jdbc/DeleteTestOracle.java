package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTestOracle {
	public static void main(String[] args) {
		Scanner sc = null;
		Statement st = null;
		Connection con = null;
		
		try {
			sc = new Scanner(System.in);
			
			//read inputs
			float startAvg=0.0f, endAvg=0.0f;
			if(sc!=null) {
				System.out.println("Enter start range of avg::");
				startAvg=sc.nextFloat();//gives 40
				System.out.println("Enter te range of avg::");
				endAvg=sc.nextFloat();//gives 60 
			}
			//load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//create statement object
			if(con!=null)
				st=con.createStatement();
			
			//prepare SQL query
			//delete from student where qvg>=40 and avg<=60
			String query = "DELETE FROM STUDENT WHERE AVG>="+startAvg+" AND AVG<="+endAvg;
			System.out.println(query);
			
			//send and execute SQL query in Db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process the result
			if(count==0)
				System.out.println("Record not found to delete");
			
			else
				System.out.println("no. of records are effected");
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
