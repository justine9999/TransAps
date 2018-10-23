
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
        		controller: 'CreateAppController',
        	    templateUrl: 'partials/create_app',
        	    parent: angular.element(document.body),
        	    targetEvent: event,
        	    clickOutsideToClose: false,
        	    fullscreen: $scope.customFullscreen
        	})
        	.then(function() {
        		console.log("submit");
        	}, function() {
        		console.log("cancel");
        	});
        }
    }
]);