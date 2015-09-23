<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>SG Customs Demo</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/starter-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- 
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
 	-->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Singapore Customs Demo</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="./">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">

      <div class="starter-template">
        <h1>Company Vault</h1>
        <p class="lead">Manage Documents </p>

        <div style="width:40%;margin: 0 auto;" id="optionsPanel">
			<ul class="list-group">
			  <li style="cursor: pointer;" class="list-group-item" id="addDocumentItem">Add Document</li>
			  <li style="cursor: pointer;" class="list-group-item" id="listAllDocumentsItem">List All Documents</li>
			  <li style="cursor: pointer;" class="list-group-item" id="viewItem">view Files</li>
			  <li style="cursor: pointer;" class="list-group-item" id="deleteItem">Delete</li>
			</ul>        	
        </div>
      	<div style="width:50%;margin: 0 auto;display:none" id="addDocumentForm">
      		<h4>Add Document</h4>
			<form role="form" enctype="multipart/form-data">
	 			<div>
					<span>Upload file: </span>
					<span class="btn btn-default btn-file">
					    Browse <input type="file" id="fileUpload" name="fileUpload"/>
					</span>
					<div id='chosenFile'>&nbsp;</div>
				</div>		  
				<br/>
				 <button id="addDocumentBtn" type="button" class="btn btn-default">Add Document</button>
				
 			</form>		
      	</div>

      <div style="width:50%;margin: 0 auto;display:none" id="listAllDocumentsForm">
		<form role="form">
<!-- 		  <div class="form-group">
		    <label for="companyId">Company ID:</label>
		    <input type="text" class="form-control" id="companyId">
		  </div>
		  <div class="form-group">
		    <label for="companyName">Company Name:</label>
		    <input type="text" class="form-control" id="companyName">
		  </div>
 -->
 	  <button id="listAllDocumentsBtn" type="button" class="btn btn-default">Get All Documents</button>
		</form>		
      </div>
      <div id="displayPanel">

      <div style="width:50%;margin: 0 auto;display:none" id="viewFilesForm">
		<form role="form">
		  <div class="form-group">
		    <label for="companyId">Company ID:</label>
		    <input type="text" class="form-control" id="companyId">
		  </div>
		  <div class="form-group">
		    <label for="companyName">Company Name:</label>
		    <input type="text" class="form-control" id="companyName">
		  </div>
		  <button id="viewBtn" type="button" class="btn btn-default">View File</button>
		</form>		
      </div>
	      <div id="displayPanel">
	
	      
	      </div>
      </div>
	<!-- 
	https://spring.io/guides/gs/consuming-rest-jquery/
	 -->
	</div>
    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug 
    
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  -->
  <script type="text/javascript">
  var currentDisplayed='optionsPanel';
  var file;
  function readyFn( jQuery ) {
	    
		$( ".btn" ).bind("click", function(e) {
			  btnClick(e);
		}); 
		$( ".list-group-item" ).bind("click", function(e) {
			  itemClick(e);
		}); 
		 $("input[type='file']").bind('change', function(event) {
/* 			 alert('inside event '+Object.keys(event));
			 alert($(this).val());
			 alert(Object.keys($('#fileUpload')));
			 alert(this.files[0].name);
			 alert(this.files[0].size); */
			 //alert(Object.keys(this.files[0]));
/* 			var file=this.files[0];
			 alert(file);
			 alert(this.files.length);
 */		    
			 $('#displayPanel').html('');
			file=this.files[0];
			$('#chosenFile').html('Selected file: '+file.name);
			//uploadFile(file);

		});		

	}	
  function itemClick(event){
		var targetId=event.target.id;
		$('#'+currentDisplayed).hide();
		currentDisplayed=targetId;
		if (targetId=='addDocumentItem') {
			$('#addDocumentForm').show();
		} 
		else if (targetId=='listAllDocumentsItem') {
			$('#listAllDocumentsForm').show();
		}
		else if (targetId=='viewItem') {
			$('#viewFilesForm').show();
		}
		//alert('clicked '+targetId);
		

  }	
  function btnClick(event){
		
		var targetId=event.target.id;
		//alert('clicked '+targetId);
		if (targetId=='retrieveBtn') {
			retrieveCompany();
		} else if (targetId=='addDocumentBtn') {
			uploadFile(file);
		} else if (targetId=='listAllDocumentsBtn') {
			listAllDocuments(null);
		}
		
		
		
  }

  function retrieveCompany() {
		//alert('company id: '+$('#companyId').val());
		var cid=$('#companyId').val();
		var cname=$('#companyName').val();
	    $.ajax({
	        url: "http://localhost:9080/companyvault/getCompanyVault/"+cid+"/"+cname,
	        dataType : 'jsonp' // for firefox COR errors
	    }).then(function(data) {
		    //alert(data.companyId);
	       $('#displayPanel').append(data.companyId);
	       $('#displayPanel').append(", "+data.companyName);
	    });
		
	}
  function uploadFile(file) {
	  	//alert('test called '+file.name);

	  	var formData = new FormData();
	  	formData.append('uploadFile',file, file.name);
	  	formData.append('fileName', file.name);
/* 	  	$.each($('#uploadFile')[0].files, function(i, file) {
	  		formData.append('file-'+i, file);
	  	});	  	
 */	  	
 		$.ajax({
		  	url:"uploadFile.page",
		  	type:"POST",
		  	data:formData,
		    cache: false,
		    contentType: false,
		    processData: false,		  	
		  	success : function (data) {
		  		//alert('success '+data);
		  		$('#displayPanel').html(data);
/* 		  		
				$('#displayPanel').append('<br/>Download url : '+window.location.href+'getFileFromSession.page?file='+file.name);
		  		$('#displayPanel').append('<br/><a href="'+window.location.href+'getFileFromSession.page?file='+file.name+'">Download file</a>');
 */		  		
					$('#displayPanel').append('<br/>Download url : '+window.location.href+'getFile.page?file='+file.name);
					$('#displayPanel').append('<br/><a href="'+window.location.href+'getFile.page?file='+file.name+'">Download file</a>');
					$('#chosenFile').html('');
					//call rest api
		  		
			  	},
			 error : function (data) {
					alert('error '+data.responseText);
			  		$('#displayPanel').html(data);
					
				}
	  	});
		
	}

  function listAllDocuments_XX(key) {
		var endpoint='http://192.168.223.198:9000/document/list'+key;
	  if (key==null || key=='') {
		  //endpoint='http://192.168.223.198:9000/document/list/all';
		  endpoint='http://54.169.32.213/document/list/all';
		  //endpoint="http://192.168.223.198:8080/companyvault/backend/getCompanyVault/1/2";
		  //endpoint="http://192.168.223.198:9080/companyvault/getCompanyVault/1/2";
		}
		$.ajax({
		  	url:endpoint,
		  	type:"GET",
		  	data:'',
		  	dataType: 'json',
		  	success : function (data) {
			  	alert('success '+data);
			  	//alert('success '+data.companyId);
		  	},
		  	error : function (data) {
		  		alert('error '+data.status);
		  		alert('error '+Object.keys(data));
		  		alert('error '+Object.keys(data.statusText));
			}	
		});	 
}

  function listAllDocuments(key) {
		var endpoint='listAllDocuments.page';
		$.ajax({
		  	url:endpoint,
		  	type:"GET",
		  	data:'',
		  	dataType: 'json',
		  	success : function (data) {
			  	//alert('success ');
			  	var payload=JSON.parse(data);
				//alert(Object.keys(payload));
				$('#displayPanel').append(payload.Contents.length + ' records retrieved');			  	
			  	//alert('success '+data.companyId);
		  	},
		  	error : function (data) {
		  		alert('error '+data.status);
		  		alert('error '+Object.keys(data));
		  		alert('error '+Object.keys(data.statusText));
			}	
		});	 
  }
  $( document ).ready( readyFn );
  </script>
  </body>
  
</html>
