<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finish Quotation</title>
</head>
<body>
	<h1>You account setup is not finished yet, once you finish it you will be able to receive jobs</h1>
	
	<form:form action='finishQuotationSetUp' method="post" commandName="quotationConfForm">

		<spring:bind path="email">
			<div class="form-group">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="email" type="text" class="form-control " id="email" placeholder="email" />
					<form:errors path="email" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="password">
			<div class="form-group">
				<label class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<form:input path="password" type="text" class="form-control " id="password" placeholder="password" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="driverLicenseUrgent">
			<div class="form-group">
				<label class="col-sm-2 control-label">driverLicenseUrgent</label>
				<div class="col-sm-10">
					<form:input path="driverLicenseUrgent" type="text" class="form-control " id="driverLicenseUrgent" placeholder="driverLicenseUrgent" value="0"/>
					<form:errors path="driverLicenseUrgent" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="academicTranscriptUrgent">
			<div class="form-group">
				<label class="col-sm-2 control-label">academicTranscriptUrgent</label>
				<div class="col-sm-10">
					<form:input path="academicTranscriptUrgent" type="text" class="form-control " id="academicTranscriptUrgent" placeholder="academicTranscriptUrgent" value="0"/>
					<form:errors path="academicTranscriptUrgent" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="birthDeathCertificateUrgent">
			<div class="form-group">
				<label class="col-sm-2 control-label">birthDeathCertificateUrgent</label>
				<div class="col-sm-10">
					<form:input path="birthDeathCertificateUrgent" type="text" class="form-control " id="birthDeathCertificateUrgent" placeholder="birthDeathCertificateUrgent" value="0"/>
					<form:errors path="birthDeathCertificateUrgent" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="businessDocumentUrgent">
			<div class="form-group">
				<label class="col-sm-2 control-label">businessDocumentUrgent</label>
				<div class="col-sm-10">
					<form:input path="businessDocumentUrgent" type="text" class="form-control " id="businessDocumentUrgent" placeholder="businessDocumentUrgent" value="0"/>
					<form:errors path="businessDocumentUrgent" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="driverLicenseMedium">
			<div class="form-group">
				<label class="col-sm-2 control-label">driverLicenseMedium</label>
				<div class="col-sm-10">
					<form:input path="driverLicenseMedium" type="text" class="form-control " id="driverLicenseMedium" placeholder="driverLicenseMedium" value="0"/>
					<form:errors path="driverLicenseMedium" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="academicTranscriptMedium">
			<div class="form-group">
				<label class="col-sm-2 control-label">academicTranscriptMedium</label>
				<div class="col-sm-10">
					<form:input path="academicTranscriptMedium" type="text" class="form-control " id="academicTranscriptMedium" placeholder="academicTranscriptMedium" value="0"/>
					<form:errors path="academicTranscriptMedium" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="birthDeathCertificateMedium">
			<div class="form-group">
				<label class="col-sm-2 control-label">birthDeathCertificateMedium</label>
				<div class="col-sm-10">
					<form:input path="birthDeathCertificateMedium" type="text" class="form-control " id="birthDeathCertificateMedium" placeholder="birthDeathCertificateMedium" value="0" />
					<form:errors path="birthDeathCertificateMedium" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="businessDocumentMedium">
			<div class="form-group">
				<label class="col-sm-2 control-label">businessDocumentMedium</label>
				<div class="col-sm-10">
					<form:input path="businessDocumentMedium" type="text" class="form-control " id="businessDocumentMedium" placeholder="businessDocumentMedium" value="0"/>
					<form:errors path="businessDocumentMedium" class="control-label" />
				</div>
			</div>
		</spring:bind>
		

		<spring:bind path="driverLicenseRelaxed">
			<div class="form-group">
				<label class="col-sm-2 control-label">driverLicenseRelaxed</label>
				<div class="col-sm-10">
					<form:input path="driverLicenseRelaxed" type="text" class="form-control " id="driverLicenseRelaxed" placeholder="driverLicenseRelaxed" value="0"/>
					<form:errors path="driverLicenseRelaxed" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="birthDeathCertificateRelaxed">
			<div class="form-group">
				<label class="col-sm-2 control-label">birthDeathCertificateRelaxed</label>
				<div class="col-sm-10">
					<form:input path="birthDeathCertificateRelaxed" type="text" class="form-control " id="birthDeathCertificateRelaxed" placeholder="birthDeathCertificateRelaxed" value="0"/>
					<form:errors path="birthDeathCertificateRelaxed" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="academicTranscriptRelaxed">
			<div class="form-group">
				<label class="col-sm-2 control-label">academicTranscriptRelaxed</label>
				<div class="col-sm-10">
					<form:input path="academicTranscriptRelaxed" type="text" class="form-control " id="academicTranscriptRelaxed" placeholder="academicTranscriptRelaxed" value="0"/>
					<form:errors path="academicTranscriptRelaxed" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="businessDocumentRelaxed">
			<div class="form-group">
				<label class="col-sm-2 control-label">businessDocumentRelaxed</label>
				<div class="col-sm-10">
					<form:input path="businessDocumentRelaxed" type="text" class="form-control " id="businessDocumentRelaxed" placeholder="businessDocumentRelaxed" value="0"/>
					<form:errors path="businessDocumentRelaxed" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button name="translatorsave" value="finishQuotation" class="btn-lg btn-primary pull-right">Finish Quotation</button>
			</div>
		</div>
        </form:form>
</body>
</html>