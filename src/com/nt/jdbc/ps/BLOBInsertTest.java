package com.nt.jdbc.ps;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBInsertTest {
	public static final String BLOB_INSERT_QUERY = ("INSERT INTO JDBC_ACTOR_INFO VALUES(ACTOR_ID_SEQ.NEXTVAL,?,?,?");
	public static void main(String[] args) {
		String actorName = null, actorAddrs = null, photoLocation = null;
		
		//read inputs
		try(Scanner sc = new Scanner(System.in)) {
			if(sc!=null) {
				System.out.println("Enter actor Name:");
				actorName = sc.next();
				
				System.out.println("Enter actor address:");
				actorAddrs = sc.next();
				
				System.out.println("Enter actress photoLocation:");
				photoLocation = sc.next();
			}
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
					PreparedStatement ps = con.prepareStatement(BLOB_INSERT_QUERY);
					InputStream is = new FileInputStream(photoLocation);
					){
					//set values Query parameter
				if(ps!=null) {
					ps.setString(1, actorName);
					ps.setString(2, actorAddrs);
					ps.setBinaryStream(3,is);
				}
				//execute the query
				int result=0;
				if(ps!=null) {
					result = ps.executeUpdate();
					
					//process the result
					if(result==0)
						System.out.println("Record not inserted");
					
					else
						System.out.println("Record inserted");
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
