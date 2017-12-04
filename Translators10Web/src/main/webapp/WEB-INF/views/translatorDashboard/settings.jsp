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
        <link rel="shortcut icon" href="favicon.ico" /> 
    
    	<style>
		        td.bg_red {
		            background-color: red !important;
		        }
		
		        td.bg_green {
		            background-color: green !important;
		        }
    	</style>
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
	                                        <a href="<c:url value='allconversations'/>">view all</a>
	                                    </li>
	                                    <li>
	                                        <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
	                                            <c:forEach items="${unreadMessageList}" var="message">
	                                            	<li>
		                                                <a href="<c:url value='/seeMyConversation/${message.conversationid}' />">
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
                                    <span class="username username-hide-on-mobile"> ${translatorName} </span>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                    <li>
                                        <a href="<c:url value='/settings'/>">
                                            <i class="icon-user"></i> My Profile
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<c:url value='allconversations'/>">
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
                         <li class="nav-item">
                            <a href="<c:url value='/dashboard'/>" class="nav-link nav-toggle">
                                <span class="title">Dashboard</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value='/myPendingActions'/>" class="nav-link nav-toggle">
                                <span class="title">Assignments to be quoted</span>
                                <span class="selected"></span>
                                <span class="arrow"></span>
                            </a>
                        </li>    
                        <li class="nav-item">
                            <a href="<c:url value='/myJobInProgress'/>" class="nav-link nav-toggle">
                                <span class="title">Assignments in progress</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value='/myHistory'/>" class="nav-link nav-toggle">
                                <span class="title">Assignments History</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                         <li class="nav-item  ">
                            <a href="<c:url value='quoteSettings'/>" class="nav-link nav-toggle">
                                <span class="title">Automatic quote set up</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                        <li class="nav-item  start active open">
                            <a href="<c:url value='/settings'/>" class="nav-link nav-toggle">
                                <span class="title">Settings</span>
                                <span class="arrow open"></span>
                            </a>
                        </li>
                       <%--  <li class="nav-item">
                            <a href="<c:url value='/suscription' />" class="nav-link nav-toggle">
                                <span class="title">Suscription</span>
                                <span class="arrow"></span>
                            </a>
                        </li>        --%>
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
                                                        <a href="#tab_1_1" onclick="activePersonalInfo()" data-toggle="tab">My details</a>
                                                    </li>
                                                    <li id="avatar">
                                                        <a href="#tab_1_2" onclick="activeAvatar()" data-toggle="tab">Update Avatar</a>
                                                    </li>
                                                    <li id="password">
                                                        <a href="#tab_1_3" onclick="activePassword()" data-toggle="tab">Update Password</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="portlet-body">
                                                <div class="tab-content">
                                                    <!-- PERSONAL INFO TAB -->
                                                    <div class="tab-pane active" id="tab_1_1">
                                                        <form:form action='updateTranslator' name="myForm2" onsubmit="return validateFormDetails()" method="post" commandName="updateTranslatorForm">
                                                                <div class="form-group">
                                                                    <form:input path="id" class="form-control " id="id" type="hidden"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label>Translator State</label>
                                                                    <form:input path="status" type="text" class="form-control"   id="status" readonly="true"/>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Nati Verified</label>
                                                                    <form:input path="natyVerified" class="form-control" id="natyVerified" readonly="true"/>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Nati Expiry Date</label>
                                                                    <form:input path="natyExtiryDate" class="form-control" id="natyExtiryDate" readonly="true"/>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Valid Suscription</label>
                                                                    <form:input path="validSuscription" class="form-control" id="validSuscription" readonly="true"/>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Manually Paused</label>
                                                                    <form:input path="manualyPaused" class="form-control" id="manualyPaused" readonly="true"/>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Full Name (for legal purposes)</label>
                                                                    <form:input path="fullName" type="text" onclick="onclickCleanField('fullNameerror')" class="form-control " id="fullName"/>
                                                                    <p id="fullNameerror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Preferred Name (for communication purposes)</label>
                                                                    <form:input path="preferedName" type="text" onclick="onclickCleanField('preferednameerror')"  class="form-control " id="preferedName"/>
                                                                    <p id="preferednameerror" style="color:red"></p>
                                                                </div>

									                            <div class="form-group">
									                                <label>E-mail</label>
									                                <form:input path="email" type="text"  onclick="onclickCleanField('emailerror')" class="form-control " id="email"/>
									                                <b><form:errors path="email" class="control-label" style="color:red;"/></b>
									                            	<p id="emailerror" style="color:red"></p>
									                            </div>

                                                                <div class="form-group">
                                                                    <label>Contact Number</label>
                                                                    <form:input path="phone" type="text" onclick="onclickCleanField('phoneerror')" class="form-control " id="phone"/>
                                                                    <p id="phoneerror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Home Address</label>
                                                                    <form:input path="address" type="text" onclick="onclickCleanField('addresserror')" class="form-control " id="address"/>
                                                                    <p id="addresserror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Paypal Client</label>
                                                                    <form:input path="paypalClientId" type="text" onclick="onclickCleanField('paypalclientiderror')" class="form-control " id="paypalClientId"/>
                                                                    <p id="paypalclientiderror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>ABN name</label>
                                                                    <form:input path="abn_name" type="text" onclick="onclickCleanField('abn_nameerror')" class="form-control " id="abn_name"/>
                                                                    <p id="abn_nameerror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>ABN Number</label>
                                                                    <form:input path="abn_number" type="text" onclick="onclickCleanField('abn_numbererror')" class="form-control " id="abn_number"/>
                                                                    <p id="abn_numbererror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>ABN Address</label>
                                                                    <form:input path="abn_address" type="text" onclick="onclickCleanField('abn_addresserror')" class="form-control " id="abn_address"/>
                                                                    <p id="abn_addresserror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Nati expiry date</label>
                                                                    <form:input path="natyExpiration" type="text" class="form-control " id="natyExpiration" readonly="true"/>
                                                                    <p id="natyExpirationerror" style="color:red"></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Nati number</label>
                                                                    <form:input path="naatiNumber" class="form-control" id="naatiNumber" readonly="true"/>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Language</label>
                                                                    <form:input path="language" class="form-control" id="language" readonly="true"/>
                                                                </div>

                                                                <div class="form-group input-group">
                                                                        <button name="updateTranslator" value="updateTranslator" id="mySubmitButton" class="btn-lg btn-primary pull-right">Save my Details</button>
                                                                </div>
									                        </form:form>    
                                                    </div>
                                                    <!-- END PERSONAL INFO TAB -->
                                                    <!-- CHANGE AVATAR TAB -->
                                                    <div class="tab-pane" id="tab_1_2">
                                                        <p> Choose  a  picture. </p>
                                                         <form method="POST" action="uploadTranslatorPhoto" enctype="multipart/form-data">
																File to upload: <input type="file" name="file">											 
																<input type="submit" value="Upload Picture"> Press here to upload the file!
														</form>	
                                                    </div>
                                                    <!-- END CHANGE AVATAR TAB -->
                                                    <!-- CHANGE PASSWORD TAB -->
                                                    <div class="tab-pane" id="tab_1_3">
                                                        <div class="panel-heading">
                                                            <h6>Minimum 8 max 25 characters alphanumeric, no special characters, at least one uppercase letter, at least one lowercase letter, at least one number </h6>
                                                        </div>
                                                         <form:form action='updateTranslatorPassword' name="myForm3" onsubmit="return validatePassword()" method="post" commandName="passwordDTOForm">
												              	<form:input path="email" type="hidden" class="form-control " id="email" />
												                <form:input path="type" type="hidden" class="form-control"  id="type" value="translator"/>
												              	
												                <div class="form-group">
												                  <label>Current Password</label>
												                  	<form:input path="currentPassword" class="form-control"  id="currentPassword" type="password" placeholder="Current Password" />
																	<form:errors path="currentPassword" class="control-label" style="color:red"/>
																	<p id="currentPassworderror" style="color:red"></p>
												                </div> 
												
												                <div class="form-group">
												                  	<label>New Password</label>
												                  	<form:input path="newPassword" class="form-control"   id="newPassword" type="password" placeholder="New Password" />
																	<form:errors path="newPassword" class="control-label" style="color:red" />
																	<p id="newPassworderror" style="color:red"></p>
												                </div>
												
												                <div class="form-group">
												                  	<label>New Password Confirmation</label>
												                  	<form:input path="confirmNewPassword" class="form-control " id="confirmNewPassword" type="password" placeholder="Confirm New Password" />
																	<form:errors path="confirmNewPassword" class="control-label" style="color:red"/>
																	<p id="confirmNewPassworderror" style="color:red"></p>
												                </div>
												
												                <div class="form-group input-group">
												                  <input class="btn btn-default" type="submit" value="Update Password">
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
            
            
            <!--Realtime-->
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
        
    	function onclickCleanMessage(){
			document.getElementById("note").style.display="none";
		} 
    	
            function validateEmail(email) {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            }

            function onclickCleanField(idfield){
                document.getElementById(idfield).style.display="none";
            }

            function validateFormDetails(){
                var text;
                var fullName = document.forms["myForm2"]["fullName"].value;
                if(fullName ==null || fullName==""){
                    document.getElementById("fullNameerror").innerHTML = "Incorrect Format";
                    $('#fullNameerror').show();
                    return false;
                }

                var preferedName = document.forms["myForm2"]["preferedName"].value;
                if(preferedName ==null || preferedName==""){
                    document.getElementById("preferednameerror").innerHTML = "Incorrect Format";
                    $('#preferednameerror').show();
                    return false;
                }

                var email = document.forms["myForm2"]["email"].value;
                if (!validateEmail(email)) {
                    document.getElementById("emailerror").innerHTML = "Email format incorrect";
                    $('#emailerror').show();
                    return false;
                }

                var phone = document.forms["myForm2"]["phone"].value;
                if (phone == null || phone == "") {
                    text = "Phone needs to be selected!";
                    document.getElementById("phoneerror").innerHTML = text;
                    $('#phoneerror').show();
                    return false;
                }

                var address = document.forms["myForm2"]["address"].value;
                if(address== null || address==""){
                    text = "Address needs to be selected";
                    document.getElementById("addresserror").innerHTML = text;
                    $('#addresserror').show();
                    return false;
                }

                var paypalClientId = document.forms["myForm2"]["paypalClientId"].value;
                if(!validateEmail(paypalClientId)){
                    text = "Incorrect Email Format";
                    document.getElementById("paypalclientiderror").innerHTML = text;
                    $('#paypalclientiderror').show();
                    return false;
                }

                var abn_nameText = "The ABN Name needs to be selected!";
                var abn_name = document.forms["myForm2"]["abn_name"].value;
                if (abn_name == null || abn_name == "") {
                    document.getElementById("abn_nameerror").innerHTML = abn_nameText;
                    $('#abn_nameerror').show();
                    return false;
                }

                var abn_numberText = "The ABN Number needs to be selected!";
                var abn_number = document.forms["myForm2"]["abn_number"].value;
                if (abn_number == null || abn_number == "") {
                    document.getElementById("abn_numbererror").innerHTML = abn_numberText;
                    $('#abn_numbererror').show();
                    return false;
                }

                var abn_addressText = "The ABN address needs to be selected!";
                var abn_address = document.forms["myForm2"]["abn_address"].value;
                if (abn_address == null || abn_address == "") {
                    document.getElementById("abn_addresserror").innerHTML = abn_addressText;
                    $('#abn_addresserror').show();
                    return false;
                }
                
                document.getElementById("mySubmitButton").disabled = true;
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
        </script>
    </body>
</html>
