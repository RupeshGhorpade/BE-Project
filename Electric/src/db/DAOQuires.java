package db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class DAOQuires {
	public String checkLogin(String email, String password) {
		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select * from  registration where cno=? and password = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				return result.getString(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> get(String email) {
		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		ArrayList<String> a = new ArrayList<String>();
		String validateUser = "select * from  complaint where filename=? ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, email);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				a.add(result.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public String getList() {
		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select * from  complaint";
		PreparedStatement preparedStatement;
		String a ="";
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				a = a + result.getString(1) + " " +result.getString(2) + " " + result.getString(3) + " " + result.getString(4) +"\n";
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	public boolean insertReg(String strName, String email, String password, String state, String board, String division, String meter) {
		int rows = 0;
	DAO dao=new DAO();
	
	Connection conn=dao.getConnection();
	String insertquery="insert into registration values(?,?,?,?,?,?,?)";
	PreparedStatement pstmt=null;
	try{
		pstmt=conn.prepareStatement(insertquery);;
		pstmt.setString(1,strName);
		pstmt.setString(2,email);
		pstmt.setString(3,password);
		pstmt.setString(4,state);
		pstmt.setString(5,board);
		pstmt.setString(6,division);
		pstmt.setString(7,meter);
		rows=pstmt.executeUpdate();
	
	}catch(SQLException e){
		System.out.println(e);
	}
	finally{
		try {
			pstmt.close();
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	if(rows==0)
		return false;
	
	return true;

	}
	public boolean insertComplaint(String complaintType, String address) {
		int rows = 0;
	DAO dao=new DAO();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();  
	Connection conn=dao.getConnection();
	String insertquery="insert into complaint values(?,?,?,?,?,?)";
	PreparedStatement pstmt=null;
	try{
		pstmt=conn.prepareStatement(insertquery);;
		pstmt.setString(1,address);
		pstmt.setString(2,complaintType);
		pstmt.setString(3,date.getMonth()+1+"");
		pstmt.setString(4,"");
		pstmt.setString(5,formatter.format(date));
		pstmt.setString(6,"");
		rows=pstmt.executeUpdate();
	
	}catch(SQLException e){
		System.out.println(e);
	}
	finally{
		try {
			pstmt.close();
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	if(rows==0)
		return false;
	
	return true;

	}
	public void update(String item) {
		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select status from  complaint where msg=?";
		PreparedStatement preparedStatement;
		String a = "";
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, item.substring(0,item.lastIndexOf(" ")));
			System.out.println("-"+preparedStatement.toString() + "@@");
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				a = result.getString(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		if("Open".equals(a))
			a = "InProgress";
		else if("InProgress".equals(a))
			a = "Close";
		else
			a = "Reject";
		
		DAO dao=new DAO();
		
		String insertquery="update  complaint set status =? where msg =?";
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(insertquery);;
			pstmt.setString(2,item.substring(0,item.lastIndexOf(" ")));
			pstmt.setString(1,a);
			System.out.println("-"+pstmt.toString() + "@@");
			pstmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println(e);
		}
		finally{
			try {
				pstmt.close();
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	

		}
		
		
	}
	public ArrayList<String> getListApprove() {
		// TODO Auto-generated method stub
		ArrayList<String> ab = new ArrayList<String>();
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select * from  complaint";
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				String a ="";
				a = a + result.getString(1) + "," +result.getString(2) + "," + result.getString(3) + "," + result.getString(4)+"," + result.getString(5) +"," + result.getString(6)+"\n";
				ab.add(a);
			}
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ab;
	}
	public void insertFileReg(String name, String imeino) {
		// TODO Auto-generated method stub
DAO dao=new DAO();
		Connection conn = dao.getConnection();
		String insertquery="update  complaint set filename =? where user =?";
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(insertquery);;
			pstmt.setString(2,imeino);
			pstmt.setString(1,name);
			System.out.println("-"+pstmt.toString() + "@@");
			pstmt.executeUpdate();
			
			
		
		}catch(SQLException e){
			System.out.println(e);
		}
		finally{
			try {
				pstmt.close();
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	
		
	}
	}
	public ArrayList<String> updateStatus(String status, String imagename) {
		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "";
		PreparedStatement preparedStatement;
		try {
			
					validateUser="update complaint set status=? where filename=?";
					preparedStatement=conn.prepareStatement(validateUser);
					preparedStatement.setString(1,"Approve");
					preparedStatement.setString(2, imagename);
					preparedStatement.executeUpdate();
					
					return get(imagename);
					
				}
				
		
			

		
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		

		// TODO Auto-generated method stub
		
		}
}
