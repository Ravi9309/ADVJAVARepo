package com.nt.jdbc.ps;

import java.io.InputStream;
import java.io.Reader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBAndCLOBInsertMySQL {
	private static final String BLOB_INSERT_QUERY = "INSERT INTO JDBC_ACTOR_INFO(ACTORNAME,ACTORADDRS,PHOTO,RESUME) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		String actorName = null, actorAddrs = null, photoLocation = null, resumeLocation = null;
		//read inputs
		try(Scanner sc = new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter actor Name");
				actorName = sc.next();
				
				System.out.println("Enter actor Addrs");
				actorAddrs = sc.next();
				
				System.out.println("Enter actor photo file location");
				photoLocation = sc.next();
				
				System.out.println("Enter actor resume fileLocation");
				resumeLocation = sc.next();
			}
			try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj115db1","root","root");
					PreparedStatement ps = con.prepareStatement(BLOB_INSERT_QUERY);
					InputStream is = new FileInputStream(photoLocation);
					Reader reader = new FileReader(resumeLocation);
					){
				//set values to query parameter
				if(ps!=null) {
					ps.setString(1, actorName);
					ps.setString(2, actorAddrs);
					ps.setBinaryStream(3, is);
					ps.setCharacterStream(4, reader);
				}
				//execute the query
				int result = 0;
				if(ps!=null)
					result = ps.executeUpdate();
				
				//process the result
				if(result==0)
					System.out.println("Record not inserted");
				
				else
					System.out.println("Record inserted");
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
