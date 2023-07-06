package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.sql.SQLException;
import java.util.Scanner;

public class CsFunctionTest2 {
	private static final String CALL_FUNCTION = "{?= call FX_GET_STUDENT_DETAILS_BY_NO(?,?,?)}";
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				CallableStatement cs = con.prepareCall(CALL_FUNCTION);
			){
			int stno = 0;
			
			if(sc!=null) {
				System.out.println("Enter student number::");
				stno = sc.nextInt();
			}
			//register OUT parameter
			if(cs!=null) {
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(1, Types.FLOAT);//set return type in 1 parameter
			}
			//set IN parameter
			if(cs!=null)
				cs.setInt(2, stno);//set 
			
			//execute PL/SQL function
			if(cs!=null)
				cs.execute();
			
			//gather result from return OUT params
			if(cs!=null) {
				System.out.println("Student name::"+cs.getString(3));
				System.out.println("Student address::"+cs.getString(4));
				System.out.println("Student average::"+cs.getFloat(1));//return type
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
