<%--Aqui fariem crides al /query/overView i les mostrem amb angular--%>

<!DOCTYPE html>
<html>
<head>
<title>Charts</title>
</head>
<body>

	<div class="col-lg-12">
		<h4>Charts (j.morris) </h4>
	</div>
	<div class="col-lg-5" ng-controller="queryOverViewCtrl">
		<div class="panel panel-default">
        	<div class="panel-heading">
               Donut Chart Example
            </div>                
            <!-- /.panel-heading -->
            <div class="panel-body">                        
            	<div id="pie-chart-donut"></div>
            </div>
           
            <!-- /.panel-body -->
         </div>
	</div>
	<div class="col-lg-5" ng-controller="queryOverViewCtrl2">
		<div class="panel panel-default">
        	<div class="panel-heading">
               Donut Chart Example2
            </div>                
            <!-- /.panel-heading -->
           
            <div class="panel-body">	                        
             	 <div id="pie-chart-donut2"></div>
			</div>
            <!-- /.panel-body -->
         </div>
	</div>
</body>
</html>