<div id="my-apps-toolbar" class="mdl-shadow--2dp">
	<button id="create-app-button" class="mdl-button mdl-js-button mdl-button--icon mdl-js-ripple-effect" ng-click="myappctrl.showCreateAppModal($event)">
	  	<i class="material-icons">add_box</i>
	</button>
	<div class="mdl-tooltip" data-mdl-for="create-app-button">Create a new app</div>
	<button id="delete-app-button" class="mdl-button mdl-js-button mdl-button--icon mdl-js-ripple-effect">
	  	<i id="delete-app-icon" class="material-icons">delete_forever</i>
	</button>
	<div class="mdl-tooltip" data-mdl-for="delete-app-button">Delete app</div>
	<button id="edit-app-button" class="mdl-button mdl-js-button mdl-button--icon mdl-js-ripple-effect">
	  	<i id="edit-app-icon" class="material-icons">edit</i>
	</button>
	<div class="mdl-tooltip" data-mdl-for="edit-app-button">Edit app</div>
</div>
<table id="my-apps-container" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
	<thead>
    	<tr id="my-app-header-row">
    		<th class="mdl-data-table__cell--non-numeric"></th>
      		<th class="mdl-data-table__cell--non-numeric">Name</th>
      		<th class="mdl-data-table__cell--non-numeric"></th>
    	</tr>
  	</thead>
	<tbody>
		<tr ng-repeat="myapp in myappctrl.getMyApps()">
			<td class="mdl-data-table__cell--non-numeric my-app-icon-cell">
				<div id="my-app-icon-container">
			  		<img id="app-card-icon" class="mdl-shadow--2dp" ng-src="{{myapp.profile_picture || './image/default_profile_pic.png'}}" />
			  	</div>
			</td>
			<td class="mdl-data-table__cell--non-numeric">{{myapp.title}}</td>
			<td class="mdl-data-table__cell--non-numeric">
				<span class="fright vcenter" ng-show="myappctrl.myappsstatus[myapp.creation_time] === 0">
					<md-tooltip md-direction="right">View App details</md-tooltip>
					<i id="app_view_details_icon" class="material-icons">line_style</i>
				</span>
				<span class="fright vcenter" ng-show="myappctrl.myappsstatus[myapp.creation_time] === 1">
					<md-tooltip md-direction="right">Creating App...</md-tooltip>
					<md-progress-circular md-mode="indeterminate" md-diameter="30"></md-progress-circular>	
				</span>
				<span class="fright vcenter" ng-show="myappctrl.myappsstatus[myapp.creation_time] === 2">
					<md-tooltip md-direction="right">Deleting App...</md-tooltip>
					<md-progress-circular md-mode="indeterminate" md-diameter="30"></md-progress-circular>
				</span>
			</td>
		</tr>
	</tbody>
</table>