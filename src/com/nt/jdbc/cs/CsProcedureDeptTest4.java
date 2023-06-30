package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureDeptTest4 {
	private static final String CS_PROCEDURE = "{CALL P_DEPT1(?,?,?)}";
	public static void main(String[] args) {
		int deptno = 0;
		try(Scanner sc = new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter department no");
				deptno = sc.nextInt();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				CallableStatement cs = con.prepareCall(CS_PROCEDURE);
				){
			//register OUT parameter
			if(cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
			}
			//set values to IN parameter
			if(cs!=null)
				cs.setInt(1, deptno);
			
			//call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			
			//gateher results from OUT parameter
			if(cs!=null) {
				System.out.println("deptname "+cs.getString(2));
				System.out.println("location "+cs.getString(3));
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
			if(se.getErrorCode()==1403)
				System.out.println("No data found");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
