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
<%@ page import="au.com.translatorss.bean.Conversation" %>
<%@ page import="au.com.translatorss.utils.ConversationUtils" %>
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
        <meta content="Preview page of Metronic Admin Theme #2 for responsive bootstrap table demos" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="resources/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="resources/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="resources/assets/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/layouts/layout2/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="resources/assets/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->

        <link rel="stylesheet" href="resources/css/intlTelInput.css">


        <link rel="shortcut icon" href="favicon.ico" /> 
    </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid" onload="onLoad()">
        <!-- BEGIN HEADER -->
        <div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <div class="page-logo">
                    <img src="resources/assets/layouts/layout2/img/logo-translatorss.svg" alt="logo" class="logo-default" />
               
                </div>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN PAGE ACTIONS -->
                <!-- DOC: Remove "hide" class to enable the page header actions -->
               
                <!-- END PAGE ACTIONS -->
                <!-- BEGIN PAGE TOP -->
                <div class="page-top">
                    <!-- BEGIN HEADER SEARCH BOX -->
                    <!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
           
                    <!-- END HEADER SEARCH BOX -->
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
                            <!-- BEGIN NOTIFICATION DROPDOWN -->
                            <!-- DOC: Apply "dropdown-dark" class below "dropdown-extended" to change the dropdown styte -->
                            <!-- DOC: Apply "dropdown-hoverable" class after below "dropdown" and remove data-toggle="dropdown" data-hover="dropdown" data-close-others="true" attributes to enable hover dropdown mode -->
                            <!-- DOC: Remove "dropdown-hoverable" and add data-toggle="dropdown" data-hover="dropdown" data-close-others="true" attributes to the below A element with dropdown-toggle class -->
               
                            <!-- END NOTIFICATION DROPDOWN -->
                            <!-- BEGIN INBOX DROPDOWN -->
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                            <li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" id="countUnreadMessages">  
                                </a>
                                 <c:if test="${not empty unreadMessageList}">
	                                <ul class="dropdown-menu">
	                                    <li class="external">
	                                        <h3>You have<span class="bold" > New ${newMessagesNumber} </span> Messages </h3>
	                                        <a href="<c:url value='conversations'/>">view all</a>
	                                    </li>
	                                    <li>
	                                        <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
	                                            <c:forEach items="${unreadMessageList}" var="message">
	                                            	<li>
		                                                <a href="<c:url value='/seeConversation/${message.conversationid}' />">
		                                                    <span class="photo">
		                                                        <img src="${message.photoUrl}" class="img-circle" alt=""> </span>
		                                                    <span class="subject">
		                                                        <span class="from"> ${message.sender} </span>
		                                                        <span class="time">${message.date} </span>
		                                                    </span>
		                                                    <span class="message"> ${message.message} </span>
		                                                </a>
		                                            </li>
	                                            </c:forEach>
	                                        </ul>
	                                    </li>
	                                </ul>
	                             </c:if>
                            </li>
                            <!-- END INBOX DROPDOWN -->

                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <img alt="" class="img-circle" src="${photoUrl}" />
                                    <span class="username username-hide-on-mobile"> ${businessUserForm.fullname} </span>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                   <li>
                                        <a href="<c:url value='userSettings'/>"><i class="icon-user"></i> My Profile </a>
                                    </li>
                                    <li>
                                    	<a href="<c:url value='conversations'/>">
                                            <i class="icon-envelope-open"></i> My Inbox
                                        </a>
                                    </li>
                                    <li class="divider"> </li>
 
                                    <li>
                                        <a href="<c:url value='${pageContext.request.contextPath}/j_spring_security_logout'/>"><i class="icon-key"></i> Log Out</a>
                                    </li>
                                </ul>
                            </li>
                            <!-- END USER LOGIN DROPDOWN -->
                            <!-- BEGIN QUICK SIDEBAR TOGGLER -->

                            <!-- END QUICK SIDEBAR TOGGLER -->
                        </ul>
                    </div>
                    <!-- END TOP NAVIGATION MENU -->
                </div>
                <!-- END PAGE TOP -->
            </div>
            <!-- END HEADER INNER -->
        </div>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <!-- END SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
                    <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
                    <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
                    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                    <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
                    <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                    <ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                        <li class="nav-item ">
                            <a href="<c:url value='userdashboard'/>" class="nav-link nav-toggle">
                                <span class="title">Dashboard</span>
                                <span class="arrow open"></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value='uploadFile'/>" class="nav-link nav-toggle">
                                <span class="title">Request a new Translation</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value='pendingActions'/>" class="nav-link nav-toggle">
                                <span class="title">Select your Translators</span>
                                <span class="arrow"></span>
                            </a>
                        </li>    
                        <li class="nav-item">
                            <a href="<c:url value='jobInProgress'/>" class="nav-link nav-toggle">
                                <span class="title">Follow Up your Translation</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value='history'/>" class="nav-link nav-toggle">
                                <span class="title">Translation History</span>
                                <span class="arrow"></span>
                            </a>
                        </li>

                        <li class="nav-item start active open">
                            <a href="<c:url value='userSettings'/>" class="nav-link nav-toggle">
                                <span class="title">Settings</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                    </ul>
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    <!-- BEGIN THEME PANEL -->
                    
                   
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <i class="icon-home"></i>
                                Home
                                <i class="fa fa-angle-right"></i>
                            </li>
                            <li>
                                <span>Settings</span>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12">
                        
                        	
                          <c:if test="${messageDisplay != null}">
                                <div id="note" class="note note-success">
                                    <p> <b>${messageDisplay}</b> </p>
                                </div>
                            </c:if>
                            
                            
                           	<div class="profile-content">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="portlet light" onclick="onclickCleanMessage()">
                                            <div class="portlet-title tabbable-line">
                                                <div class="caption caption-md">
                                                    <i class="icon-globe theme-font hide"></i>
                                                    <span class="caption-subject font-blue-madison bold uppercase">Profile Account</span>
                                                </div>
                                                <ul class="nav nav-tabs">
                                                    <li class="active" id="personal">
                                                        <a href="#tab_1_1" onclick="activePersonalInfo()" data-toggle="tab">Personal Info</a>
                                                    </li>
                                                    <li id="avatar">
                                                        <a href="#tab_1_2" onclick="activeAvatar()" data-toggle="tab">Change Avatar</a>
                                                    </li>
                                                    <li id="password">
                                                        <a href="#tab_1_3" onclick="activePassword()" data-toggle="tab">Change Password</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="portlet-body">
                                                <div class="tab-content">
                                                    <!-- PERSONAL INFO TAB -->
                                                    <div class="tab-pane active" id="tab_1_1">
                                                       	<form:form action='updateBusinessUser' onsubmit="return validateForm()" method="post" name="myForm1" commandName="businessUserForm">
                                                                <form:input path="id" type="hidden" class="form-control " id="id"/>

                                                                <div class="form-group">
                                                                    <label>Full Name</label>
                                                                    <form:input path="fullname" type="text" class="form-control " onclick="onclickCleanField('fullnameerror')" id="fullname" placeholder="Full Name" />
                                                                    <form:errors path="fullname" class="control-label" />
                                                                    <p id="fullnameerror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Prefered Name</label>
                                                                    <form:input path="preferedname" type="text" class="form-control " onclick="onclickCleanField('preferednameerror')" id="preferedname" placeholder="Prefered Name" />
                                                                    <form:errors path="preferedname" class="control-label" />
                                                                    <p id="preferednameerror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
												                  <label>E-mail</label>
												                  <form:input path="email" type="text" class="form-control " onclick="onclickCleanField('emailerror')" id="email" placeholder="email" />
												                  <form:errors path="email" class="control-label" style="color:red"/>
												                  <p id="emailerror" style="color:red"></p>
												                </div>
												

												                <div class="form-group">
												                  <label>Phone</label>
												                  <form:input path="phone" type="tel" class="form-control " onclick="onclickCleanField('phoneerror')" id="phone" placeholder="phone" />
												                  <form:errors path="phone" class="control-label" />
												                  <p id="phoneerror" style="color:red"></p>
												                </div>
												                <div class="form-group">
												                  <label>Postal Address</label>
												                  <form:input path="address" type="text" class="form-control " onclick="onclickCleanField('addresserror')" id="address" placeholder="address" />
												                  <form:errors path="address" class="control-label" /> 
												                  <p id="addresserror" style="color:red"></p>
												                </div>
												                <div class="form-group input-group">
												                  <input class="btn btn-default" type="submit" id="mySubmitButton" value="Save/update my details">
												                </div>
											             </form:form>
                                                    </div>
                                                    <!-- END PERSONAL INFO TAB -->
                                                    <!-- CHANGE AVATAR TAB -->
                                                    <div class="tab-pane" id="tab_1_2">
                                                        <p> Select a profile photo. </p>
                                                        <form method="POST" action="uploadPhoto" enctype="multipart/form-data">
																File to upload: <input type="file" name="file">											 
																<input type="submit" value="Upload"> Press here to upload the file!
														</form>	
                                                    </div>
                                                    <!-- END CHANGE AVATAR TAB -->
                                                    <!-- CHANGE PASSWORD TAB -->
                                                    <div class="tab-pane" id="tab_1_3">
                                                          <form:form action='updateBusinessUserPassword' method="post" name="myForm3" onsubmit="return validatePassword()"  commandName="passwordDTOForm">
												                <form:input path="type" type="hidden" class="form-control " id="type" value="businessuser"/>
												              	<form:input path="email" type="hidden" class="form-control " id="email"/>
												              	
												                <div class="form-group">
												                  <label>Current Password</label>
												                  	<form:input path="currentPassword" type="password" class="form-control " id="currentPassword" placeholder="Current Password" />
																    <form:errors path="currentPassword" class="control-label" style="color:red"/>
																	<p id="currentPassworderror" style="color:red"></p>
												                </div> 
												
												                <div class="form-group">
												                  	<label>New Password</label>
												                  	<form:input path="newPassword" type="password" class="form-control " id="newPassword" placeholder="New Password" />
																	<form:errors path="newPassword" class="control-label" />
																	<p id="newPassworderror" style="color:red"></p>
												                </div>
												
												                <div class="form-group">
												                  	<label>New Password Confirmation</label>
												                  	<form:input path="confirmNewPassword" type="password" class="form-control " id="confirmNewPassword" placeholder="Confirm New Password" />
																	<form:errors path="confirmNewPassword" class="control-label" />
																	<p id="confirmNewPassworderror" style="color:red"></p>
												                </div>
												
												                <div class="form-group input-group">
												                  <input class="btn btn-default" type="submit" value="Update">
												                </div>
												           </form:form>
                                                    </div>
                                                    <!-- END CHANGE PASSWORD TAB -->
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END PROFILE CONTENT -->
                            </div>
                             
                            
                        </div>
                    </div>
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div class="page-footer">
            <div class="page-footer-inner"> 2017 &copy; Translatorss
                <div class="scroll-to-top">
                    <i class="icon-arrow-up"></i>
                </div>
            </div>
        </div>    
            <!-- END FOOTER -->
            <!-- BEGIN QUICK NAV -->
           
            <div class="quick-nav-overlay"></div>
            <!-- END QUICK NAV -->
            <!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<script src="../assets/global/plugins/ie8.fix.min.js"></script> 
<![endif]-->
            <!-- BEGIN CORE PLUGINS -->
            <script src="resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
            <!-- END CORE PLUGINS -->
            <!-- BEGIN THEME GLOBAL SCRIPTS -->
            <script src="resources/assets/global/scripts/app.min.js" type="text/javascript"></script>
            <!-- END THEME GLOBAL SCRIPTS -->
            <!-- BEGIN THEME LAYOUT SCRIPTS -->
            <script src="resources/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
            <script src="resources/assets/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
            <script src="resources/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
            <script src="resources/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
            <!-- END THEME LAYOUT SCRIPTS -->
            
            
            <!-- jQuery -->
		      <script src="resources/js/jquery.js"></script>
		      <script src="resources/js/modal.js"></script>
		 	  <script src="resources/js/jquery.countdown.js"></script>
			  <script src="resources/js/jquery.countdown.min.js"></script>
		 	  <script src="resources/js/countdown1.js"></script>
		      <script src="resources/js/datatable.js"></script>

            <!-- Phone Number Format-->
              <script src="resources/js/intlTelInput.js"></script>



        <!-- Realtime-->
			  <script src="resources/js/realtime/sockjs.js"></script>
			  <script src="resources/js/realtime/stomp.js"></script>
			  <script src="resources/js/realtime/realtime.js"></script>
			  <script>
			      function onLoad() {
			          requestSetCountOfUnreadMessages()
			          var funcs=[
			              subscribeUnreadMessages
			          ];
			          connect(funcs);

			    	  var field = ${info};
			    	
			    	   if(field==100){
			    		  $('#tab_1_1').show();
			    		  $('#tab_1_2').hide();
			    		  $('#tab_1_3').hide();
			    		  document.getElementById("personal").classList.add('active');
			    		  document.getElementById("avatar").classList.remove('active');
			    		  document.getElementById("password").classList.remove('active');
		    	       }
			    	  
			    	   if(field==200){
				    		  $('#tab_1_1').hide();
				    		  $('#tab_1_2').show();
				    		  $('#tab_1_3').hide();
				    		  document.getElementById("personal").classList.remove('active');
				    		  document.getElementById("avatar").classList.add('active');
				    		  document.getElementById("password").classList.remove('active');
			    	   }
			    	   
			    	   if(field==300){
				    		  $('#tab_1_1').hide();
				    		  $('#tab_1_2').hide();
				    		  $('#tab_1_3').show();
				    		  document.getElementById("personal").classList.remove('active');
				    		  document.getElementById("avatar").classList.remove('active');
				    		  document.getElementById("password").classList.add('active');
				    	  }
			      }
			  </script>

        <%--<script>--%>
            <%--$("#phone").intlTelInput({--%>
                <%--utilsScript: "resources/js/utils.js"--%>
            <%--});--%>
        <%--</script>--%>

		    
        <script>
        
        	function activePersonalInfo(){
        		$('#tab_1_1').show();
	    		  $('#tab_1_2').hide();
	    		  $('#tab_1_3').hide();
	    		  document.getElementById("personal").classList.add('active');
	    		  document.getElementById("avatar").classList.remove('active');
	    		  document.getElementById("password").classList.remove('active');
        	}
        
        	function activeAvatar(){
        		  $('#tab_1_1').hide();
	    		  $('#tab_1_2').show();
	    		  $('#tab_1_3').hide();
	    		  document.getElementById("personal").classList.remove('active');
	    		  document.getElementById("avatar").classList.add('active');
	    		  document.getElementById("password").classList.remove('active');
        	}
        	
        	function activePassword(){
      		      $('#tab_1_1').hide();
	    		  $('#tab_1_2').hide();
	    		  $('#tab_1_3').show();
	    		  document.getElementById("personal").classList.remove('active');
	    		  document.getElementById("avatar").classList.remove('active');
	    		  document.getElementById("password").classList.add('active');
      	    }
        	
			function resetpasswordErrorValues() {
			    $('#currentPassworderror').hide();
			    $('#newPassworderror').hide();
			    $('#confirmPassworderror').hide(); 
			}

            function onclickCleanField(idfield){
                document.getElementById(idfield).style.display="none";
            }

			function validatePassword(){
				resetpasswordErrorValues();
				var text;
			
				var currentPassword = document.forms["myForm3"]["currentPassword"].value;
				if(currentPassword==null || currentPassword==""){
					document.getElementById("currentPassworderror").innerHTML = "Incorrect Password Format";
			    	$('#currentPassworderror').show();
			        return false;
				}else{
					text = checkPwd(currentPassword);
					if(text!=null){
						document.getElementById("currentPassworderror").innerHTML = text;
				    	$('#currentPassworderror').show();
				        return false;
					}
				}
				
				var newPassword = document.forms["myForm3"]["newPassword"].value;
				if(newPassword==null || newPassword==""){
					document.getElementById("newPassworderror").innerHTML = "Incorrect Password Format";
			    	$('#newPassworderror').show();
			        return false;
				}else{
					text = checkPwd(newPassword);
					if(text!=null){
						document.getElementById("newPassworderror").innerHTML = text;
			    		$('#newPassworderror').show();
			        	return false;
					}
				}
			
				var confirmNewPassword = document.forms["myForm3"]["confirmNewPassword"].value;
				if(confirmNewPassword==null || confirmNewPassword==""){
					document.getElementById("confirmNewPassworderror").innerHTML = "Incorrect Password Format";
			    	$('#confirmNewPassworderror').show();
			        return false;
				}else{
					text = checkPwd(confirmNewPassword);
					if(text!=null){
						document.getElementById("confirmNewPassworderror").innerHTML = text;
				    	$('#confirmNewPassworderror').show();
				        return false;
					}
				}
				
				if(newPassword!=confirmNewPassword){
					document.getElementById("confirmNewPassworderror").innerHTML = "Password was no the same";
			    	$('#confirmNewPassworderror').show();
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
			
			 function resetPayModal() {
			        $('#nameerror').hide();
			        $('#passworderror').hide();
			        $('#phoneerror').hide();
			        $('#emailerror').hide();
			        $('#addresserror').hide();
			        $('#naatinumbererror').hide();
			        $('#paypalclientiderror').hide();
			        $('#languagelisterror').hide();
			}
			
			function onclickCleanField(idfield){
			  document.getElementById(idfield).style.display="none";
			}

			function onclickCleanMessage(){
				document.getElementById("note").style.display="none";
			} 
			
			function validateForm() {
                resetPayModal();
                var text;
                
                var name = document.forms["myForm1"]["fullname"].value;
                if (name == null || name == "") {
                    text = "Full Name was not selected!";
                    document.getElementById("fullnameerror").innerHTML = text;
                    $('#fullnameerror').show();
                    return false;
                }
                
                var preferedname = document.forms["myForm1"]["preferedname"].value;
                if (preferedname == null || preferedname == "") {
                    text = "The Prefered Name was not selected!";
                    document.getElementById("preferednameerror").innerHTML = text;
                    $('#preferednameerror').show();
                    return false;
                }

                var email = document.forms["myForm1"]["email"].value;
                if (!validateEmail(email)) {
                    document.getElementById("emailerror").innerHTML = "Email format incorrect";
                    $('#emailerror').show();
                    return false;
                }

                var phone = document.forms["myForm1"]["phone"].value;
                if (phone == null || phone == "") {
                    text = "Phone needs to be selected!";
                    document.getElementById("phoneerror").innerHTML = text;
                    $('#phoneerror').show();
                    return false;
                }

                var address = document.forms["myForm1"]["address"].value;
                if(address== null || address==""){
                    text = "Address needs to be selected";
                    document.getElementById("addresserror").innerHTML = text;
                    $('#addresserror').show();
                    return false;
                }
                
                document.getElementById("mySubmitButton").disabled = true;
            }

            function validateEmail(email) {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            }
        </script>
    </body>
</html>
