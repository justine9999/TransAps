<!DOCTYPE html>
 
<html lang="en" ng-app="mainApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
        <link rel="stylesheet" href="css/material.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <base href="/TransAps/">
    </head>
    <body>
    	<div id="main-outter-container">
    		<div class="top-image-placeholder"></div>
			<div id="main-nav" stick-top="fix-to-top"">
		      <header class="mdl-layout__header">
		        <div class="mdl-layout__header-row">
		          <span class="mdl-layout-title">Transperfect AppStore</span>
		          <div class="mdl-layout-spacer"></div>
		          <nav class="mdl-navigation mdl-layout--large-screen-only">
		            <a class="mdl-navigation__link" href="">Search</a>
		            <a class="mdl-navigation__link" href="">Account Sign in</a>
		          </nav>
		        </div>
		      </header>
		    </div>
    		<div id="main-body">
    			<div id="main-body-container">
    				<div class="mdl-grid">
					  	<div class="mdl-cell mdl-cell--3-col">
					  		<div id="main-body-left-container">
					  			<div id="find-app">
					  				<table class="mdl-data-table mdl-js-data-table">
									  <thead>
									    <tr>
									      <th class="mdl-data-table__cell--non-numeric">FIND APPS</th>
									      <th></th>
									    </tr>
									  </thead>
									  <tbody>
									    <tr>
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
					  			<div id="manage-app">
					  				<table class="mdl-data-table mdl-js-data-table">
									  <thead>
									    <tr>
									      <th class="mdl-data-table__cell--non-numeric">MANAGE MY APPS</th>
									      <th></th>
									    </tr>
									  </thead>
									  <tbody>
									    <tr>
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
		    					<div ui-view></div>
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
    	
    	<script src="js/lib/jquery-3.3.1.js"></script>
    	<script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/lib/material.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/AppService.js"></script>
        <script src="js/app/AppController.js"></script>   
    </body>
</html>