<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en" ng-app="app"><!--<![endif]-->
<!-- //TODO  http://stackoverflow.com/questions/17151165/grails-sitemesh-layout-set-attribute-ng-app-in-html-child-element -->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/> - <g:meta name="app.title"/></title>
		<base href="${resource()}/" /><!-- angular gets urls -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="plugins/bootstrap/3.3.1/bootstrap.css"/>
  		<asset:stylesheet src="plugins/bootstrap/3.3.1/bootstrap-theme.css"/>
  		<asset:stylesheet src="plugins/ng-table/ng-table.css"/>
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<asset:javascript src="plugins/bootstrap/3.3.1/bootstrap.js"/>
		<asset:javascript src="plugins/angular/1.3.8/angular.js"/>
		<asset:javascript src="plugins/ng-table/ng-table.js"/>
		<asset:javascript src="angular/app.js"/>
		<asset:javascript src="angular/controllers.js"/>
		<g:layoutHead/>
		
	</head>
	<body>
		<header class="navbar">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Excel-Parser</a>
			</div>
		</header>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</body>
</html>
