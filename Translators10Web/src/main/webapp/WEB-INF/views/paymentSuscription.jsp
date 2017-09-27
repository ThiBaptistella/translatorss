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
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">

	<!-- Google Web Fonts
	================================================== -->
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
	<link rel="stylesheet" href="resources/css/skins/blue.css">
	<link rel="stylesheet" href="resources/css/custom.css">
	<!-- <link rel="stylesheet" type="text/css" href="js/plugins/DataTables/datatables.css"> -->
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
	<script src="resources/vendor/modernizr.js"></script>
	<link rel="stylesheet" href="resources/vendor/jquery-file-upload/css/jquery.fileupload.css">
	<link rel="shortcut icon" href="resources/images/favicon.png">
	<link rel="apple-touch-icon" href="resources/images/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="resources/images/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="resources/images/apple-touch-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="144x144" href="resources/images/apple-touch-icon-144x144.png">

</head>


<body>

<!-- Pricing -->
<section class="section light" id="pricing">
	<div class="container">
		<div class="row">
			<div class="title-centered">
				<h2>SERVICE PACKAGES</h2>
			</div>
			<div class="pricing-table">
				<div class="col-md-3">
					<div class="plan" data-animation="fadeInLeft" data-animation-delay="0">
						<header class="pricing-head">
							<h3>Free (15 days)</h3>
							<span class="price"><sup>$</sup> 00</span>
							<small>per month</small>										
					    </header>
							<div class="pricing-body">
								<ul>
									<li>Proin gravida nibhconseq</li>
									<li>Enean sollicitud</li>
									<li>Lorem quis bibendum</li>
								</ul>
							</div>
							<footer class="pricing-footer">
								<a href="<c:url value='/suscription/1/0'/>" class="btn btn-default" >Buy Now</a>								
							</footer>
						</div>
					</div>
					<div class="col-md-3">
						<div class="plan" data-animation="fadeInLeft" data-animation-delay="300">
							<header class="pricing-head">
								<h3>Basic (3 Months)</h3>
								<span class="price"><sup>$</sup> 39</span>
								<small>per month</small>										
							</header>
							<div class="pricing-body">
									<ul>
										<li>Proin gravida nibhconseq</li>
										<li>Enean sollicitud</li>
										<li>Lorem quis bibendum</li>
									</ul>
							</div>
							<footer class="pricing-footer">
 									<%-- <a href="<c:url value='/suscription/2/117'/>" class="btn btn-default" >Buy Now</a>	 --%>
									<a href="#" onclick="showPayModal(117,2);"><img src="https://paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></a>
							</footer>
						</div>
					</div>
					<div class="col-md-3">
							<div class="plan popular" data-animation="fadeInLeft" data-animation-delay="600">
								<header class="pricing-head">
									<h3>Popular (6 Months)</h3>
									<span class="price"><sup>$</sup> 29</span>
									<small>per month</small>									
								</header>
									<div class="pricing-body">
										<ul>
											<li>Proin gravida nibhconseq</li>
											<li>Enean sollicitud</li>
											<li>Lorem quis bibendum</li>
										</ul>
									</div>
									<footer class="pricing-footer">
										<%-- <a href="<c:url value='/suscription/3/174'/>" class="btn btn-default" >Buy Now</a> --%>
										<a href="#" onclick="showPayModal(174,3);"><img src="https://paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></a>
									</footer>
								</div>
					</div>
					<div class="col-md-3">
								<div class="plan" data-animation="fadeInLeft" data-animation-delay="900">
									<header class="pricing-head">
										<h3>Premium (1 year)</h3>
										<span class="price"><sup>$</sup> 19</span>
										<small>per month</small>										</header>
										<div class="pricing-body">
											<ul>
												<li>Proin gravida nibhconseq</li>
												<li>Enean sollicitud</li>
												<li>Lorem quis bibendum</li>
											</ul>
										</div>
										<footer class="pricing-footer">
											<%-- <a href="<c:url value='/suscription/4/118'/>" class="btn btn-default" >Buy Now</a> --%>
											<a href="#" onclick="showPayModal(228,4);"><img src="https://paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></a>
										</footer>
									</div>
					</div>
			</div>
			 <!--modal  -->
                                <div class="modal" id="payModal" role="dialog" aria-labelledby="myMdlLabel"
                                     aria-hidden="false">
                                    <div class="modal-backdrop  in" style="height: 902px;"></div>
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                                                </button>
                                                <h4 class="modal-title" id="myMdlLabel">Payment Dialog</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <form id="checkoutContainer" method="post" action='choseSuscription' enctype="application/x-www-form-urlencoded">
                                                            <input id="suscriptionvalue" name="suscriptionvalue" type="hidden">
                                                            <input id="type" name="type" type="hidden">
                                                            <input id="isDonation" type="checkbox" name="isDonation" aria-labelledby="isDonationLabel">
                                                            <label id="isDonationLabel" for="isDonation">Would you like to make a donation $${donationValue}?</label>
                                                            <br/>
                                                            <input id="isReadTerms" type="checkbox">
                                                            <label for="isReadTerms">I have read&nbsp;</label>
                                                            <a href="#" onclick="$('#termsDiv').toggle();">Terms and Conditions</a>
                                                            <label for="isReadTerms">&nbsp;and accept them.</label>
                                                            <div id="termsDiv" style="display: none;border: 2px solid;max-height: 500px;overflow-y: scroll">
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
                                <!--end modal  -->
		</div>
	</div>
</section>
<!-- jQuery -->
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/modal.js"></script>

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
        $('#suscriptionvalue').val(0);
        $('#type').val(0);
        $('#termsDiv').hide();
        $('.paypal-button-widget').hide();
    }

    function showPayModal(suscription, type) {
        resetPayModal();
        $('#suscriptionvalue').val(suscription);
        $('#type').val(type);
        $('#payModal').show();
    }

    window.paypalCheckoutReady = function () {
        paypal.checkout.setup('DE3ZKSXBXWBVN', {
            environment: 'sandbox',
            container: 'checkoutContainer'
        });
    };



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
</body>
</html>