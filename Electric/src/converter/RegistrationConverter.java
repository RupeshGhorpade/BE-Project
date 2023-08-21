package converter;

import javax.servlet.http.HttpServletRequest;

import bean.BusinessBean;
import bean.RegistrationBean;


public class RegistrationConverter {

	public RegistrationBean getValues(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		RegistrationBean bean = new RegistrationBean();
		bean.setFname(request.getParameter("fname"));
		bean.setLname(request.getParameter("lname"));
		bean.setMailid(request.getParameter("emailid"));
		bean.setMno(request.getParameter("mno"));
		bean.setBranch(request.getParameter("branch"));
		bean.setYear(request.getParameter("year"));
		bean.setDob(request.getParameter("dob"));
		bean.setGender(request.getParameter("gender"));
		return bean;
	
		
	}
	public RegistrationBean getLoginData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationBean rb=new RegistrationBean();
		rb.setMailid(request.getParameter("uname"));
		rb.setPassword(request.getParameter("password"));
		return rb;
	}

	public RegistrationBean getForgetPassword(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationBean rb=new RegistrationBean();
		rb.setMailid(request.getParameter("emailid"));
		return rb;
	}

	public RegistrationBean getChangepwd(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationBean rb=new RegistrationBean();
		rb.setPassword(request.getParameter("cpwd"));
		rb.setChangepwd(request.getParameter("npwd"));
		return rb;
	}
	public BusinessBean getQuery(HttpServletRequest request) {
		// TODO Auto-generated method stub
		BusinessBean bb = new BusinessBean();
		bb.setFname(request.getParameter("fname"));
		bb.setQuery(request.getParameter("query"));
		return bb;
	}
	public BusinessBean getEvent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		BusinessBean bb = new BusinessBean();
		bb.setEventdate(request.getParameter("eventdate"));
		bb.setDescription(request.getParameter("description"));
		return bb;
	}

}
