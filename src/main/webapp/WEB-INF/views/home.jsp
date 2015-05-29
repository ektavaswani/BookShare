<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>BookShare!</title>
<link rel="stylesheet" href="resources/css/bootstrap.css"></link>
<link rel="stylesheet" href="resources/css/bookshare.css"></link>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/jsrender.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
</head>
<body class="content" onload="javascript:validate_login();">
	<nav class="navbar">
  		<div class="container-fluid">
  			<div class="navbar-header">
      			<a class="navbar-brand" href="#">
      				<h3 class="brand"><b>BookShare!</b></h3>
      			</a>
    		</div>
  		  	<ul class="nav navbar-nav">
				<li class="navbar-text">
					<p class="navbar-text">
						<a href="#">Home</a>
					</p>
				</li>
				<li class="navbar-text">
					<p class="navbar-text">
						<a href="#" data-toggle="modal" data-target="#profile">Profile</a>
					</p>
				</li>
        <li class="navbar-text">
          <p class="navbar-text">
            <a href="#" data-toggle="modal" data-target="#addEntry">Add Entry</a>
          </p>
        </li>
        <li class="navbar-text">
          <p class="navbar-text">
            <a href="#">Offers</a>
          </p>
        </li>
		</ul>
		 <ul class="nav navbar-nav navbar-right">
			<li class="navbar-text navbar-right">
         		<p class="navbar-text">
           			<a href="javascript:logout();">Logout</a>
         		</p>
        	</li>
		</ul>
  		</div>
	</nav>


	<form class="searchbook" action="#">
        <div class="row">
         <div class="col-md-8"> <input type="text" class="form-control" placeholder="Search Book by Name/ISBN/Author">
         </div>
         <button type="submit" class="btn btn-search"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
        </div>
    </form>

	<div class="row booksgrid">
  		<div class="col-md-1 indibook">
  			<p>
  				<b>Book2</b>-By Sumit<br/>
  				<img default="Book" src="resources/img/books.png" width="20%"></img>
  				<p>
  					Price : $3<br/>
  					Condition : New<br/>
  					Seller Rating : 3*<br/>
  				</p>
  				<form action="#">
  					<input type="hidden" value="book1" name="bookid"></input>
  					<input type="submit" value="Buy" class="btn btn-success"></input>
  				</form>
  				<br/>
  			</p>
  		</div>
  		<div class="col-md-1 indibook">
  			<p>
  				<b>Book2</b>-By Ekta<br/>
  				<img default="Book" src="resources/img/books.png" width="20%"></img>
  				<p>
  					Price : $3<br/>
  					Condition : New<br/>
  					Seller Rating : 3*<br/>
  				</p>
  				<form action="#">
  					<input type="hidden" value="book1" name="bookid"></input>
  					<input type="submit" value="Buy" class="btn btn-success"></input>
  				</form>
  				<br/>
  			</p>
  		</div>
  		<div class="col-md-1 indibook">
  			<p>
  				<b>Book2</b>-By Hitesh<br/>
  				<img default="Book" src="resources/img/books.png" width="20%"></img>
  				<p>
  					Price : $3<br/>
  					Condition : New<br/>
  					Seller Rating : 3*<br/>
  				</p>
  				<form action="#">
  					<input type="hidden" value="book1" name="bookid"></input>
  					<input type="submit" value="Buy" class="btn btn-success"></input>
  				</form>
  				<br/>
  			</p>
  		</div>
  		<div class="col-md-1 indibook">
  			<p>
  				<b>Book2</b>-By Ramnivas<br/>
  				<img default="Book" src="resources/img/books.png" width="20%"></img>
  				<p>
  					Price : $3<br/>
  					Condition : New<br/>
  					Seller Rating : 3*<br/>
  				</p>
  				<form action="#">
  					<input type="hidden" value="book1" name="bookid"></input>
  					<input type="submit" value="Buy" class="btn btn-success"></input>
  				</form>
  				<br/>
  			</p>
  		</div>

  		<br/>
  		
  		<div class="col-md-1 indibook">
  			<p>
  				<b>Book2</b>-By Sumit<br/>
  				<img default="Book" src="resources/img/books.png" width="20%"></img>
  				<p>
  					Price : $3<br/>
  					Condition : New<br/>
  					Seller Rating : 3*<br/>
  				</p>
  				<form action="#">
  					<input type="hidden" value="book1" name="bookid"></input>
  					<input type="submit" value="Buy" class="btn btn-success"></input>
  				</form>
  				<br/>
  			</p>
  		</div>
  		<div class="col-md-1 indibook">
  			<p>
  				<b>Book2</b>-By Ekta<br/>
  				<img default="Book" src="resources/img/books.png" width="20%"></img>
  				<p>
  					Price : $3<br/>
  					Condition : New<br/>
  					Seller Rating : 3*<br/>
  				</p>
  				<form action="#">
  					<input type="hidden" value="book1" name="bookid"></input>
  					<input type="submit" value="Buy" class="btn btn-success"></input>
  				</form>
  				<br/>
  			</p>
  		</div>
  		<div class="col-md-1 indibook">
  			<p>
  				<b>Book2</b>-By Hitesh<br/>
  				<img default="Book" src="resources/img/books.png" width="20%"></img>
  				<p>
  					Price : $3<br/>
  					Condition : New<br/>
  					Seller Rating : 3*<br/>
  				</p>
  				<form action="#">
  					<input type="hidden" value="book1" name="bookid"></input>
  					<input type="submit" value="Buy" class="btn btn-success"></input>
  				</form>
  				<br/>
  			</p>
  		</div>
  		<div class="col-md-1 indibook">
  			<p>
  				<b>Book2</b>-By Ramnivas<br/>
  				<img default="Book" src="resources/img/books.png" width="20%"></img>
  				<p>
  					Price : $3<br/>
  					Condition : New<br/>
  					Seller Rating : 3*<br/>
  				</p>
  				<form action="#">
  					<input type="hidden" value="book1" name="bookid"></input>
  					<input type="submit" value="Buy" class="btn btn-success"></input>
  				</form>
  				<br/>
  			</p>
  		</div>
  		<br/><br/><br/><br/><br/><br/>
	</div>
</div>
	<div class="footer">
		<b> All Rights Reserved BookShare!</b>
	</div>

<!-- Modal -->
<div class="modal fade" id="profile" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
<script id="profileTemplate" type="text/x-jsrender">
<div>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">BookShare! Profile</h4>
      </div>
      <div class="modal-body">
          <form action="#">      
            <label>Email ID </label>: {{>email}}
            <br/>
            <label>Name : </label>{{>firstName}} {{>lastName}}
			<br/>
			  <label>Major : </label>{{>major}}
            <br/><br/>
			
          </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#changeMajor">Change Major</button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#changePassword">Change Password</button>
      </div>
    </div>
  </div>
</div>
</script>


<!-- Modal change major -->
<div class="modal fade" id="changeMajor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">BookShare! Profile</h4>
      </div>
      <div class="modal-body">
          <form action="#" id="changeMajorForm">      
           <select name="major">
           <option>Computer Science</option>
           <option>Software Engineering</option>
           <option>Electrical Engineering</option>
           <option>Engineering Management</option>
           </select>
          </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-dismiss="modal" id="changeMajor">Save</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal profile-->


<div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">BookShare! Profile</h4>
      </div>
      <div class="modal-body">
          <form action="#" id="changePasswordForm">      
            <label>Current Password</label><br/>
            <input type="password" class="form-control col-md-4" required placeholder="Current Password">
            <br/><br/>
            <label>New Password</label><br/>
            <input type="password" class="form-control col-md-4" required placeholder="New Password">
            <br/><br/>
            <label>Confirm Password</label><br/>
            <input type="password" class="form-control col-md-4" required placeholder="Confirm Password">
            <br/><br/>
          </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-dismiss="modal" id="changePassword">Change Password</button>
      </div>
    </div>
  </div>
</div>





<!-- Modal addEntry-->
<div class="modal fade" id="addEntry" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">BookShare! Add New Book</h4>
      </div>
      <div class="modal-body">
           <form action="#">      
            <label>Book Title</label><br/>
            <input type="text" name="book_title" class="form-control col-md-4" placeholder="Book Title">
            <br/><br/>
            <label>Author</label><br/>
            <input type="text" name="author" class="form-control col-md-4" placeholder="Author">
            <br/><br/>
            <label>ISBN</label><br/>
            <input type="text" name="isbn" class="form-control col-md-4" placeholder="Book ISBN">
            <br/><br/>
            <label>Price</label><br/>
            <input type="number" name="price" class="form-control col-md-4" placeholder="Book price">
            <br/><br/>
          </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-toggle="modal" onlick="add_new_entry()">Save</button>
      </div>
    </div>
  </div>
</div>



<script type="text/javascript" src="resources/js/BookShare.js"></script>
</body>
</html>