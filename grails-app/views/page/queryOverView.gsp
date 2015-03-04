<%--Aqui fariem crides al /query/overView i les mostrem amb angular--%>

<!DOCTYPE html>
<html>
<head>
<title>Charts</title>
</head>
<body>
<div ng-controller="queryOverViewCtrl" ng-init="initChartsDef()">
	<div class="col-lg-12">
		<div class="panel panel-default" >
			<div class="panel-heading">
              Filters
            </div> 
            <br />
			<div class="col-lg-4" >	
				Roger year <select ng-model="rogerYear" ng-options="year as year for year in rogerYears" ng-change='updateCharts()'></select>	
				Select year <select ng-options='year.name for year in years track by year.value' ng-model="year" ng-change='updateCharts()'></select><br /><br />
				Select Month <select ng-options='month.name for month in months track by month.value' ng-model="month" ng-change='updateCharts()'></select>
			</div>
			<div class="col-lg-1">
			From:
			</div>
			<div class="col-lg-2 input-group" ng-controller="DatepickerFromCtrl">
	              <input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dtFrom" is-open="opened" datepicker-options="dateOptionsFrom" ng-required="true" close-text="Close" />
	              <span class="input-group-btn">
	                <button type="button" class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
	              </span>      
	        </div>
	        <div class="col-lg-1">
			To:
			</div>
			<div class="col-lg-2 input-group" ng-controller="DatepickerToCtrl">
	              <input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dtTo" is-open="opened" datepicker-options="dateOptionsTo" ng-required="true" close-text="Close" />
	              <span class="input-group-btn">
	                <button type="button" class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
	              </span>
	      
	        </div>
	        <br />
        </div>
	</div>
	<div class="col-lg-12" >
		<div class="panel panel-default" >
        	<div class="panel-heading">
               Movements by Category
            </div>                
            <!-- /.panel-heading -->
            <div class="panel-body">                        
            	<div id="pie-chart-barchar"></div>
            </div>
           
            <!-- /.panel-body -->
         </div>
	</div>
	<div class="col-lg-6">
		<div class="panel panel-default">
        	<div class="panel-heading">
               Balance State               
            </div>                
            <!-- /.panel-heading -->
           
            <div class="panel-body">	                        
             	 <div id="pie-chart-balance"></div>
			</div>
            <!-- /.panel-body -->
         </div>
	</div>
</div>
</body>
</html>
