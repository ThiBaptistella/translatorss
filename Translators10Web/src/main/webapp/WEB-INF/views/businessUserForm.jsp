<!DOCTYPE html>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.7
Version: 4.7.1
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Dribbble: www.dribbble.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
Renew Support: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
	<meta charset="utf-8" />
	<title>Translatorss</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1" name="viewport" />
	<meta content="Preview page of Metronic Admin Theme #1 for form layouts" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
	<link href="resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
	<link href="resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN THEME GLOBAL STYLES -->
	<link href="resources/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
	<link href="resources/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
	<!-- END THEME GLOBAL STYLES -->
	<!-- BEGIN THEME LAYOUT STYLES -->
	<link href="resources/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
	<link href="resources/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
	<link href="resources/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
	<!-- END THEME LAYOUT STYLES -->
	<link rel="shortcut icon" href="favicon.ico" /> </head>
<!-- END HEAD -->

<body onload="loadForm()">
<div><!-- class="page-wrapper"-->


	<div ><!-- class="page-container"-->

		<div>
			<!-- BEGIN CONTENT BODY -->
			<div class="page-content" >

				<div class="row"><!--class="row"-->
					<div class="col-md-12"><!--class="col-md-12"-->
						<div class="tabbable-line boxless tabbable-reversed">
							<div class="tab-content">
								<div class="tab-pane active" id="tab_0">
									<div class="portlet box blue">
										<div class="portlet-title">
										</div>
										<div class="portlet-body form">
											<div id="loginform">
												
												<form:form action='${pageContext.request.contextPath}/j_spring_security_check' name="myFormUserSignIn" onsubmit="return validateFormSignIn()" class="form-horizontal" method="post" commandName="loginSRForm" id="loginForm">
													<div class="form-body">
														
													
														<div class="form-group">
															<label class="col-md-6 control-label"><h1><b>Sign In</b></h1></label>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">Email Address</label>
															<div class="col-md-4">
																<div class="input-group">
																	<span class="input-group-addon input-circle-left"><i class="fa fa-envelope"></i></span>
																	<form:input path="email" class='form-control input-circle-right' onclick="cleanErrorMessage()" size='4' type='text' id="email" name="email" value="${param.customer}" placeholder="Enter your Email" />
																	<form:errors path="email" />
																</div>
																<p id="emailsigninerror" style="color:red"></p>
															</div>
														</div>

														<div class="form-group">
															<label class="col-md-4 control-label">Password</label>
															<div class="col-md-4">
																<div class="input-group">
																	<form:input path="password" autocomplete='off' class='form-control input-circle-left' size='20' type='password' id="password" name="password" placeholder="Password" />
																	<span class="input-group-addon input-circle-right"><i class="fa fa-user"></i></span>
																</div>
																<p id="passwordsigninerror" style="color:red"></p>
															</div>
														</div>
														<div class="form-group" id="loginerrormessage" style="color:red" >
							                                <label class="col-md-6 control-label"><b><strong>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</strong></b></label>
							                            </div>
														
														<div class="form-group">
															<label class="col-md-4 control-label"></label>
															<div class="col-md-4">
																<div class="input-group">
																	<label>
																		Remember Me
																		<input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me">
																	</label>
																</div>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label"></label>
															<div class="col-md-4">
																<div class="input-group">
																	<button type="submit" id="SignInButton" class="btn btn-circle blue">Sign In</button>
																</div>
															</div>
														</div>
													</div>
												</form:form>
											</div>
											<div id="registerform">
												<form:form name="myFormUserregister" action='registerBusinessUser' class="form-horizontal" onsubmit="return validateFormRegister()" method="post" commandName="businessUserForm" >
												<div class="form-body">
													<div class="form-group">
														<label class="col-md-6 control-label"><h1><b>Create a new Account</b></h1></label>
													</div>
													<spring:bind path="fullname">
														<div class="form-group spaceImput">
															<div class="col-sm-2"></div>
															<label class="col-sm-2 control-label">Full Name:</label>
															<div class="col-sm-6">
																<form:input path="fullname" type="text" class="form-control" id="fullname" placeholder="Full Name" onclick="onclickCleanField('fullnameerror')"/>
																<form:errors path="fullname" class="control-label" />
																<p id="fullnameerror" style="color:red"></p>
															</div>
															<div class="col-sm-2"></div>
														</div>
													</spring:bind>

													<spring:bind path="preferedname">
														<div class="form-group spaceImput">
															<div class="col-sm-2"></div>
															<label class="col-sm-2 control-label">Prefered Name:</label>
															<div class="col-sm-6">
																<form:input path="preferedname" type="text" class="form-control" id="preferedname" placeholder="Prefered Name"  onclick="onclickCleanField('preferednameerror')"/>
																<form:errors path="preferedname" class="control-label" />
																<p id="preferednameerror" style="color:red"></p>
															</div>
															<div class="col-sm-2"></div>
														</div>
													</spring:bind>

													<spring:bind path="phone">
														<div class="form-group spaceImput">
															<div class="col-sm-2"></div>
															<label class="col-sm-2 control-label">Phone:</label>
															<div class="col-sm-6">
																<form:input path="phone" type="text" class="form-control" id="phone" placeholder="Phone"  onclick="onclickCleanField('phoneerror')"/>
																<form:errors path="phone" class="control-label" />
																<p id="phoneerror" style="color:red"></p>
															</div>
															<div class="col-sm-2"></div>
														</div>
													</spring:bind>

													<spring:bind path="address">
														<div class="form-group spaceImput">
															<div class="col-sm-2"></div>
															<label class="col-sm-2 control-label">Address:</label>
															<div class="col-sm-6">
																<form:input path="address" type="text" class="form-control" id="address" placeholder="Address" onclick="onclickCleanField('addresserror')"/>
																<form:errors path="address" class="control-label" />
																<p id="addresserror" style="color:red"></p>
															</div>
															<div class="col-sm-2"></div>
														</div>
													</spring:bind>

													<spring:bind path="paypalid">
														<div class="form-group spaceImput">
															<div class="col-sm-2"></div>
															<label class="col-sm-2 control-label">Paypal Client Id:</label>
															<div class="col-sm-6">
																<form:input path="paypalid" type="text" class="form-control" id="paypalid" placeholder="Paypal Client Id" onclick="onclickCleanField('paypalclientiderror')"/>
																<form:errors path="paypalid" class="control-label" />
																<p id="paypalclientiderror" style="color:red"></p>
															</div>
															<div class="col-sm-2"></div>
														</div>
													</spring:bind>



													<spring:bind path="email">
														<div class="form-group spaceImput">
															<div class="col-sm-2"></div>
															<label class="col-sm-2 control-label">Email:</label>
															<div class="col-sm-6">
																<form:input path="email" type="text" class="form-control" id="email" placeholder="Email" onclick="onclickCleanField('emailerror')"/>
																<form:errors path="email" class="control-label" style="color:red;"/>
																<h5><p id="emailerror" style="color:red"></p></h5>
															</div>
															<div class="col-sm-2"></div>
														</div>
													</spring:bind>

													<spring:bind path="password">
														<div class="form-group spaceImput">
															<div class="col-sm-2"></div>
															<label class="col-sm-2 control-label">Password:</label>
															<div class="col-sm-6">
																<form:input path="password" type="password" class="form-control" id="password" placeholder="Password" onclick="onclickCleanField('passworderror')"/>
																<form:errors path="password" class="control-label"/>
																<p id="passworderror" style="color:red"></p>
															</div>
															<div class="col-sm-2"></div>
														</div>
													</spring:bind>

													<div class="form-group">
														<div class="col-sm-10 btnRegister">
															<button name="translatorsave" id="RegisterButton" value="TranslatorSave" class="btn-lg btn-primary pull-right">Register</button>
														</div>
														<div class="col-sm-2"></div>
													</div>
													</form:form>
												</div>
											</div>



											<div class="form-actions">
												<div class="form-horizontal">
													<div class="row">
														<div class="col-md-7 control-label" id ="message">
															<div class="a-divider a-divider-break"><h5><b>New to Translatorss?</b></h5></div>

														</div>
													</div>
													<b/>
													<div class="row">
														<div class="col-md-7 control-label">
															<button type="submit" class="btn btn-circle green" id="createAccountButton" onclick="showCreateAccount()">Create an Account</button>
															<button type="submit" class="btn btn-circle green" id="signInButton" onclick="showSignIn()">Sign In</button>
														</div>
													</div>
												</div>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTAINER -->





	<!-- BEGIN FOOTER -->
	<div class="page-footer">
		<div class="page-footer-inner"> 2017 &copy;
			<a target="_blank" href="http://www.translatorss.com.au/">Translatorss</a>
		</div>

	</div>
	<!-- END FOOTER -->
</div>
<!-- BEGIN QUICK NAV -->

<!-- END QUICK NAV -->
<!--[if lt IE 9]>
<script src="resources/assets/global/plugins/respond.min.js"></script>
<script src="resources/assets/global/plugins/excanvas.min.js"></script>
<script src="resources/assets/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="resources/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
<script src="resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="resources/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="resources/assets/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="resources/assets/pages/scripts/form-samples.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="resources/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
<script src="resources/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
<script src="resources/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
<script src="resources/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->


<script>
	function cleanErrorMessage() {
	    document.getElementById("loginerrormessage").style.display="none";
	    document.getElementById("emailsigninerror").style.display="none";
	}
	
	function onclickCleanField(idfield){
        document.getElementById(idfield).style.display="none";
    }
	
    function showCreateAccount() {
        document.getElementById('registerform').style.display="block";
        document.getElementById('loginform').style.display="none";
        document.getElementById('signInButton').style.display="block";
        document.getElementById('createAccountButton').style.display="none";
        document.getElementById('message').style.display="none";
    }

    function showSignIn(){
        document.getElementById('registerform').style.display="none";
        document.getElementById('loginform').style.display="block";
        document.getElementById('createAccountButton').style.display="block";
        document.getElementById('signInButton').style.display="none";
    }

    function loadForm(){
        document.getElementById('registerform').style.display="none";
        document.getElementById('signInButton').style.display="none";

    }

    
    function validateFormSignIn(){
        var email = document.forms["myFormUserSignIn"]["email"].value; 
        if (!validateEmail(email)) {
            document.getElementById("emailsigninerror").innerHTML = "Email format incorrect";
            $('#emailsigninerror').show();
            return false;
        }
        var password = document.forms["myFormUserSignIn"]["password"].value;
        if (password == null || password == "") {
            text = "This field must not be empty!";
            document.getElementById("passwordsigninerror").innerHTML = text;
            $('#passwordsigninerror').show();
            return false;
        }
        document.getElementById("SignInButton").disabled = true;

    }
    
    function validateFormRegister(){
        var text;
        var name = document.forms["myFormUserregister"]["fullname"].value;
        if (name == null || name == "") {
            text = "Full Name was not selected!";
            document.getElementById("fullnameerror").innerHTML = text;
            $('#fullnameerror').show();
            return false;
        }

        var preferedname = document.forms["myFormUserregister"]["preferedname"].value;
        if (preferedname == null || preferedname == "") {
            text = "The Prefered Name was not selected!";
            document.getElementById("preferednameerror").innerHTML = text;
            $('#preferednameerror').show();
            return false;
        }

        var phone = document.forms["myFormUserregister"]["phone"].value;
        if (phone == null || phone == "") {
            text = "Phone needs to be selected!";
            document.getElementById("phoneerror").innerHTML = text;
            $('#phoneerror').show();
            return false;
        }

        var address = document.forms["myFormUserregister"]["address"].value;
        if(address== null || address==""){
            text = "Address needs to be selected";
            document.getElementById("addresserror").innerHTML = text;
            $('#addresserror').show();
            return false;
        }

        var paypalid = document.forms["myFormUserregister"]["paypalid"].value
        if(paypalid== null || paypalid==""){
            text = "Paypal needs to be selected";
            document.getElementById("paypalclientiderror").innerHTML = text;
            $('#paypalclientiderror').show();
            return false;
        }
        
        
        var email = document.forms["myFormUserregister"]["email"].value;
        if (!validateEmail(email)) {
            document.getElementById("emailerror").innerHTML = "Email format incorrect";
            $('#emailerror').show();
            return false;
        }

        var currentPassword = document.forms["myFormUserregister"]["password"].value;
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
        document.getElementById("RegisterButton").disabled = true; 
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