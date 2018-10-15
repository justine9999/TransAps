<!DOCTYPE html>
 
<html lang="en" ng-app="mainApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
        <base href="/TransAps/">
        
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
        <script src="js/lib/bootstrap.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/AppService.js"></script>
        <script src="js/app/AppController.js"></script>
    </head>
    <body>
    	<div id="main-outter-container">
    		<h4><b>About Us</b></h4>
    		<div id="main-nav" stickTop="fix-to-top">header</div>
    		<div id="main-body">
				<div ui-view></div>
			</div>
	        <div id="main-footer">footer</div> 
    	</div>    
    </body>
</html>