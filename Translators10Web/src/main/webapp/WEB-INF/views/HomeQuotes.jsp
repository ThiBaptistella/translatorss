<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html lang="en">
<head>

<!-- Basic Page Needs
	================================================== -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Translatorss</title>

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

</head>
<body class="one-page" data-target=".header" data-spy="scroll"
	data-offset="200">

	<div class="site-wrapper">

		<!-- Header -->
		<header class="header header-fixed">
			<!-- Header Social -->
			<div class="header_top">
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
			</div>
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

		<!-- Main -->
		<div class="main" role="main">


			<!-- Start here -->
			<section class="section dark fullbody" id="about">
				<div class="container">
				<div class="row">
					<div class="title-centered">
						<h1>Quotes Available</h1>
					</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped" id="datatables">
                                    <thead>
                                    <tr>
                                        <th class="text-center" style="color:white;">Name</th>
                                        <th class="text-center" style="color:white;">Price(AU$)</th>
                                        <th class="text-center" style="color:white;">Would Recommend Rating</th>
                                        <th class="text-center" style="color:white;">Communication Rating</th>
                                        <th class="text-center" style="color:white;">Service Described Rating</th>
                                        <th class="text-center" style="color:white;">Choose</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${translatorQuoteList}" var="translatorQuoteRequest">
                                        <tr class="active">
                                            <td class="text-center">${translatorQuoteRequest.name}</td>
                                            <td class="text-center" data-quote-id="${translatorQuoteRequest.quotationId}">${translatorQuoteRequest.quote}</td>
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
                                                        <form id="checkoutContainer" method="post" action='chose' enctype="application/x-www-form-urlencoded">
                                                             <input id="quotationId" name="quotationId" type="hidden">
                                                             <input id="isDonation" type="checkbox" name="isDonation" aria-labelledby="isDonationLabel">
                                                            <label id="isDonationLabel" for="isDonation">Would you like to make a donation $${donationValue}?</label>
                                                            <br/>
                                                            <input id="isReadTerms" type="checkbox">
                                                            <label for="isReadTerms">I have read&nbsp;</label>
                                                            <a href="#" onclick="$('#termsDiv').toggle();">Terms and Conditions</a>
                                                            <label for="isReadTerms">&nbsp;and accept them.</label>
                                                            <div id="termsDiv"
                                                                 style="display: none;border: 2px solid;max-height: 500px;overflow-y: scroll">
                                                                <%@ include file="terms.jsp" %>
                                                            </div>
                                                            <div style="clear: both"></div>
                                                        </form>
                                                        <button id="btnCancel" class="close">Cancel</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                              </div>  
						</div>
					</div>
				</div>
			</section>
			<!-- Finish here -->

			<!-- Footer -->
			<footer class="footer" id="footer">
				<div class="footer-copyright">
					<div class="container">
						<div class="text-center">
							<ul class="social-links social-links__dark">
								<li><a
									href="https://www.facebook.com/Translatorss/?fref=ts"><i
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
	</div>

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

    window.paypalCheckoutReady = function () {
        paypal.checkout.setup('DE3ZKSXBXWBVN', {
            environment: 'sandbox',
            container: 'checkoutContainer'
        });
    };

    var eventSource = new EventSource("/translatorss/checkQuoteUpdated/${serviceRequestid}/" + guid());
    var onMessageListener = function (event) {

        var data = event.data;
        var dataChunks = data.split('|');

        var q_name = dataChunks[0].split(':')[1];
        var q_wr = dataChunks[1].split(':')[1];
        var q_sd = dataChunks[2].split(':')[1];
        var q_com = dataChunks[3].split(':')[1];

        var q_id = dataChunks[4].split(':')[1];
        var q_val = dataChunks[5].split(':')[1];
        var q_valid = dataChunks[6].split(':')[1];
        var q_is_insert = dataChunks[7].split(':')[1];
//        alert("Id:" + q_id + " name:" + q_name + "Value:" + q_val + " q_com:" + q_com + " q_sd:" + q_sd + " q_wr:" + q_wr + " q_is_insert:" + q_is_insert);

        $('#messageDiv').show();

        // New quote to be inserted into table
        if (q_is_insert === "true") {
            $('#datatables').find('.dataTables_empty').parent().remove();
            $('#datatables > tbody:last-child').append(
                '<tr class="active odd" role="row">'+
                '<td class="text-center sorting_1">'+q_name+'</td>'+
                '<td class="text-center" data-quote-id="'+q_id+'">'+q_val+'</td>'+
                createStarTDAsString(q_wr)+
                createStarTDAsString(q_com)+
                createStarTDAsString(q_sd)+
                '<td><a href="#" onclick="showPayModal('+q_id+');"><img src="https://paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></a>'+
                '</td>'+
                '</tr>');
            $('#messageLine').html("Translator "+q_name+" created a new quote: "+q_val);
        }
        // Current quote to be updated in table
        else {
            var quoteElem = $('[data-quote-id="' + q_id + '"]');
            if (q_valid === 'false') {
                quoteElem.parent().hide();
            } else {
                quoteElem.parent().show();
            }
            quoteElem.text(q_val);
            $('#messageLine').html("Translator: "+q_name+" modified the quote: "+q_val);
        }
    };
    eventSource.addEventListener("message", onMessageListener);

    function createStarTDAsString(val) {
        var td = '<td class="text-center">';
        for (i=1;i<=5;i++) {
            td+='<i class="fa fa-star'+((val<i)?'-o':'')+'"></i>'
        }
        td+='</td>';
        return td;
    }

    function guid() {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000)
                .toString(16)
                .substring(1);
        }

        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
    }

</script>

<script src="//www.paypalobjects.com/api/checkout.js" async></script>


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

