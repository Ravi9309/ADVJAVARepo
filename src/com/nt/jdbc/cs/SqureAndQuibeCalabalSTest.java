package com.nt.jdbc.cs;
/*
 * create or replace NONEDITIONABLE PROCEDURE P_CUBEANDSQURE 
(
  X IN NUMBER 
, Y OUT NUMBER 
, Z OUT NUMBER 
) AS 
BEGIN
  y:=x*x;
  z:=x*x*x;
END P_CUBEANDSQURE;
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import java.sql.CallableStatement;

public class SqureAndQuibeCalabalSTest {
	private static final String CALL_PROCEDURE = "{CALL P_CUBEANDSQURE(?,?,?)}";
	public static void main(String[] args) {
		int number = 0;
		
		try(Scanner sc = new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter your number");
				number = sc.nextInt();
			}//if
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
				){
			//register out parameter
			if(cs!=null)
				cs.registerOutParameter(2, Types.INTEGER);
				cs.registerOutParameter(3, Types.INTEGER);
				
			//Register values in parameters
			if(cs!=null) {
				cs.setInt(1, number);
			}
			//execute PL/SQL oricedure
			if(cs!=null)
				cs.execute();
			
			//gather results from out parameters
			if(cs!=null) {
				int result = 0;
				result = cs.getInt(2);
				System.out.println("CUBE::"+result);
				
				result = cs.getInt(3);
				System.out.println("SQURE::"+result);
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
