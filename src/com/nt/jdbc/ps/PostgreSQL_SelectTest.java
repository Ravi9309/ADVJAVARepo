package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreSQL_SelectTest {
	public static final String GET_PRODUCT_QUERY = "SELECT PID,PNAME,PRICE,QTY FROM PRODUCT";
	public static void main(String[] args) {
		try(//Connection con = DriverManager.getConnection("jdbc:postgresql:ntaj115db","postgres","tiger");
				Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ntaj115db","postgres","tiger");
				PreparedStatement ps = con.prepareStatement(GET_PRODUCT_QUERY);
				ResultSet rs = ps.executeQuery();){
			
			//process the RsultSet
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
				}//while
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
