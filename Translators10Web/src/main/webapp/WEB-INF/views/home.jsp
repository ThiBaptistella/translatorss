<!DOCTYPE html>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en-gb" dir="ltr" vocab="http://schema.org/">
<head>
<title></title>
<link rel="stylesheet" href="resources/css/uikit.min.css" />
<script src="resources/js/jquery.js"></script>
<script src="resources/js/uikit.min.js"></script>
<script src="resources/js/uikit-icons.min.js"></script>
<link href="//fonts.googleapis.com/css?family=Roboto%7CWork+Sans" rel="stylesheet" type="text/css" id="google-fonts-css">

<link href="http://fonts.googleapis.com/css?family=Anton|Muli:300,400,400italic,300italic|Oswald" rel='stylesheet' type='text/css'>

<!-- CSS
        ================================================== -->
<!-- Base + Vendors CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/fonts/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="resources/vendor/owl-carousel/owl.carousel.css" media="screen">
<link rel="stylesheet" href="resources/vendor/owl-carousel/owl.theme.css" media="screen">
<link rel="stylesheet" href="resources/vendor/magnific-popup/magnific-popup.css" media="screen">
<link rel="stylesheet" href="resources/vendor/mediaelement/mediaelementplayer.css" />
<link rel="stylesheet" href="resources/vendor/rs-plugin/css/settings.css" media="screen">
<link rel="stylesheet" href="resources/vendor/circliful/css/jquery.circliful.css" />

<!-- Theme CSS-->
<link rel="stylesheet" href="resources/css/theme.css">
<link rel="stylesheet" href="resources/css/theme-elements.css">
<link rel="stylesheet" href="resources/css/animate.min.css">
<link rel="stylesheet" href="resources/css/animate.min.css">
<link rel="stylesheet" href="resources/css/popup.css">
<link rel="stylesheet" href="resources/css/style.css">

<!-- Skin CSS -->
<link rel="stylesheet" href="resources/css/skins/blue.css">

<!-- Custom CSS-->
<link rel="stylesheet" href="resources/css/custom.css">

<!-- <link rel="stylesheet" type="text/css" href="js/plugins/DataTables/datatables.css"> -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">

<!-- Head Libs -->
<script src="resources/vendor/modernizr.js"></script>

<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="resources/vendor/jquery-file-upload/css/jquery.fileupload.css">

<!-- Base + Vendors CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/fonts/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="resources/vendor/owl-carousel/owl.carousel.css" media="screen">
<link rel="stylesheet" href="resources/vendor/owl-carousel/owl.theme.css" media="screen">
<link rel="stylesheet" href="resources/vendor/magnific-popup/magnific-popup.css" media="screen">
<link rel="stylesheet" href="resources/vendor/mediaelement/mediaelementplayer.css" />
<link rel="stylesheet" href="resources/vendor/rs-plugin/css/settings.css" media="screen">
<link rel="stylesheet" href="resources/vendor/circliful/css/jquery.circliful.css" />

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
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">

<!-- Head Libs -->
<script src="resources/vendor/modernizr.js"></script>

<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="resources/vendor/jquery-file-upload/css/jquery.fileupload.css">


<!-- new part from jomla -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/templates/yootheme/vendor/yootheme/theme/platforms/joomla/assets/images/favicon.png">
<link rel="apple-touch-icon-precomposed" href="/templates/yootheme/vendor/yootheme/theme/platforms/joomla/assets/images/apple-touch-icon.png">

<base href="<c:url value='/' />" />

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="author" content="Super User" />
<meta name="generator" content="Joomla! - Open Source Content Management" />
<title>Translatorss 2017</title>
<!--  
<link href="http://translatorss.com.au/modules/mod_fronttranslatorss/assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="http://translatorss.com.au/modules/mod_fronttranslatorss/assets/css/nice-select.css" rel="stylesheet" type="text/css" />
-->
<link href="/resources/css/theme.css" rel="stylesheet" type="text/css" id="theme-style-css" />
<link href="/resources/css/custom.css" rel="stylesheet" type="text/css" id="theme-custom-css" />
<link href="//fonts.googleapis.com/css?family=Roboto%7CWork+Sans" rel="stylesheet" type="text/css" id="google-fonts-css" />
<script src="resources/assets/global/plugins/jquery.min.js?5c75d2fac1157e9a0ef38837602b1014" type="text/javascript"></script>


<script src="resources/login_files/jquery-noconflict.js?5c75d2fac1157e9a0ef38837602b1014" type="text/javascript"></script>
<script src="resources/assets/global/plugins/jquery-migrate.min.js?5c75d2fac1157e9a0ef38837602b1014" type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js?5c75d2fac1157e9a0ef38837602b1014" type="text/javascript"></script>
<script src="http://translatorss.com.au/modules/mod_fronttranslatorss/assets/js/jquery.nice-select.min.js" type="text/javascript"></script>
<script src="http://translatorss.com.au/modules/mod_fronttranslatorss/assets/js/script.js" type="text/javascript"></script>
<script src="https://www.google-analytics.com/analytics.js" type="text/javascript" defer="defer"></script>
<script src="/resources/login_files/uikit.min.js?v=1.4.3" type="text/javascript"></script>
<script src="/resources/js/uikit-icons.min.js?v=1.4.3" type="text/javascript"></script>


<script src="/resources/login_files/theme.js?v=1.4.3" type="text/javascript"></script>


<!-- end new part from jomla -->
</head>
<body>

	<!-- nav bar login -->
	<div class="tm-header-mobile uk-hidden@m">
		<nav class="uk-navbar-container" uk-navbar>
			<div class="uk-navbar-left">
						
				<a class="uk-navbar-item uk-logo" href="<c:url value='/'/>">
					<img src="/resources/login_files/logo-translatorss.svg" alt
					class="uk-responsive-height">
				</a>
			</div>
			<div class="uk-navbar-right">
				<a class="uk-navbar-toggle" href="#tm-mobile" uk-toggle>
					<div uk-navbar-toggle-icon></div>
				</a>
			</div>
		</nav>
		<div id="tm-mobile" uk-offcanvas mode="reveal" overlay flip>
			<div class="uk-offcanvas-bar">
				<div class="uk-child-width-1-1" uk-grid>
					<div>
						<div class="uk-panel" id="module-0">
							<ul class="uk-nav uk-nav-default">
								<li><a href="/login">login</a></li>
								<li><a href="/join-us">join us</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="tm-header uk-visible@m" uk-header>
		<div class="uk-navbar-container" uk-sticky media="768"
			cls-active="uk-active uk-navbar-sticky">
			<div class="uk-container ">
				<nav class="uk-navbar"
					uk-navbar="{&quot;align&quot;:&quot;center&quot;,&quot;dropbar&quot;:true,&quot;dropbar-anchor&quot;:&quot;!.uk-container&quot;,&quot;dropbar-mode&quot;:&quot;slide&quot;}">
					<div class="uk-navbar-left">
						<a href="<c:url value='/' />" class="uk-logo uk-navbar-item"> 
						<img src="resources/images/translatorss/logo-translatorss.svg" alt class="uk-responsive-height"> 
						<img src="resources/images/translatorss/logo-translatorss.svg" alt class="uk-responsive-height uk-logo-inverse"></a>
					</div>
					<div class="uk-navbar-right">
						<ul class="uk-navbar-nav">
							<li><a href="<c:url value='/login' />">login</a></li>
							<li><a href="<c:url value='/login' />">join us</a></li>
						</ul>
						<div class="uk-navbar-item" id="module-0">
							<div>
								<ul class="uk-grid-small uk-flex-middle uk-flex-nowrap" uk-grid>
									<li><a href="https://www.facebook.com"class="uk-icon-link" uk-icon="icon: facebook"></a></li>
									<li><a href="https://www.instagram.com"class="uk-icon-link" uk-icon="icon: instagram"></a></li>
									<li><a href="https://www.youtube.com" class="uk-icon-link"uk-icon="icon: youtube"></a></li>
								</ul>
							</div>
						</div>
					</div>
				</nav>
			</div>
		</div>
	</div>
	<!-- end nav bar login -->

	<!-- form  -->
	<form:form name="myForm" class="form-horizontal" action='serviceRequestProcesorHome' onsubmit="return validateSRForm()" method="post" commandName="serviceRequestDTO" enctype="multipart/form-data">
		<div class="uk-section-default uk-section">
			<div class="uk-container">
				<div class="uk-grid-margin" uk-grid>
					<div class="uk-width-1-1@m">
						<div class="module-translator uk-panel">
							<h3 class="uk-h3 uk-heading-divider">Hire a Talented Naati Translator</h3>
							
						  
							<div class="uk-margin-remove front-translatorss uk-visible@m" uk-grid="">
								<div class="left-actions uk-padding-remove uk-width-1-3@m">
									<div class="step1">
										<div class="icons">
											<i class="ticon-touch"></i>
										</div>
										<div class="text">
											<span class="step-name">Step 1</span>
											<h3 class="step-title1">Tell Translatorss what you need</h3>
										</div>
									</div>
									<div class="step2">
										<div class="icons">
											<i class="ticon-users"></i>
										</div>
										<div class="text">
											<span class="step-name">Step 2</span>
											<h3 class="step-title2">Select your Translator</h3>
										</div>
									</div>
									<div class="step3">
										<div class="icons">
											<i class="ticon-wallet"></i>
										</div>
										<div class="text">
											<span class="step-name">Step 3</span>
											<h3 class="step-title3">Pay and get your document
												translated</h3>
										</div>
									</div>
								</div>
								<div class="right-content uk-width-2-3@m">
									<div class="uk-grid">
										<spring:bind path="serviceRequestCategory">
											<label class="texthead">Original Language</label>											
											<spring:bind path="languagefrom">
												<form:select id="languagefrom" class="uk-margin uk-select" path="languagefrom" items="${languageList}" itemValue="description" itemLabel="description" multiple="false" />
												<p id="languagefromerror" style="color: red"></p>
											</spring:bind>
										</spring:bind>
									</div>
									
										
									<div class="uk-grid">
										<select class="uk-select">
											<option>English</option>
										</select>
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 upload-field">
											<spring:bind path="files">
												<label class="texthead">Upload File(s)</label>
												<input type="file" id="uploadfile" name="files" onclick="onclickCleanField('fileerror')" multiple="multiple"/>
												<p id="fileerror" style="color:red"></p>
												<b><form:errors path="files" style="color:red;" /></b>
											</spring:bind>

										</div>
										<div class="uk-width-1-2 copy-field">
											<spring:bind path="hardcopy">
												<label class="texthead">Hard copy</label>
												<form:checkbox id="hardcopy" class="uk-radio"
													path="hardcopy" />
											</spring:bind>
										</div>
									</div>
									<div class="uk-grid">
										<spring:bind path="serviceRequestCategory">
											<label class="texthead">Category</label>
											<form:select id="servicerequestcategory" onclick="onclickCleanField('servicerequestcategoryerror')"
												class="uk-margin uk-select" path="serviceRequestCategory">
												<form:option value="" selected="">Please Select.....</form:option>
												<optgroup label="Personal Content">
													 <form:option value="Birth Certificate">Birth Certificate</form:option>
                                                     <form:option value="Passport">Passport</form:option>
                                                     <form:option value="Drivers License">Drivers License</form:option>
                                                     <form:option value="Marriage Certificate">Marriage Certificate</form:option>
                                                     <form:option value="Other">Other</form:option>
												</optgroup>
											</form:select>
											<p id="servicerequestcategoryerror" style="color: red"></p>
										</spring:bind>
									</div>
									<div class="uk-grid">
										<spring:bind path="description">
											<label class="texthead">Description</label>
											<form:textarea id="description" path="description" rows="5" onclick="onclickCleanField('descriptionerror')"
												maxlength="200" class="uk-textarea"
												placeholder="Description: Eg: Applying, To Immigration, To be used on Court, Requested by School" />
											<p id="descriptionerror" style="color: red"></p>
										</spring:bind>
									</div>
									<div class="uk-grid">
										<spring:bind path="timeFrame">
											<label class="texthead">TimeFrame</label>
											<form:select id="timeframe" class="uk-margin uk-select" onclick="onclickCleanField('timeframeerror')"
												path="timeFrame">
												<form:option value="" selected="">Please Select...</form:option>
												<form:option value="Urgent">Urgent (24 Hours)</form:option>
												<form:option value="Medium">Medium (3 Days)</form:option>
												<form:option value="Relaxed">Relaxed (7 Days)</form:option>
											</form:select>
											<p id="timeframeerror" style="color: red"></p>
										</spring:bind>
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2">
											<p>Discovery now and free the cost of your translation</p>
										</div>
										<div class="uk-width-1-2">
											<p class="uk-margin">
												<button class="uk-button uk-button-primary"
													name="serviceRequestCreate" value="serviceRequestCreate">START</button>

											</p>
										</div>
									</div>

								</div>
							</div>
							
							
		

							<!-- Modal -->
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">×</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">Modal title</h4>
										</div>
										<div class="modal-body">Please wait, redirecting you ...
										</div>
									</div>
								</div>
							</div>
							<!--end modal  -->
						</div>
					</div>
				</div>
				<div class="uk-grid-margin" uk-grid>
					<div class="uk-width-1-1"></div>
				</div>
			</div>
		</div>
	</form:form>
	<!--end form  -->

	<!--  <button class="uk-button uk-button-primary"  onclick="showQuoteModal()">Display Modal</button>-->

<!-- Get quote / End -->
<div class="modal" id="quoteModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-backdrop  in" style="height: 102px;">
	</div>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" onclick="hideModal()"
								aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Translator Selection</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div data-example-id="striped-table" class="bs-example">
									<div class="panel panel-default">
										<div class="table-responsive" style="padding: 10px">
											<table class="table table-striped display" id="records_table">
												<thead>
													<tr>
														<th>Name</th>
														<th>Price(AU$)</th>
														<th>Would Recommend Rating</th>
														<th>Communication Rating</th>
														<th>Service Described Rating</th>
														<th >Choose</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

			</div>
	</div>
</div>

	<!-- end get quote -->

	<!-- testimonial -->
	<div class="uk-section-primary uk-section"
		uk-scrollspy="{&quot;target&quot;:&quot;[uk-scrollspy-class]&quot;,&quot;cls&quot;:&quot;uk-animation-scale-up&quot;,&quot;delay&quot;:300}">
		<div class="uk-container">
			<div class="uk-grid-margin" uk-grid>
				<div class="uk-width-1-1@m uk-text-center">
					<p class="uk-text-center" uk-scrollspy-class>Lorem ipsum dolor
						sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
						dolor. Aenean massa. Cum sociis natoque.
					<footer> Camilla Stevens // Brazilian student. </footer>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- end testimonial -->

	<!-- services -->
	<div class="uk-section-muted uk-section uk-section-large"
		uk-scrollspy="{&quot;target&quot;:&quot;[uk-scrollspy-class]&quot;,&quot;cls&quot;:&quot;uk-animation-slide-left-medium&quot;,&quot;delay&quot;:false}">
		<div class="uk-container">
			<div class="uk-grid-large uk-flex-middle uk-grid-margin-large"
				uk-grid>
				<div class="uk-width-xlarge@m">
					<div
						class="uk-margin uk-card uk-card-default uk-card-large uk-card-body"
						uk-scrollspy-class>
						<div class="uk-margin uk-h6 uk-margin-remove-adjacent">Services</div>
						<h3 class="uk-margin uk-h1">Certified Native Translation
							Service</h3>
						<div class="uk-margin">
							<p class="uk-margin-large-bottom">Lorem ipsum dolor sit amet,
								consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
								Aenean massa. Cum sociis natoque.</p>
						</div>
					</div>
				</div>
				<div class="uk-width-expand@m">
					<div
						class="uk-margin-medium uk-text-left@s uk-text-center uk-grid-match uk-child-width-1-1 uk-child-width-1-2@s"
						uk-scrollspy-class="uk-animation-slide-right" uk-grid>
						<div>
							<div uk-scrollspy-class class="uk-panel">
								<img
									src="/resources/login_files/home-services-04.svg" alt>
								<h3 class="uk-margin uk-h4">Vision</h3>
								<div class="uk-margin">Lorem ipsum dolor sit amet,
									eleifend gravida non maecenas veritatis vestum eos, nisl
									volutpat.</div>
							</div>
						</div>
						<div>
							<div uk-scrollspy-class class="uk-panel">
								<img src="/resources/login_files/home-services-04.svg" alt>
								<h3 class="uk-margin uk-h4">Mission</h3>
								<div class="uk-margin">Lorem ipsum dolor sit amet,
									eleifend gravida non maecenas veritatis vestum eos, nisl
									volutpat.</div>
							</div>
						</div>
						<div>
							<div uk-scrollspy-class class="uk-panel">
								<img src="/resources/login_files/home-services-04.svg" alt>
								<h3 class="uk-margin uk-h4">Values</h3>
								<div class="uk-margin">Lorem ipsum dolor sit amet,
									eleifend gravida non maecenas veritatis vestum eos, nisl
									volutpat.</div>
							</div>
						</div>
						<div>
							<div uk-scrollspy-class class="uk-panel">
								<img src="/resources/login_files/home-services-04.svg" alt>
								<h3 class="uk-margin uk-h4">Team</h3>
								<div class="uk-margin">Lorem ipsum dolor sit amet,
									eleifend gravida non maecenas veritatis vestum eos, nisl
									volutpat.</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end services -->

	<!-- join us -->
	<div class="uk-section-image uk-position-relative uk-light"
		uk-scrollspy="{&quot;target&quot;:&quot;[uk-scrollspy-class]&quot;,&quot;cls&quot;:&quot;uk-animation-slide-right-medium&quot;,&quot;delay&quot;:false}">
		<div
			style="background-image: url('/resources/login_files/join-us-home.jpg'); background-color: #fff;"
			class="uk-background-norepeat uk-background-cover uk-background-image@m uk-section uk-section-xsmall uk-flex uk-flex-middle"
			uk-height-viewport="offset-top: true">
			<div class="uk-position-cover"
				style="background-color: rgba(0, 0, 0, 0);"></div>
			<div class="uk-width-1-1">
				<div class="uk-container uk-position-relative">
					<div class="uk-grid-large uk-grid-margin-large" uk-grid>
						<div class="uk-width-expand@m">
							<div class="uk-margin" uk-scrollspy-class>
								<video controls
									poster="/resources/login_files/element-video-placeholder.png"></video>
							</div>
						</div>
						<div class="uk-width-2-3@m">
							<h1 class="uk-margin uk-text-center " uk-scrollspy-class>
								join us</h1>
							<h1
								class="uk-margin-medium uk-margin-remove-top uk-width-xlarge uk-margin-auto uk-text-center"
								uk-scrollspy-class>Become a Translatorss</h1>
							<div
								class="uk-margin uk-width-xlarge uk-margin-auto uk-text-center"
								uk-scrollspy-class>Lorem ipsum dolor sit amet,
								consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
								Aenean massa. Cum sociis natoque penatibus et magnis dis
								parturient montes, nascetur ridiculus mus. Donec quam felis,
								ultricies nec, pellentesque eu, pretium quis, sem.</div>
							<div class="uk-margin uk-text-center" uk-scrollspy-class>
								<a class="uk-button uk-button-default"
									href="http://www.translatorss.com.au/join-us" title="JOIN US">
									JOIN US </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end join us -->

	<!-- footer -->
	<div class="uk-section-muted uk-section uk-section-large"
		uk-scrollspy="{&quot;target&quot;:&quot;[uk-scrollspy-class]&quot;,&quot;cls&quot;:&quot;uk-animation-fade&quot;,&quot;delay&quot;:false}">
		<div class="uk-container">
			<div class="uk-grid-large uk-grid-margin-large" uk-grid>
				<div class="uk-width-expand@m uk-width-1-2@s">
					<div class="uk-margin" uk-scrollspy-class>
						<a href="/index.php"><img src="/resources/login_files/logo-translatorss.svg"
							alt width="70%"></a>
					</div>
					<div class="uk-margin uk-width-xlarge" uk-scrollspy-class>
						test test test.</div>
					<div class="uk-margin" uk-scrollspy-class>
						<div class="uk-child-width-auto uk-flex-inline uk-grid-medium"
							uk-grid>
							<div>
								<a uk-icon="icon: instagram;ratio: 0.8"
									href="https://www.instagram.com/" class="uk-link-muted"></a>
							</div>
							<div>
								<a uk-icon="icon: twitter;ratio: 0.8"
									href="https://twitter.com/" class="uk-link-muted"></a>
							</div>
							<div>
								<a uk-icon="icon: facebook;ratio: 0.8"
									href="https://www.facebook.com/" class="uk-link-muted"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="uk-width-expand@m uk-width-1-2@s">
					<h3 class="uk-h5" uk-scrollspy-class>Syney</h3>
					<div class="uk-margin" uk-scrollspy-class>
						Bennelong Point, <br> Sydney NSW 2000, Austrália<br> +61
						2 9250 7111<br> 
					</div>
				</div>
				<div class="uk-width-expand@m uk-width-1-2@s">
					<h3 class="uk-h5" uk-scrollspy-class>Brisbane</h3>
					<div class="uk-margin" uk-scrollspy-class>
						Bennelong Point, <br> Sydney NSW 2000, Austrália<br> +61
						2 9250 7111<br> 
						
					</div>
				</div>
				<div class="uk-width-expand@m uk-width-1-2@s">
					<h3 class="uk-h5" uk-scrollspy-class>Melbourne</h3>
					<div class="uk-margin" uk-scrollspy-class>
						Bennelong Point, <br> Sydney NSW 2000, Austrália<br> +61
						2 9250 7111<br>
					
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- end footer -->



	<script>
    $(document)
        .ready(
            function() {
                //add more file components if Add is clicked
                $('#addFile')
                    .click(
                        function() {
                            var fileIndex = $(
                                '#fileTable tr')
                                .children().length;
                            $('#fileTable')
                                .append(
                                    '<tr><td>'
                                    + '   <input type="file" name="files['+ fileIndex +']" />'
                                    + '</td></tr>');
                        });
            });
</script>
	<script>
	  function onclickCleanField(idfield){
	        document.getElementById(idfield).style.display="none";
	  }
	
	 function validateSRForm() {
	        var text;
	      
	        if(document.getElementById("uploadfile").files.length < 1){
	        	 text = "You need to select at least one file!";
	             document.getElementById("fileerror").innerHTML = text;
	             $('#fileerror').show();
	             return false;
	        }

	        var servicerequestcategory = document.forms["myForm"]["servicerequestcategory"].value;
	        if (servicerequestcategory == null || servicerequestcategory == "") {
	            text = "The category needs to be selected!";
	            document.getElementById("servicerequestcategoryerror").innerHTML = text;
	            $('#servicerequestcategoryerror').show();
	            return false;
	        }

	        var description = document.forms["myForm"]["description"].value;
	        if (description == null || description == "") {
	            text = "A descriptioin is needed!";
	            document.getElementById("descriptionerror").innerHTML = text;
	            $('#descriptionerror').show();
	            return false;
	        }



	        var timeframe = document.forms["myForm"]["timeframe"].value;
	        if (timeframe == null || timeframe == "") {
	            text = "The timeframe needs to be selected!";
	            document.getElementById("timeframeerror").innerHTML = text;
	            $('#timeframeerror').show();
	            return false;
	        }
	        
	     
	        
	        
	        document.getElementById("mySubmitButton").disabled = true;

	    }
	 
	function createStarTDAsString(val) {
        var td = '<td class="text-center">';
        for (i=1;i<=5;i++) {
            td+='<i class="fa fa-star'+((val<i)?'-o':'')+'"></i>'
        }
        td+='</td>';
        return td;
    }
	
	function hideModal() {
        document.getElementById("quoteModal").style.display="none";
    }
	
    function showQuoteModal() {
//        var xhr = new XMLHttpRequest();
//        var body = {
//
//            "languagefrom": document.getElementById("languagefrom").value,
//			"files[0]":document.getElementById("file"),
//            "hardcopy": document.getElementById("hardcopy").value,
//            "servicerequestcategory": document.getElementById("servicerequestcategory").value,
//            "description": document.getElementById("description").value,
//            "timeFrame": document.getElementById("timeframe").value
//        };
//        xhr.open("POST", "/sbmtSRHome");
//        xhr.setRequestHeader("Content-Type", "application/json");
//        xhr.send(JSON.stringify(body));
//        document.getElementById("messageSbmt").value = "";

        $('#quoteModal').show();
    }

</script>

<script type="text/javascript">
        $(document).ready(function() {
            $('#submitForm').submit(function(e) {
                var frm = $('#submitForm');
                e.preventDefault();
                var data = {};
                var Form = this;

                //Gather Data also remove undefined keys(buttons)
                $.each(this, function(i, v){
                    var input = $(v);
                        data[input.attr("name")] = input.val();
                        delete data["undefined"];
                });
                $.ajax({
                    type: "POST",
                    url: "/sbmtSRHome",
                    dataType : 'json',
                    data : JSON.stringify(data),
                    success : function(data){
                        var trHTML = '';
                        $.each(data, function (i, item) {
                            trHTML += '<tr><td>' + item.name + '</td><td>' + item.quote + '</td><td>' + createStarTDAsString(item.quality) + '</td></tr>';
                        });
                        $("#result").html("Resultados");
                        $('#records_table').append(trHTML);
                        $('#records_table').show();
                        $('#quoteModal').show();
                    },
                    error : function(){
                        $(this).html("Error!");
                    }
                });
            });
        });
</script>


</body>
</html>
