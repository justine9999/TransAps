<table id="my-apps-container" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
	<thead>
    	<tr>
      		<th class="mdl-data-table__cell--non-numeric">Name</th>
    	</tr>
  	</thead>
	<tbody>
		<div ng-repeat="myapp in myappctrl.getMyApps()">
			<tr>
				<td class="mdl-data-table__cell--non-numeric">{{myapp.title}}</td>
				<td class="mdl-data-table__cell--non-numeric">View Details</td>
			</tr>
		</div>
	</tbody>
</table>