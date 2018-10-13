package com.aeturnum.scs.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DbConnect {
	
	public static Connection connObj;
	public static String JDBC_URL = "jdbc:sqlserver://192.168.103.101:1433;databaseName=phoenix";

	
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connObj = DriverManager.getConnection(JDBC_URL, "sa", "admin123$$");
			if(connObj != null) {
				DatabaseMetaData metaObj = (DatabaseMetaData) connObj.getMetaData();
				System.out.println("Driver Name?= " + metaObj.getDriverName() + ", Driver Version?= " + metaObj.getDriverVersion() + ", Product Name?= " + metaObj.getDatabaseProductName() + ", Product Version?= " + metaObj.getDatabaseProductVersion());
			}
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		
		return connObj;
	}

}
