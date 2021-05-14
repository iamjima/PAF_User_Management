package userApi;

import java.sql.*;

import connection.UserConnection;

public class User {

	UserConnection userConnection = new UserConnection();
    
	public String insertSeller(String email, String password, String name, String dob, String phone, String address) 
	{ 
		String output = ""; 
		try
		{ 
			Connection connect = userConnection.getConnection(); 
			if (connect == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
			// create a prepared statement
			String query = " insert into seller(`sellertID`,`email`,`password`,`name`,`dob`,`phone`,`address`)"
					+ " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = connect.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, password);
			preparedStmt.setString(4, name); 
			preparedStmt.setString(5, dob);
			preparedStmt.setFloat(6, Float.parseFloat(phone)); 
			preparedStmt.setString(7, address); 
			// execute the statement3
			preparedStmt.execute(); 
			connect.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	public String insertCustomer(String email, String password, String name, String dob, String phone, String address) 
	{ 
		String output = ""; 
		try
		{ 
			Connection connect = userConnection.getConnection(); 
			if (connect == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
			// create a prepared statement
			String query = " insert into customer(`sellertID`,`email`,`password`,`name`,`dob`,`phone`,`address`)"
					+ " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = connect.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, password);
			preparedStmt.setString(4, name); 
			preparedStmt.setString(5, dob);
			preparedStmt.setFloat(6, Float.parseFloat(phone)); 
			preparedStmt.setString(7, address); 
			// execute the statement3
			preparedStmt.execute(); 
			connect.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
    public String readSellers(){
    	
		String output = "";
		
		try{
			Connection connect = userConnection.getConnection();
			if (connect == null){
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output ="<table class='table table-bordered' style='border-color:#1A237E; overflow-x:auto;'>"
					+ "<thead>"
					+ "<tr>"
					+ "<th scope='col'>#</th>"
					+ "<th scope='col'>ID</th>"
					+ "<th scope='col'>Name</th>"
					+ "<th scope='col'>Phone</th>"
					+ "<th scope='col'>Email</th>"
					+ "<th scope='col'>Dob</th>"
					+ "<th scope='col'>Address</th>"
					+ "<th scope='col'>Password</th>"
					+ "<th scope='col'>Update</th>"
					+ "<th scope='col'>Delete</th>"
					+ "</tr>"
					+ "</thead>";
			String query = "select * from seller";
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int i=0;
			
			output +="<tbody>";
			
			// iterate through the rows in the result set
			while (rs.next()){
				i++;
				String hash = Integer.toString(i);
				String sellerID = Integer.toString(rs.getInt("sellerID"));
				String name = rs.getString("name");
				String phone = Float.toString(rs.getFloat("phone"));
				String email = rs.getString("email");
				String dob = rs.getString("dob");
				String address = rs.getString("address");
				String password = rs.getString("password");
				
				// Add into the html table
				output += "<tr>";
				output += "<td scope='row'>" + hash + "</td>";
				output += "<td>" + sellerID + "</td>";
				output += "<td>" + name + "</td>"; 
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + dob + "</td>"; 
				output += "<td>" + address + "</td>";
				output += "<td>" + password + "</td>";
				// buttons
				output += "<td>"
						+ "	  <button type='button' name='btnUpdate' class='btn operation-update btnUpdate' style='text-decoration: none; data-sellerid='" + sellerID + "'>"
						+ "     <svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-arrow-clockwise operation-update' viewBox='0 0 16 16'>"
						+ "  		<path fill-rule='evenodd' d='M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z'/>"
						+ "  		<path d='M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z'/>"
						+ "		</svg>Update"
						+ "   </button>"
						+ "</td>"
						+ "<td>"
						+ "   <button type='button' name='btnRemove' class='btn operation-delete btnRemove' style='text-decoration: none; data-sellerid='" + sellerID + "''>"
						+ "     <svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' fill='currentColor' class='bi bi-x operation-delete' viewBox='0 0 16 16'>"
						+ "  		<path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z'/>"
						+ "		</svg>Delete"
						+ "	  </button>"
						+ "</td>"
						+ "</tr>";
				}
				connect.close();
				output +="</tbody>";
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e){
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
		return output;
	}
    
    public String readCustomers(){
    	
		String output = "";
		
		try{
			Connection connect = userConnection.getConnection();
			if (connect == null){
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output ="<table class='table table-bordered' style='border-color:#1A237E; overflow-x:auto;'>"
					+ "<thead>"
					+ "tr>"
					+ "<th scope='col'>#</th>"
					+ "<th scope='col'>ID</th>"
					+ "<th scope='col'>Name</th>"
					+ "<th scope='col'>Phone</th>"
					+ "<th scope='col'>Email</th>"
					+ "<th scope='col'>Dob</th>"
					+ "<th scope='col'>Address</th>"
					+ "<th scope='col'>Password</th>"
					+ "<th scope='col'>Update</th>"
					+ "<th scope='col'>Delete</th>"
					+ "</tr>"
					+ "</thead>";
			String query = "select * from customer";
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int i=0;
			
			output += "<tbody>";
			
			// iterate through the rows in the result set
			while (rs.next()){
				i++;
				String hash = Integer.toString(i);
				String sellerID = Integer.toString(rs.getInt("customerID"));
				String name = rs.getString("name");
				String phone = Float.toString(rs.getFloat("phone"));
				String email = rs.getString("email");
				String dob = rs.getString("dob");
				String address = rs.getString("address");
				String password = rs.getString("password");
				
				// Add into the html table
				output += "<tr>";
				output += "<td scope='row'>" + hash + "</td>";
				output += "<td>" + sellerID + "</td>";
				output += "<td>" + name + "</td>"; 
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + dob + "</td>"; 
				output += "<td>" + address + "</td>";
				output += "<td>" + password + "</td>";
				// buttons
				output += "<td>"
						+ "	  <button type='button' name='btnUpdate' class='btn operation-update btnUpdate' style='text-decoration: none; data-customerid='" + sellerID + "'>"
						+ "     <svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-arrow-clockwise operation-update' viewBox='0 0 16 16'>"
						+ "  		<path fill-rule='evenodd' d='M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z'/>"
						+ "  		<path d='M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z'/>"
						+ "		</svg>Update"
						+ "   </button>"
						+ "</td>"
						+ "<td>"
						+ "   <button type='button' name='btnRemove' class='btn operation-delete btnRemove' style='text-decoration: none; data-customerid='" + sellerID + "''>"
						+ "     <svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' fill='currentColor' class='bi bi-x operation-delete' viewBox='0 0 16 16'>"
						+ "  		<path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z'/>"
						+ "		</svg>Delete"
						+ "	  </button>"
						+ "</td>"
						+ "</tr>";
						
				}
				connect.close();
				output += "</tbody>";
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e){
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
		return output;
	}
    
    public String updateSeller(String ID, String email, String password, String name, String dob, String phone, String address)
    { 
    	String output = ""; 
    try
    { 
    	Connection connect = userConnection.getConnection(); 
    	if (connect == null) 
    	{
    		return "Error while connecting to the database for updating."; 
    	} 
    	// create a prepared statement
    	String query = "UPDATE seller SET email=?, password=?, name=?, dob=?, phone=?, address=? WHERE sellerID=?"; 
    	
    	PreparedStatement preparedStmt = connect.prepareStatement(query); 
    	// binding values
    	preparedStmt.setString(1, email); 
    	preparedStmt.setString(2, password); 
    	preparedStmt.setString(3, name); 
    	preparedStmt.setString(4, dob); 
    	preparedStmt.setFloat(5, Float.parseFloat(phone)); 
    	preparedStmt.setString(6, address); 
    	preparedStmt.setInt(7, Integer.parseInt(ID)); 
    	// execute the statement
    	preparedStmt.execute(); 
    	connect.close(); 
    	output = "Updated successfully"; 
    	} 
    	catch (Exception e) 
    	{ 
    		output = "Error while updating the item."; 
    		System.err.println(e.getMessage()); 
    	} 
    	return output; 
    } 
    
    public String updateCustomer(String ID, String email, String password, String name, String dob, String phone, String address)
    { 
    	String output = ""; 
    try
    { 
    	Connection connect = userConnection.getConnection(); 
    	if (connect == null) 
    	{
    		return "Error while connecting to the database for updating."; 
    	} 
    	// create a prepared statement
    	String query = "UPDATE customer SET email=?, password=?, name=?, dob=?, phone=?, address=? WHERE sellerID=?"; 
    	
    	PreparedStatement preparedStmt = connect.prepareStatement(query); 
    	// binding values
    	preparedStmt.setString(1, email); 
    	preparedStmt.setString(2, password); 
    	preparedStmt.setString(3, name); 
    	preparedStmt.setString(4, dob); 
    	preparedStmt.setFloat(5, Float.parseFloat(phone)); 
    	preparedStmt.setString(6, address); 
    	preparedStmt.setInt(7, Integer.parseInt(ID)); 
    	// execute the statement
    	preparedStmt.execute(); 
    	connect.close(); 
    	output = "Updated successfully"; 
    	} 
    	catch (Exception e) 
    	{ 
    		output = "Error while updating the item."; 
    		System.err.println(e.getMessage()); 
    	} 
    	return output; 
    } 
    
    public String deleteSeller(String sellerID){
    	
		String output = "";
		
		try{
			Connection connect = userConnection.getConnection();
			if (connect == null){
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from seller where sellerID=?";
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(sellerID));

			// execute the statement
			preparedStmt.execute();
			connect.close();
			output = "Deleted successfully";
		}
		catch (Exception e){
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
    
    public String deleteCustomer(String customerID){
    	
		String output = "";
		
		try{
			Connection connect = userConnection.getConnection();
			if (connect == null){
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from customer where customerID=?";
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerID));

			// execute the statement
			preparedStmt.execute();
			connect.close();
			output = "Deleted successfully";
		}
		catch (Exception e){
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
