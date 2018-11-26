<table id="filter-container" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
	<tbody>
		<tr id="filter-current">
			<td id="filter-set-options" class="mdl-data-table__cell--non-numeric">
				<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--11-col">
						<span id="tps-tag" class="mdl-chip mdl-chip--deletable" ng-repeat="tag in appctrl.selectedTags">
						    <span id="tps-tag-text" class="mdl-chip__text">{{tag.text}}</span>
						    <button ng-click="appctrl.removeTag($index)" type="button" class="mdl-chip__action"><i class="material-icons">close</i></button>
						</span>
					</div>
					<div class="mdl-cell mdl-cell--1-col">
						<button class="fright mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect">
							<i class="material-icons">save</i>
						</button>
					</div>
				</div>
			</td>
		</tr>
		<tr id="filter-keywords">
			<td id="filter-set-options" class="mdl-data-table__cell--non-numeric">
				<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--11-col">
						<md-autocomplete md-search-text="appctrl.searchText" md-items="item in appctrl.querySearch(appctrl.searchText)" md-item-text="item.text" placeholder="Type in keyword..." add-tag-enter="appctrl.addTag(appctrl.searchText)">
							<span md-highlight-text="appctrl.searchText">{{item.text}}</span>
						</md-autocomplete>
					</div>
					<div class="mdl-cell mdl-cell--1-col">
						<button class="fright mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect" ng-click="appctrl.addTag(appctrl.searchText)">
							<i class="material-icons">add</i>
						</button>
					</div>
				</div>
			</td>
		</tr>
		<tr id="filter-options">
			<td id="filter-set-options" class="mdl-data-table__cell--non-numeric">
				<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--10-col tps-border-right">
						<button  id="filter-language" class="filter-dropdown mdl-button mdl-js-button">Language ▾</button >
						<button  id="filter-app-type" class="filter-dropdown mdl-button mdl-js-button">App Type ▾</button >
						<button  id="filter-purpose" class="filter-dropdown mdl-button mdl-js-button">Purpose ▾</button >
						<button  id="filter-division" class="filter-dropdown mdl-button mdl-js-button">Division ▾</button >
						<button  id="filter-language" class="filter-dropdown mdl-button mdl-js-button">Source File Type ▾</button >
					</div>
					<div class="mdl-cell mdl-cell--2-col tps-v-center-child">
						<butto id="filter-saved-button" class="filter-dropdown mdl-button mdl-js-button">Saved Filters ▾</button >
					</div>
				</div>
			</td>
		</tr>
	</tbody>
</table>
<div id="app-cnt-filter-container">
	<span id="app-cnt-number-wrapper" class="mdl-chip mdl-chip--contact">
		<span id="app-cnt-number" class="mdl-chip__contact mdl-color--cyan mdl-color-text--white">{{appctrl.apps.length}}</span>
		<span class="mdl-chip__text"><strong>Amazing Apps</strong></span>
	</span>
	<span id="app-filter-wrapper" class="fright">
		<md-fab-speed-dial md-direction="left" md-open="appctrl.isFilterOpen" class="md-scale">
	        <md-fab-trigger ng-style="appctrl.isFilterOpen && appctrl.fostyle">
	          	<md-button id="filter-menu-button" class="md-raised">
	          		<i class="material-icons">sort</i>
	          		Filter
	          	</md-button>
	        </md-fab-trigger>
	        <md-fab-actions>
	          	<md-button sorttype="0" tags="{{appctrl.selectedTags}}" sort="appctrl.selectedFilter" filter-change-styler class="md-raised">
	          		<span class="mdl-list__item-secondary-action flight">
						<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="filter-option-1">
							<input type="checkbox" id="filter-option-1" class="mdl-checkbox__input" ng-checked="appctrl.selectedFilter===0" />
						</label>
					</span>
	          		Top Rated
	          	</md-button>
	          	<md-button sorttype="1" tags="{{appctrl.selectedTags}}" sort="appctrl.selectedFilter" filter-change-styler class="md-raised">
	          		<span class="mdl-list__item-secondary-action flight">
						<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="filter-option-2">
							<input type="checkbox" id="filter-option-2" class="mdl-checkbox__input" ng-checked="appctrl.selectedFilter===1" />
						</label>
					</span>
	          		Most Downloads
	          	</md-button>
	          	<md-button sorttype="2" tags="{{appctrl.selectedTags}}" sort="appctrl.selectedFilter" filter-change-styler class="md-raised">
	          		<span class="mdl-list__item-secondary-action flight">
						<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="filter-option-3">
							<input type="checkbox" id="filter-option-3" class="mdl-checkbox__input" ng-checked="appctrl.selectedFilter===2" />
						</label>
					</span>
	          		Most Recent
	          	</md-button>
	        </md-fab-actions>
		</md-fab-speed-dial>
	</span>
</div>