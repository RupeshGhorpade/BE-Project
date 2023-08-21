package service;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import util.EMail;
import bean.BusinessBean;
import bean.RegistrationBean;
import converter.RegistrationConverter;
import db.DBQuires;

public class RegistrationService {

	public boolean insertValues(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationConverter convert =new RegistrationConverter();
		RegistrationBean bean=convert.getValues(request);
		DBQuires db =new DBQuires();
		boolean isSuccess = db.insertRegistrationForm(bean);
		if(isSuccess==true){
			EMail mail =new EMail();
			isSuccess=mail.sendMail(bean);
		}
		return isSuccess;
		
		
	}
	public String checkCredintials(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationConverter lc=new RegistrationConverter();
		RegistrationBean bean=lc.getLoginData(request);
		if(bean.getMailid().equalsIgnoreCase("admin") && bean.getPassword().equals("admin"))
			return "ADMIN";
		return "";
		
	/*	DBQuires db=new DBQuires();
		String userName=db.checkLogin(bean);
		return userName;*/
		
	}

	public boolean forgetPassword(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationConverter lc=new RegistrationConverter();
		RegistrationBean bean=lc.getForgetPassword(request);
		DBQuires db=new DBQuires();
		bean=db.getForgetPassword(bean);
		if(bean==null)
			return false;
		else{
			EMail mail= new EMail();
		mail.sendMail(bean);
		}
		return true;
	}

	public int changepwd(HttpServletRequest request,String emailid) {
		// TODO Auto-generated method stub
		RegistrationConverter lc=new RegistrationConverter();
		RegistrationBean rb=lc.getChangepwd(request);
		DBQuires db=new DBQuires();
		return db.changePwd(rb,emailid);
		
	}
	public void postQuery(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		RegistrationConverter lc=new RegistrationConverter();
		BusinessBean bb=lc.getQuery(request);
		DBQuires db = new DBQuires();
		db.insertQuery(bb);
		
		
	}
	public ArrayList<BusinessBean> getQueries() {
		// TODO Auto-generated method stub
		DBQuires db = new DBQuires();
		ArrayList<BusinessBean> queries = (ArrayList<BusinessBean>) db.getQueries();  
		return queries;
	}
	public ArrayList<String> getComment(HttpServletRequest request) {
		// TODO Auto-generated method stub
		DBQuires db = new DBQuires();
		ArrayList<String> comments = db.getComments((String)request.getParameter("query"));
		return comments;
	}
	public void createevent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationConverter lc=new RegistrationConverter();
		BusinessBean bb=lc.getEvent(request);
		DBQuires db = new DBQuires();
		db.insertEvent(bb);
		
		
	}
	public ArrayList<BusinessBean> getevents() {
		// TODO Auto-generated method stub
		DBQuires db = new DBQuires();
		ArrayList<BusinessBean> events = (ArrayList<BusinessBean>) db.getEvents();  
		return events;
	}
	public void insertComments(HttpServletRequest request) {
		// TODO Auto-generated method stub
		DBQuires db = new DBQuires();
	BusinessBean bb = new  BusinessBean();
	bb.setQuery(request.getParameter("query"));
	bb.setComments(request.getParameter("comment"));
		db.insertComments(bb);
		
		
	}

}
