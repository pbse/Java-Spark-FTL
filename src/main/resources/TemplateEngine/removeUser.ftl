 <style>
 table th a {
 	text-transform: capitalize;
 }
 </style>

   <div class="starter-template">
    	<h2> Remove User </h2>
    	<p id="status"></p>
  		<form action="" method="POST" role="form">
    		<div class="form-group">
      			<label for="id">Select Id to Remove</label>
      			<select >
    		</div>
   </div>	
 	<script src="js/awesomeTable.js" type="text/javascript"></script>
 	<script>
 		$( document ).ready(function() {
 			$.getJSON('/getusers',function(json){
    			if ( json.length == 0 ) {
        			console.log("NO DATA!");
        			$(".userTable").text("No Users Found");
    			}
    			else {
    				var tbl = new awesomeTableJs({
						data:json,
						tableWrapper:".userTable",
						paginationWrapper:".paginationContainer",
						buildPageSize: false,
						buildSearch: false,
					});
					tbl.createTable();	
    			}
			});
 			
		});
	
	</script>