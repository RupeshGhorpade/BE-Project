package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DAOQuires;

import service.RegistrationService;
import bean.BusinessBean;


/**
 * Servlet implementation class Registration
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String action=request.getParameter("page");
		System.out.println(action);
		String nextPath="";
		
		if("Registration".equalsIgnoreCase(action)){
		
		RegistrationService service=new RegistrationService();
		boolean isSuccess=service.insertValues(request);
		if(isSuccess==false)
			request.setAttribute("message", "Error while inserting values<br> This might happen because of the below errors <br>1.Duplicate Email-Id <br>2.Unable to connect Database<br>3.Unable to connect Internet");
			else
			{
			request.setAttribute("message", "Account created successfully<br> Please visit your "+request.getParameter("emailid")+" for the credential");
			}
		nextPath="/JSP/Registration.jsp";
		}
		if("save".equalsIgnoreCase(action)){
			String status = request.getParameter("status");
			String imagename = request.getParameter("imagename");
			ArrayList<String> to = new DAOQuires().updateStatus(status,imagename);
			int amount=0;
			if(to.size()>1){
				amount = Integer.parseInt(to.get(0)) +Integer.parseInt(to.get(1))*6;
						
			}
				
			System.out.println("Electric Consumption is "+amount);
			CallSMScAPI.SMSSender("adugar","abc123","","Electric Consumption is "+amount,"WebSMS","0");
			nextPath = "/JSP/Portal.jsp";
		}
		else if("login".equals(action))
		{
			RegistrationService ls =new RegistrationService();
			
			String username=ls.checkCredintials(request);
			
			if(username==null)
			{
				request.setAttribute("username","invalid");
				nextPath="/JSP/Login.jsp";
			}
			else if(username.equals("ADMIN") )
			{
				request.setAttribute("username","ADMIN");
				session.setAttribute("emailid", "ADMIN");
				nextPath="/JSP/Portal.jsp";
			}
			else
			{
				session.setAttribute("emailid", request.getParameter("uname"));
				request.setAttribute("username", username);
				ArrayList<BusinessBean> queryList= 	ls.getQueries();
					request.setAttribute("queryList",queryList);
				nextPath="/JSP/Viewquery.jsp";
			}
		}
		else if ("forgetpassword".equalsIgnoreCase(action)) {
			RegistrationService ls=new RegistrationService();
			boolean isSuccess=ls.forgetPassword(request);
			if(isSuccess==false)
				request.setAttribute("msg", "false");
			else
				request.setAttribute("msg", "true");
			nextPath="/JSP/ForgetPassword.jsp";
		}
		else if("changepwd".equals(action)){
			
			RegistrationService ls = new RegistrationService();
			int noofrowsaffected=ls.changepwd(request,(String)session.getAttribute("emailid"));
			System.out.println(session.getAttribute("emailid"));
			if(noofrowsaffected>0){
				request.setAttribute("message", "Password Change Successfully");
			}
			else{
				request.setAttribute("message", "Current Password does not match");
			}

			nextPath="/JSP/Changepassword.jsp";
			
			}
		else if("query".equals(action)){
			RegistrationService ls = new RegistrationService();
			ls.postQuery(request);
			nextPath="/JSP/Postquery.jsp";
			request.setAttribute("message", "Thank you for Posting Query\nSeniors will Reply Soon");
		}
		else if("viewpost".equals(action)){
			RegistrationService ls = new RegistrationService();
		ArrayList<BusinessBean> queryList= 	ls.getQueries();
			nextPath="/JSP/Viewquery.jsp";
			request.setAttribute("queryList",queryList);
		}
	
		else if("comments".equals(action)){
			RegistrationService ls = new RegistrationService();
		ArrayList<String> commentList= 	ls.getComment(request);
			nextPath="/JSP/Viewcomment.jsp";
			request.setAttribute("commentList",commentList);
			request.setAttribute("query",request.getParameter("query"));
		}
		else if("create event".equals(action)){
			RegistrationService ls = new RegistrationService();
			ls.createevent(request);
			nextPath="/JSP/AdminPortal.jsp";
			request.setAttribute("message", "Thank you for creating Event\nAll Alumni Will Get Notification");
		}
		else if("viewevents".equals(action)){
			RegistrationService ls = new RegistrationService();
			ArrayList<BusinessBean> events= 	ls.getevents();
				nextPath="/JSP/Viewevent.jsp";
				request.setAttribute("events",events);
		}
		else if("postcomment".equals(action)){
			RegistrationService ls = new RegistrationService();
			ls.insertComments(request);
			ArrayList<String> commentList= 	ls.getComment(request);
				nextPath="/JSP/Viewcomment.jsp";
				request.setAttribute("commentList",commentList);
				request.setAttribute("query",request.getParameter("query"));
		}
		
	
	
			RequestDispatcher rd=request.getRequestDispatcher(nextPath);	
			rd.forward(request, response);
		
		
		
	}

}
