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
    	<tr>
      		<th class="mdl-data-table__cell--non-numeric">Name</th>
    	</tr>
  	</thead>
	<tbody>
		<tr ng-repeat="myapp in myappctrl.getMyApps()">
			<td class="mdl-data-table__cell--non-numeric">{{myapp.title}}</td>
			<td class="mdl-data-table__cell--non-numeric">View Details</td>
		</tr>
	</tbody>
</table>