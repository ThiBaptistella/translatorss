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
               
        <link href="resources/css/star-rate.css" rel="stylesheet" type="text/css" />
       
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> 
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        
        
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
                            <li class="dropdown dropdown-extended dropdown-inbox">
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
                                    <span class="username username-hide-on-mobile">${translatorName}</span>
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
<!--                                         <a href="page_user_login_1.html"><i class="icon-key"></i> Log Out </a>
 -->                                        <a href="<c:url value='${pageContext.request.contextPath}/j_spring_security_logout'/>"><i class="icon-key"></i> Log Out</a>
                                    
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
                        <li class="nav-item start active open">
                            <a href="<c:url value='/dashboard'/>" class="nav-link nav-toggle">
                                <span class="title">Dashboard</span>
                                <span class="arrow open"></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value='/myPendingActions'/>" class="nav-link nav-toggle">
                                <span class="title">Assignments to be quoted</span>
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
                        <li class="nav-item ">
                            <a href="<c:url value='/settings'/>" class="nav-link nav-toggle">
                                <span class="title">Settings</span>
                                <span class="arrow"></span>
                            </a>
                        </li>
                        
                       <%--  <li class="nav-item">
                            <a href="<c:url value='/suscription' />" class="nav-link nav-toggle">
                                <span class="title">Suscription</span>
                                <span class="selected"></span>
                                <span class="arrow"></span>
                            </a>
                        </li>     --%>
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
                                <span>Tables</span>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE HEADER-->
                    <div class="row">
                   
                        <div class="col-md-12">
							<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <a class="dashboard-stat dashboard-stat-v2 blue" href="<c:url value='/myJobInProgress'/>">
                                <div class="visual">
                                    <i class="fa fa-comments"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="1349">${assignmentsInProgress}</span>
                                    </div>
                                    <div class="desc">My Assignments in progress</div>
                                </div>
                            </a>
                        </div>

                         <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
                            <a class="dashboard-stat dashboard-stat-v2 green"">
                                <div class="visual">
                                    <i class="fa fa-bar-chart-o"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="12,5">${totalRevenue}</span> </div>
                                    <div class="desc">Total Earned</div>
                                </div>
                            </a>
                        </div>
                        
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
                            <a class="dashboard-stat dashboard-stat-v2 red" href="<c:url value='/myPendingActions'/>">
                                <div class="visual">
                                    <i class="fa fa-bar-chart-o"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="12,5">${specialAssignmentsUnquoted}</span> </div>
                                    <div class="desc">Special Assignments quoted </div>
                                </div>
                            </a>
                        </div>
                   
                   		
                    </div>
                  </div>
                  <div class="row">
                         <%-- <div class="col-lg-6 col-xs-12 col-sm-12">
							<img alt="Google Bar Chart" src="${barURL}">
                         </div> --%>
                         <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
                            <a class="dashboard-stat dashboard-stat-v2 green" href="<c:url value='/myPendingActions'/>">
                                <div class="visual">
                                    <i class="fa fa-bar-chart-o"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="12,5">${myAutomaticQuotedAssignments}</span> </div>
                                    <div class="desc">My Automatic Quoted Assignments</div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <a class="dashboard-stat dashboard-stat-v2 blue" href="<c:url value='/myHistory'/>">
                                <div class="visual">
                                    <i class="fa fa-bar-chart-o"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="12,5">${myTotalApprovedAssignments}</span> </div>
                                    <div class="desc">My Total Approved Assignments</div>
                                </div>
                            </a>
                        </div>
                  </div>
                 
                  
                   <div class="note note-success">
                        My Ratings:
	                        <p>
	                        	<div class="stars"> 

									<c:if test="${OnTimeDelivery =='5'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                            </c:if>
		                            <c:if test="${OnTimeDelivery =='4'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${OnTimeDelivery =='3'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${OnTimeDelivery =='2'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${OnTimeDelivery =='1'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${OnTimeDelivery =='0'}">
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                             </c:if>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <c:if test="${CustomerService =='5'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                            </c:if>
		                            <c:if test="${CustomerService =='4'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${CustomerService =='3'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${CustomerService =='2'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${CustomerService =='1'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${CustomerService =='0'}">
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                             </c:if>
		                             <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <span>&nbsp;</span>
		                            <c:if test="${Quality =='5'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                            </c:if>
		                            <c:if test="${Quality =='4'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${Quality =='3'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${Quality =='2'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${Quality =='1'}">
		                                                    <i class="star on"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                            </c:if>
		                            <c:if test="${Quality =='0'}">
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                                                    <i class="star"></i>
		                             </c:if>
								</div> 
	                          
			                </p>
			                <b>On time delivery</b>
		                		<span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
								<span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
			                <b>Customer Service</b>	
			                <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <span>&nbsp;</span>
	                            <b>Quality of translated documents</b>          
                  </div>
                  
                  <div class="note note-success">
                        My Status:<b> ${translatorStatus}</b>
                  </div>
                  
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
         
           
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

			<!-- Realtime -->
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
    </body>

</html>
