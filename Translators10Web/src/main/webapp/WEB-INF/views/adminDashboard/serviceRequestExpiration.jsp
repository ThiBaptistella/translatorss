<%@ page session="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">

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

    <style>
        td.bg_red {
            background-color: red !important;
        }

        td.bg_green {
            background-color: green !important;
        }
    </style>
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
                            <i class="fa fa-info-circle"></i><strong>${message}</strong>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Service Request Expiration</h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
        					<div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped" id="datatables">
                                    <thead>
                                    <tr>
                                         <th width="80">Translator ID</th>
                                         <th width="80">Translator Prefered Name</th>
                                         <th width="80">Translator Status</th>
                                         <th width="80">Customer ID</th>
                                         <th width="80">Request ID</th>
                                         <th width="80">Request Date</th>
                                         <th width="80">Time left to close the quote</th>
                                         <th width="80">Request Status</th>
                                         <th width="80">Time left to finish the assignment</th>
                                         <th width="80">Quote Price</th>
									     <th width="80">Customer Name</th>
									     <th width="80">Chat</th>
									     <th width="80">Category</th>
									     <th width="80">Description</th>
									     <th width="80">TimeFrame</th>
									     <th width="80">HardCopy</th>
									     <th width="80">Original Language</th>
									     <th width="80">Expire Request</th>
                                    </tr>
                                    </thead>
                                    <tbody>
		                                    <c:forEach items="${dtoList}" var="quote">
										        <tr>
										            <td>${quote.translatorid}</td>
										            <td>${quote.translatorName}</td>
										            <td>${quote.translatorStatus}</td>
										            <td>${quote.customerID}</td>
										            <td>${quote.serviceRequestID}</td>
										            <td>${quote.date}</td>
                                                    <td class="countdown bg_green" data-id="${quote.serviceRequestID}"></td>
                                                    <td class="finishdate" hidden="true">${quote.timeLeftCloseQuote}</td>
										            <td>${quote.serviceRequestStatus}</td>
										            <td>${quote.timeLefToFinishAssignment}</td>
										            <td>${quote.quote}</td>
										            <td>${quote.customerName}</td>
										            <td>
														<c:if test="${serviceRequest.translatorId != null }">
												            <a href="<c:url value='/seeServiceRequestDetials2/${quote.serviceRequestID}/${quote.translatorid}'/>" >See Details</a>
												        </c:if>										            
										            </td>
										            <td>${quote.category}</td>
										            <td>${quote.description}</td>
										            <td>${quote.timeFrame}</td>
										            <td>${quote.hardcopy}</td>
										            <td>${quote.origenLanguage}</td>
										            <td> 
											            <a href="<c:url value='/expireServiceRequest2/${quote.serviceRequestID}' />" >Expire</a>
										            </td>
										        </tr>
										    </c:forEach>
                                    </tbody>
                                </table>
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

</body>

</html>

