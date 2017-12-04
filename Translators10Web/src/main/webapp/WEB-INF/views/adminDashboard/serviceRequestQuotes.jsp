<!DOCTYPE html>
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

    <!-- Bootstrap Core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="resources/images/favicon.png">

    <!-- Custom CSS -->
    <link href="resources/css/sb-admin.css" rel="stylesheet">

    <!-- Data table plugin -->
    <!-- <link rel="stylesheet" type="text/css" href="js/plugins/DataTables/datatables.css"> -->
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">

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
            <a href="indexTranslator.jsp" class="img-responsive"><img src="resources/images/logo.png"/></a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
                        class="caret"></b></a>
                <ul class="dropdown-menu message-dropdown">
                    <li class="message-footer">
                        <a href="<c:url value='conversations'/>">Read All New Messages</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b
                        class="caret"></b></a>
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
                            <i class="fa fa-fw fa-table"></i> My Quotation
                        </li>
                    </ol>
                </div>
            </div>

                <div id="messageDiv" class="row" style="display: <c:choose><c:when test='${message !=null}'>block</c:when><c:otherwise>none</c:otherwise></c:choose>;">
                    <div class="col-lg-12">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="fa fa-info-circle"></i><strong id="messageLine">${message}</strong>
                        </div>
                    </div>
                </div>
            <div class="row">

                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">TRANSLATOR SELECTION</h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped" id="datatables">
                                    <thead>
                                    <tr>
                                        <th class="text-center">Name</th>
                                        <th class="text-center">Price(AU$)</th>
                                        <th class="text-center">Would Recommend Rating</th>
                                        <th class="text-center">Communication Rating</th>
                                        <th class="text-center">Service Described Rating</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${translatorQuoteList}" var="translatorQuoteRequest">
                                        <tr class="active">
                                            <td class="text-center">${translatorQuoteRequest.name}</td>
                                            <td class="text-center" data-quote-id="${translatorQuoteRequest.quotationId}">${translatorQuoteRequest.quote}</td>
                                            <td class="text-center">
                                                <c:if test="${translatorQuoteRequest.wouldRecomend =='5'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.wouldRecomend =='4'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.wouldRecomend =='3'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.wouldRecomend =='2'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.wouldRecomend =='1'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.wouldRecomend =='0'}">
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                            </td>

                                            <td class="text-center">
                                                <c:if test="${translatorQuoteRequest.communication =='5'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.communication =='4'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.communication =='3'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.communication =='2'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.communication =='1'}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </c:if>
                                                <c:if test="${translatorQuoteRequest.communication =='0'}">
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
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/modal.js"></script>

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
    <script src="resources/js/eventsource.js"></script>

    <!-- plug in datatable -->
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>

</div>


</body>

</html>