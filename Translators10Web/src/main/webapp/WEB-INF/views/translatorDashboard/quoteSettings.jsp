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

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid"  onload="onLoad()">
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
                         <li class="nav-item  start active open">
                            <a href="<c:url value='quoteSettings'/>" class="nav-link nav-toggle">
                                <span class="title">Automatic quote set up</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                        <li class="nav-item  ">
                            <a href="<c:url value='/settings'/>" class="nav-link nav-toggle">
                                <span class="title">Settings</span>
                                <span class="arrow open"></span>
                            </a>
                        </li>
                        <%-- <li class="nav-item">
                            <a href="<c:url value='/suscription' />" class="nav-link nav-toggle">
                                <span class="title">Suscription</span>
                                <span class="arrow"></span>
                            </a>
                        </li>   --%>     
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
                                <span>Quote Value Settings</span>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12">
                           	<div class="profile-content">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="portlet light ">
                                            <div class="portlet-title tabbable-line">
                                                <div class="caption caption-md">
                                                    <i class="icon-globe theme-font hide"></i>
                                                    <span class="caption-subject font-blue-madison bold uppercase">Profile Account</span>
                                                </div>
                                                <ul class="nav nav-tabs">
                                                    <li class="active">
                                                        <a href="#tab_1_1" data-toggle="tab">Urgent</a>
                                                    </li>
                                                    <li>
                                                        <a href="#tab_1_2" data-toggle="tab">Medium</a>
                                                    </li>
                                                    <li>
                                                        <a href="#tab_1_3" data-toggle="tab">Relaxed</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="portlet-body">
                                                <div class="tab-content">
                                                    <!-- PERSONAL INFO TAB -->
                                                    <div class="tab-pane active" id="tab_1_1">
                                                        <form:form action='urgentUpdateQuotationConfig' name="myForm" onsubmit="return validateUrgentForm()" method="post" commandName="urgentQuoteForm" enctype="multipart/form-data">
							                    				<div class="panel-heading">
																	<h3 class="panel-title"><strong>Urgent Timeframe Requests</strong> - 24 Hours</h3>
                                                                    <h6>Tickbox Activates automatic quotation for requests to be completed within 24 hours (for digital copy) </h6>
																	<input type="checkbox" id="urgentValue" onclick="enableDisableUrgent()">Disable
																	<p id="urgentMessage" style="color:green"></p>
																</div>
							                    				<div class="panel-body">
							                    	        		<div class="form-group">
							                        	        		<label for="111">Document Category: Drivers License</label>
							                        	        		<form:input path="driverLic" type="text" class="form-control " id="urgenDriverLic" />
							                        	        		<form:errors path="driverLic" class="control-label" />
							                        	        		<p id="urgenDriverLicerror" style="color:red">${message}</p>
							                            			</div>
                                                                    <div class="form-group">
                                                                        <label for="444">Document Category: Passport</label>
                                                                        <form:input path="passport" type="text" class="form-control " id="urgentBusinessDocument"/>
                                                                        <form:errors path="passport" class="control-label" />
                                                                        <p id="urgentBusinessDocumenterror" style="color:red">${message}</p>
                                                                    </div>
                                                                    <div class="form-group">
							                                			<label for="222">Document Category: Birth, Death or Marriage Certificate</label>
							                                			<form:input path="birthDeath" type="text" class="form-control " id="urgentBirthDeath"/>
							                                			<form:errors path="birthDeath" class="control-label" />
							                                			<p id="urgentBirthDeatherror" style="color:red">${message}</p>
							                            			</div>
							                  						<div class="form-group">
							                                			<label for="333">Document Category: Academic Records / Transcripts</label>
							                                			<form:input path="academicTranscript" type="text" class="form-control " id="urgentAcademicTranscript"/>
							                                			<form:errors path="academicTranscript" class="control-label" />
							                                			<p id="urgentAcademicTranscripterror" style="color:red">${message}</p>
							                            			</div>

																</div>
																<div class="form-group">
																	<div class="col-sm-offset-2 col-sm-10">
																		<button name="updateQuotation" value="updateQuotation" id="urgentBotton" class="btn-lg btn-primary pull-right">Update Urgent Quotation</button>
																	</div>
																</div>
														</form:form>	
                                                    </div>
                                                    <!-- END PERSONAL INFO TAB -->
                                                    <!-- CHANGE AVATAR TAB -->
                                                    <div class="tab-pane" id="tab_1_2">
                                                        <form:form action='mediumUpdateQuotationConfig' name="myForm" onsubmit="return validateMediumForm()"  method="post" commandName="mediumQuoteForm" enctype="multipart/form-data">
							                    				<div class="panel-heading">
																	<h3 class="panel-title"><strong>Medium Timeframe Requests</strong> - 48 Hours</h3>
                                                                    <h6>Tickbox Activates automatic quotation for requests to be completed within 48 hours (for digital copy) </h6>
																	<input type="checkbox" id="mediumValue" onclick="enableDisableMedium()">Disable
																	<p id="mediumMessage" style="color:green"></p>
																</div>
							                    				<div class="panel-body">
							                    	        		<div class="form-group">
							                        	        		<label for="111">Document Category: Drivers License</label>
							                        	        		<form:input path="driverLic" type="text" class="form-control " id="mediumDriverLic" />
							                        	        		<form:errors path="driverLic" class="control-label" />
							                        	        		<p id="mediumDriverLicerror" style="color:red">${message}</p>
							                            			</div>
                                                                    <div class="form-group">
                                                                        <label for="444">Document Category: Passport</label>
                                                                        <form:input path="passport" type="text" class="form-control " id="mediumBusinessDocument"/>
                                                                        <form:errors path="passport" class="control-label" />
                                                                        <p id="mediumBusinessDocumenterror" style="color:red">${message}</p>
                                                                    </div>
								                 		 			<div class="form-group">
							                                			<label for="222">Document Category: Birth, Death or Marriage Certificate</label>
							                                			<form:input path="birthDeath" type="text" class="form-control " id="mediumBirthDeath"/>
							                                			<form:errors path="birthDeath" class="control-label" />
							                                			<p id="mediumBirthDeatherror" style="color:red">${message}</p>
							                            			</div>
							                  						<div class="form-group">
							                                			<label for="333">Document Category: Academic Records / Transcripts</label>
							                                			<form:input path="academicTranscript" type="text" class="form-control " id="mediumAcademicTranscript"/>
							                                			<form:errors path="academicTranscript" class="control-label" />
							                                			<p id="mediumAcademicTranscripterror" style="color:red">${message}</p>
							                            			</div>

																</div>
																<div class="form-group">
																	<div class="col-sm-offset-2 col-sm-10">
																		<button name="updateQuotation" value="updateQuotation" id="mediumBotton" class="btn-lg btn-primary pull-right">Update Medium Quotation</button>
																	</div>
																</div>
														</form:form>	
                                                    </div>
                                                    <!-- END CHANGE AVATAR TAB -->
                                                    <!-- CHANGE PASSWORD TAB -->
                                                    <div class="tab-pane" id="tab_1_3">
                                                         <form:form action='relaxedUpdateQuotationConfig' name="myForm" onsubmit="return validateRelaxedForm()" method="post" commandName="relaxedQuoteForm" enctype="multipart/form-data">
							                    				<div class="panel-heading">
																	<h3 class="panel-title"><strong>Relaxed Timeframe Requests</strong> - 72 Hours</h3>
                                                                    <h6>Tickbox Activates automatic quotation for requests to be completed within 72 hours (for digital copy) </h6>
                                                                    <input type="checkbox" id="relaxedValue" onclick="enableDisableRelaxed()">Disable
																	<p id="relaxedMessage" style="color:green"></p>
																</div>
							                    				<div class="panel-body">
							                    	        		<div class="form-group">
							                        	        		<label for="111">Document Category: Drivers License</label>
							                        	        		<form:input path="driverLic" type="text" class="form-control " id="relaxedDriverLic" />
							                        	        		<form:errors path="driverLic" class="control-label" />
							                        	        		<p id="relaxedDriverLicerror" style="color:red">${message}</p>
							                            			</div>
                                                                    <div class="form-group">
                                                                        <label for="444">Document Category: Passport</label>
                                                                        <form:input path="passport" type="text" class="form-control " id="relaxedBusinessDocument"/>
                                                                        <form:errors path="passport" class="control-label" />
                                                                        <p id="relaxedBusinessDocumenterror" style="color:red">${message}</p>
                                                                    </div>
                                                                    <div class="form-group">
							                                			<label for="222">Document Category: Birth, Death or Marriage Certificate</label>
							                                			<form:input path="birthDeath" type="text" class="form-control " id="relaxedBirthDeath"/>
							                                			<form:errors path="birthDeath" class="control-label" />
							                                			<p id="relaxedBirthDeatherror" style="color:red">${message}</p>
							                            			</div>
							                  						<div class="form-group">
							                                			<label for="333">Document Category: Academic Records / Transcripts</label>
							                                			<form:input path="academicTranscript" type="text" class="form-control " id="relaxedAcademicTranscript"/>
							                                			<form:errors path="academicTranscript" class="control-label" />
							                                			<p id="relaxedAcademicTranscripterror" style="color:red">${message}</p>
							                            			</div>

																</div>
																<div class="form-group">
																	<div class="col-sm-offset-2 col-sm-10">
																		<button name="updateQuotation" value="updateQuotation" id="relaxedBotton" class="btn-lg btn-primary pull-right">Update Relaxed Quotation</button>
																	</div>
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
		          }
		    </script>
		    <script type="text/javascript">
			    function validateUrgentForm(){
					var drivLic = document.getElementById('urgenDriverLic').value;
					alert(drivLic);
					if (isNaN(drivLic)||drivLic==null){
						  document.getElementById('urgenDriverLicerror').innerHTML ="Value must be a number";
						  return false;
			    	}
			    	if (drivLic<20){
						  document.getElementById('urgenDriverLicerror').innerHTML ="Value must be greater than $20";
						  return false;
			    	}
			    	
			    	
			    	var birthDeath = document.getElementById('urgentBirthDeath').value;
			    	if (isNaN(birthDeath)||birthDeath==null){
						  document.getElementById('urgentBirthDeatherror').innerHTML ="Value must be a number";
						  return false;
			    	}
			    	if (birthDeath<20){
						  document.getElementById('urgentBirthDeatherror').innerHTML ="Value must be greater than $20";
						  return false;
			    	}
			    	
			    	var academic = document.getElementById('urgentAcademicTranscript').value;
			    	if (isNaN(academic)||academic==null){
						  document.getElementById('urgentAcademicTranscripterror').innerHTML ="Value must be a number";
						  return false;
			    	}
			    	if (academic<20){
						  document.getElementById('urgentAcademicTranscripterror').innerHTML ="Value must be greater than $20";
						  return false;
			    	}
			    	
			    	var businessDoc=document.getElementById('urgentBusinessDocument').value;
			    	if (isNaN(businessDoc)||businessDoc==null){
						  document.getElementById('urgentBusinessDocumenterror').innerHTML ="Value must be a number";
						  return false;
			    	}
			    	if (businessDoc<20){
						  document.getElementById('urgentBusinessDocumenterror').innerHTML ="Value must be greater than $20";
						  return false;
			    	}
			    }
		    </script>
		    
		    <script type="text/javascript">
				    function validateMediumForm(){
						var drivLic = document.getElementById('mediumDriverLic').value;
						alert(drivLic);
						if (isNaN(drivLic)||drivLic==null){
							  document.getElementById('mediumDriverLicerror').innerHTML ="Value must be a number";
							  return false;
				    	}
				    	if (drivLic<20){
							  document.getElementById('mediumDriverLicerror').innerHTML ="Value must be greater than $20";
							  return false;
				    	}
				    	
				    	
				    	var birthDeath = document.getElementById('mediumBirthDeath').value;
				    	if (isNaN(birthDeath)||birthDeath==null){
							  document.getElementById('mediumBirthDeatherror').innerHTML ="Value must be a number";
							  return false;
				    	}
				    	if (birthDeath<20){
							  document.getElementById('mediumBirthDeatherror').innerHTML ="Value must be greater than $20";
							  return false;
				    	}
				    	
				    	var academic = document.getElementById('mediumAcademicTranscript').value;
				    	if (isNaN(academic)||academic==null){
							  document.getElementById('mediumAcademicTranscripterror').innerHTML ="Value must be a number";
							  return false;
				    	}
				    	if (academic<20){
							  document.getElementById('mediumAcademicTranscripterror').innerHTML ="Value must be greater than $20";
							  return false;
				    	}
				    	
				    	var businessDoc=document.getElementById('mediumBusinessDocument').value;
				    	if (isNaN(businessDoc)||businessDoc==null){
							  document.getElementById('mediumBusinessDocumenterror').innerHTML ="Value must be a number";
							  return false;
				    	}
				    	if (businessDoc<20){
							  document.getElementById('mediumBusinessDocumenterror').innerHTML ="Value must be greater than $20";
							  return false;
				    	}
				    }
	    	</script>
	    	
	    	<script type="text/javascript">
			    function validateRelaxedForm(){
					var drivLic = document.getElementById('relaxedDriverLic').value;
					alert(drivLic);
					if (isNaN(drivLic)||drivLic==null){
						  document.getElementById('relaxedDriverLicerror').innerHTML ="Value must be a number";
						  return false;
			    	}
			    	if (drivLic<20){
						  document.getElementById('relaxedDriverLicerror').innerHTML ="Value must be greater than $20";
						  return false;
			    	}
			    	
			    	
			    	var birthDeath = document.getElementById('relaxedBirthDeath').value;
			    	if (isNaN(birthDeath)||birthDeath==null){
						  document.getElementById('relaxedBirthDeatherror').innerHTML ="Value must be a number";
						  return false;
			    	}
			    	if (birthDeath<20){
						  document.getElementById('relaxedBirthDeatherror').innerHTML ="Value must be greater than $20";
						  return false;
			    	}
			    	
			    	var academic = document.getElementById('relaxedAcademicTranscript').value;
			    	if (isNaN(academic)||academic==null){
						  document.getElementById('relaxedAcademicTranscripterror').innerHTML ="Value must be a number";
						  return false;
			    	}
			    	if (academic<20){
						  document.getElementById('relaxedAcademicTranscripterror').innerHTML ="Value must be greater than $20";
						  return false;
			    	}
			    	
			    	var businessDoc=document.getElementById('relaxedBusinessDocument').value;
			    	if (isNaN(businessDoc)||businessDoc==null){
						  document.getElementById('relaxedBusinessDocumenterror').innerHTML ="Value must be a number";
						  return false;
			    	}
			    	if (businessDoc<20){
						  document.getElementById('relaxedBusinessDocumenterror').innerHTML ="Value must be greater than $20";
						  return false;
			    	}
			    }
		    </script>
	    	
		    <script type="text/javascript">
				function enableDisableUrgent(){
					if (document.getElementById('urgentValue').checked){
				          document.getElementById('urgenDriverLic').disabled = true;
						  document.getElementById('urgentBirthDeath').disabled = true;
						  document.getElementById('urgentAcademicTranscript').disabled = true;
						  document.getElementById('urgentBusinessDocument').disabled = true;
						  document.getElementById('urgentBotton').disabled = true;
						  document.getElementById('urgentMessage').innerHTML ="Urgent Values are Disabled";

						  $.ajax({  
			                    type : 'GET',  
			                    url : "/disableValue/Urgent",  
			 
			                });  
					}else{
						  document.getElementById('urgenDriverLic').disabled = false;
						  document.getElementById('urgentBirthDeath').disabled = false;
						  document.getElementById('urgentAcademicTranscript').disabled = false;
						  document.getElementById('urgentBusinessDocument').disabled = false;
						  document.getElementById('urgentBotton').disabled = false;
						  document.getElementById('urgentMessage').innerHTML ="Urgent Values are Enabled";
						  $.ajax({  
			                    type : 'GET',  
			                    url : "/enableValue/Urgent",  
			 
			                });  
					}
				}
				
				function enableDisableMedium(){
					if (document.getElementById('mediumValue').checked){
				          document.getElementById('mediumDriverLic').disabled = true;
						  document.getElementById('mediumBirthDeath').disabled = true;
						  document.getElementById('mediumAcademicTranscript').disabled = true;
						  document.getElementById('mediumBusinessDocument').disabled = true;
						  document.getElementById('mediumBotton').disabled = true;
						  document.getElementById('mediumMessage').innerHTML ="Medium Values are Disabled";

						  $.ajax({  
			                    type : 'GET',  
			                    url : "/disableValue/Medium",  
			 
			                });  
					}else{
						  document.getElementById('mediumDriverLic').disabled = false;
						  document.getElementById('mediumBirthDeath').disabled = false;
						  document.getElementById('mediumAcademicTranscript').disabled = false;
						  document.getElementById('mediumBusinessDocument').disabled = false;
						  document.getElementById('mediumBotton').disabled = false;
						  document.getElementById('mediumMessage').innerHTML ="Medium Values are Enabled";
						  $.ajax({  
			                    type : 'GET',  
			                    url : "/enableValue/Medium",  
			 
			                });  
					}
				}
				
				function enableDisableRelaxed(){
					if (document.getElementById('relaxedValue').checked){
				          document.getElementById('relaxedDriverLic').disabled = true;
						  document.getElementById('relaxedBirthDeath').disabled = true;
						  document.getElementById('relaxedAcademicTranscript').disabled = true;
						  document.getElementById('relaxedBusinessDocument').disabled = true;
						  document.getElementById('relaxedBotton').disabled = true;
						  document.getElementById('relaxedMessage').innerHTML ="Relaxed Values are Disabled";

						  $.ajax({  
			                    type : 'GET',  
			                    url : "/disableValue/Relaxed",  
			 
			                });  
					}else{
						  document.getElementById('relaxedDriverLic').disabled = false;
						  document.getElementById('relaxedBirthDeath').disabled = false;
						  document.getElementById('relaxedAcademicTranscript').disabled = false;
						  document.getElementById('relaxedBusinessDocument').disabled = false;
						  document.getElementById('relaxedBotton').disabled = false;
						  document.getElementById('relaxedMessage').innerHTML ="Relaxed Values are Enabled";
						  $.ajax({  
			                    type : 'GET',  
			                    url : "/enableValue/Relaxed",  
			 
			                });  
					}
				}
				
			</script>
			<script>
		        function onLoad() {
		            requestSetCountOfUnreadMessages()
		            var funcs=[
		                subscribeUnreadMessages
		            ];
		            connect(funcs);
		            
		            if(${urgentQuoteForm.valid}===false){
		                  document.getElementById("urgentValue").checked = true;
		                  document.getElementById('urgenDriverLic').disabled = true;
						  document.getElementById('urgentBirthDeath').disabled = true;
						  document.getElementById('urgentAcademicTranscript').disabled = true;
						  document.getElementById('urgentBusinessDocument').disabled = true;
						  document.getElementById('urgentBotton').disabled = true;
						  document.getElementById('urgentMessage').innerHTML ="Urgent Values are Disabled";
		            }
		            
		            
		            if(${mediumQuoteForm.valid}===false){
		                  document.getElementById("mediumValue").checked = true;
		                  document.getElementById('mediumDriverLic').disabled = true;
						  document.getElementById('mediumBirthDeath').disabled = true;
						  document.getElementById('mediumAcademicTranscript').disabled = true;
						  document.getElementById('mediumBusinessDocument').disabled = true;
						  document.getElementById('mediumBotton').disabled = true;
						  document.getElementById('mediumMessage').innerHTML ="Medium Values are Disabled";
		            }
		            
		            if(${relaxedQuoteForm.valid}===false){
		                  document.getElementById("relaxedValue").checked = true;
		                  document.getElementById('relaxedDriverLic').disabled = true;
						  document.getElementById('relaxedBirthDeath').disabled = true;
						  document.getElementById('relaxedAcademicTranscript').disabled = true;
						  document.getElementById('relaxedBusinessDocument').disabled = true;
						  document.getElementById('relaxedBotton').disabled = true;
						  document.getElementById('relaxedMessage').innerHTML ="Relaxed Values are Disabled";
		            }
		        }
		    </script>
    </body>
</html>
