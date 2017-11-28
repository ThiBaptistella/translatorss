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
        <link rel="shortcut icon" href="favicon.ico" /> 
    	<link href="resources/css/message.css" rel="stylesheet">
    	<script src="resources/js/realtime/sockjs.js"></script>
	    <script src="resources/js/realtime/stomp.js"></script>
		<script src="resources/js/realtime/realtime.js"></script>
		<script src="resources/js/realtime/chatMessages.js"></script>
    	<script>
	        function onLoad() {
	            requestSetCountOfUnreadMessages();
	            var funcs=[
	                subscribeUnreadMessages,
	            ];
	            connect(funcs);
	        }
	    </script>
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
		                                                        <img src="${message.photoUrl}" class="img-circle" alt="">
															</span>
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
                                    <span class="username username-hide-on-mobile">${businessUserForm.fullname}</span>
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
						<li class="nav-item  ">
							<a href="<c:url value='uploadFile'/>" class="nav-link nav-toggle">
								<span class="title">Request a new Translation</span>
								<span class="arrow"></span>
							</a>
						</li>
                        <li class="nav-item start active open">
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
                        <li class="nav-item ">
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
                                <span>Select your translator</span>
                                <i class="fa fa-angle-right"></i>
                            </li>
                             <li>
                                <span>Quote List</span>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12">
                           
                            <!-- BEGIN SAMPLE TABLE PORTLET-->
                            <c:if test="${translatorQuoteList.size() ==0}">
                                <div class="note note-success">
                                    <p> <b>Soon you will receive quotes from translatorss.</b> </p>
                                </div>
                            </c:if>
							<c:if test="${paymentNotAvailable==true}">
                                <div class="note note-success">
                                    <p> <b>At this moment the payment system is not available, please try later!.</b> </p>
                                </div>
                            </c:if>


                            <div class="portlet box green">
                                <div class="portlet-title">
                                    <div class="caption"><i class="fa fa-cogs"></i>Quote List </div>
                                </div>
                                <div class="portlet-body flip-scroll">
                                    <table class="table table-bordered table-striped table-condensed flip-content">
                                        <thead class="flip-content">
                                            
                                                      <tr>
                                                      		<th class="text-center">Picture</th>
					                                        <th class="text-center">Translator Name</th>
					                                        <th class="text-center">Quote Price(AU$)</th>
					                                        <th class="text-center">Time Delivery Rating</th>
														    <th class="text-center">Quality Rating</th>
					                                        <th class="text-center">Customer Service</th>
														    <th class="text-center">Translator feedback history</th>
					                                        <th class="text-center">Action</th>
					                                        <th class="text-center">Select translator</th>
					                                    </tr>
                                           
                                        </thead>
                                        <tbody>
                                          <c:forEach items="${translatorQuoteList}" var="translatorQuoteRequest">
		                                        <tr class="active">
		                                        	<td class="text-center"> <img alt="" class="img-circle" src="${translatorQuoteRequest.photo}" height="52" width="52"/></td>
		                                            <td class="text-center">${translatorQuoteRequest.name}</td>
		                                            <td class="text-center" data-quote-id="${translatorQuoteRequest.quotationId}">${translatorQuoteRequest.quote}</td>
		                                            <td class="text-center">
		                                                <c:if test="${translatorQuoteRequest.timeDelivery =='5'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.timeDelivery =='4'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.timeDelivery =='3'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.timeDelivery =='2'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.timeDelivery =='1'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.timeDelivery =='0'}">
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                            </td>
		
		                                            <td class="text-center">
		                                                <c:if test="${translatorQuoteRequest.quality =='5'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.quality =='4'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.quality =='3'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.quality =='2'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.quality =='1'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.quality =='0'}">
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                            </td>
		                                            <td class="text-center">
		                                                <c:if test="${translatorQuoteRequest.serviceDescribed =='5'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.serviceDescribed =='4'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.serviceDescribed =='3'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.serviceDescribed =='2'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.serviceDescribed =='1'}">
		                                                    <i class="fa fa-star"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                                <c:if test="${translatorQuoteRequest.serviceDescribed =='0'}">
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                    <i class="fa fa-star-o"></i>
		                                                </c:if>
		                                            </td>
													<td class="text-center">
														<a href="<c:url value='/rateInformation/${translatorQuoteRequest.translatorId}' />" >Work History</a>
													</td>

													<td>
														<a href="<c:url value='/startConversation/${translatorQuoteRequest.translatorId}/${serviceRequestid}/${translatorQuoteRequest.quotationId}'/>" >Chat with translator</a>
													</td>
		                                            <td><a href="#"
		                                                   onclick="showPayModal(${translatorQuoteRequest.quotationId});"><img
		                                                    src="https://paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></a>
		                                            </td>
		                                        </tr>
                                    		</c:forEach>
                                        </tbody>
                                    </table>
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
	                                                        <form id="checkoutContainer" method="post" action='chose' onsubmit="closeModal();"
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
	                                                    </div>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                                <!--end modal  -->                              
                                </div>
                            </div>
                            <!-- END SAMPLE TABLE PORTLET-->
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
				
				    function closeModal(){
				    	 $('#payModal').hide();
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
