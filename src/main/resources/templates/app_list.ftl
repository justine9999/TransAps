<table id="filter-container" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
	<tbody>
		<tr id="filter-current">
			<td id="filter-current-tags" class="mdl-data-table__cell--non-numeric">
				<span id="tps-tag" class="mdl-chip mdl-chip--deletable" ng-repeat="tag in appctrl.selectedTags">
				    <span id="tps-tag-text" class="mdl-chip__text">{{tag.text}}</span>
				    <button ng-click="appctrl.removeTag($index)" type="button" class="mdl-chip__action"><i class="material-icons">close</i></button>
				</span>
			</td>
			<td id="filter-current-save">
				<button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect">
					<i class="material-icons">save</i>
				</button>
			</td>
		</tr>
		<tr id="filter-keywords">
			<td id="filter-keywords-input" class="mdl-data-table__cell--non-numeric">
				<md-autocomplete md-search-text="appctrl.searchText" md-items="item in appctrl.querySearch(appctrl.searchText)" md-item-text="item.text" placeholder="Type in keyword..." add-tag-enter="appctrl.addTag(appctrl.searchText)">
					<span md-highlight-text="appctrl.searchText">{{item.text}}</span>
				</md-autocomplete>
			</td>
			<td id="filter-keywords-add">
				<button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect" ng-click="appctrl.addTag(appctrl.searchText)">
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
	<span id="app-cnt-number-wrapper" class="mdl-chip mdl-chip--contact">
		<span id="app-cnt-number" class="mdl-chip__contact mdl-color--cyan mdl-color-text--white">{{appctrl.appcnt}}</span>
		<span class="mdl-chip__text"><strong>Amazing Apps</strong></span>
	</span>
</div>
<div id="apps-container">
	<app-card ng-repeat="app in appctrl.getAllApps()"></app-card>
</div>