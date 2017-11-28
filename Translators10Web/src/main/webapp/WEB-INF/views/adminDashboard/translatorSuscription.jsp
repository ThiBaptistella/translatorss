<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Translatorss - Dashboard</title>

  <!-- Bootstrap Core CSS -->
  <link href="resources/css/bootstrap.min.css" rel="stylesheet">
  <link rel="shortcut icon" href="resources/images/favicon.png">
  <!-- Custom CSS -->
  <link href="resources/css/sb-admin.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body>

  <div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <!--   <a class="navbar-brand" href="indexTranslator.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Translatorss"></a>
        <a class="navbar-brand" href="indexTranslator.jsp"><img src="../../images/translatorss/logo.png" alt="Translatorss"></a>
      -->
      <a href="indexTranslator.jsp" class="img-responsive"><img src="resources/images/logo.png"  /></a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
        <ul class="dropdown-menu message-dropdown"> 
          <li class="message-footer">
                  <a href="<c:url value='conversations'/>">Read All New Messages</a>
          </li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
        <ul class="dropdown-menu alert-dropdown">

          <li>
            <a href="#">You have new tassks! <span class="label label-success">Alert Badge</span></a>
          </li>
          <li>
            <a href="#">You have new quotations! <span class="label label-warning">Alert Badge</span></a>
          </li>
          <li>
            <a href="#">Deadline approaching! <span class="label label-danger">Alert Badge</span></a>
          </li>

          <li class="divider"></li>
          <li>
            <a href="#">View All</a>
          </li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${loggedInAdmin.user.name} <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li>
            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
          </li>
          <li>
            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
          </li>
          <li>
            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
          </li>
          <li class="divider"></li>
          <li>
            <a href="<c:url value='${pageContext.request.contextPath}/j_spring_security_logout'/>"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
          </li>
        </ul>
      </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
      <ul class="nav navbar-nav side-nav">
          <li class="active">
              <a href="<c:url value='indexAdmin'/>" ><i class="fa fa-fw fa-dashboard"></i>Dashboard</a>
          </li>
          <li>
              <a href="<c:url value='translatorList'/>" ><i class="fa fa-fw fa-edit"></i>Translators List</a>
          </li>
          <li>
              <a href="<c:url value='translatorSuscription'/>" ><i class="fa fa-fw fa-table"></i>Register New Translator</a>
          </li>
          <li>
              <a href="<c:url value='caseResolution'/>" ><i class="fa fa-fw fa-table"></i>Translators Assignments</a>
          </li>
          <li>
              <a href="<c:url value='serviceRequestExpiration'/>" ><i class="fa fa-fw fa-table"></i>Translators Requests/Quotes and Service Request Expiration</a>
          </li>
          <li>
              <a href="<c:url value='customerList'/>" ><i class="fa fa-fw fa-edit"></i>Clients  List</a>
          </li>
          <li>
              <a href="<c:url value='serviceRequestConfiguration'/>" ><i class="fa fa-fw fa-table"></i>System Configuration</a>
          </li>
          <li>
              <a href="<c:url value='paymentCenter'/>" ><i class="fa fa-fw fa-table"></i>Payment Center</a>
          </li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </nav>

  <div id="page-wrapper">

    <div class="container-fluid">

      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <ol class="breadcrumb">
            <li>
              <i class="fa fa-dashboard"></i>  <a href="indexUser.jsp">Dashboard</a>
            </li>
            <li class="active">
              <i class="fa fa-fw fa-gear"></i> Settings
            </li>
          </ol>
        </div>
      </div>
       <c:if test="${message !='null'}">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="fa fa-info-circle"></i><strong>${translatorRegistrationMessage}</strong>
                        </div>
                    </div>
                </div>
            </c:if>
      
      <!-- /.row -->
      <div class="row">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title">Translator Suscription</h3>
            </div>
            <div class="panel-body">
              <div class="col-lg-4">
              	<div class="panel panel-info">
				 <div class="panel-body">
					<form:form action='registerTranslator' onsubmit="return validateForm()" name="myForm" method="post" commandName="translatorForm" enctype="multipart/form-data" role="form">
                        <div class="form-group">
                            <label>Full Name:</label>
                            <form:input path="fullname" id="fullname" onclick="onclickCleanField('fullnameerror')" type="text" class="form-control" placeholder="Full Name" />
                            <form:errors path="fullname" class="control-label" />
                            <p id="fullnameerror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Preferred Name:</label>
                            <form:input path="user.name" id="preferedname" onclick="onclickCleanField('preferednameerror')" type="text" class="form-control" placeholder="Preferred Name" />
                            <form:errors path="user.name" class="control-label" />
                            <p id="preferednameerror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Email:</label>
                            <form:input path="user.email" id="email" onclick="onclickCleanField('emailerror')" type="email" class="form-control" placeholder="email" />
                            <form:errors path="user.email" class="control-label" style="color:red"/>
                            <p id="emailerror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Password:</label>
                            <form:input path="user.password" id="password" onclick="onclickCleanField('passworderror')" class="form-control" placeholder="password" />
                            <p><h6>Minimum 8 max 25 characters alphanumeric,no special characters, at least one uppercase letter, at least one lowercase letter, at least one number</h6></p>
                            <form:errors path="user.password" class="control-label" />
                            <p id="passworderror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Contact Number:</label>
                            <form:input path="phone" id="phone" type="text" onclick="onclickCleanField('phoneerror')" class="form-control" placeholder="Phone" />
                            <form:errors path="phone" class="control-label" />
                            <p id="phoneerror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Home Address:</label>
                            <form:input path="address" id="address2" type="text" onclick="onclickCleanField('addresserror')" class="form-control" placeholder="Home Address" />
                            <form:errors path="address" class="control-label" />
                            <p id="addresserror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Paypal Client Id:</label>
                            <form:input path="paypalClientId" id="paypalclientid" onclick="onclickCleanField('paypalclientiderror')" type="text" class="form-control " placeholder="paypalClientId" />
                            <form:errors path="paypalClientId" class="control-label" />
                            <p id="paypalclientiderror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>ABN Name:</label>
                            <form:input path="abn_name" id="abn_name" onclick="onclickCleanField('abn_nameerror')" type="text" class="form-control" placeholder="ABN Name" />
                            <form:errors path="abn_name" class="control-label" />
                            <p id="abn_nameerror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>ABN Number:</label>
                            <form:input path="abn_number" id="abn_number" onclick="onclickCleanField('abn_numbererror')" type="text" class="form-control" placeholder="ABN Number" />
                            <form:errors path="abn_number" class="control-label" />
                            <p id="abn_numbererror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>ABN Addess:</label>
                            <form:input path="abn_address" id="abn_address" onclick="onclickCleanField('abn_addresserror')" type="text" class="form-control" placeholder="ABN Address" />
                            <form:errors path="abn_address" class="control-label" />
                            <p id="abn_addresserror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Nati Number:</label>
                            <form:input path="naatiNumber" id="naatinumber" onclick="onclickCleanField('naatinumbererror')" type="text" class="form-control" placeholder="naatiNumber" />
                            <form:errors path="naatiNumber" class="control-label" style="color:red"/>
                            <p id="naatinumbererror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Nati expiry date:</label>
                            <form:input path="natyExpiration" type="date" class="form-control" id="natyExpiration" onclick="onclickCleanField('natyExpirationerror')" placeholder="natyExpiration" />
                            <form:errors path="natyExpiration" class="control-label" style="color:red"/>
                            <p id="natyExpirationerror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <label>Languages to Translate:</label>
                            <form:select path="languageList" id="languagelist" onclick="onclickCleanField('languagelisterror')" items="${languageList}" itemValue="id" itemLabel="description" multiple="false" size="15" class="form-control" />
                            <form:errors path="languageList" class="control-label" />
                            <p id="languagelisterror" style="color:red"></p>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 btnRegister">
                                <button name="translatorsave" value="translatorSaveAdmin" class="btn-lg btn-primary pull-right">Register</button>
                            </div>
                            <div class="col-sm-2"></div>
                        </div>
					</form:form>
				</div>
            </div>
           </div>
            </div>
        </div>
      </div>
    </div>
  </div>
</div>
	<script>


        function onclickCleanField(idfield){
            document.getElementById(idfield).style.display="none";
        }

		function validateForm() {

			var nameEmptyText = "Full Name was not selected!";
		    var name = document.forms["myForm"]["fullname"].value;
		    if (name == null || name == "") {
		    	document.getElementById("fullnameerror").innerHTML = nameEmptyText;
		    	$('#fullnameerror').show();
		        return false;
		    }

            var preferednameEmptyText = "Prefered Name was not Selected!";
            var preferedname = document.forms["myForm"]["preferedname"].value;
            if (preferedname == null || preferedname == "") {
                text = "A password is needed!";
                document.getElementById("preferednameerror").innerHTML = preferednameEmptyText;
                $('#preferednameerror').show();
                return false;
            }

            var email = document.forms["myForm"]["email"].value;
            if (!validateEmail(email)) {
                document.getElementById("emailerror").innerHTML = "Email format incorrect";
                $('#emailerror').show();
                return false;
            }

            var passwordEmptyText = "Password was not Selected!";
            var password = document.forms["myForm"]["password"].value;
            if (password == null || password == "") {
                document.getElementById("passworderror").innerHTML = passwordEmptyText;
                $('#passworderror').show();
                return false;
            }else{
                var text = checkPwd(password);
                if(text!=null){
                    document.getElementById("passworderror").innerHTML = text;
                    $('#passworderror').show();
                    return false;
                }
            }

            var phoneEmptyText = "Phone was not selected";
            var phone = document.forms["myForm"]["phone"].value;
            if (phone == null || phone == "") {
                document.getElementById("phoneerror").innerHTML = phoneEmptyText;
                $('#phoneerror').show();
                return false;
            }

            var addressEmptyText = "Address was not selected";
            var address2= document.getElementById("address2").value;
		    if (address2 == null || address2 == "") {
		    	document.getElementById("addresserror").innerHTML = addressEmptyText;
		    	$('#addresserror').show();
		        return false;
		    }

            var paypalclientid = document.forms["myForm"]["paypalclientid"].value;
            if (!validateEmail(paypalclientid)) {
                document.getElementById("paypalclientiderror").innerHTML = "Email format incorrect";
                $('#paypalclientiderror').show();
                return false;
            }

            var abn_nameText = "The ABN Name needs to be selected!";
            var abn_name = document.forms["myForm"]["abn_name"].value;
            if (abn_name == null || abn_name == "") {
                document.getElementById("abn_nameerror").innerHTML = abn_nameText;
                $('#abn_nameerror').show();
                return false;
            }

            var abn_numberText = "The ABN Number needs to be selected!";
            var abn_number = document.forms["myForm"]["abn_number"].value;
            if (abn_number == null || abn_number == "") {
                document.getElementById("abn_numbererror").innerHTML = abn_numberText;
                $('#abn_numbererror').show();
                return false;
            }

            var abn_addressText = "The ABN address needs to be selected!";
            var abn_address = document.forms["myForm"]["abn_address"].value;
            if (abn_address == null || abn_address == "") {
                document.getElementById("abn_addresserror").innerHTML = abn_addressText;
                $('#abn_addresserror').show();
                return false;
            }

            var naatinumberEmptyText = "Nati number was not selected";
	        var naatinumber = document.forms["myForm"]["naatinumber"].value;
		    if (naatinumber == null || naatinumber == "") {
		    	document.getElementById("naatinumbererror").innerHTML = naatinumberEmptyText;
		    	$('#naatinumbererror').show();
		        return false;
		    }

            var natyExpirationEmptyText = "Nati number was not selected";
            var natyExpiration = document.forms["myForm"]["natyExpiration"].value;
            if (natyExpiration == null || natyExpiration == "") {
                document.getElementById("natyExpirationerror").innerHTML = natyExpirationEmptyText;
                $('#natyExpirationerror').show();
                return false;
            }

		    var languagelist = document.forms["myForm"]["languagelist"].value;
		    if (languagelist == null || languagelist == "") {
		    	text = "The languagelist needs to be selected!";
		    	document.getElementById("languagelisterror").innerHTML = text;
		    	$('#languagelisterror').show();
		        return false;
		    }
		}

        function checkPwd(str) {
            if (str.length < 6) {
                return "Password needs Minimum 6 characteres";
            } else if (str.length > 25) {
                return "Password needs Maximum 25 characteres";
            } else if (str.search(/\d/) == -1) {
                return "Password needs a number";
            } else if (str.search(/[a-z]/) == -1) {
                return "Password needs a lowercase Letter";
            } else if (str.search(/[A-Z]/) == -1) {
                return "Password needs a Uppercase Letter";
            }else if(!(str.search(/[$@$!%*?&\_]/)== -1)){
                return "No Allow Special Character($@$!%*?&\_) in password ";
            } else if (str.search(/[^a-zA-Z0-9\!\@\$\%\?\&\*\(\)\_\+]/) != -1) {
                return "Incorect Format";
            }
            return null;
        }

        function validateEmail(email) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }
	</script>


<!-- jQuery -->
<script src="resources/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>