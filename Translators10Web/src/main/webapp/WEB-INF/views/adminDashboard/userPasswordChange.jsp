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
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/favicon.png">
    <link href="resources/css/sb-admin.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
    <link href="resources/css/plugins/morris.css" rel="stylesheet">
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
                            <i class="fa fa-dashboard"></i> Dashboard
                        </li>
                        <li class="active">
                            <i class="fa fa-fw fa-edit"></i> Change Password
                        </li>
                    </ol>
                </div>
            </div>

            <c:if test="${userPasswordChangeMessage !='null'}">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="fa fa-info-circle"></i><strong>${userPasswordChangeMessage}</strong>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">User Password </h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <form:form action='updateBusinessUserPasswordAdmin' method="post" name="myForm3" onsubmit="return validatePassword2()"  commandName="passwordDTOForm">
                                <form:input path="type" type="hidden" class="form-control " id="type" value="businessuser"/>
                                <form:input path="email" type="hidden" class="form-control " id="email"/>

                                <div class="form-group">
                                    <label>New Password</label>
                                    <form:input path="newPassword" type="text" class="form-control " id="newPassword" placeholder="New Password" />
                                    <form:errors path="newPassword" class="control-label" />
                                    <p id="newPassworderror" style="color:red"></p>
                                </div>

                                <div class="form-group">
                                    <label>New Password Confirmation</label>
                                    <form:input path="confirmNewPassword" type="text" class="form-control " id="confirmNewPassword" placeholder="New Password Confirmation" />
                                    <form:errors path="confirmNewPassword" class="control-label" />
                                    <p id="confirmNewPassworderror" style="color:red"></p>
                                </div>

                                <div class="form-group input-group">
                                    <input class="btn btn-default" type="submit" value="Update">
                                </div>
                            </form:form>

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

<script>
    function resetpasswordErrorValues() {
        $('#passworderror').hide();
    }

    function validatePassword2() {
        resetpasswordErrorValues();
        var text;

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


