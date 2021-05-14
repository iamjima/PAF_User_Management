package userApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerApi
 */
@WebServlet("/CustomerApi")
public class CustomerApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerApi() {
        super();
        // TODO Auto-generated constructor stub
    }

    User Obj = new User();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output=Obj.insertCustomer(request.getParameter("email"), 
				request.getParameter("password"), 
				request.getParameter("name"), 
				request.getParameter("dob"),
				request.getParameter("phone"),
				request.getParameter("address"));
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 
		 String output = Obj.updateCustomer(paras.get("hidItemIDSave").toString(),     
				 paras.get("email").toString(),     
				 paras.get("password").toString(),        
				 paras.get("name").toString(),
				 paras.get("dob").toString(),
				 paras.get("phone").toString(),
				 paras.get("address").toString()); 
		 
		 response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 
		 String output = Obj.deleteCustomer(paras.get("customerid").toString()); 
		 
		 response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map 
				private static Map getParasMap(HttpServletRequest request) 
				{  
					Map<String, String> map = new HashMap<String, String>();  
					try  
					{   
						Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
						String queryString = scanner.hasNext() ?          
								scanner.useDelimiter("\\A").next() : "";  
								scanner.close(); 
				 
				  String[] params = queryString.split("&");   
				  for (String param : params)   
				  { 

			 
				   String[] p = param.split("=");    
				   map.put(p[0], p[1]);   
				   }  
				  }  
					catch (Exception e)  
					{  
						System.out.println(e);
					}  
					
					System.out.println(map);
					return map; 
					
				}

}
