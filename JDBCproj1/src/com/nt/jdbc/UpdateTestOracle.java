package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateTestOracle {
	public static void main(String[] args) {
		Scanner sc = null;
		Statement st = null;
		Connection con = null;
		
		try {
			sc = new Scanner(System.in);
			
			//read inputs 
			String newName=null,newAddrs=null;
			float newAvg=0.0f;	
			int no = 0;
			
				if(sc!=null) {
					System.out.println("Enter student no::");
					no=sc.nextInt();
					
					System.out.println("Enter new Name for student::");
					newName=sc.next();//giver 'raja'
					
					System.out.println("Enter new Avg for student::");
					newAvg=sc.nextFloat();
					
					System.out.println("Enter new Addrs for student::");
					newAddrs=sc.next();
				}
				//convert input values as required for the SQL query
				
				newName="'"+newName+"'";
				newAddrs="'"+newAddrs+"'";
				
				
				//Load jdbc driver class (optional)
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//Established the Connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				
				//create Statement obj
				if(con!=null)
					st=con.createStatement();
				
				//prepare SQL query
					//update student set sadd="hyd" sname="ramesh" avg=67.99 where sno=1001;
				String query = "UPDATE STUDENT SET SADD="+newAddrs+",SNAME="+newName+",AVG= "+newAvg+"WHERE SNO="+no;
				System.out.println(query);
				
				//send and execute SQL query in Db S/w
				int count=0;
				if(st!=null)
					count = st.executeUpdate(query);
				
				//process the result
				if(count==0)
					System.out.println("Record not found to update");
				
				else
					System.out.println(count+"no.of records are updated");
		}//try
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
