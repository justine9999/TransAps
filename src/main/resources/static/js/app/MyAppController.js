
'use strict';
 
app.controller('MyAppController', ['AppService', '$scope', '$mdDialog', '$element', '$mdToast', function( AppService, $scope, $mdDialog, $element, $mdToast) {
 
        var self = this;
        self.getMyApps = getMyApps;        
        self.showCreateAppModal = showCreateAppModal;
        self.insertAppRow = insertAppRow;
        self.getAppStatus = getAppStatus;
        self.showActionToast = showActionToast;
        self.myapps = [];
        //0-normal, 1-creating, 2-deleting
        self.myappsstatus = {};
 
        function getMyApps(){
        	self.myapps = AppService.getMyApps();
        	
        	//initialize myapps status
        	if(Object.keys(self.myappsstatus).length === 0){
        		for (var i = 0; i < self.myapps.length; i++) {
            		self.myappsstatus[self.myapps[i].creation_time] = 0;
            	}
        	}
        	
        	return self.myapps;
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
        	.then(function(result) {
        		console.log("submit a new app form");
        		var app = result.app;
        		var croppedImage = result.croppedImage;
        		self.insertAppRow(app, 1);
        		AppService.createApp(app, croppedImage).then(
        	          function(create_app_result) {
        	        	 var app_creation_time = create_app_result.creation_time;
        	        	 var iconurl = create_app_result.iconurl;
        	        	 self.myappsstatus[app_creation_time] = 0;
        	        	 app.profile_picture = iconurl;
        	        	 self.showActionToast('New App created: [ '+app.title+' ]', 'Undo', 'success');
                   }, function(app_to_delete) {
                	   	 deleteAppRow(app_to_delete);
                	   	 self.showActionToast('Unable to create App: [ '+app.title+' ]', 'Detail', 'error');
                });
        	}, function() {
        		console.log("cancel");
        	});
        }
        
        function insertAppRow(app, new_status) {
        	self.myappsstatus[app.creation_time] = new_status;
        	self.myapps.unshift(app);
        }
        
        function getAppStatus(app_creation_time) {
        	return self.myappsstatus[app_creation_time];
        }
        
        function deleteAppRow(app_to_delete) {
        	delete self.myappsstatus[app_to_delete.creation_time];
        	var index = self.myapps.indexOf(app_to_delete);
        	self.myapps.splice(index, 1);
        }
        
        function showActionToast(textContent, actionText, type) {
            var toast = $mdToast.simple()
              .textContent(textContent)
              .action(actionText)
              .highlightAction(true)
              .parent(document.querySelectorAll('#toaster-container'))
              .position('bottom left')
              .hideDelay(300000)
              .theme(type+'-toast');

            $mdToast.show(toast).then(function(response) {
              if ( response == 'ok' ) {
                Console.log('You clicked the \'UNDO\' action.');
              }
            });
        }
    }
]);