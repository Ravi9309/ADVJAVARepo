package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
 CREATE OR REPLACE FUNCTION FX_GET_EMP_DETAILS_BY_ENO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT NUMBER 
) RETURN NUMBER AS 
DNO NUMBER(5);
BEGIN
  SELECT ENAME,JOB,SAL,DEPTNO INTO NAME,DESG,SALARY,DNO FROM EMP WHERE EMPNO = NO;
  RETURN DNO;
END FX_GET_EMP_DETAILS_BY_ENO;
 */

public class CsFuntionTest {
	private static final String CALL_FUNCTION = "{?= call FX_GET_EMP_DETAILS_BY_ENO(?,?,?,?)}";
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
			Connection  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				CallableStatement cs = con.prepareCall(CALL_FUNCTION);
			){
			int no = 0;
			
			if(sc!=null) {
				System.out.println("Enter Employee number::");
				no = sc.nextInt();
			}
			//register return,OUT parameters with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(1, Types.INTEGER);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.FLOAT);
			}
			//set to IN params 
			if(cs!=null)
				cs.setInt(2, no);
			
			//Call PL/SQL function
			if(cs!=null)
				cs.execute();
			
			//gather results from return, OUT params
			if(cs!=null) {
				System.out.println("Employee name::"+cs.getString(3));
				System.out.println("Employe desg::"+cs.getString(4));
				System.out.println("Employee salary::"+cs.getFloat(5));
				System.out.println("Employee deptNo::"+cs.getInt(1));
			}//if
		}//catch
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
