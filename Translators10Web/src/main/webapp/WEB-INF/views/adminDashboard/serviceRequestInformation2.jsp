<%@ page import="au.com.translatorss.bean.Conversation" %>
<%@ page import="au.com.translatorss.bean.ServiceRequest" %>
<%@ page import="au.com.translatorss.utils.ConversationUtils" %>
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

</head>

<body onload="onLoad()">

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
      <div class="row">
       <div class="col-md-10">
            <div class="inbox-body">
                 <div class="inbox-header">
                             
                 </div>
                 <div class="inbox-content">
                 	<div  style="height:300px; overflow-y : scroll;">
                      <table class="table table-striped table-advance table-hover" id="datatables">
	                          <thead>
	                  		  </thead>
		                      <tbody>
									<c:forEach items="${messageList}" var="message" >
						               <tr>
							           <c:choose>
							               <c:when test="${message.read}">
							                      <tr class="active" id="${message.id}">
							               </c:when>
							               <c:otherwise>
							                      <tr style="background-color: #cacaca" id="${message.id}">
							               </c:otherwise>
							           </c:choose>
							           <td class="text-center"><img alt="" class="img-circle" src="${message.photoUrl}" height="62" width="52"/></td>
							           <td class="text-center">${message.message}</td>
							           <td class="text-center">${message.date}</td>
						               </tr>
						             </c:forEach>
		                        </tbody>
                        </table>
                      </div>
                            <!--  <form:form name="myForm" commandName="message">
											<div class="conv-write">
												<div class="write-wrap">
													<div class="write-row cf">
														<spring:bind path="message">
															<form:textarea id="messageSbmt" path="message" rows="3"
																		   cols="75" maxlength="600" class="form-control "
																		   onfocus="onMessageFocus(${conversationid})"/>
														</spring:bind>
														<spring:bind path="conversationid">
															<form:input path="conversationid" type="hidden"
																		class="form-control " id="conversationid"/>
														</spring:bind>
														<spring:bind path="photoUrl">
															<form:input path="photoUrl" type="hidden" class ="form-control" id="photoUrl"/>
														</spring:bind>
														<spring:bind path="sender">
															<form:input path="sender" type="hidden" class="form-control "
																		id="sender"/>
														</spring:bind>
													</div>
													<div class="write-controls cf">
														<span class="char-count"><em>0</em> / 600</span>
														<div class="icn-submit"><input
																class="btn-standard btn-azure-grad btn-send-message"
																type="button" value="Send" onclick="onMessageSubmit()"/>
														</div>
													</div>
												</div>
											</div>
									</form:form>--> 
									
									 <c:if test="${serviceRequestStatus == 'OpenService'}">
                            <form:form name="myForm" action='submitMessageAdmin' method="post" onsubmit="return validateForm()" commandName="message">
                           		<div class="conv-write">
                           			 <div class="write-wrap">
                           			 	<div class="write-row cf">
                           			 		<spring:bind path="message">
                    							<form:textarea path="message" rows="3" cols="75" maxlength="600" class="form-control" id="message"/>
               							    </spring:bind>   
               							    <spring:bind path="conversationid">
               							        <form:input path="conversationid" type="hidden" class="form-control " id="conversationid"/>
               							    </spring:bind>
               							    <spring:bind path="sender">
               							        <form:input path="sender" type="hidden" class="form-control " id="sender"/>
               							    </spring:bind>
               							</div>  
               							 <div class="write-controls cf">
               							 		<span class="char-count"><em>0</em> / 600</span>
												<div class="icn-submit"><input class="btn-standard btn-azure-grad btn-send-message"  type="submit" value="Send" /></div>                		
               							 </div>             							
                           			 </div>
                          		</div> 
                          </form:form> 
                          </c:if>  	
                    </div>
                </div> 	
      		</div>
	        <div class="col-lg-6">
			 	<div class="panel panel-primary">
					 <div class="panel-heading">
					         <h3 class="panel-title">Service Request Details</h3>
					 </div>
					 <div class="panel-body">
				          <div class="table-responsive">
				              <table class="table table-bordered table-hover table-striped" id="datatables">
				                 <thead>
				                    <tr>
				                        <th class="text-center">ID</th>
				                        <th class="text-center">Finish Time</th>
				                        <th class="text-center">Category</th>
				                        <th class="text-center">Timeframe</th>
				                        <th class="text-center">From</th>
				                        <th class="text-center">To</th>
				                        <th class="text-center">Hard copy?</th>
				                        <th class="text-center">Status</th>
				                        <th class="text-center">Translator Assigned</th>
				                     </tr>
				                   </thead>
				                   <tbody>
					                      <c:forEach items="${serviceRequestList}" var="serviceRequest"> 
					                         <tr class="active">
					                             <td>${serviceRequest.id}</td>
					                             <td>${serviceRequest.finishDate}</td>
					                             <td>${serviceRequest.serviceRequestCategory.description}</td>
					                             <td>${serviceRequest.timeFrame.description}</td>
					                             <td>${serviceRequest.languagefrom}</td>
					                             <td>${serviceRequest.languageTo}</td>
					                             <td>${serviceRequest.hardcopy}</td>
					                             <td>${serviceRequest.serviceRequestStatus.description}</td>
					                             <td>${serviceRequest.translator.user.name}</td>
											 </tr>
					                       </c:forEach> 
					               </tbody>
				              </table>
				          </div>
					 </div>
				</div>
	        </div>
	        <div class="col-lg-6">
			 	<div class="panel panel-primary">
					 <div class="panel-heading">
					         <h3 class="panel-title">Service Request Files Details</h3>
					 </div>
					 <div class="panel-body">
				          <div class="table-responsive">
				              <table class="table table-bordered table-hover table-striped" id="datatables">
				                 <thead>
				                    <tr>
				                        <th class="text-center">ID</th>
				                        <th class="text-center">File name</th>
			                            <th class="text-center">Sender</th>
			                            <th class="text-center">Date</th>
				                     </tr>
				                   </thead>
				                   <tbody>
					                      <c:forEach items="${fileList}" var="file">
														<tr class="active">
															<td>${file.id}</td>
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
        	<c:if test="${serviceRequestStatus == 'OpenService'}">
	        <div class="col-lg-6">
	          <div class="panel panel-primary">
	            <div class="panel-heading">
	              <h3 class="panel-title">Service Request Details</h3>
	            </div>
	            <div class="panel-body">
				 	<spring:url value="/adminApproveSR" var="adminApproveSR"/>
					<button class="btn btn-primary" onclick="location.href='${adminApproveSR}'">Approve Job</button>
					<spring:url value="/adminCancelSR" var="adminCancelSR" />
					<button class="btn btn-primary" onclick="location.href='${adminCancelSR}'">Cancel Job</button>
	            </div>
	          </div>
	        </div>
        </c:if>
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

<!-- Bootstrap Core JavaScript -->
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/realtime/sockjs.js"></script>
<script src="resources/js/realtime/stomp.js"></script>
<script src="resources/js/realtime/realtime.js"></script>
<script src="resources/js/realtime/chatMessages.js"></script>
</body>

</html>