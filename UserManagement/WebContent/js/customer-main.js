$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
 	{ 
 		$("#alertSuccess").hide(); 
 	} 
 	$("#alertError").hide(); 
}); 

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
 	$("#alertSuccess").text(""); 
 	$("#alertSuccess").hide(); 
 	$("#alertError").text(""); 
 	$("#alertError").hide(); 
 	
	// Form validation-------------------
	var status = validateItemForm(); 
	if (status != true) 
 	{ 
 		$("#alertError").text(status); 
 		$("#alertError").show(); 
 		
 		return; 
 	} 
 	
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 	$.ajax( 
 	{ 
 		url : "CustomerApi", 
 		type : type, 
 		data : $("#formItem").serialize(), 
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemSaveComplete(response.responseText, status); 
 		} 
 	}); 
});

function onItemSaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response);
 		console.log(resultSet);
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully saved."); 
 			$("#alertSuccess").show(); 
 			$("#divItemsGrid").html(resultSet.data); 
 		} 
 		else if (resultSet.status.trim() == "error") 
 		{ 
 			$("#alertError").text(resultSet.data); 
 			$("#alertError").show(); 
 		} 
 	} 
 	else if (status == "error") 
 	{ 
 		$("#alertError").text("Error while saving."); 
 		$("#alertError").show(); 
 	} 
 	else
 	{ 
 		$("#alertError").text("Unknown error while saving.."); 
 		$("#alertError").show(); 
 	} 
 	
 	$("#hidItemIDSave").val(""); 
 	$("#formItem")[0].reset();
}

// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{    
	$("#hidItemIDSave").val($(this).data("customerid"));     
	$("#name").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#phone").val($(this).closest("tr").find('td:eq(3)').text());
	$("#email").val($(this).closest("tr").find('td:eq(4)').text());
	$("#dob").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#address").val($(this).closest("tr").find('td:eq(6)').text());
	$("#address").val($(this).closest("tr").find('td:eq(7)').text());
	 
}); 

// DELETE =============================================================
$(document).on("click", ".btnRemove", function(event)
{ 
	console.log($(this).data("productid"));
 	$.ajax( 
 	{ 
 		url : "CustomerApi", 
 		type : "DELETE", 
 		data : "customerid=" + $(this).data("customerid"),
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemDeleteComplete(response.responseText, status); 
 		} 
 	}); 
});

function onItemDeleteComplete(response, status)
{ 
	console.log(JSON.parse(response));
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully deleted."); 
 			$("#alertSuccess").show(); 
 			$("#divItemsGrid").html(resultSet.data); 
 		} 
 		else if (resultSet.status.trim() == "error") 
 		{ 
 			$("#alertError").text(resultSet.data); 
 			$("#alertError").show(); 
 		} 
 	} 
 	else if (status == "error") 
 	{ 
 		$("#alertError").text("Error while deleting."); 
 		$("#alertError").show(); 
 	} 
 	else
 	{ 
 		$("#alertError").text("Unknown error while deleting.."); 
 		$("#alertError").show(); 
 	} 
}

// CLIENT-MODEL========================================================================= 
function validateItemForm() 
{  
	 
	if ($("#name").val().trim() == "")  
	{   
		return "Insert Name";  
	} 
 
	 
	if ($("#phone").val().trim() == "")  
	{   
		return "Insert Phone.";  
	} 
	// category  
	if ($("#dob").val().trim() == "")  
	{   
		return "Insert Date of Birth.";  
	}   
	
	// stock  
	if ($("#email").val().trim() == "")  
	{   
		return "Insert email.";  
	}
	 
	// description
	if ($("#address").val().trim() == "")  
	{   
		return "Insert address.";  
	}
	if ($("#password").val().trim() == "")  
	{   
		return "Insert password.";  
	}

	return true; 
}