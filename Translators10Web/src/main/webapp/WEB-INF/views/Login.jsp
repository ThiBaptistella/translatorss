<!DOCTYPE html>
<!-- saved from url=(0036)http://www.translatorss.com.au/login -->

<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en-gb" dir="ltr" vocab="http://schema.org/"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="http://www.translatorss.com.au/templates/yootheme/vendor/yootheme/theme/platforms/joomla/assets/images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="http://www.translatorss.com.au/templates/yootheme/vendor/yootheme/theme/platforms/joomla/assets/images/apple-touch-icon.png">
    <!--<base href="http://www.translatorss.com.au/login">--><base href=".">

    <meta name="author" content="Super User">
    <meta name="generator" content="Joomla! - Open Source Content Management">
    <title>login</title>
    <link href="resources/login_files/theme.css" rel="stylesheet" type="text/css" id="theme-style-css">
    <link href="resources/login_files/custom.css" rel="stylesheet" type="text/css" id="theme-custom-css">
    <link href="resources/login_files/css" rel="stylesheet" type="text/css" id="google-fonts-css">
    <script type="application/json" class="joomla-script-options loaded">{"system.paths":{"root":"","base":""},"system.keepalive":{"interval":3600000,"uri":"\/component\/ajax\/?format=json"}}</script>
    <script src="resources/login_files/jquery.min.js" type="text/javascript"></script>
    <script src="resources/login_files/jquery-noconflict.js" type="text/javascript"></script>
    <script src="resources/login_files/jquery-migrate.min.js" type="text/javascript"></script>
    <script src="resources/login_files/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/login_files/core.js" type="text/javascript"></script>
    <!--[if lt IE 9]><script src="/media/system/js/polyfill.event.js?5c75d2fac1157e9a0ef38837602b1014" type="text/javascript"></script><![endif]-->
    <script src="resources/login_files/keepalive.js" type="text/javascript"></script>
    <script src="resources/login_files/analytics.js" type="text/javascript" defer="defer"></script>
    <script src="resources/login_files/uikit.min.js." type="text/javascript"></script>
    <script src="resources/login_files/uikit-icons.min.js" type="text/javascript"></script>
    <script src="resources/login_files/theme.js" type="text/javascript"></script>
</head>
<body class="">
<div class="tm-header-mobile uk-hidden@m">
    <nav class="uk-navbar-container uk-navbar" uk-navbar="">
        <div class="uk-navbar-left">
            <a class="uk-navbar-item uk-logo" href="http://www.translatorss.com.au/">
                <img src="resources/login_files/logo-translatorss.svg" alt="" class="uk-responsive-height">        </a>
        </div>
        <div class="uk-navbar-right">
            <a class="uk-navbar-toggle" href="http://www.translatorss.com.au/login#tm-mobile" uk-toggle="">
                <div uk-navbar-toggle-icon="" class="uk-navbar-toggle-icon uk-icon"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" icon="navbar-toggle-icon" ratio="1"><rect y="9" width="20" height="2"></rect><rect y="3" width="20" height="2"></rect><rect y="15" width="20" height="2"></rect></svg></div>
            </a>
        </div>
    </nav>
    <div id="tm-mobile" uk-offcanvas="" mode="reveal" overlay="" flip="" class="uk-offcanvas">
        <div class="uk-offcanvas-bar">
            <div class="uk-child-width-1-1 uk-grid" uk-grid="">
                <div>
                    <div class="uk-panel" id="module-0">
                        <ul class="uk-nav uk-nav-default">
                            <li class="uk-active"><a href="http://www.translatorss.com.au/login">login</a></li>
                            <li><a href="http://www.translatorss.com.au/join-us">join us</a></li></ul>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<div class="tm-header uk-visible@m tm-header-transparent" >
    <div class="uk-navbar-container uk-sticky uk-navbar-transparent uk-light"  media="768" cls-active="uk-active uk-navbar-sticky" animation="uk-animation-slide-top" top=".tm-header + [class*=&quot;uk-section&quot;]" cls-inactive="uk-navbar-transparent uk-light">
        <div class="uk-container ">
            <nav class="uk-navbar" uk-navbar="{&quot;align&quot;:&quot;center&quot;,&quot;dropbar&quot;:true,&quot;dropbar-anchor&quot;:&quot;!.uk-container&quot;,&quot;dropbar-mode&quot;:&quot;slide&quot;}">
                <div class="uk-navbar-left">
                    <a href="http://www.translatorss.com.au/" class="uk-logo uk-navbar-item">
                        <img src="resources/login_files/logo-translatorss.svg" alt="" class="uk-responsive-height"><img src="resources/login_files/logo-translatorss-bco.svg" alt="" class="uk-responsive-height uk-logo-inverse"></a>
                </div>
                <div class="uk-navbar-right">
                    <ul class="uk-navbar-nav">
                        <li class="uk-active"><a href="http://www.translatorss.com.au/login">login</a></li>
                        <li><a href="http://www.translatorss.com.au/join-us">join us</a></li>
                    </ul>
                    <div class="uk-navbar-item" id="module-0">
                        <div><ul class="uk-grid-small uk-flex-middle uk-flex-nowrap uk-grid" uk-grid="">
                            <li class="uk-first-column">
                                <a href="https://www.facebook.com/" class="uk-icon-link uk-icon" uk-icon="icon: facebook"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" icon="facebook" ratio="1"></svg></a>
                            </li>
                            <li>
                                <a href="https://www.instagram.com/" class="uk-icon-link uk-icon" uk-icon="icon: instagram"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" icon="instagram" ratio="1"></svg></a>
                            </li>
                            <li>
                                <a href="https://www.youtube.com/" class="uk-icon-link uk-icon" uk-icon="icon: youtube"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" icon="youtube" ratio="1"></svg></a>
                            </li>
                        </ul>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
        <div class="uk-navbar-dropbar uk-navbar-dropbar-slide"></div>
    </div>
    <div class="uk-sticky-placeholder" style="height: 80px; margin: 0px;" hidden="hidden">
    </div>
</div>

<div class="uk-section-default uk-light">
    <div style="background-image: url(&quot;http://www.translatorss.com.au/images/yootheme/overview-header.jpg&quot;); box-sizing: border-box; min-height: 100vh; height: 100vh;" class="uk-background-norepeat uk-background-cover uk-background-center-center uk-section uk-section-xsmall uk-flex uk-flex-middle" uk-height-viewport="offset-top: true">
        <div class="uk-width-1-1">
            <div class="uk-container">
                <div class="tm-header-placeholder uk-margin-remove-adjacent" style="height: 80px"></div>
                <div class="uk-grid-margin uk-grid" uk-grid="">
                    <div class="uk-width-expand@m uk-first-column">
                    </div>
                    <div class="uk-width-1-2@m">
                        <div class="uk-panel uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">
                            <h3>Login Form</h3>
                            <form:form action='${pageContext.request.contextPath}/j_spring_security_check' method="post" commandName="customerloginForm">
                                <div class="uk-margin">
                                    <form:input path="email" class="uk-input" size='18' type='text' id="email" name="email" value="${param.customer}" placeholder="Enter your Email" />
                                    <form:errors path="email" />
                                </div>
                                <div class="uk-margin">
                                    <form:input path="password" autocomplete='off' class='uk-input' size='18' type='text' id="password" name="password" placeholder="Password" />
                                    <span class="input-group-addon input-circle-right"><i class="fa fa-user"></i></span>
                                </div>
                                <div class="uk-margin">
                                    <label>
                                        <input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me">Remember Me
                                    </label>
                                </div>
                                <div class="uk-margin">
                                    <button type="submit" class="uk-button uk-button-primary">Sign In</button>
                                </div>

                            </form:form>
                        </div>
                    </div>
                    <div class="uk-width-expand@m">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="uk-section-muted uk-section uk-section-large">
    <div class="uk-container">
        <div class="uk-grid-large uk-grid-margin-large uk-grid" uk-grid="">
            <div class="uk-width-expand@m uk-width-1-2@s uk-first-column">
                <div class="uk-margin uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">
                    <a href="http://www.translatorss.com.au/index.php"><img src="resources/login_files/logo-translatorss.svg" alt="" width="70%"></a>
                </div>
                <div class="uk-margin uk-width-xlarge uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">
                    Lorem ipsum dolor sit amet, consectetuer adipiscing elit aenean.
                </div>
                <div class="uk-margin uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">
                    <div class="uk-child-width-auto uk-flex-inline uk-grid-medium uk-grid" uk-grid="">
                        <div class="uk-first-column">
                            <a uk-icon="icon: instagram;ratio: 0.8" href="https://www.instagram.com/" class="uk-link-muted uk-icon"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 20 20" icon="instagram" ratio="0.8"></svg></a>
                        </div>
                        <div>
                            <a uk-icon="icon: twitter;ratio: 0.8" href="https://twitter.com/" class="uk-link-muted uk-icon"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 20 20" icon="twitter" ratio="0.8"></svg></a>
                        </div>
                        <div>
                            <a uk-icon="icon: facebook;ratio: 0.8" href="https://www.facebook.com/" class="uk-link-muted uk-icon"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 20 20" icon="facebook" ratio="0.8"></svg></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="uk-width-expand@m uk-width-1-2@s">
                <h3 class="uk-h5 uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">Syney</h3>
                <div class="uk-margin uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">
                    Bennelong Point, <br>
                    Sydney NSW 2000, Austrália<br>
                    +61 2 9250 7111<br>
                    <span><a href="mailto:sydney@translatorss.com.au">sydney@translatorss.com.au</a></span>
                </div>
            </div>
            <div class="uk-width-expand@m uk-width-1-2@s">
                <h3 class="uk-h5 uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">Brisbane    </h3>
                <div class="uk-margin uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">
                    Bennelong Point, <br>
                    Sydney NSW 2000, Austrália<br>
                    +61 2 9250 7111<br>
                    <span><a href="mailto:brisbane@translatorss.com.au">brisbane@translatorss.com.au</a></span>
                </div>
            </div>
            <div class="uk-width-expand@m uk-width-1-2@s">
                <h3 class="uk-h5 uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">Melbourne    </h3>
                <div class="uk-margin uk-scrollspy-inview uk-animation-fade" uk-scrollspy-class="" style="">
                    Bennelong Point, <br>
                    Sydney NSW 2000, Austrália<br>
                    +61 2 9250 7111<br>
                    <span><a href="mailto:melbourne@translatorss.com.au">melbourne@translatorss.com.au</a></span></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>