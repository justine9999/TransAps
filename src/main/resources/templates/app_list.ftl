<div id="apps-container">
	<div id="skeleton" ng-show="$root.applistpreloader">
		<md-progress-linear md-mode="query"></md-progress-linear>
		<div class="mdl-grid skeleton-row-3" ng-repeat="x in [].constructor(3) track by $index">
			<div class="mdl-cell mdl-cell--3-col skeleton-cell"></div>
			<div class="mdl-cell mdl-cell--3-col skeleton-cell"></div>
			<div class="mdl-cell mdl-cell--3-col skeleton-cell"></div>
			<div class="mdl-cell mdl-cell--3-col skeleton-cell"></div>
		</div>									
	</div>
	<app-card ng-repeat="app in appctrl.getAllApps()"></app-card>
</div>