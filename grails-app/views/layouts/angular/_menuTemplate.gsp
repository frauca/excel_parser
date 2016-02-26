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
				</div> <!-- /input-group -->
			</li>
			<li><g:link controller="page" action="index" target="_self">
					<i class="fa fa-home fa-fw"></i> Home</g:link></li>
			<li><a href="#"><i class="fa fa-gears fa-fw"></i> Settings<span
					class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><g:link controller="page" action="configuration"
							target="_self">
							</i> Settings</g:link></li>
					<li><g:link controller="page" action="queriesManager"
							target="_self">
							</i> Queries</g:link></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
					Charts<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><g:link controller="page" action="basicAnalyzer"
							target="_self">
							</i> Basic analisis</g:link></li>
					<li><g:link controller="page" action="queryOverView"
							target="_self">
							</i> Charts Analyze (jmorris)</g:link></li>
				</ul> <!-- /.nav-second-level --></li>
		</ul>
	</div>
</div>