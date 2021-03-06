<%--Aqui fariem crides al /query/overView i les mostrem amb angular--%>

<!DOCTYPE html>
<html>
<head>
<title>Charts</title>
</head>
<body>

	<div id="content" ng-controller="queryOverViewCtrl" ng-init="initChartsDef()">
		    <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
		        <li class="active"><a href="#anual" data-toggle="tab" ng-click='updateCharts()'>Anual</a></li>
		        <li><a href="#monthly" data-toggle="tab" ng-click='updateCharts()'>Mensual</a></li>
		        <li><a href="#weekly" data-toggle="tab" ng-click='updateCharts()'>Weekly</a></li>	        
		    </ul>

			<div class="tab-content">

					<div id="anual" class="tab-pane fade in active">
						<div class="panel panel-default" >
							<div class="panel-heading">
				              Filters
				            </div> 
				            <br />
							<div class="col-lg-4" >	
								Select year <select ng-options="year as year for year in years" ng-model="year" ng-change='updateCharts()'></select><br /><br />				
								
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
				
						<div class="col-lg-12" >
							<div class="panel panel-default" >
					        	<div class="panel-heading">
					              Out payments
					            </div>                
					            <!-- /.panel-heading -->
					            <div class="panel-body">                        
					            	<div id="pie-chart-barchar-Out"></div>
					            </div>
					           
					            <!-- /.panel-body -->
					         </div>
						</div>
						<div class="col-lg-12" >
							<div class="panel panel-default" >
					        	<div class="panel-heading">
					               In payments
					            </div>                
					            <!-- /.panel-heading -->
					            <div class="panel-body">                        
					            	<div id="pie-chart-barchar-In"></div>
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
					<div id="monthly" class="tab-pane fade">
						<div class="panel panel-default" >
							<div class="panel-heading">
				              Filters
				            </div> 
				            <br />
							<div class="col-lg-4" >													
								Select Month <select ng-options='month as month for month in months' ng-model="month" ng-change='updateCharts()'></select>
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
				
						<div class="col-lg-12" >
							<div class="panel panel-default" >
					        	<div class="panel-heading">
					              Out payments
					            </div>                
					            <!-- /.panel-heading -->
					            <div class="panel-body">                        
					            	<div id="pie-chart-barchar-Out"></div>
					            </div>
					           
					            <!-- /.panel-body -->
					         </div>
						</div>
						<div class="col-lg-12" >
							<div class="panel panel-default" >
					        	<div class="panel-heading">
					               In payments
					            </div>                
					            <!-- /.panel-heading -->
					            <div class="panel-body">                        
					            	<div id="pie-chart-barchar-In"></div>
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
					<div id="weekly" class="tab-pane fade">
						Hola que assé #weekly?
					</div>
					
					
				</div>
				
				
				
		
	</div>
	
	
</body>
</html>
