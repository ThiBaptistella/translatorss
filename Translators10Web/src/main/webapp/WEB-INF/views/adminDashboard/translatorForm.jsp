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
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/favicon.png">
    <link href="resources/css/sb-admin.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
    <link href="resources/css/plugins/morris.css" rel="stylesheet">
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="indexTranslator.jsp" class="img-responsive"><img src="resources/images/logo.png"/></a>
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
                        <a href="#">You have new tasks! <span class="label label-success">Alert Badge</span></a>
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
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                        class="fa fa-user"></i> ${businessUserForm.user.name} <b class="caret"></b></a>
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
                            <i class="fa fa-dashboard"></i> <a href="indexUser.jsp">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-fw fa-edit"></i> Pending Actions
                        </li>
                    </ol>
                </div>
            </div>

            <c:if test="${message !='null'}">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="fa fa-info-circle"></i><strong>${translatorFormMessage}</strong>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Translator Details</h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                        			<form:form method="post" name="myForm3" onsubmit="return validateform()" action='translatorAdminEdit' commandName="translatorFormAdmin" enctype="application/x-www-form-urlencoded">
                                                <form:input path="id" type="hidden" class="form-control " id="id" />           
		                                        <spring:bind path="paypalClientId">
														<label class="col-sm-2 control-label">Paypal Client ID</label>
														<div class="col-sm-10">
															<form:input path="paypalClientId" type="text" class="form-control " id="paypalClientId" placeholder="Paypal Client ID" />
															<form:errors path="paypalClientId" class="control-label" />
															<p id="paypalclientidrerror" style="color:red"></p>
														</div>
												</spring:bind>
												 <spring:bind path="naatiNumber">
															<label class="col-sm-2 control-label">Naati Number</label>
															<div class="col-sm-10">
																<form:input path="naatiNumber" type="text" class="form-control " id="naatiNumber" placeholder="Naati Number" />
																<form:errors path="naatiNumber" class="control-label" style="color:red"/>
																<p id="naatinumbererror" style="color:red"></p>
															</div>
													</spring:bind>
											 
													<spring:bind path="name">
															<label class="col-sm-2 control-label">Name</label>
															<div class="col-sm-10">
																<form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
																<form:errors path="name" class="control-label"  />
															</div>
													</spring:bind>
														
													<spring:bind path="phone">
															<label class="col-sm-2 control-label">Phone</label>
																<div class="col-sm-10">
																	<form:input path="phone" class="form-control" id="phone" placeholder="Phone" />
																	<form:errors path="phone" class="control-label" />
																	<p id="phoneerror" style="color:red"></p>
																</div>
														
													</spring:bind>	
														
													<spring:bind path="address">
															<label class="col-sm-2 control-label">Address</label>
															<div class="col-sm-10">
																<form:input path="address" rows="5" class="form-control" id="address" placeholder="address" />
																<form:errors path="address" class="control-label" />
																<p id="addresserror" style="color:red"></p>
															</div>
														
													</spring:bind>	
																	
													<spring:bind path="email">
															<label class="col-sm-2 control-label">Email</label>
															<div class="col-sm-10">
																<form:input path="email" class="form-control" id="email" placeholder="Email" />
																<form:errors path="email" class="control-label" style="color:red"/>
																<p id="emailerror" style="color:red"></p>
															</div>
														
													</spring:bind>
											
													<spring:bind path="password">
															<label class="col-sm-2 control-label">Password</label>
															<div class="col-sm-10">
																<form:input path="password" class="form-control" id="password" placeholder="password" />
																<form:errors path="password" class="control-label" />
																<p id="passworderror" style="color:red"></p>
															</div>
													</spring:bind>
											
											  	 	<spring:bind path="natyExpiration">
															<label class="col-sm-2 control-label">Naati Expiration</label>
															<div class="col-sm-10">
																<form:input path="natyExpiration" type="date" class="form-control" id="natyExpiration" placeholder="natyExpiration" />					
																<form:errors path="natyExpiration" class="control-label" />
															</div>
													</spring:bind>
											
													<spring:bind path="abn_number">
															<label class="col-sm-2 control-label">ABN Number</label>
															<div class="col-sm-10">
																<form:input path="abn_number" class="form-control" id="abn_number" placeholder="abn number" />					
																<form:errors path="abn_number" class="control-label" />
															</div>
													</spring:bind>
													
													<spring:bind path="abn_name">
															<label class="col-sm-2 control-label">ABN Name</label>
															<div class="col-sm-10">
																<form:input path="abn_name" class="form-control" id="abn_name" placeholder="abn name" />					
																<form:errors path="abn_name" class="control-label" />
															</div>
													</spring:bind>
											
													 <spring:bind path="status">
															<label class="col-sm-2 control-label">Status</label>
															<div class="col-sm-10">
																<form:input path="status" type="text" class="form-control" id="status" placeholder="status" readonly="true"/>					
																<form:errors path="status" class="control-label" />
															</div>
													</spring:bind>
													
													<spring:bind path="remainingDays">
															<label class="col-sm-2 control-label">remainingDays</label>
															<div class="col-sm-10">
																<form:input path="remainingDays" type="text" class="form-control" id="remainingDays" placeholder="remainingDays" readonly="true"/>					
																<form:errors path="remainingDays" class="control-label" />
															</div>
													</spring:bind>              
                                              <div class="form-group input-group">
												  <input class="btn btn-default" type="submit" value="Update Information" id="saveQuotationButton">
											  </div>
                                      </form:form>
                        </div>
                    </div>
                    <div class="col-lg-6">
					          <div class="panel panel-primary">
					            <div class="panel-heading">
					              <h3 class="panel-title">Flags Information</h3>
					            </div>
					            <div class="panel-body">
					               	<div class="col-lg-12">
			                            <div class="table-responsive">
			                                <table class="table table-bordered table-hover table-striped" id="datatables">
			                                    <thead>
			                                    <tr>
			                                        <!-- <th class="text-center">Naati Verified</th> -->
			                                        <th class="text-center">Naati Date Expired</th>
			                                        <th class="text-center">Valid Suscription</th>
			                                        <th class="text-center">Manually Paused</th>
			                                        <!-- <th class="text-center">Inactive Cancelled</th>
			                                        <th class="text-center">Inactive Refunded</th> -->
			                                    </tr>
			                                    </thead>
			                                    <tbody>
				                                        <tr class="active">
				                                           <%--  <td>${natyVerified}</td> --%>
				                                            <td>${natyExtiryDate}</td>
				                                            <td>${validSuscription}</td>
				                                            <td>${manualyPaused}</td>
				                                            <%-- <td>${inactiveCancelled}</td>
				                                            <td>${inactiveRefunded}</td> --%>
				                                        </tr>				                                    
			                                    </tbody>
			                                </table>
			                            </div>
                        		</div>
					            </div>
					          </div>
	        		</div>
                    <div class="col-lg-6">
					          <div class="panel panel-primary">
						            <div class="panel-heading">
						              <h3 class="panel-title">Action Buttoms</h3>
						            </div>
						            <div class="panel-body">
						               	<div class="col-lg-12">
				                            <c:if test="${translatorStatus == 'Active'}">
												<spring:url value="/manuallyPause" var="manuallyPause" />
												<button class="btn btn-info" onclick="location.href='${manuallyPause}'">Manually Pause</button>
											</c:if>
	
											<c:if test="${manualyPaused == 'true'}">
												<spring:url value="/manuallyUnpause" var="manuallyUnpause" />
												<button class="btn btn-primary" onclick="location.href='${manuallyUnpause}'">Manually Unpause</button>
											</c:if>
										
										
											<%-- <spring:url value="/manuallyInactiveNoRefound" var="manuallyInactiveNoRefound" />
											<button class="btn btn-primary" onclick="location.href='${manuallyInactiveNoRefound}'">Manually Inactivate (No refund)</button>
											
											
											<spring:url value="/manuallyInactiveRefound" var="manuallyInactiveRefound" />
											<button class="btn btn-info" onclick="location.href='${manuallyInactiveRefound}'">Manually Inactivate(Refund)</button>
											
											<spring:url value="/manuallyReactivate" var="manuallyReactivate" />
											<button class="btn btn-primary" onclick="location.href='${manuallyReactivate}'">Manually Reactivate (From Inactive)</button> --%>
	                        		    </div>
						            </div>
					          </div>
	        		</div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->
    </div>
</div>
<!-- /#wrapper -->


<!-- jQuery -->
<script src="resources/js/jquery.js"></script>
<script src="resources/js/modal.js"></script>
<script src="resources/js/jquery.countdown.js"></script>
<script src="resources/js/jquery.countdown.min.js"></script>
<script src="resources/js/countdown1.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="resources/js/bootstrap.min.js"></script>

<script src="resources/js/datatable.js"></script>

<!-- Morris Charts JavaScript -->
<script src="resources/js/plugins/morris/raphael.min.js"></script>
<script src="resources/js/plugins/morris/morris.min.js"></script>
<script src="resources/js/plugins/morris/morris-data.js"></script>

<!-- Flot Charts JavaScript -->
<!--[if lte IE 8]>
<script src="js/excanvas.min.js"></script><![endif]-->
<script src="resources/js/plugins/flot/jquery.flot.js"></script>
<script src="resources/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="resources/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="resources/js/plugins/flot/jquery.flot.pie.js"></script>
<script src="resources/js/plugins/flot/flot-data.js"></script>

<!-- plug in datatable -->
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>

<script>

		function resetErrorValues() {
		    $('#passworderror').hide();
		    $('#paypalclientidrerror').hide();
		    $('#naatinumbererror').hide();
		    $('#phoneerror').hide();	    
		    $('#addresserror').hide();
		    $('#emailerror').hide();

		}
		
		function validateform(){
			resetErrorValues();
			var text;
		
			var currentPassword = document.forms["myForm3"]["password"].value;
			if(currentPassword==null || currentPassword==""){
				document.getElementById("passworderror").innerHTML = "Incorrect Password Format";
		    	$('#passworderror').show();
		        return false;
			}else{
				text = checkPwd(currentPassword);
				if(text!=null){
					document.getElementById("passworderror").innerHTML = text;
			    	$('#passworderror').show();
			        return false;
				}
			}
			
			
			var email = document.forms["myForm3"]["email"].value;
		    if (!validateEmail(email)) {
		    	document.getElementById("emailerror").innerHTML = "Email format incorrect";
		    	$('#emailerror').show();
		        return false;
		    }
		    var paypalclientid = document.forms["myForm3"]["paypalClientId"].value;
		    if (!validateEmail(paypalclientid)) {
		    	document.getElementById("paypalclientidrerror").innerHTML = "Email format incorrect";
		    	$('#paypalclientidrerror').show();
		        return false;
		    }
		    var phone = document.forms["myForm3"]["phone"].value;
		    if (phone==null || phone=="" || isNaN(phone)) {
		    	document.getElementById("phoneerror").innerHTML = "Field format incorrect";
		    	$('#phoneerror').show();
		        return false;
		    }
		    var naatinumber = document.forms["myForm3"]["naatiNumber"].value;
		    if (naatinumber==null || naatinumber=="" || isNaN(naatinumber)) {
		    	document.getElementById("naatinumbererror").innerHTML = "Field format incorrect";
		    	$('#naatinumbererror').show();
		        return false;
		    }
		    var address = document.forms["myForm3"]["address"].value;
		    if (address==null || address=="" ||isEmpty(address)) {
		    	document.getElementById("addresserror").innerHTML = "Field format incorrect";
		    	$('#addresserror').show();
		        return false;
		    } 
			
		}
		
		 function validateEmail(email) {
			    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			    return re.test(email);
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
</script>


</body>

</html>

