package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsAgeCalculator_JavaLogic_TWR_Java9 {
	private static final String GET_DOB_BY_ID = "SELECT DOB FROM CITIZEN_DETAILS WHERE CIT=?";
	public static void main(String[] args) {
		int no = 0;
		Scanner sc = new Scanner(System.in);
		try(sc){
			//read inputs
			if(sc!=null) {
				System.out.println("Enter citizen number");
				no = sc.nextInt();
			}
		}//try--Scanner will closed here automatically
		catch(Exception e) {
			e.printStackTrace();
		}
		//establised the connection 
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ115db1","root","root"); 
				PreparedStatement ps = con.prepareStatement(GET_DOB_BY_ID);){
			
			//set values to query params
			if(ps!=null)
				ps.setInt(1, no);
			
			//execute the query
			ResultSet rs = ps.executeQuery();
			
			try(rs){
				//process the result
				if(rs!=null) {
					if(rs.next()) {
						java.sql.Date sqdob = rs.getDate(1);
						long dobMs = sqdob.getTime();
						long sysDateMs = System.currentTimeMillis();
						
						float age = (sysDateMs-dobMs)/(1000.0f*60.0f*60.0f*24.0f*365.25f);
						
						System.out.println("person age:"+age);
					}
					else {
						System.out.println("person not found");
					}
				}//if
			}//try2--close Rs object here automatically
		}//try1--close con,ps object here automatically
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class

