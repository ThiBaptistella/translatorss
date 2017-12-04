<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	  <link rel="shortcut icon" href="images/favicon.png">
    <!-- Custom CSS -->
    <link href="resources/css/sb-admin.css" rel="stylesheet">

    <!-- <link rel="stylesheet" type="text/css" href="js/plugins/DataTables/datatables.css"> -->
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">

    <!-- Morris Charts CSS -->
    <link href="resources/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="resources/css/message.css" rel="stylesheet">

</head>

<body onload="onLoad()">

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
               <!--   <a class="navbar-brand" href="indexTranslator.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Translatorss"></a>
                             <a class="navbar-brand" href="indexTranslator.jsp"><img src="../../images/translatorss/logo.png" alt="Translatorss"></a>
               -->
               <a href="indexTranslator.jsp" class="img-responsive"><img src="resources/images/logo.png"  /></a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope" id="countUnreadMessages"></i> <b class="caret"></b></a>
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${businessUserForm.user.name} <b class="caret"></b></a>
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
                        <a href="<c:url value='userdashboard'/>" ><i class="fa fa-fw fa-dashboard"></i>Dashboard</a>
                    </li>
                    <li>
                        <a href="<c:url value='pendingActions'/>" ><i class="fa fa-fw fa-edit"></i>Select your Translator</a>
                    </li>
                    <li>
                        <a href="<c:url value='jobInProgress'/>" ><i class="fa fa-fw fa-table"></i>Follow Up your Translation</a>
                    </li>
                    <li>
                        <a href="<c:url value='history'/>" ><i class="fa fa-fw fa-table"></i>Translation History</a>
                    </li>
                    <li>
                        <a href="<c:url value='uploadFile'/>" ><i class="fa fa-fw fa-download"></i>Request a new Translation</a>
                    </li>
                    <li>
                        <a href="<c:url value='userSettings'/>" ><i class="fa fa-fw fa-wrench"></i>Settings</a>
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
                                <i class="fa fa-fw fa-edit"></i> Job in Progress
                            </li>
                        </ol>
                    </div>
                </div>

                <div class="row">

  				<div class="panel panel-primary">
				  <div class="panel-heading">
				    <h3 class="panel-title">Job in progress</h3>
				  </div>
				  <div class="panel-body">
                    <div class="col-lg-12">
                       	               		<section class="page-content">
                			<section class="section  dark" id="getQuote" >
                			 	<div class="container">
                			 		<div class="row">
                			 			<div class="col-md-4 step">
                			 			
											<h4>Rate Translator Job</h4>
											<form:form action='submitRate' method="POST" commandName="rateTranslator">
												<table>
												<tr>
												<td>Translator Comunication : </td>
												<td>
													<form:radiobuttons path="translatorComunication" items="${numberList1}"  /> 
												</td>
												</tr>
												<tr>
												<td>Service Described : </td>
												<td>
													<form:radiobuttons path="serviceDescribed" items="${numberList2}" /> 
												</td>
												</tr>
												<tr>
													<td>Would Recomend : </td>
													<td>
														<form:radiobuttons path="wouldRecomend" items="${numberList3}" /> 
													</td>
												</tr>
												<tr>
													<td>Feedback : </td>
													<td>
														<form:textarea path="feedback" rows="5" cols="30" maxlength="50" class="form-control " id="feedback" placeholder="feedback"/>
													</td>
												</tr>
												<tr>
												<td>
				               						<form:input path="customerId" type="hidden" class="form-control " id="customerId"/>
													<form:input path="translatorId" type="hidden" class="form-control " id="translatorId"/>
												</td>
												</tr>
												<tr>
													<td colspan="3"><input type="submit" value=Evaluate /></td>
												</tr>
												</table>
											</form:form>
                			 			</div>
                			 		</div>
                			 	</div>
                			</section>
                		</section>
                    </div>
                   </div>
                 </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

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
    <!--[if lte IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
    <script src="resources/js/plugins/flot/jquery.flot.js"></script>
    <script src="resources/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="resources/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="resources/js/plugins/flot/jquery.flot.pie.js"></script>
    <script src="resources/js/plugins/flot/flot-data.js"></script>

    <!-- plug in datatable -->
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>



</body>

</html>