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
        <meta content="Preview page of Metronic Admin Theme #2 for user inbox" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="resources/assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="resources/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="resources/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="resources/assets/apps/css/inbox.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="resources/assets/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/layouts/layout2/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="resources/assets/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="favicon.ico" />
	    <style>
		        td.bg_red {
		            background-color: red !important;
		        }
		
		        td.bg_green {
		            background-color: green !important;
		        }
    	</style>
    	<link href="resources/css/message.css" rel="stylesheet">
    	
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
		                                                <a href="<c:url value='/seeConversation/${message.conversation.id}' />">
		                                                    <span class="photo">
		                                                        <img src="resources/assets/layouts/layout2/img/avatar2.jpg" class="img-circle" alt=""> </span>
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
                            <!-- BEGIN TODO DROPDOWN -->
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                            
                            <!-- END TODO DROPDOWN -->
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
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
    <!--                         <li class="dropdown dropdown-extended quick-sidebar-toggler">
                                <span class="sr-only">Toggle Quick Sidebar</span>
                                <i class="icon-logout"></i>
                            </li> -->
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
						<li class="nav-item  ">
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

                        <li class="nav-item">
                            <a href="<c:url value='userSettings'/>" class="nav-link nav-toggle">
                                <span class="title">Settings</span>
                                <span class="arrow"></span>
                            </a>
                        </li>  
                    </ul>   
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->

                    
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <i class="icon-home"></i>
										Home
								<i class="fa fa-angle-right"></i>
                            </li>
                            <li>
                                <span>Inbox</span>
                            </li>
                        </ul>
                    </div>
                    <!-- END PAGE HEADER-->
                    <div class="inbox">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="inbox-body">
                                    <div class="inbox-content" >
										<div  style="height:300px; overflow-y : scroll;">
											 <table class="table table-bordered table-hover table-striped" id="datatables">
														<tbody>
														<c:forEach items="${messageList}" var="message" >
														   <tr>
																<c:choose>
																	<c:when test="${message.read == false} && ${message.sender}!= ${businessUserFormName}">
																		<tr style="background-color: #cacaca" id="${message.id}">
																	</c:when>
																	<c:otherwise>
																		<tr id="${message.id}">
																	</c:otherwise>
																</c:choose>
																<td class="text-center">
																	<img alt="${message.sender}" class="img-circle" src="${message.photoUrl}" height="62" width="52"/>
																	<footer>
																		<span> ${message.sender}</span>
																	</footer>
																</td>
																<td class="text-center">${message.message}</td>
																<td class="text-center">${message.date}</td>
															</tr>
														</c:forEach>
														</tbody>
											</table>
										</div>
		                                Use a quick response
		                                <form:form name="myForm" commandName="message">
		                                        <div class="conv-write">
		                                            <div class="write-wrap">
		                                                <div class="write-row cf">
		                                                    <spring:bind path="message">
		                                                        <form:textarea id="messageSbmt" path="message" rows="3" cols="75" maxlength="600" class="form-control " onfocus="onMessageFocus(${conversationid})"/>
		                                                    </spring:bind>
		                                                    <spring:bind path="conversationid">
		                                                        <form:input path="conversationid" type="hidden" class="form-control " id="conversationid"/>
		                                                    </spring:bind>
															<spring:bind path="photoUrl">
																<form:input path="photoUrl" type="hidden" class ="form-control" id="photoUrl"/>
															</spring:bind>
		                                                    <spring:bind path="sender">
		                                                        <form:input path="sender" type="hidden" class="form-control" id="sender"/>
		                                                    </spring:bind>
		                                                </div>
		                                                <div class="write-controls cf">
		                                                    <span class="char-count"><em>0</em> / 600</span>
		                                                    <div class="icn-submit"><input
		                                                            class="btn-standard btn-azure-grad btn-send-message"
		                                                            type="button" value="Send" onclick="validateForm()"/>
		                                                    </div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                  </form:form>
		                                  <p id="demo" style="color:red">${messageChat}</p>
		                                  
		                                  <div class="col-lg-4">
					                        	<div class="panel panel-primary">
						                            <div class="panel-heading">
						                                <h3 class="panel-title">Upload File for Revision</h3>
						                            </div>
						                            <div class="panel-body">
						                                <form:form name="myForm" class="form-horizontal" role="form" onsubmit=" return validateFileSubmit()"  action='serviceResponseCustomerProcesor' method="post" commandName="serviceResponse" enctype="multipart/form-data">
						                                    <fieldset id="fieldset">
						                                        <table id="fileTable">
						                                            <tr>
						                                                <td><input id="fileInput" name="files[0]" type="file"/></td>
						                                                <p id="demo" style="color:red"></p>
						                                            </tr>
						                                        </table>
						                                        <br />
						                                        <spring:bind path="id">
						                                            <form:input path="id" type="hidden" class="form-control " id="id" placeholder="id"/>
						                                        </spring:bind>
						                                        <div class="form-group">
						                                            <div class="col-sm-offset-2 col-sm-10">
						                                                <button name="serviceResponseCustomerCreate" id="submitFileForm" value="serviceResponseCustomerCreate" class="btn btn-primary btn-bMember">Upload File!</button>
						                                            </div>
						                                        </div>
						                                    </fieldset>
						                                </form:form>
						                             </div>   
					                        	</div>
                						  </div>
                						  <div class="col-lg-8">
						                    <div class="panel panel-primary">
						                        <div class="panel-heading">
						                            <h3 class="panel-title">Service Request Details</h3>
						                        </div>
						                        <div class="panel-body">
						                            <div class="col-lg-12">
						                                <div class="table-responsive">
						                                    <table class="table table-bordered table-hover table-striped">
						                                        <thead>
						                                        <tr>
						                                            <th class="text-center">ID</th>
						                                            <th class="text-center">Translator Name</th>
						                                            <th class="text-center">Category</th>
						                                            <th class="text-center">Description</th>
						                                            <th class="text-center">Timeframe</th>
						                                            <th class="text-center">From</th>
						                                            <th class="text-center">To</th>
						                                            <th class="text-center">Hard copy?</th>
						                                            <th class="text-center">Quote</th>
						                                            <th class="text-center">Status</th>
						                                            
						                                        </tr>
						                                        </thead>
						                                        <tbody>
						                                        <c:forEach items="${serviceRequestList}" var="serviceRequest">
						                                            <tr class="active">
						                                                <td class="servicerequestid">${serviceRequest.id}</td>
						                                                <td>${serviceRequest.translatorName}</td>
						                                                <td class="finishdate" hidden="true">${serviceRequest.finishDate}</td>
						                                                <td>${serviceRequest.serviceRequestCategory}</td>
						                                                <td>${serviceRequest.description}</td>
						                                                <td>${serviceRequest.timeFrame}</td>
						                                                <td>${serviceRequest.languagefrom}</td>
						                                                <td>${serviceRequest.languageTo}</td>
						                                                <td>${serviceRequest.hardcopy}</td>
						                                                <td>${serviceRequest.quote}</td>
						                                                <td>${serviceRequest.status}</td>
						                                            </tr>
						                                        </c:forEach>
						                                        </tbody>
						                                    </table>
						                                </div>
						                            </div>
						                        </div>
						                    </div>
						                  </div>
                						  <div class="col-lg-8">
									          <div class="panel panel-primary">
									            <div class="panel-heading">
									              <h3 class="panel-title">Service Request Files Details</h3>
									            </div>
									            <div class="panel-body">
									               	<div class="col-lg-12">
								                            <div class="table-responsive">
								                                <table class="table table-bordered table-hover table-striped">
								                                    <thead>
								                                    <tr>
									                                    <th class="text-center">File name</th>
								                                        <th class="text-center">Sender</th>
								                                        <th class="text-center">Date</th>
								                                    </tr>
								                                    </thead>
								                                    <tbody>
									                                   <c:forEach items="${fileList}" var="file">
									                                        <tr class="active">
									                                        	<td>
									                                        		<a href="${file.url}" download="${file.fileName}">${file.fileName}</a>
									                                        	</td>
									                                            <td>${file.createdBy}</td>
									                                            <td>${file.createdAt}</td>
									                                        </tr>
									                                    </c:forEach>
					
								                                    </tbody>
								                                </table>
								                            </div>
					                        		</div>
									            </div>
									          </div>
										  </div>
                						  <div class="col-lg-4">
									          <div class="panel panel-primary">
									            <div class="panel-heading">
									              <h3 class="panel-title">Action</h3>
									            </div>
									            <div class="panel-body">
									               	<div class="col-lg-12">
														<div class="panel-body">
															<c:if test="${serviceRequestStatus == 'OpenService'}">
																 <spring:url value="/approbeJob" var="adminApproveSR" />
																 <button class="btn btn-primary" onclick="location.href='${adminApproveSR}'">Approve Job</button>
															</c:if>
															<c:if test="${serviceRequestStatus == 'Quoted'}">
																 <a href="#"onclick="showPayModal(${quote.id});"><img src="https://paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></a>
															</c:if>
									          			</div>

														<!--modal  -->
														<div class="modal" id="payModal" role="dialog" aria-labelledby="myMdlLabel"
															 aria-hidden="false">
															<div class="modal-backdrop  in" style="height: 902px;"></div>
															<div class="modal-dialog">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close" data-dismiss="modal"
																				aria-label="Close"><span aria-hidden="true">&times;</span>
																		</button>
																		<h4 class="modal-title" id="myMdlLabel">Payment Dialog</h4>
																	</div>
																	<div class="modal-body">
																		<div class="row">
																			<div class="col-md-12">
																				<form id="checkoutContainer" method="post" action='chose'
																					  enctype="application/x-www-form-urlencoded">
																					<input id="quotationId" name="quotationId" type="hidden">
																					<input id="isDonation" type="checkbox" name="isDonation"
																						   aria-labelledby="isDonationLabel">
																					<label id="isDonationLabel" for="isDonation">Would you like
																						to make a donation $${donationValue}?</label>
																					<br/>
																					<input id="isReadTerms" type="checkbox">
																					<label for="isReadTerms">I have read&nbsp;</label>
																					<a href="#" onclick="$('#termsDiv').toggle();">Terms and
																						Conditions</a>
																					<label for="isReadTerms">&nbsp;and accept them.</label>
																					<div id="termsDiv"
																						 style="display: none;border: 2px solid;max-height: 500px;overflow-y: scroll">
																						<%@ include file="terms.jsp" %>
																					</div>
																					<div style="clear: both"></div>
																				</form>
																				<button id="btnCancel" class="close">Cancel</button>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
				                                		<!--end modal  -->
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
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
           
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
 
            <div class="quick-nav-overlay"></div>
            
            <!-- BEGIN CORE PLUGINS -->
            <script src="resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
            <!-- END CORE PLUGINS -->
            <!-- BEGIN PAGE LEVEL PLUGINS -->
            <script src="resources/assets/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/vendor/tmpl.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/vendor/load-image.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/vendor/canvas-to-blob.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-image.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-audio.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-video.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-validate.js" type="text/javascript"></script>
            <script src="resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-ui.js" type="text/javascript"></script>
            <!-- END PAGE LEVEL PLUGINS -->
            <!-- BEGIN THEME GLOBAL SCRIPTS -->
            <script src="resources/assets/global/scripts/app.min.js" type="text/javascript"></script>
            <!-- END THEME GLOBAL SCRIPTS -->
            <!-- BEGIN PAGE LEVEL SCRIPTS -->
            <script src="resources/assets/apps/scripts/inbox.min.js" type="text/javascript"></script>
            <!-- END PAGE LEVEL SCRIPTS -->
            <!-- BEGIN THEME LAYOUT SCRIPTS -->
            <script src="resources/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
            <script src="resources/assets/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
            <script src="resources/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
            <script src="resources/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
            <!-- END THEME LAYOUT SCRIPTS -->
             <!-- Realtime-->
		    <script src="resources/js/realtime/sockjs.js"></script>
		    <script src="resources/js/realtime/stomp.js"></script>
		    <script src="resources/js/realtime/realtime.js"></script>
		    <script src="resources/js/realtime/chatMessages.js"></script>
		    <script>
		        function onLoad() {
		            requestSetCountOfUnreadMessages();
		            var funcs=[
		                subscribeUnreadMessages,
		                subscribeChatMessage.bind(this, ${conversationid})
		            ];
		            connect(funcs);
		        }
		    </script>
		
			<script>
				function validateFileSubmit(){
			        document.getElementById("submitFileForm").disabled = true;
				}
			</script>
		
		    <script>
				function validateForm() {
                    var text;
				    var x = document.getElementById("messageSbmt").value;
				    if (x == null || x == "") {
				    	text = "Input not valid";
				    	document.getElementById("demo").innerHTML = text;
				        return false;
				    }
                    onMessageSubmit();
				}
			</script>
			
            <script src="resources/js/jquery.js"></script>
		    <script src="resources/js/modal.js"></script>
			<script src="resources/js/jquery.countdown.js"></script>
			<script src="resources/js/jquery.countdown.min.js"></script>
		 	<script src="resources/js/countdown1.js"></script>

<!--             <script type="text/javascript">
					 	 var es = new EventSource("/translatorss/checkMessageTranslatorInsert/${conversationid}/" + guid());
					     var onMessageListener = function (event) {
						         var data = event.data;
						         var dataChunks = data.split('|');
						         var ms_id = dataChunks[0].split(':')[1];
						         var ms_sd = dataChunks[1].split(':')[1];
						         var ms_ms = dataChunks[2].split(':')[1];
						         var ms_dt_hs = dataChunks[3].split(':')[1];
						         var ms_dt_ms = dataChunks[3].split(':')[2];
						         var ms_dt_ss = dataChunks[3].split(':')[3];
						         var ms_dt_mls = dataChunks[3].split(':')[4];
				
						         $('#datatables > tbody:last-child').append(
						                 '<tr class="active odd" role="row">'+
							                 '<td class="text-center sorting_1">'+ms_sd+'</td>'+
							                 '<td class="text-center sorting_1">'+ms_ms+'</td>'+
							                 '<td class="text-center sorting_1">'+ms_dt_hs+':'+ms_dt_ms+':'+ms_dt_ss+'</td>'+
						                 '</tr>');
					     }
					     es.addEventListener("message", onMessageListener);
				
					     function guid() {
					         function s4() {
					             return Math.floor((1 + Math.random()) * 0x10000)
					                 .toString(16)
					                 .substring(1);
					         }
				
					         return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
					             s4() + '-' + s4() + s4() + s4();
					     }
			</script> -->
			
			<script type="text/javascript">
					 resetPayModal();
				
				    $('#isReadTerms').click(function () {
				        if ($(this).is(':checked')) {
				            $('.paypal-button-widget').show();
				        } else {
				            $('.paypal-button-widget').hide();
				        }
				    });
				
				    function resetPayModal() {
				        $('#isDonation').prop('checked', false);
				        $('#isReadTerms').prop('checked', false);
				        $('#quotationId').val(0);
				        $('#termsDiv').hide();
				        $('.paypal-button-widget').hide();
				    }
				
				    function showPayModal(quotationId) {
				        resetPayModal();
				        $('#quotationId').val(quotationId);
				        $('#payModal').show();
				    }
				
				    window.paypalCheckoutReady = function () {
				        paypal.checkout.setup('DE3ZKSXBXWBVN', {
				            environment: 'sandbox',
				            container: 'checkoutContainer'
				        });
				    };
			</script>
			<script src="//www.paypalobjects.com/api/checkout.js" async></script>
    </body>

</html>
