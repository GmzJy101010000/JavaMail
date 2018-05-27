package com.zgm.dao;



import java.beans.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;


import org.omg.CORBA.PUBLIC_MEMBER;

public  class BaseDao {
    
    
    public static String  url;
    public static String  User;
    public static String  Pwd;
    public static String  Driver;
    
    private static Properties pro;
    static FileInputStream in;
	static{  
		pro=new Properties();
		
	    URL  url1=BaseDao.class.getClassLoader().getResource("/jdbc.properties");
	   String path =url1.getPath();
	  
	try {
		in = new FileInputStream(path);
	} catch (FileNotFoundException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	   
		try {
			System.out.println("dsf"+in);
			pro.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    User = pro.getProperty("user");  
         Pwd = pro.getProperty("password");  
         url= pro.getProperty("url");  
        Driver= pro.getProperty("jdbcDriver");  
	    try {
	    	
			Class.forName(Driver);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
 }   
	public static Connection getconn(){
		 Connection con=null;
		try {
			con=DriverManager.getConnection(url,User,Pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void CloseSQL(ResultSet rs,Statement st,Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}