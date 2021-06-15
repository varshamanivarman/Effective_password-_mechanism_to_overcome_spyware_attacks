package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FileDao
{
	public static Connection getConnection(){  
        Connection con=null;  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost/biotouchpass", "root", "root");  
            //System.out.println("------------CLOUD USER CREATED------------");
        }catch(Exception e){System.out.println(e);}  
        return con;  
    } 
	
	
	 public static boolean ownerCheck(OwnerPojo op) throws SQLException
	 {
		 boolean emailexists=false;
		 Connection con=FileDao.getConnection();
		 String query="select * from users where email = ?";
		 PreparedStatement st = con.prepareStatement(query);
		 st.setString(1, op.getEmail());
		 ResultSet rs=st.executeQuery();
		 if(rs.next()) {
		   emailexists = true;
		 }
		 
		return emailexists;
		 
	 }
	 
	  public static int ownerSave(OwnerPojo op){  
	        int status=0;  
	        try{  
	            Connection con=FileDao.getConnection();  
	            String query="insert into users(username,email,password,phone) values (?,?,?,?)";
	            PreparedStatement ps=con.prepareStatement(query);  
	            ps.setString(1, op.getUsername());  
	            ps.setString(2, op.getEmail());  
	            ps.setString(3, op.getPassword());
	            ps.setString(4, op.getPhone());
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    }  
	  
	
	
	  public static String ownerLogin(OwnerPojo olog)
	  {
		  String status="";
		  try {
			  Connection con=FileDao.getConnection(); 
			  String email="";
			  String password="";
			  String name="";
			  String query="select * from users where email='"+olog.getEmail()+"'";
			  PreparedStatement st = con.prepareStatement(query);
			  ResultSet rs=st.executeQuery();
				 while(rs.next()) 
				 {
				//   name=rs.getString("name");
				   email=rs.getString("email");
				   password=rs.getString("password");
				  // phone=rs.getString("password");
				   System.out.println(email+"     "+password);
				   System.out.println(olog.getEmail()+"       "+olog.getPassword());
				 }
				
				 if (email.equals(olog.getEmail())&& password.equals(olog.getPassword())) {
					 status=name;
					
				} else {
					status="no";

				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  
		return status;
		  
	  }
	
	  public static String LoginDetails(String email) throws SQLException
		 {
		//  System.err.println("fdfdfsdf");
			 String username="";
			 String uemail="";
			 String password="";
			 String phone="";
			 Connection con=FileDao.getConnection();
			 String query="select * from users where email =?";
			 PreparedStatement st = con.prepareStatement(query);
			 st.setString(1, email);
			 ResultSet rs=st.executeQuery();
			 if(rs.next()) {
				 username=rs.getString("username");
				 uemail=rs.getString("email");
				 password=rs.getString("password");
				 phone=rs.getString("phone");
			 }
			 
			return username+","+uemail+","+password+","+phone;
		 }
	
	
	
}
