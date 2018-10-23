<table id="filter-container" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
	<tbody>
		<tr id="filter-current">
			<td id="filter-current-tags" class="mdl-data-table__cell--non-numeric">some tag</td>
			<td id="filter-current-save">
				<button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect">
					<i class="material-icons">save</i>
				</button>
			</td>
		</tr>
		<tr id="filter-keywords">
			<td id="filter-keywords-input" class="mdl-data-table__cell--non-numeric">
				<div class="mdl-textfield mdl-js-textfield">
					<input class="mdl-textfield__input" type="text" id="input-keywords">
					<label class="mdl-textfield__label" for="input-keywords">Type in keyword...</label>
				</div>
			</td>
			<td id="filter-keywords-add">
				<button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect">
					<i class="material-icons">add</i>
				</button>
			</td>
		</tr>
		<tr id="filter-options">
			<td id="filter-set-options" class="mdl-data-table__cell--non-numeric">
				<button  id="filter-language" class="filter-dropdown mdl-button mdl-js-button">Language ▾</button >
				<button  id="filter-app-type" class="filter-dropdown mdl-button mdl-js-button">App Type ▾</button >
				<button  id="filter-purpose" class="filter-dropdown mdl-button mdl-js-button">Purpose ▾</button >
				<button  id="filter-division" class="filter-dropdown mdl-button mdl-js-button">Division ▾</button >
				<button  id="filter-language" class="filter-dropdown mdl-button mdl-js-button">Source File Type ▾</button >
			</td>
			<td id="filter-saved">
				<button  id="filter-saved-button" class="filter-dropdown mdl-button mdl-js-button">Saved Filters ▾</button >
			</td>
		</tr>
	</tbody>
</table>
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