
'use strict';
 
app.controller('MyAppController', ['AppService', '$scope', '$mdDialog', function( AppService, $scope, $mdDialog) {
 
        var self = this;
        self.getMyApps = getMyApps;        
        self.showCreateAppModal = showCreateAppModal; 
 
        function getMyApps(){
        	var obj = AppService.getMyApps();
        	return obj;
        }
        
        function showCreateAppModal(event) {
        	$mdDialog.show({
        		controller: 'CreateAppFormController',
        	    templateUrl: 'partials/create_app',
        	    parent: angular.element(document.body),
        	    targetEvent: event,
        	    clickOutsideToClose: false,
        	    fullscreen: $scope.customFullscreen
        	})
        	.then(function(app) {
        		console.log("submit a new app form");
        		AppService.createApp(app);
        	}, function() {
        		console.log("cancel");
        	});
        }
    }
]);