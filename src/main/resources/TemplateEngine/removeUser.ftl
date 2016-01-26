<div class="starter-template">
	<h2> Remove User </h2>
    	<p id="status"></p>
  	<div class="form-group">
      		<label for="id">Select Id to Remove</label>
      		<select id="users" name="userid"></select>
    	</div>
      <button type="submit" class="btn btn-default">Submit</button>
      <p id="status"></p>
</div>	

<script>
$( document ).ready(function() {
      var user = ${users};
      var sel = $('#users');
      $.each(user, function(key,val){
        sel.append('<option value="' + val + '">' + val + '</option>');   
      });
      $("button").on("click", function(e) {
      	e.preventDefault();
        var this_ = $(this);
        var arr = $("#users").val();
        // Ajax Call
        $.ajax({
            type: "PUT",
            url: 'removeUser/' + arr,
            success : function(e) {
                $("#users option:selected").remove();
                $("#status").text(e);
            },
            error : function(e) {
                $("#status").text(e);
            }
        });
        return false;
    });
});
	
</script>
