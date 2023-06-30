package com.nt.jdbc.cs;
/*
 * create or replace NONEDITIONABLE PROCEDURE P_SUME 
(
  X IN NUMBER 
, Y IN NUMBER 
, z OUT NUMBER 
) AS 
BEGIN
  z:=x+y;
END P_SUME;
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import java.sql.CallableStatement;

public class CsProcedureTest1 {
	private static final String CALL_PROCEDURE = "{CALL P_SUME(?,?,?)}";
	public static void main(String[] args) {
		int first = 0, second = 0;
		
		try(Scanner sc = new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter first number");
				first = sc.nextInt();
				
				System.out.println("Enter second number");
				second = sc.nextInt();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			CallableStatement cs = con.prepareCall(CALL_PROCEDURE);	
				){
			//register OUT parameter
			if(cs!=null)
				cs.registerOutParameter(3, Types.INTEGER);
			//register values IN parameters
			if(cs!=null) {
				cs.setInt(1, first);
				cs.setInt(2, second);
			}
			//execute PL/SQL procedure
			if(cs!=null)
				cs.execute();
			
			//gather results from OUT parameters
			if(cs!=null) {
				int result = 0;
				result = cs.getInt(3);
				System.out.println("sum"+result);
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
