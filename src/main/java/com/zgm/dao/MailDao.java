package com.zgm.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zgn.entity.*;
public class MailDao extends BaseDao implements MailUserDao {

	static Connection co=getconn();

	
    
    public static int findmaxUserid(){
    	int a=0;
    	String sql="select max(id) from email.mail_user";
    	java.sql.PreparedStatement ps;
		try {
			System.out.println(sql);
			ps = co.prepareStatement(sql);			 
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
			
				a=rs.getInt("max(id)");				
			}
		} catch (SQLException e) {
			System.out.println("²éÑ¯Ê§°Ü£¡");
			e.printStackTrace();
		}
    	return a;
    }



	public boolean addMailUser(mailUser user) {
		
			
			boolean a1=false;
	    	int bb=findmaxUserid();
	    	bb=bb+=1;
	    	System.out.println(bb);
	    	String sql="insert into email.mail_user values ('"+bb+"','"+user.getName()+"','"+user.getPwd()+"','"+user.getStatus()+"','"+user.getCode()+"')";
	    	java.sql.PreparedStatement ps;
			try {
				System.out.println(sql);
				ps = co.prepareStatement(sql);
				ps.executeUpdate(); 
				a1=true;
				
			} catch (SQLException e) {
				System.out.println("×¢²áÊ§°Ü£¡");
				e.printStackTrace();
			}
				
	    	
	    	return a1;
			
			

		

	}
	
}
