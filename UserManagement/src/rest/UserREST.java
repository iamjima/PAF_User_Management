package rest;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

import userApi.User;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserREST {
	
	//Product prductObj = new Product();
	User obj= new User();
	
	@GET
	@Path("/Sellers")
	@Produces(MediaType.TEXT_HTML)
	public String readSellers() {
		
		String output="<html>"
				+ "   <head>"
				+ "       <meta charset='utf-8'>"
				+ "       <meta name='viewport' content='width=device-width, initial-scale=1'>"
				+ "       <title>Manage Products</title>"
				+ "       <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl' crossorigin='anonymous'>"
				+ "       <link rel='stylesheet' href='../css/product-list.css'>"
				+ "       <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js' integrity='sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0' crossorigin='anonymous'></script>"
				+ "		  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js' integrity='sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi' crossorigin='anonymous'></script>"
				+ "		  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js' integrity='sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG' crossorigin='anonymous'></script>"
				+ "    	  <script type='text/javascript'></script>"
				+ "   </head>"
				+ "   <body oncontextmenu='return false' class='snippet-body'>"
				+ "       <div class='container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto'>"
				+ "    	  <div class='cad card0 border-0'>"
				+ "        	 <div class='row d-flex'>"
				+ "            	<div class='col-lg-10'>"
				+ "                	<div class='card2 card border-0 px-4 py-5'>"
				+ "                		<div class='row mb-4 px-3'>"
				+ "                			<h1 class='mb-5 mr-4 mt-2 left' style='margin-left: -24px;'>Seller Management</h1>"
				+ 							obj.readSellers()
				+ "                		</div>"
				+ "            		</div>"
				+ "        	 	</div>"
				+ "      	  </div>"
				+ "   	   </div>"
				+ "   	 </div>"
				+ "   </body>"
				+ "</html>";
		return output;	
	}
	
	@POST
	@Path("/Sellers")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertSeller(@FormParam("email") String email, 
			 @FormParam("password") String password, 
			 @FormParam("name") String name, 
			 @FormParam("dob") String dob,
			 @FormParam("phone") String phone,
			 @FormParam("address") String address){
		
		String output = obj.insertSeller(email, password, name, dob, phone, address); 
		return output; 
	}
	
	@PUT
	@Path("/Seller")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateSeller(String sellerData) {
		
		//Convert the input string to a JSON object 
		 JsonObject itemObject = new JsonParser().parse(sellerData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String sellerID = itemObject.get("sellerID").getAsString(); 
		 String email = itemObject.get("email").getAsString(); 
		 String password = itemObject.get("password").getAsString(); 
		 String name = itemObject.get("name").getAsString(); 
		 String dob = itemObject.get("dob").getAsString(); 
		 String phone = itemObject.get("phone").getAsString();
		 String address = itemObject.get("address").getAsString();
		 String output = obj.updateCustomer(sellerID, email, password, name, dob, phone, address); 
		return output; 
	}
	
	@DELETE
	@Path("/Seller")
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteSeller(String sellerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(sellerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String sellerID = doc.select("sellerID").text(); 
	 String output = obj.deleteSeller(sellerID); 
	return output; 
	}
	
	@GET
	@Path("/Customers")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers() {
		
		String output="<html>"
				+ "   <head>"
				+ "       <meta charset='utf-8'>"
				+ "       <meta name='viewport' content='width=device-width, initial-scale=1'>"
				+ "       <title>Manage Products</title>"
				+ "       <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl' crossorigin='anonymous'>"
				+ "       <link rel='stylesheet' href='../css/product-list.css'>"
				+ "       <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js' integrity='sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0' crossorigin='anonymous'></script>"
				+ "		  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js' integrity='sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi' crossorigin='anonymous'></script>"
				+ "		  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js' integrity='sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG' crossorigin='anonymous'></script>"
				+ "    	  <script type='text/javascript'></script>"
				+ "   </head>"
				+ "   <body oncontextmenu='return false' class='snippet-body'>"
				+ "       <div class='container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto'>"
				+ "    	  <div class='cad card0 border-0'>"
				+ "        	 <div class='row d-flex'>"
				+ "            	<div class='col-lg-10'>"
				+ "                	<div class='card2 card border-0 px-4 py-5'>"
				+ "                		<div class='row mb-4 px-3'>"
				+ "                			<h1 class='mb-5 mr-4 mt-2 left' style='margin-left: -24px;'>Customer Management</h1>"
				+ 							obj.readCustomers()
				+ "                		</div>"
				+ "            		</div>"
				+ "        	 	</div>"
				+ "      	  </div>"
				+ "   	   </div>"
				+ "   	 </div>"
				+ "   </body>"
				+ "</html>";
		return output;	
	}
	
	@POST
	@Path("/Customers")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertCustomer(@FormParam("email") String email, 
			 @FormParam("password") String password, 
			 @FormParam("name") String name, 
			 @FormParam("dob") String dob,
			 @FormParam("phone") String phone,
			 @FormParam("address") String address){
		
		String output = obj.insertCustomer(email, password, name, dob, phone, address); 
		return output; 
	}
	
	@PUT
	@Path("/Customer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String customerData) {
		
		//Convert the input string to a JSON object 
		 JsonObject itemObject = new JsonParser().parse(customerData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String customerID = itemObject.get("customerID").getAsString(); 
		 String email = itemObject.get("email").getAsString(); 
		 String password = itemObject.get("password").getAsString(); 
		 String name = itemObject.get("name").getAsString(); 
		 String dob = itemObject.get("dob").getAsString(); 
		 String phone = itemObject.get("phone").getAsString();
		 String address = itemObject.get("address").getAsString();
		 String output = obj.updateCustomer(customerID, email, password, name, dob, phone, address); 
		return output; 
	}
	
	@DELETE
	@Path("/Customer")
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCustomer(String customerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String customerID = doc.select("customerID").text(); 
	 String output = obj.deleteCustomer(customerID); 
	return output; 
	}
}
