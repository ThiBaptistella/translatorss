<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html lang="en">
<head>

<!-- Basic Page Needs
	================================================== -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ADMIN USER- FOR UPDATE TRANSLATOR DATA</title>

<!-- Mobile Specific Metas
	================================================== -->
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">

<!-- Google Web Fonts
	================================================== -->
<link
	href="http://fonts.googleapis.com/css?family=Anton|Muli:300,400,400italic,300italic|Oswald"
	rel='stylesheet' type='text/css'>

<!-- CSS
	================================================== -->
<!-- Base + Vendors CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/css/fonts/font-awesome/css/font-awesome.css">
<link rel="stylesheet"
	href="resources/vendor/owl-carousel/owl.carousel.css" media="screen">
<link rel="stylesheet"
	href="resources/vendor/owl-carousel/owl.theme.css" media="screen">
<link rel="stylesheet"
	href="resources/vendor/magnific-popup/magnific-popup.css"
	media="screen">
<link rel="stylesheet"
	href="resources/vendor/mediaelement/mediaelementplayer.css" />
<link rel="stylesheet"
	href="resources/vendor/rs-plugin/css/settings.css" media="screen">
<link rel="stylesheet"
	href="resources/vendor/circliful/css/jquery.circliful.css" />

<!-- Theme CSS-->
<link rel="stylesheet" href="resources/css/theme.css">
<link rel="stylesheet" href="resources/css/theme-elements.css">
<link rel="stylesheet" href="resources/css/animate.min.css">
<link rel="stylesheet" href="resources/css/animate.min.css">
<link rel="stylesheet" href="resources/css/popup.css">

<!-- Skin CSS -->
<link rel="stylesheet" href="resources/css/skins/blue.css">

<!-- Custom CSS-->
<link rel="stylesheet" href="resources/css/custom.css">

<!-- <link rel="stylesheet" type="text/css" href="js/plugins/DataTables/datatables.css"> -->
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">

<!-- Head Libs -->
<script src="resources/vendor/modernizr.js"></script>

<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet"
	href="resources/vendor/jquery-file-upload/css/jquery.fileupload.css">

<!--[if lt IE 9]>
	<link rel="stylesheet" href="vendor/rs-plugin/css/settings-ie8.css" media="screen">
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="vendor/respond.min.js"></script>
	<![endif]-->

<!--[if IE]>
	<link rel="stylesheet" href="css/ie.css">
	<![endif]-->

<!-- Favicons
	================================================== -->
<link rel="shortcut icon" href="resources/images/favicon.png">
<link rel="apple-touch-icon"
	href="resources/images/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="resources/images/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="resources/images/apple-touch-icon-114x114.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="resources/images/apple-touch-icon-144x144.png">

<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>

</head>

<body class="one-page" data-target=".header" data-spy="scroll"
	data-offset="200">

	<div class="site-wrapper">

		<!-- Header -->
		<header class="header header-fixed">
			<!-- Header Social -->
<!-- 			<div class="header_top">
				<div class="container">
					<div class="text-center">
						<ul class="header-links text-right">
							<li style="border: none;"><a
								href="https://www.facebook.com/Translatorss/?fref=ts"><i
									class="fa fa-facebook"></i></a><a
								href="https://www.linkedin.com/company/translatorss-pty-ltd?trk=company_logo"><i
									class="fa fa-linkedin"></i></a></li>
						</ul>
					</div>
				</div>
			</div> -->
			<!-- End -->
			<div class="header-main">
				<div class="container">
					<nav class="navbar navbar-default fhmm" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle">
								<i class="fa fa-bars"></i>
							</button>
							<!-- Logo -->
							<div class="logo">
								<!-- <a href="index.html"><img src="images/logo.png" alt="Stability"></a> -->
								<a href="index-one-page.html"><img
									src="resources/images/logo.png" /></a>
								<!--<p class="tagline">Responsive HTML template</p>-->
							</div>
							<!-- Logo / End -->
						</div>
						<!-- end navbar-header -->

					</nav>
					<!-- end navbar navbar-default fhmm -->
				</div>
			</div>
		</header>
		<!-- Header / End -->


		<div class="row titleAdmin">
			<div class="title-centered">
				<h1>Add-Update a Translator</h1>
			</div>
		</div>

		<div class="row">

			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div data-example-id="striped-table" class="bs-example">
					<div class="panel panel-default">
						<div class="table-responsive" style="padding: 10px">
									
			<div class="col-md-2">
				<h3>Translators List</h3>
			</div>
			
							<c:if test="${!empty listTranslators}">
								<table class="tg table table-striped display" id="datatables">
								<thead>
									<tr>
										<th width="80">Full Name</th>
										<th width="120">Password</th>
										<th width="120">Address</th>
										<th width="120">Phone</th>
										<th width="120">naatiNumber</th>
										<th width="120">paypalClientId</th>
										<th width="120">Status</th>
										<th width="60">Activate Translator</th>
										<th width="60">Pause Translator</th>
										<th width="60">Delete</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${listTranslators}" var="translator">
										<tr>
											<td>${translator.name}</td>
											<td>${translator.password}</td>
											<td>${translator.address}</td>
											<td>${translator.phone}</td>
											<td>${translator.naatiNumber}</td>
											<td>${translator.paypalClientId}</td>
											<td>${translator.status}</td>
											<td><a
												href="<c:url value='/activateTranslator/${translator.id}' />">Activate</a></td>
											<td><a
												href="<c:url value='/pauseTranslator/${translator.id}' />">Pause</a></td>
											<td><a href="<c:url value='/remove/${translator.id}' />">Delete</a></td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</c:if>
							<div class="text-right">
												<ul class="pagination-custom list-unstyled list-inline">
													<li><a class="btn btn-sm btn-default" href="#">«</a></li>
													<li><a class="btn btn-sm btn-primary" href="#">1</a></li>
													<li><a class="btn btn-sm btn-default" href="#">2</a></li>
													<li><a class="btn btn-sm btn-default" href="#">3</a></li>
													<li><a class="btn btn-sm btn-default" href="#">4</a></li>
													<li><a class="btn btn-sm btn-default" href="#">5</a></li>
													<li><a class="btn btn-sm btn-default" href="#">»</a></li>
												</ul>
											</div>
						</div>
					</div>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
		<!-- Footer -->
		<footer class="footer" id="footer">
			<div class="footer-copyright">
				<div class="container">
					<div class="text-center">
						<ul class="social-links social-links__dark">
							<li><a href="https://www.facebook.com/Translatorss/?fref=ts"><i
									class="fa fa-facebook"></i></a></li>

							<li><a
								href="https://www.linkedin.com/company/translatorss-pty-ltd?trk=company_logo"><i
									class="fa fa-linkedin"></i></a></li>
						</ul>
						Copyright &copy; 2015 <a href="index.html">Translatorss</a>
						&nbsp;| &nbsp;All Rights Reserved
					</div>
				</div>
			</div>
		</footer>
		<!-- Footer / End -->

	</div>
	<!-- Main / End -->


	<div id="preloader-overlay"></div>


	<!-- Javascript Files
	================================================== -->
	<script src="resources/vendor/jquery-1.11.0.min.js"></script>
	<script src="resources/vendor/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/vendor/bootstrap.min.js"></script>
	<script src="resources/vendor/headhesive.min.js"></script>
	<script src="resources/vendor/fhmm.js"></script>
	<script src="resources/vendor/jquery.hoverIntent.minified.js"></script>
	<script src="resources/vendor/jquery.flickrfeed.js"></script>
	<script src="resources/vendor/isotope/isotope.pkgd.min.js"></script>
	<script src="resources/vendor/isotope/jquery.imagesloaded.min.js"></script>
	<script src="resources/vendor/magnific-popup/jquery.magnific-popup.js"></script>
	<script src="resources/vendor/owl-carousel/owl.carousel.min.js"></script>
	<script src="resources/vendor/jquery.fitvids.js"></script>
	<script src="resources/vendor/jquery.appear.js"></script>
	<script src="resources/vendor/jquery.stellar.min.js"></script>
	<script src="resources/vendor/snap.svg-min.js"></script>
	<script
		src="resources/vendor/mediaelement/mediaelement-and-player.min.js"></script>
	<script src="resources/vendor/circliful/js/jquery.circliful.min.js"></script>
	<script src="resources/vendor/jquery.easing.1.3.js"></script>
	<script src="resources/vendor/jquery.scrollTo.min.js"></script>
	<script src="resources/vendor/jquery.queryloader2.js"></script>
	<script src="resources/js/custom.js"></script>
	<script src="resources/js/modal.js"></script>
	<script src="resources/js/datatable.js"></script>

	<!-- Upload -->

	<script
		src="resources/vendor/jquery-file-upload/js/jquery.ui.widget.js"></script>
	<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
	<script
		src="resources/vendor/jquery-file-upload/js/jquery.iframe-transport.js"></script>
	<!-- The basic File Upload plugin -->
	<script
		src="resources/vendor/jquery-file-upload/js/jquery.fileupload.js"></script>


	<!-- One Page Version Inits -->
	<script src="resources/js/one-page.js"></script>

	<!-- jQuery REVOLUTION Slider  -->
	<script
		src="resources/vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
	<script
		src="resources/vendor/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>

	<!-- plug in datatable -->
	<script type="text/javascript" charset="utf8"
		src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="resources/js/datatable.js"></script>


	<!-- Contact Form -->
	<script src="resources/vendor/jquery.validate.js"></script>
	<script src="resources/js/contact.js"></script>

	<!-- Google Map -->
	<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script src="resources/vendor/jquery.gmap3.min.js"></script>

	<script>
		jQuery(document).ready(function() {

			// Revolution Slider
			jQuery('.tp-banner').revolution({
				dottedOverlay : "twoxtwo-custom",
				delay : 6000,
				startwidth : 1140,
				startheight : 556,
				hideThumbs : 10,
				fullWidth : "off",
				forceFullWidth : "off",
				fullScreen : "on",
				fullScreenOffsetContainer : "",
				hideCaptionAtLimit : 480,
				//navigationType: "none",
				soloArrowLeftHOffset : 20,
				soloArrowRightHOffset : 20,
				navigationType : "bullet",
				navigationArrows : "solo", // nexttobullets, solo (old name verticalcentered), none
				navigationStyle : "round" // round, square, navbar, round-old, square-old, navbar-old
			});

			// Google Map Init
			jQuery('#map_canvas').gmap3({
				marker : {
					address : '40.717599,-74.005136'
				},
				map : {
					options : {
						zoom : 17,
						scrollwheel : false,
						streetViewControl : true
					}
				}
			});
		});

		$(function() {
			'use strict';
			// Change this to the location of your server-side upload handler:
			//	    var url = window.location.hostname === 'blueimp.github.io' ?
			//             '//jquery-file-upload.appspot.com/' : 'server/php/';

			var url = 'http://jquery-file-upload.appspot.com/';
			$('#fileupload').fileupload(
					{
						url : url,
						dataType : 'json',
						done : function(e, data) {
							$.each(data.result.files, function(index, file) {
								$('<p/>').text(file.name).appendTo('#files');
							});
						},
						progressall : function(e, data) {
							var progress = parseInt(data.loaded / data.total
									* 100, 10);
							$('#progress .progress-bar').css('width',
									progress + '%');
						}
					}).prop('disabled', !$.support.fileInput).parent()
					.addClass($.support.fileInput ? undefined : 'disabled');
		});
	</script>
	<!--  POPUP LOST PASSWORD -->
	<script type="text/javascript">
		var link;
		var element;
		function openPopUp(url) {
			link = url;
			element = document.getElementById("background");
			element.style.display = "block";
			element = document.getElementById("popup");
			element.style.display = "block";

		}

		function closePopUp() {
			element = document.getElementById("popup");
			element.style.display = "none";
			element = document.getElementById("background");
			element.style.display = "none";
		}
	</script>
</body>
</html>