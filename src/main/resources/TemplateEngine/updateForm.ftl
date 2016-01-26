<h2>Enter Details of Exisiting User to Update</h2>
   <p id="status"></p>
  <form action="" method="POST" role="form">
    <div class="form-group">
      <label for="id">User ID (Less than 8 Characters)</label>
      <input type="text" class="form-control" id="id" name="id" placeholder="Enter User ID to Update">
    </div>
    <div class="form-group">
      <label for="firstName">First Name</label>
      <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter First Name">
    </div>
    <div class="form-group">
      <label for="middleName">Middle Name</label>
      <input type="text" class="form-control" id="middleName" name="middleName" placeholder="Enter Middle Name">
    </div>
    <div class="form-group">
      <label for="lastName">Last Name</label>
      <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter Last Name">
    </div>
    <div class="form-group">
      <label for="age">Age</label>
      <input type="number" class="form-control" id="age" name="age">
    </div>
    <div class="form-group">
      <label for="phone">Phone Number (Must be of 10 Digits)</label>
      <input type="number" class="form-control" id="phone" name="phone">
    </div>
    <div class="form-group">
      <label for="gender">Gender</label>
      <select class="form-control" id="gender" name="gender">
        <option value="M">M</option>
        <option value="F">F</option>
      </select>
    </div>
    <div class="form-group">
      <label for="zip">Zip Code</label>
      <input type="number" class="form-control" id="zip" name="zip" value=0>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>

<!-- Simple JS Function to convert the data into JSON and Pass it as ajax Call --!>
<script>
$(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        var this_ = $(this);
        var array = this_.serializeArray();
        var json = {};
        $.each(array, function() {
            json[this.name] = this.value || '';
        });
        json = JSON.stringify(json);
    
        // Ajax Call
        $.ajax({
            type: "POST",
            url: "updateUser",
            data: json,
            dataType: "json",
            success : function() {
                $("#status").text("User SuccesFully Updated");
                this_.find('input, select').val('');
            },
            error : function(e) {
                $("#status").text(e.responseText);
            }
        });
        $("html, body").animate({ scrollTop: 0 }, "slow");
        return false;
    });
});

</script>