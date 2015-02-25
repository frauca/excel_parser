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
		<asset:javascript src="plugins/metisMenu/dist/metisMenu.min.js"/>
		<!-- Custom Theme JavaScript -->
   		<asset:javascript src="plugins/dist/js/sb-admin-2.js" />
		<asset:javascript src="plugins/angular/1.3.8/angular.js"/>
		<asset:javascript src="plugins/angular/1.3.8/angular-resource.js"/>
		<asset:javascript src="plugins/angular-ui/0.12/ui-bootstrap-tpls-0.12.0.js"/>
		<asset:javascript src="plugins/ng-table/ng-table.js"/>		
		<asset:javascript src="angular/app.js"/>
		<asset:javascript src="angular/controllers.js"/>
		<asset:javascript src="angular/services.js"/>
		<!-- MetisMenu CSS -->
	    <asset:stylesheet src="plugins/metisMenu/dist/metisMenu.min.css"/>	    
	    <!-- Custom CSS -->
	    <asset:stylesheet src="plugins/dist/css/sb-admin-2.css"/>	
	    <!-- Custom Fonts -->
	    <asset:stylesheet src="plugins/font-awesome/css/font-awesome.min.css"/>
		<!-- Morris Charts JavaScript -->
	    <asset:javascript src="plugins/raphael/raphael-min.js"/>
	    <asset:javascript src="plugins/morrisjs/morris.min.js" />
	    <asset:javascript src="plugins/angular-morris-chart/src/angular-morris-chart.min.js" />
	    
	    <!-- Morris Charts CSS -->
    	<asset:stylesheet src="plugins/morrisjs/morris.css" />
	    
		
		
		<g:layoutHead/>
		
	</head>
	<body>
	<div id="wrapper">
		<header class="navbar">

		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Excel-Parser</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">


                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>                        
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav in" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                         <li>
                        	<g:link controller="page" action="index" target="_self" ><i class="fa fa-home fa-fw"></i> Home</g:link>                           
                        </li>
                        <li>
                        	<g:link controller="page" action="configuration" target="_self" ><i class="fa fa-gears fa-fw"></i> Settings</g:link>                           
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Charts<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level collapse">
                                <li>
                                	<g:link controller="page" action="queryOverView" target="_self" ></i> Charts Analyze (jmorris)</g:link>                                    
                                </li>
                                <%--<li>
                                    <a href="morris.html">Morris.js Charts</a>
                                </li>
                            --%></ul>
                            <!-- /.nav-second-level -->
                        </li>                       
            <!-- /.navbar-static-side -->
        </nav>
		</header>
		<div id="page-wrapper" style="min-height: 572px;">			            
			<div class="row">
				<g:layoutBody/>
			</div>
		</div>
		
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</div>
	</body>
</html>
