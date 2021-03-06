<!DOCTYPE html>
 
<html lang="en" ng-app="mainApp">
    <head>
        <title>TransAps</title>
        <link rel="icon" href="image/tps_tab_icon.png">
        <link href="css/app.css" rel="stylesheet"/>
        <link rel="stylesheet" href="css/material.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.8/angular-material.min.css">
        <link rel="stylesheet" type="text/css" href="css/ng-img-crop.css">
        <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
        <base href="/TransAps/">
    </head>
    <body>
    	<div id="main-outter-container">
    		<div class="top-image-placeholder"></div>
			<div id="main-nav" stick-top="fix-to-top">
		      <header class="mdl-layout__header">
		        <div class="mdl-layout__header-row">
		          <span id="app-title" class="mdl-layout-title">
		          	<i id="app-title-icon" class="material-icons">widgets</i>
		          	<a id="app-title-text">
		          		<span id="app-title-company-name">Transperfect </span>
		          		<span id="app-title-tool-name">AppStore</span>
		          	</a>
		          </span>
		          <div class="mdl-layout-spacer"></div>
		          <nav class="mdl-navigation mdl-layout--large-screen-only">
		          	<div ui-view="view_user_operation"></div>
					<div id="global-search-icon-container" class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
			            <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
			              <i id="global-search-icon" class="material-icons">search</i>
			            </label>
			            <div class="mdl-textfield__expandable-holder">
			              <input class="mdl-textfield__input" type="text" id="search">
			              <label class="mdl-textfield__label" for="search">Enter keywords to search...</label>
			            </div>
			        </div>
		          </nav>
		        </div>
		      </header>
		    </div>
    		<div id="main-body">
    			<div id="main-body-container">
    				<div class="mdl-grid">
					  	<div class="mdl-cell mdl-cell--3-col">
					  		<div id="main-body-left-container">
					  			<div id="find-app" class="mdl-shadow--2dp">
					  				<table class="mdl-data-table mdl-js-data-table">
									  <thead>
									    <tr>
									      <th class="mdl-data-table__cell--non-numeric">
									      	<i class="material-icons module-header1-icon">add_box</i>
									      	<span class="module-header1-text">FIND APPS</span>
									      </th>
									      <th></th>
									    </tr>
									  </thead>
									  <tbody>
									    <tr viewchange state-change-styler ui-sref="home">
									      <td class="mdl-data-table__cell--non-numeric">All Apps</td>
									      <td>
									      	<span class="mdl-list__item-secondary-action fright">
										      <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="list-checkbox-1">
										        <input type="checkbox" id="list-checkbox-1" class="mdl-checkbox__input" />
										      </label>
										    </span>
									      </td>
									    </tr>
									    <tr>
									      <td class="mdl-data-table__cell--non-numeric">Most Recent Apps</td>
									      <td>
									      	<span class="mdl-list__item-secondary-action fright">
										      <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="list-checkbox-2">
										        <input type="checkbox" id="list-checkbox-2" class="mdl-checkbox__input" />
										      </label>
										    </span>
									      </td>
									    </tr>
									    <tr>
									      <td class="mdl-data-table__cell--non-numeric">Top Rated Apps</td>
									      <td>
									      	<span class="mdl-list__item-secondary-action fright">
										      <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="list-checkbox-3">
										        <input type="checkbox" id="list-checkbox-3" class="mdl-checkbox__input" />
										      </label>
										    </span>
									      </td>
									    </tr>
									  </tbody>
									</table>
					  			</div>
					  			<div id="manage-app" class="mdl-shadow--2dp">
					  				<table class="mdl-data-table mdl-js-data-table">
									  <thead>
									    <tr>
									      <th class="mdl-data-table__cell--non-numeric">
									      	<i class="material-icons module-header1-icon">add_box</i>
									      	<span class="module-header1-text">MANAGE APPS</span>
									      </th>
									      <th></th>
									    </tr>
									  </thead>
									  <tbody>
									    <tr viewchange state-change-styler ui-sref="my-apps">
									      <td class="mdl-data-table__cell--non-numeric">My Apps</td>
									      <td>
									      	<span class="mdl-list__item-secondary-action fright">
										      <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="list-checkbox-4">
										        <input type="checkbox" id="list-checkbox-4" class="mdl-checkbox__input" />
										      </label>
										    </span>
									      </td>
									    </tr>
									    <tr>
									      <td class="mdl-data-table__cell--non-numeric">Starred Apps</td>
									      <td>
									      	<span class="mdl-list__item-secondary-action fright">
										      <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="list-checkbox-5">
										        <input type="checkbox" id="list-checkbox-5" class="mdl-checkbox__input" />
										      </label>
										    </span>
									      </td>
									    </tr>
									    <tr>
									      <td class="mdl-data-table__cell--non-numeric">Favorite Apps</td>
									      <td>
									      	<span class="mdl-list__item-secondary-action fright">
										      <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="list-checkbox-6">
										        <input type="checkbox" id="list-checkbox-6" class="mdl-checkbox__input" />
										      </label>
										    </span>
									      </td>
									    </tr>
									  </tbody>
									</table>
					  			</div>
					  		</div>
					  	</div>
					  	<div class="mdl-cell mdl-cell--9-col">
					  		<div id="main-body-right-container">
					  			<div id="skeleton" ng-show="$root.preloader">
					  				<md-progress-linear md-mode="query"></md-progress-linear>
									<div class="mdl-grid skeleton-row-1">
										<div class="mdl-cell mdl-cell--9-col skeleton-cell"></div>
										<div class="mdl-cell mdl-cell--3-col skeleton-cell"></div>
									</div>
									<div class="mdl-grid skeleton-row-1">
										<div class="mdl-cell mdl-cell--10-col skeleton-cell"></div>
										<div class="mdl-cell mdl-cell--2-col skeleton-cell"></div>
									</div>
									<div class="mdl-grid skeleton-row-2" ng-repeat="x in [].constructor(10) track by $index">
										<div class="mdl-cell mdl-cell--12-col skeleton-cell"></div>
									</div>									
								</div>
								<div ui-view="view_app_filter"></div>
					  			<div ui-view="view_app_list"></div>
		    				</div>
					  	</div>
					</div>
    			</div>
			</div>
	        <div id="main-footer">
				<footer class="mdl-mini-footer">
				  <div class="mdl-mini-footer__left-section">
				    <div class="mdl-logo">Copy right</div>
				    <ul class="mdl-mini-footer__link-list">
				      <li><a href="#">Help</a></li>
				      <li><a href="#">Privacy & Terms</a></li>
				    </ul>
				  </div>
				</footer>
		      </footer>
			</div> 
    	</div>
    	<div id="toaster-container"><div>
    	
    	<script src="js/lib/jquery-3.3.1.js"></script>
    	<script src="js/lib/angular.min.js" ></script>
    	<script src="js/lib/angular-animation.min.js" ></script>
    	<script src="js/lib/angular-aria.min.js" ></script>
    	<script src="js/lib/angular-messages.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/angular-material.min.js" ></script>
        <script src="js/lib/ng-img-crop.js"></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/lib/material.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/AppService.js"></script>
        <script src="js/app/AppController.js"></script>
        <script src="js/app/MyAppController.js"></script>  
        <script src="js/app/CreateAppFormController.js"></script>
        <script src="js/lib/angular-sanitize.js"></script>
        <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
        <script src="js/lib/image-resize.min.js"></script>
        <script src="js/lib/video-resize.min.js"></script>
    </body>
</html>