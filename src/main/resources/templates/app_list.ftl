<div id="app-cnt-container">
	<span class="mdl-chip mdl-chip--contact">
		<span id="app-cnt-number" class="mdl-chip__contact mdl-color--teal mdl-color-text--white">{{appctrl.appcnt}}</span>
		<span class="mdl-chip__text"><strong>Amazing Apps</strong></span>
	</span>
</div>
	<div id="apps-container">
	<span ng-repeat="app in appctrl.getAllApps()">
		<app-card></app-card>
	</span>
</div>