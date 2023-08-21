

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import db.DAOQuires;

/**
 * Servlet implementation class Business
 */
public class Business extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Business() {
        super();
        // TODO Auto-generated constructor stub
    }
String imeino="";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	static String username ="";
	Map<String,String> respMap= new HashMap<String, String>();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextpath="";
		
		String UPLOAD_DIRECTORY = "C:";                 	
		String action=request.getParameter("action");
		String strName=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String state=request.getParameter("state");
		String board=request.getParameter("board");
		String division=request.getParameter("division");
		String meter=request.getParameter("meter");
		System.out.println("Action="+action);
		HttpSession session = request.getSession(true);
		if("addElectric".equals(action))
		{		
			imeino = strName;
			System.out.println(strName+ email+ password);
			new DAOQuires().insertComplaint(strName, password);
			respMap.put("error", "false");
			respMap.put("status", "true");
			sendResponseToApp(request,response);
		}
		if("close".equals(action))
		{			
			String item=request.getParameter("IMEI");
			new DAOQuires().update(item);
			respMap.put("error", "true");
			respMap.put("status", "true");
			sendResponseToApp(request,response);
		}
		if("getList".equalsIgnoreCase(action))
		{			
			String list = new DAOQuires().getList();
			respMap.put("status", list);
	      
	      	sendResponseToApp(request,response);
		}
		if(action == null)
		{
			if(imeino!=null)
			{


				
				
				
				System.out.println("Path="+imeino);
				File file = new File(UPLOAD_DIRECTORY);
				if (!file.exists()) {
					if (file.mkdir()) {
						System.out.println("Directory is created!");
					} else {
						System.out.println("Failed to create directory!");
					}
				}
				 String name=null;
				System.out.println(UPLOAD_DIRECTORY);
				if(ServletFileUpload.isMultipartContent(request)){
		            try {
		                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		              
		                for(FileItem item : multiparts){
		                    if(!item.isFormField()){
		                         name = new File(item.getName()).getName();
		                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
		                    }
		                }
		           
		               //File uploaded successfully
		                
		                // create Encrypt folder
		            
		                //Encrypt the File
		                new DAOQuires().insertFileReg(name,imeino);
		            } catch (Exception ex) {
		            	System.out.println(ex);
		            	System.out.println("not success");
		               request.setAttribute("messae", "File Upload Failed due to " + ex);
		            }          
		         
		        }else{
		         	System.out.println("not success");
		            request.setAttribute("message",
		                                 "Sorry this Servlet only handles file upload request");
		        }
				
			
			}
			if(strName == null)
			{
				if(new DAOQuires().checkLogin(email,password) == null)
				{
					//session.setAttribute("emailid",email);
					respMap.put("error", "true");
						respMap.put("error_msg", "Unable to insert the record");
				}
				else{
					respMap.put("error", "false");
					respMap.put("error_msg", "Login Success");
					//session.setAttribute("emailid",email);
				}
				sendResponseToApp(request,response);	
			}
			else if(new DAOQuires().insertReg(strName,email,password,state,board,division,meter) == false)
			{
					respMap.put("error", "true");
					respMap.put("error_msg", "Unable to insert the record");
			}
			else{}
			
			
			
			sendResponseToApp(request,response);
			
			
		}
		respMap.put("status", "true");
		sendResponseToApp(request,response);
     
	
	}
	private String prepareParametersToSend() {		
		Gson gson = new Gson();
		Type respMapType = new TypeToken<Map<String,String>>() {}.getType();
		return gson.toJson(respMap,respMapType);
	}
	public void sendResponseToApp(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{		
		try {
			String respString = prepareParametersToSend();
			
			 DataInputStream in = 
		                new DataInputStream((InputStream)request.getInputStream());
		        response.setContentType("text/plain");
		        response.setContentLength(respString.length());
		        PrintWriter out = response.getWriter();
		        out.println(respString);
		        in.close();
		        out.close();
		        out.flush();
		        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
