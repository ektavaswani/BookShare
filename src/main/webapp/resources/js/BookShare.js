/**
 * 
 */

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};



//signup handing
$(function() {
    $('#createnew').click(function(event) {
    	if(!this.checkValidity())
    		{
    			console.log("failed in validation");
  				event.preventDefault();
				return false;
			} else {
			console.log("validation successful");
				event.preventDefault();
    			$.ajax({
    				url : "/"+$( '#createnewform' ).attr( 'action' ),
    				data :  JSON.stringify($('#createnewform').serializeObject()),
    				type : "POST",
    				contentType : "application/json",
    				success: function(result) {
    					window.location.replace("/");
    				},
    				failure : function(result) {
    					console.log(result);
    					alert("Some unexpected error occurred, Please try later");
    				}
    			}).error(function(status, result, xhr){
    				alert(JSON.parse(status.responseText).message);
    			});
    		}  
    	});
});

//logout
function logout(){
	$.ajax({
		url : "/logout",
		data : getStoredUserInfo(),
		type : "POST",
		success: function(result) {
			var resultObj = JSON.parse(result);
			if(resultObj.success == "true") {
				clearStoredUserInfo();
			    window.location.replace("/");
			} else {
				clearStoredUserInfo();
			    window.location.replace("/");
			}
		},
		failure : function(result) {
			clearStoredUserInfo();
		    window.location.replace("/");
		},
		error: function(jqXHR, exception) {
			clearStoredUserInfo();
		    window.location.replace("/");
        }
	}).error(function(status, result, xhr){
		alert(JSON.parse(status.responseText).message);
	});
}  
    


//login handling

$(function() {
    $('#login').click(function(event) {
    	if(!this.checkValidity())
    		{
    			console.log("failed in validation");
  				event.preventDefault();
				return false;
			} else {
			console.log("validation successful");
				event.preventDefault();
    			$.ajax({
    				url : "/"+$( '#loginform' ).attr( 'action' ),
    				data :  JSON.stringify($('#loginform').serializeObject()),
    				type : "POST",
    				contentType : "application/json",
    				success: function(result) {
    					console.log(result);
    					var resultObj = JSON.parse(result);
    					if(resultObj.success == "true") {
    						storeUserInfo(resultObj.token, resultObj.email);
    						window.location.replace("/home");
    					} else {
    						alert("Invalid login details");
    					}
    				},
    				failure : function(result) {
    					console.log("login failed");
    					
    					alert("Some unexpected error occurred, Please try later");
    				},
    				error: function(jqXHR, exception) {
    		            if (jqXHR.status === 401) {
    		            	console.log(jqXHR);
    		                location.href="/?error_message=Invalid username or password!";
    		            } else {
    		                alert('Error Logging in try again');
    		            }
    		        }
    			}).error(function(status, result, xhr){
    				alert(JSON.parse(status.responseText).message);
    			});
    		}  
    	});
});

function validate_login() {

	$.ajax({
		url : "/getuser",
		data :  getStoredUserInfo(),
		type : "POST",
		success: function(result) {
			$("#profile").html(
					$("#profileTemplate").render(result)
					);
		},
		failure : function(result) {
			console.log(result);
			alert("Some unexpected error occurred, Please try later");
		}
	}).error(function(status, result, xhr){
		alert(JSON.parse(status.responseText).message);
	});

}


function supportsLocalStorage()
{
	try {
		return 'localStorage' in window && window['localStorage'] !== null;
	} 
	catch (e)  {
		return false;
	}
}

function storeUserInfo(token, email)
{
	if(supportsLocalStorage()) {
		localStorage.setItem("token", token);
		localStorage.setItem("email", email);
	}
	else {
		console.log("Browser doesn't support local storage");
	}
}

function getStoredUserInfo()
{
	if(supportsLocalStorage()) {
		return {
			"token": localStorage.getItem("token"),
			"email": localStorage.getItem("email")}
	} else {
		console.log("Browser doesn't support local storage");
	}
}

function clearStoredUserInfo()
{
	if(supportsLocalStorage()) {
		localStorage.removeItem("token");
		localStorage.removeItem("email");
	} else {
		console.log("Browser doesn't support local storage");
	}
}