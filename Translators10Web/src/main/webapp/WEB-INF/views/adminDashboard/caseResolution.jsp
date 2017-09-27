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

  <!-- Bootstrap Core CSS -->
  <link href="resources/css/bootstrap.min.css" rel="stylesheet">
  <link rel="shortcut icon" href="resources/images/favicon.png">
  <!-- Custom CSS -->
  <link href="resources/css/sb-admin.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

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
      <a href="indexTranslator.jsp" class="img-responsive"><img src="resources/images/logo.png"  /></a>
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
            <a href="#">You have new tassks! <span class="label label-success">Alert Badge</span></a>
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
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${loggedInAdmin.user.name} <b class="caret"></b></a>
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
              <i class="fa fa-dashboard"></i>  <a href="indexUser.jsp">Dashboard</a>
            </li>
            <li class="active">
              <i class="fa fa-fw fa-gear"></i> Settings
            </li>
          </ol>
        </div>
      </div>
      <!-- /.row -->
      <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Translator Assignments (Case Resolution)</h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
        					<div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped" id="datatables">
                                    <thead>
                                    <tr>
                                         <th width="60">Translator Id</th>
									     <th width="60">Translator Prefered Name</th>
									     <th width="60">Translator Status</th>
									     <th width="60">Customer Id</th>
									     <th width="60">Service Request Id</th>
									     <th width="60">Service Request Status</th>
									     <th width="60">Request Date</th>
									     <th width="60">Time left to finish the assignment</th>
									     <th width="60">Quote price</th>
									     <th width="60">Client full name</th>
									     <th width="60">Document category</th>
									     <th width="60">Translation Description </th>
									     <th width="60">Digital Copy Urgency</th>
									     <th width="60">Hard copy (within Australia only) </th>
									     <th width="60">Original Language</th>
									     <th width="60">Language to be translated to</th>
									     <th width="60">View Assignment/Case Resolution</th>
                                         <th width="60">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
		                                    <c:forEach items="${dtoList}" var="serviceRequest">
										        <tr>
										            <td>${serviceRequest.translatorId}</td>
										            <td>${serviceRequest.translatorName}</td>
										            <td>${serviceRequest.translatorStatus}</td>
										            <td>${serviceRequest.customerId}</td>
										            <td>${serviceRequest.id}</td>
										            <td>${serviceRequest.status}</td>
										            <td>${serviceRequest.creationDate}</td>

                                                    <td class="countdown bg_green" data-id="${serviceRequest.id}"></td>
                                                    <td class="finishdate" hidden="true">${serviceRequest.finishDate}</td>

										            <td>${serviceRequest.quote}</td>
										            <td>${serviceRequest.clientName}</td>
										            <td>${serviceRequest.serviceRequestCategory}</td>
										            <td>${serviceRequest.description}</td>
										            <td>${serviceRequest.timeFrame}</td>
										            <td>${serviceRequest.hardcopy}</td>
										            <td>${serviceRequest.languagefrom}</td>
										            <td>English</td>
										            <td> 
										            <c:if test="${serviceRequest.translatorId != null }">
											            <a href="<c:url value='/seeServiceRequestDetials/${serviceRequest.id}'/>" >See Details</a>
											        </c:if>
										            </td>
                                                    <td>
                                                        <c:if test="${serviceRequest.translatorId == null }">
                                                            <a href="<c:url value='/adminCancelSR/${serviceRequest.id}'/>" >Cancel</a>
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
                

    </div>
  </div>
</div>
<script>
		function validateForm() {
			var text;
		    var x = document.forms["myForm"]["servicerequestid"].value;
		    if (isNaN(x)|| x == null || x == "") {
		    	text = "Input not valid";
		    	document.getElementById("demo").innerHTML = text;
		        return false;
		    }
		}
</script>
<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

  <!-- jQuery -->
  <script src="resources/js/jquery.js"></script>
  <script src="resources/js/modal.js"></script>
  <script src="resources/js/jquery.countdown.js"></script>
  <script src="resources/js/jquery.countdown.min.js"></script>
  <script src="resources/js/countdown1.js"></script>
  <script src="resources/js/datatable.js"></script>
</body>
</html>