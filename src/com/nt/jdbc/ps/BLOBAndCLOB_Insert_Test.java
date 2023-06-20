package com.nt.jdbc.ps;

import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class BLOBAndCLOB_Insert_Test {
		public static final String BLOBCLOB_INSERT_RETRIEVE_QUERY = "INSERT INTO STUDENT1(SNO,SNAME,SADD,PHOTO,RESUME) VALUES(?,?,?,?,?)";
		public static void main(String[] args) {
			int sno = 0;
			String sname = null, sadd = null,photoLocation = null, resumeLocation = null;
			
			//read inputs
			try(Scanner sc = new Scanner(System.in)){
				if(sc!=null) {
					System.out.println("Enter student no");
					sno = sc.nextInt();
					
					System.out.println("Enter student name");
					sname = sc.next();
					
					System.out.println("Enter student addrs");
					sadd = sc.next();
					
					System.out.println("Enter student photolocation");
					photoLocation = sc.next();
					
					System.out.println("Enter student resumeLocation");
			
			}
			//Established the connection 
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				PreparedStatement ps = con.prepareStatement(BLOBCLOB_INSERT_RETRIEVE_QUERY);
				InputStream is = new FileInputStream(photoLocation);
				Reader reader = new FileReader(resumeLocation);
				
				){
				if(ps!=null) {
				ps.setInt(1, sno);
				ps.setString(2, sname);
				ps.setString(3, sadd);
				ps.setBinaryStream(4, is);
				ps.setCharacterStream(5, reader);
				}
				int result = 0;
				if(ps!=null)
					result = ps.executeUpdate();
				
				//process the result
				if(result==0)
					System.out.println("Record not inserted");
					
				else 
					System.out.println("record inserted");
						
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}




