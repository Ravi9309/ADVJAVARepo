package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class CsProcedureTest5 {
	private static final String CS_PROCEDURE = "{CALL P_GET_STUDENT_DETAILS_BY_LETTER_S(?,?,?)}";
	public static void main(String[] args) {
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
					CallableStatement cs = con.prepareCall(CS_PROCEDURE);
			){
			String sname = null;
		
			//register out parameter
			if(cs!=null)
			cs.registerOutParameter(1, Types.INTEGER);
			cs.registerOutParameter(3, Types.VARCHAR);
			
			//set IN parameter 
			if(cs!=null) {
				cs.setString(2, sname);
			}
			
			//Call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			
			//gather the results from parameter
			if(cs!=null) {
				System.out.println("student no::"+cs.getInt(1));
				System.out.println("student address::"+cs.getString(3));
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
